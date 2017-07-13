package com.harium.etyl.util;

import com.harium.etyl.util.io.IOHelper;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PathHelper {

    public static InputStream loadAsset(String path) throws IOException {
        return new FileInputStream(new File(currentDirectory() + "assets" + File.separator + path));
    }

    public static String currentDirectory() {
        String currentDirectory = "";

        try {
            String path = new File(".").getCanonicalPath().toString();
            currentDirectory = path + File.separator;

            return currentDirectory;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentDirectory;
    }

    public static String currentFileDirectory() {

        String currentDirectory = "";

        try {
            String path = new File(".").getCanonicalPath().toString();
            currentDirectory = IOHelper.FILE_PREFIX + path + File.separator;

            return currentDirectory;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentDirectory;
    }

    public static String upperDirectory(String path) {

        String separator = File.separator;
        if (!path.contains(separator)) {
            separator = "\\";
        }

        return path.substring(0, path.lastIndexOf(separator) + 1);
    }

    public static String currentPath() {
        URL location = PathHelper.class.getProtectionDomain().getCodeSource().getLocation();
        String currentDirectory = IOHelper.FILE_PREFIX + location.getPath();
        return currentDirectory;
    }

    public static String desktopDirectory() {
        FileSystemView filesys = FileSystemView.getFileSystemView();
        return filesys.getHomeDirectory().getAbsolutePath() + File.separator;
    }

    public static String programFilesDirectory() {
        return System.getenv("ProgramFiles");
    }

    public static String getExternalStoragePath() {
        return currentPath();
    }

    public static String clearPath(String path) {
        String cleanPath = path.replaceAll("/", File.separator);

        final String upper = "../";
        int index = cleanPath.indexOf(upper);
        while (index != -1) {
            int lastFolder = lastFolder(cleanPath, index);
            String before = cleanPath.substring(0, lastFolder - 1);
            String after = cleanPath.substring(index + 3);
            cleanPath = before + after;

            index = cleanPath.indexOf(upper);
        }
        return cleanPath;
    }

    private static int lastFolder(String path, int cursor) {
        int index = cursor;
        while (path.charAt(index) != File.separatorChar) {
            index--;
        }
        return index;
    }

}
