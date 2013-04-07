package com.vstar.lib.utils;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import com.vstar.lib.VstarApplication;

public class PathUtil {

    private static final String APP_DIR_NAME = "/VStar_News";

    private static final String HTML_DIR_NAME = "/html";
    private static final String IMAGE_DIR_NAME = "/image";
    private static final String MUSIC_DIR_NAME = "/music";
    private static final String DOWNLOAD_DIR_NAME = "/download";

    private static File ROOT_DIRECTORY;
    private static File CACHE_DIRECTORY;

    public static File getRootDirectory() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ROOT_DIRECTORY = new File(Environment.getExternalStorageDirectory(), APP_DIR_NAME);
            ROOT_DIRECTORY.mkdirs();
        }
        if (ROOT_DIRECTORY == null)
            ROOT_DIRECTORY = VstarApplication.getInstance().getFilesDir();
        return ROOT_DIRECTORY;
    }

    public static File getCacheDirectory() {
        CACHE_DIRECTORY = getExternalCacheDir(VstarApplication.getInstance());
        if (CACHE_DIRECTORY == null)
            CACHE_DIRECTORY = VstarApplication.getInstance().getCacheDir();
        return CACHE_DIRECTORY;
    }

    @SuppressLint("NewApi")
    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    public static File getHtmlDirectory() {
        return findOrCreateDirectory(getCacheDirectory(), HTML_DIR_NAME);
    }

    public static File getImageDirectory() {
        return findOrCreateDirectory(getRootDirectory(), IMAGE_DIR_NAME);
    }

    public static File getMusicDirectory() {
        return findOrCreateDirectory(getRootDirectory(), MUSIC_DIR_NAME);
    }

    public static File getDownloadDirectory() {
        return findOrCreateDirectory(getRootDirectory(), DOWNLOAD_DIR_NAME);
    }

    private static File findOrCreateDirectory(File parent, String directoryName) {
        File directory = new File(parent, directoryName);
        if (!directory.exists())
            directory.mkdirs();

        return directory;
    }

    private static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }
}
