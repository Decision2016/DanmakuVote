package cn.decision01.danmakuvote.utils;


import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.event.VoteEvent;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class DanmakuListener {
    private static final String confUrl = "https://api.live.bilibili.com/room/v1/Danmu/getConf?room_id=";
    private static final String requestUrl = "https://api.live.bilibili.com/xlive/web-room/v1/index/getInfoByRoom?room_id=";

    private Socket socket;
    private String token;
    private int RoomId, count;
    private boolean running = true;
    private long startTime, endTime;
    private VoteEvent[] events;

    public DanmakuListener(int roomid, VoteEvent[] _events) {
        events = _events;
        RoomId = roomid;
        count = 0;
    }

    private boolean isRunning() {
        return running && socket != null && socket.isConnected() && !socket.isClosed() && (System.currentTimeMillis() <= endTime);
    }

    private static int getLiveRoom(int _roomid) {
        String livingInfo = HttpRequestUtil.get(requestUrl + _roomid);
        // todo: 获取OP设置的直播间号
        JSONObject livingJson = JSONObject.parseObject(livingInfo);
        JSONObject data = livingJson.getJSONObject("data");
        return data.getJSONObject("room_info").getInteger("room_id");
    }

    private void sendSocketData(int totalLength, int headerLength, int proVer, int operation, int param, byte[] data) throws IOException {
        if (socket.isClosed() || socket == null) return ;

        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(totalLength);
            bout.write(ByteUtils.intToBytes(totalLength));
            bout.write(ByteUtils.shortToBytes((short) headerLength));
            bout.write(ByteUtils.shortToBytes((short) proVer));
            bout.write(ByteUtils.intToBytes(operation));
            bout.write(ByteUtils.intToBytes(param));

            if (data != null && data.length != 0) {
                bout.write(data);
            }

            byte[] buffer = bout.toByteArray();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OutputStream os = socket.getOutputStream();
                        os.write(buffer);
                        os.flush();
                    } catch (Exception e) {
                        running = false;
                    }
                }
            });
            t.start();
        } catch (Exception e) {
            running = false;
        }
    }

    private void sendAuth() throws IOException {
        int roomId = getLiveRoom(RoomId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", 0);
        jsonObject.put("roomid", roomId);
        jsonObject.put("protover", 2);
        jsonObject.put("platform", "web");
        jsonObject.put("token", token);

        String stringInfo = jsonObject.toString();
        sendSocketData(stringInfo.length() + 16, 16, 2, 7, 1,
                stringInfo.getBytes(StandardCharsets.UTF_8));
    }

    private void countDanmaku(String message) {
        for (VoteEvent event : events) {
            if (message.equals(event.getEventName())) {
                event.addCount();
                count ++;
                Objective objective = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("DanmakuVote");
                Score score = objective.getScore(event.getEventName());
                score.setScore(event.getCount());
            }
        }
    }

    private String parseDanmaku(String _msg) {
        if (_msg.length() <= 16) return null;
        JSONObject danmakuJson = JSONObject.parseObject(_msg.substring(16));
        return danmakuJson.getJSONArray("info").getString(1);
    }

    private void sendHeartBeat() throws IOException {
        sendSocketData(16, 16, 2, 2, 1, null);
    }

    public void setTime(long _start, long delta) {
        startTime = _start;
        endTime = startTime + delta;
    }

    public int listenLiving() throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(HttpRequestUtil.get(confUrl + RoomId));

        token = String.valueOf(jsonObject.getJSONObject("data").get("token"));
        int port = jsonObject.getJSONObject("data").getInteger("port");
        String address = jsonObject.getJSONObject("data").getString("host");
        socket = new Socket(address, port);
        sendAuth();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isRunning()) {
                        sendHeartBeat();
                        Thread.sleep(30000);
                        if(System.currentTimeMillis() >= endTime) {
                            running = false;
                            socket.close();
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        byte[] stableBuffer = new byte[socket.getReceiveBufferSize()];
        byte[] msgBuffer = new byte[socket.getReceiveBufferSize()];
        int error_cnt = 0;
        InputStream in = socket.getInputStream();
        while (isRunning()) {
            int size;

            try {
                size = in.read(stableBuffer);
            } catch (SocketException e) {
                running = false;
                break;
            }

            if (size > 0) {
                int bufferPos = 0;
                int packageLength = ByteUtils.bytesToInt(stableBuffer, bufferPos);

                if (packageLength < 16 || packageLength > size) {
                    error_cnt += 1;
                    if (error_cnt >= 10) {
                        running = false;
                        break;
                    }
                    continue;
                }

                error_cnt = 0;
                bufferPos += 4;

                // magic
                bufferPos += 2;
                // version
                bufferPos += 2;

                // action
                int action = ByteUtils.bytesToInt(stableBuffer, bufferPos);
                bufferPos += 4;

                int bodyLength = packageLength - 16;
                if (bodyLength == 0) continue;;

                bufferPos += 4;

                switch (action) {
                    case 5: {
                        // todo： 在高并发的情况下无法处理所有信息
                        Inflater inflater = new Inflater();

                        inflater.setInput(stableBuffer, 16, packageLength - 16);
                        int _size = 0;
                        try {
                            _size = inflater.inflate(msgBuffer);
                            inflater.end();
                        } catch (DataFormatException e) {
                            e.printStackTrace();
                        }
                        String msg = new String(msgBuffer, 0, _size, "utf-8");
                        String danmaku = parseDanmaku(msg);
                        if(danmaku != null) countDanmaku(danmaku);
                        break;
                    }

                    case 8: {
                        break;
                    }
                }
            }
            else if (size == -1) {
                error_cnt += 1;
                if (error_cnt >= 10) {
                    running = false;
                    break;
                }
            }
        }
        return count;
    }
}
