package com.zcwfeng.fastdev.secure.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Time: 2015/10/9.
 */
public class MD5Util {

    // 用来将字节转换成 16 进制表示的字符
    private static final char HEX_DIGITS[] =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 网络序，从低到高组织
     * @param n
     * @return
     */
    public static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[3] = (byte)(n & 0xff);
        b[2] = (byte)(n >> 8 & 0xff);
        b[1] = (byte)(n >> 16 & 0xff);
        b[0] = (byte)(n >> 24 & 0xff);
        return  b;
    }

    public static void main(String[] args){
        String str = getMD5("123");
        System.out.println(str);
        byte[] bytes = MD5Encrypt("123");

        byte[] b1 = toHL(123);
        byte[] b2 = toLH(123);
        System.out.println(b1);

        for (int i = 0; i < bytes.length; i+=4){
            byte[] tmp = new byte[4];
            System.arraycopy(bytes, 0, tmp, 0, 4);
            int tmp_i = bytesToIntLH(tmp);
            tmp = toHL(tmp_i);
            System.arraycopy(tmp, 0, bytes, 0, 4);
        }

        System.out.println(bytes);

    }

    public static int bytesToIntLH(byte[] bytes) {
        int number = bytes[3] & 0xFF;
        // "|="按位或赋值。
        number |= ((bytes[2] << 8) & 0xFF00);
        number |= ((bytes[1] << 16) & 0xFF0000);
        number |= ((bytes[0] << 24) & 0xFF000000);
        return number;
    }

    /**
     * 网络序，从高到低组织
     * @param n
     * @return
     */
    public static byte[] toHL(int n) {
        byte[] b = new byte[4];
        b[0] = (byte)(n & 0xff);
        b[1] = (byte)(n >> 8 & 0xff);
        b[2] = (byte)(n >> 16 & 0xff);
        b[3] = (byte)(n >> 24 & 0xff);
        return  b;
    }

    public static String md5(byte[] bytes) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            byte[] b = md5.digest();
            result = byte16to32(b);
        } catch (Exception e) {
            // do nothing
        }
        return result;
    }

    /**
     * 16位byte转换为32位字符串
     *
     * @param byte16
     * @return
     */
    public static String byte16to32(byte[] byte16) {
        // 用字节表示就是 16 个字节
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
            // 转换成 16 进制字符的转换
            byte byte0 = byte16[i]; // 取第 i 个字节
            str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = HEX_DIGITS[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        return new String(str); // 换后的结果转换为字符串
    }

    /**
     * byte数组md5byte数组
     */
    public static byte[] MD5Encrypt(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] b = md.digest();
            return b;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get byte[] result
     */
    public static byte[] MD5Encrypt(String plainStr) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainStr.getBytes());
            byte[] b = md.digest();
            return b;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get String result 32
     */
    public static String getMD5(String plainStr) {

        byte[] b = MD5Encrypt(plainStr);
        if (b == null || b.length <= 0) {
            return "fail";
        }
        int i;

        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }

    /**
     * Get String result 16
     */
    public static String get16MD5(String plainStr) {

        byte b[] = MD5Encrypt(plainStr);
        if (b == null || b.length <= 0) {
            return "fail";
        }
        int i;

        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString().substring(8, 24);
    }

    /**
     * 基于位移的int转化成byte[]
     *
     * @param number
     * @return byte[]
     */
    public static byte[] intToByte(int number) {
        byte[] abyte = new byte[4];
        // "&" 与（AND），对两个整型操作数中对应位执行布尔代数，两个位都为1时输出1，否则0。
        abyte[0] = (byte) (0xff & number);
        // ">>"右移位，若为正数则高位补0，若为负数则高位补1
        abyte[1] = (byte) ((0xff00 & number) >> 8);
        abyte[2] = (byte) ((0xff0000 & number) >> 16);
        abyte[3] = (byte) ((0xff000000 & number) >> 24);
        return abyte;
    }


}

