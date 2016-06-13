package com.zcwfeng.fastdev.utils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Time: 2016/5/22.
 */
public class BytesUtil {
    /**
     * 排序高到低
     *
     * @param bytes
     * @return
     */
    public static byte[] sortHL(byte[] bytes) {
        return orderBytes(bytes, false);
    }

    /**
     * 排序低到高
     *
     * @param bytes
     * @return
     */
    public static byte[] sortLH(byte[] bytes) {
        return orderBytes(bytes, true);
    }

    /**
     * byte数组排序，
     *
     * @param bytes
     * @param isAsc true排序低到高,false排序高到低
     * @return
     */
    private static byte[] orderBytes(byte[] bytes, boolean isAsc) {

        Integer[] unsignedBytes = new Integer[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            unsignedBytes[i] = bytes[i] & 0xff;
        }

        if (isAsc) {//升序
            Arrays.sort(unsignedBytes);
        } else {//降序
            Arrays.sort(unsignedBytes, new IntegerComparator());
        }

        for (int i = 0; i < unsignedBytes.length; i++) {
            int tmp = unsignedBytes[i];
            bytes[i] = (byte) tmp;
        }

        return bytes;
    }

    /**
     * 网络序，从低到高组织
     *
     * @param data
     *            long
     * @return byte[]
     * */
    public static byte[] toLH(long data) {
        byte[] bytes = new byte[8];
        bytes[7] = (byte) (data & 0xff);
        bytes[6] = (byte) ((data >> 8) & 0xff);
        bytes[5] = (byte) ((data >> 16) & 0xff);
        bytes[4] = (byte) ((data >> 24) & 0xff);
        bytes[3] = (byte) ((data >> 32) & 0xff);
        bytes[2] = (byte) ((data >> 40) & 0xff);
        bytes[1] = (byte) ((data >> 48) & 0xff);
        bytes[0] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    /**
     * 网络序，从高到低组织
     *
     * @param data
     * @return
     */
    public static byte[] toHL(long data) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data >> 8) & 0xff);
        bytes[2] = (byte) ((data >> 16) & 0xff);
        bytes[3] = (byte) ((data >> 24) & 0xff);
        bytes[4] = (byte) ((data >> 32) & 0xff);
        bytes[5] = (byte) ((data >> 40) & 0xff);
        bytes[6] = (byte) ((data >> 48) & 0xff);
        bytes[7] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    /**
     * 网络序，从低到高组织
     *
     * @param n
     * @return
     */
    public static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * 网络序，从高到低组织
     *
     * @param n
     * @return
     */
    public static byte[] toHL(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * 基于arraycopy合并两个byte[] 数组
     *
     * @param bytes1
     * @param bytes2
     * @return byte[] bytes3
     */
    public static byte[] combineBytes(byte[] bytes1, byte[] bytes2) {
        byte[] bytes3 = new byte[bytes1.length + bytes2.length];
        System.arraycopy(bytes1, 0, bytes3, 0, bytes1.length);
        System.arraycopy(bytes2, 0, bytes3, bytes1.length, bytes2.length);
        return bytes3;
    }

}

/**
 * Integer比较器
 */
class IntegerComparator implements Comparator<Integer> {

    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }

}

