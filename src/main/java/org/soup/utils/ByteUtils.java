package org.soup.utils;

import java.util.List;

public class ByteUtils {

    private ByteUtils() {
    }

    public static Boolean getBit(byte[] bitMessage, int bitIndex) {
        int byteIndex = bitIndex / 8;
        int bitPosition = 7 - (bitIndex % 8);
        return ((bitMessage[byteIndex] >> bitPosition) & 1) == 1;
    }

    public static byte[] concatenate(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static byte[] intToByteArray(int value) {
        byte[] result = new byte[4];
        result[0] = (byte) ((value >> 24) & 0xFF);
        result[1] = (byte) ((value >> 16) & 0xFF);
        result[2] = (byte) ((value >> 8) & 0xFF);
        result[3] = (byte) (value & 0xFF);
        return result;
    }

    public static int byteArrayToInt(byte[] b) {
        int value = 0;
        for (byte item : b) {
            value <<= 8;
            value |= item & 0xFF;
        }
        return value;
    }

    public static byte[] toByteArray(List<Boolean> booleanList) {
        int size = booleanList.size();
        int byteSize = (size + 7) / 8;
        byte[] byteArray = new byte[byteSize];

        for (int i = 0; i < size; i++)
            if (booleanList.get(i))
                byteArray[i / 8] |= (byte) (1 << (7 - (i % 8)));

        return byteArray;
    }
}
