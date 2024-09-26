package org.soup.args;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOUtils {
    private IOUtils() {
    }

    public static byte[] readFileOrStdInToByteArray(String filename) {
        try (InputStream is = (filename == null ? System.in : new FileInputStream(filename))) {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFileOrStdInToString(String filename) {
        return new String(readFileOrStdInToByteArray(filename), StandardCharsets.UTF_8);
    }

    public static void writeStringToFileOrStdOut(String filename, String content) {
        try (OutputStream os = (filename == null ? System.out : new FileOutputStream(filename))) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeBytesToFileOrStdOut(String filename, byte[] content) {
        try (OutputStream os = (filename == null ? System.out : new FileOutputStream(filename))) {
            os.write(content);
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
