package com.vstar.lib.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    public static final int IO_BUFFER_SIZE = 8 * 1024;

    public interface SaveFileCallBack {
        public void saveFileSize(int size);
    }

    public static void saveFile(File fileout, byte[] bytes) throws IOException {
        if (!fileout.exists())
            fileout.createNewFile();
        FileOutputStream fos = new FileOutputStream(fileout);
        fos.write(bytes);
        StreamUtil.close(fos);
    }

    public static void saveFile(File fileOut, File fileIn) throws IOException {
        FileInputStream fis = new FileInputStream(fileIn);
        FileOutputStream fos = new FileOutputStream(fileOut);
        int len;
        byte[] buffer = new byte[IO_BUFFER_SIZE];
        while ((len = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        StreamUtil.close(fis);
        StreamUtil.close(fos);
    }

    public static void saveFile(File fileout, InputStream inStream) throws IOException {
        saveFile(fileout, inStream, null);
    }

    public static void saveFile(File fileOut, InputStream inStream,
                                SaveFileCallBack saveFileCallBack) throws IOException {
        // if (!fileOut.exists())
        // fileOut.createNewFile();
        FileOutputStream fos = new FileOutputStream(fileOut);
        BufferedInputStream bis = new BufferedInputStream(inStream, IO_BUFFER_SIZE);
        int saveFileSize = 0;
        int len;
        byte[] buffer = new byte[IO_BUFFER_SIZE];
        while ((len = bis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
            if (saveFileCallBack != null) {
                saveFileSize += len;
                saveFileCallBack.saveFileSize(saveFileSize);
            }
        }
        StreamUtil.close(inStream);
        StreamUtil.close(bis);
        StreamUtil.close(fos);
    }

    /**
     * 获取目录大小
     * 
     * @param dir
     *            需要统计大小的目录
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 如果遇到目录则通过递归调用继续统计
            }
        }
        return dirSize;
    }

}
