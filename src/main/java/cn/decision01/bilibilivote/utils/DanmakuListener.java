package cn.decision01.bilibilivote.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class DanmakuListener {

    private static String wssUrl = "wss://broadcastlv.chat.bilibili.com:2245/sub";
    private static String requestUrl = "https://api.live.bilibili.com/room/v1/Room/room_init?id=";

    private static int getLiveRoom() {
        String livingInfo = HttpRequestUtil.get(requestUrl + "687");
        // todo: 获取OP设置的直播间号
        JSONObject livingJson = JSONObject.parseObject(livingInfo);
        JSONObject data = (JSONObject) livingJson.get("data");
        return (int)data.get("room_id");
    }

    private void postHeartPackage() {

    }

    public void listenLiving() {
        int roomId= getLiveRoom();
        try {
            URI uri = new URI(wssUrl);
            WebSocketClient wssClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {

                }

                @Override
                public void onMessage(String message) {
                    System.out.println(message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
            wssClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DanmakuListener.getLiveRoom();
    }

}
