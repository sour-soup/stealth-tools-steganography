package org.soup.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SteganographyUtils {
    private static final Map<Character, Character> RUS_TO_ENG = Stream.of(
            new Character[][]{
                    {'А', 'A'}, {'С', 'C'}, {'Е', 'E'},
                    {'К', 'K'}, {'М', 'M'}, {'О', 'O'},
                    {'Т', 'T'}, {'Х', 'X'}, {'Р', 'P'},
                    {'а', 'a'}, {'с', 'c'}, {'е', 'e'},
                    {'о', 'o'}, {'х', 'x'}, {'р', 'p'}
            }
    ).collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));

    private static final Map<Character, Character> ENG_TO_RUS;

    static {
        ENG_TO_RUS = RUS_TO_ENG.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    private SteganographyUtils() {
    }

    public static String encode(String text, byte[] message) {
        StringBuilder result = new StringBuilder();
        int messageLength = message.length;
        message = ByteUtils.concatenate(ByteUtils.intToByteArray(messageLength), message);
        int bitIndex = 0;
        int bitLength = message.length * 8;
        for (char c : text.toCharArray()) {
            if (bitIndex < bitLength && (RUS_TO_ENG.containsKey(c) || ENG_TO_RUS.containsKey(c))) {
                char rus = RUS_TO_ENG.containsKey(c) ? c : ENG_TO_RUS.get(c);
                char eng = ENG_TO_RUS.containsKey(c) ? c : RUS_TO_ENG.get(c);
                result.append(ByteUtils.getBit(message, bitIndex) ? rus : eng);
                ++bitIndex;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static byte[] decode(String encodedText) {
        List<Boolean> result = new ArrayList<>();
        for (char c : encodedText.toCharArray()) {
            if (RUS_TO_ENG.containsKey(c))
                result.add(true);
            else if (ENG_TO_RUS.containsKey(c))
                result.add(false);
        }
        byte[] byteArray = ByteUtils.toByteArray(result);

        byte[] lengthBytes = Arrays.copyOfRange(byteArray, 0, 4);
        int length = ByteUtils.byteArrayToInt(lengthBytes);

        return Arrays.copyOfRange(byteArray, 4, 4 + length);
    }
}
