package cn.decision01.danmakuvote.Utlis;

import java.nio.ByteBuffer;

public class ByteUtlis {
    public static byte[] shortToBytes(short x) {
        byte[] b = new byte[2];
        b[1] = (byte) (x & 0xff);
        b[0] = (byte) (x & 0xff00);
        return b;
    }

    public static byte[] intToBytes(int x) {
        byte[] b = new byte[4];
        b[3] = (byte) (x & 0xff);
        b[2] = (byte) (x & 0xff00);
        b[1] = (byte) (x & 0xff0000);
        b[0] = (byte) (x & 0xff000000);
        return b;
    }

    public static int bytesToInt(byte[] bs, int start) {
        int res = 0;
        ByteBuffer bb = ByteBuffer.wrap(bs, start, 4);
        res = bb.getInt();
        return res;
    }
}
