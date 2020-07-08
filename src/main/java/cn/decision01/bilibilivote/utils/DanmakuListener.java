package cn.decision01.bilibilivote.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class DanmakuListener {
    private static final String confUrl = "https://api.live.bilibili.com/room/v1/Danmu/getConf?room_id=";
    private static final String requestUrl = "https://api.live.bilibili.com/xlive/web-room/v1/index/getInfoByRoom?room_id=";

    private Socket socket;
    private String token;
    private int RoomId = 5850690;
    private boolean running = true;
    private int startTime, endTime;

    private byte[] shortToBytes(short x) {
        byte[] b = new byte[2];
        b[1] = (byte) (x & 0xff);
        b[0] = (byte) (x & 0xff00);
        return b;
    }

    private byte[] intToBytes(int x) {
        byte[] b = new byte[4];
        b[3] = (byte) (x & 0xff);
        b[2] = (byte) (x & 0xff00);
        b[1] = (byte) (x & 0xff0000);
        b[0] = (byte) (x & 0xff000000);
        return b;
    }

    private boolean isRunning() {
        return running && socket != null && socket.isConnected() && !socket.isClosed();
    }

    private int bytesToInt(byte[] bs, int start) {
        int res = 0;
        ByteBuffer bb = ByteBuffer.wrap(bs, start, 4);
        res = bb.getInt();
        return res;
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
            bout.write(intToBytes(totalLength));
            bout.write(shortToBytes((short) headerLength));
            bout.write(shortToBytes((short) proVer));
            bout.write(intToBytes(operation));
            bout.write(intToBytes(param));

            if (data != null && data.length != 0) {
                bout.write(data);
            }

            byte[] buffer = bout.toByteArray();
            System.out.println(Base64.encodeBase64String(buffer));

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

    private String parseDanmaku(String _msg) {
        // todo: parse Danmaku
        return null;
    }

    private void sendHeartBeat() throws IOException {
        sendSocketData(16, 16, 2, 2, 1, null);
    }

    private void setTime(int _start, int delta) {
        startTime = _start;
        endTime = startTime + delta;
    }
    public void listenLiving() throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(HttpRequestUtil.get(confUrl + RoomId));
        JSONArray jsonArray;

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
            int size = in.read(stableBuffer);
            System.out.println(size);
            if (size > 0) {
                int bufferPos = 0;
                int packageLength = bytesToInt(stableBuffer, bufferPos);

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
                int action = bytesToInt(stableBuffer, bufferPos);
                bufferPos += 4;

                int bodyLength = packageLength - 16;
                if (bodyLength == 0) continue;;

                bufferPos += 4;

                switch (action) {
                    case 5: {
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
                        System.out.println(msg);
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
    }
}
