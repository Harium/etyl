package com.harium.etyl.util.io;

import com.harium.etyl.util.StringUtils;

import java.io.*;

public class IOHelper {

    public static final String FILE_PREFIX = "file:///";
    public static final String STREAM_PREFIX = "stream:///";
    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";

    public static final String ENCODING_UTF_8 = "UTF-8";

    public static void write(String path, String text) {

        Writer writer = null;

        try {

            File file = getFile(path);

            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), ENCODING_UTF_8));
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }

    public static File getFile(String path) throws IOException {

        File file = new File(fixPrefixPath(path));

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    private static String fixPrefixPath(String path) {

        String filePath = path;

        if (path.startsWith(FILE_PREFIX)) {
            filePath = path.substring(5);
        }

        if (path.contains(StringUtils.WINDOWS_SPACING)) {
            filePath = fixPath(filePath);
        }

        return filePath;
    }

    public static String fixPath(String path) {
        return path.replaceAll(StringUtils.WINDOWS_SPACING, StringUtils.WHITE_SPACE);
    }

    public static boolean startsWithHttp(String path) {
        return path.startsWith(IOHelper.HTTP_PREFIX) || path.startsWith(IOHelper.HTTPS_PREFIX);
    }

    public static void createDirectory(String path) {
        new File(path).mkdirs();
    }

}
