package com.vstar.lib.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public class L {

    private static final String TAG = "ApplcationDebug";

    public static boolean Debug = true;
    private static boolean enableWrite = false;

    public static final String DEBUG = "DEBUG";
    public static final String WARN = "WARN";
    public static final String ERROR = "ERROE";
    public static final String VERBOSE = "VERBOSE";
    public static final String INFO = "INFO";

    public static final String LOG_SUFFIX = "txt";
    private static final String DEFAULT_LOG_DIR = android.os.Environment.getExternalStorageDirectory()
                                                                        .getAbsolutePath()
            + File.separator + "leonlog";

    private static String mLogDir = DEFAULT_LOG_DIR;

    public static String makeLogTag(Class<?> clazz) {
        return clazz != null ? clazz.getSimpleName() : TAG;
    }

    private static String getTag() {
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        StackTraceElement s = stes[stes.length - 4];
        String fileName = s.getFileName();
        return fileName.substring(0, fileName.indexOf(".")) + " | " + s.getMethodName();
    }

    public static void setDebug(boolean isdebug) {
        Debug = isdebug;
    }

    public static void setEnableWriteLog(boolean enable) {
        enableWrite = enable;
    }

    public static void setupLogDir(String dir_path) {
        mLogDir = dir_path;
    }

    public static void i(String str) {
        i(TAG, str);
    }

    public static void i(Object tag, String str) {
        i(tag, str, (Throwable) null);
    }

    public static void i(Object tag, String msg, Throwable tr) {
        log(INFO, tag, msg, tr);
    }

    public static void i(Object tag, String format, Object... objects) {
        log(INFO, tag, format, objects);
    }

    public static void e(String str) {
        e(TAG, str);
    }

    public static void e(Object tag, String str) {
        e(tag, str, (Throwable) null);
    }

    public static void e(Object tag, String msg, Throwable tr) {
        log(ERROR, tag, msg, tr);
    }

    public static void e(Object tag, String format, Object... objects) {
        log(ERROR, tag, format, objects);
    }

    public static void v(String str) {
        v(TAG, str);
    }

    public static void v(Object tag, String str) {
        v(tag, str, (Throwable) null);
    }

    public static void v(Object tag, String msg, Throwable tr) {
        log(VERBOSE, tag, msg, tr);
    }

    public static void v(Object tag, String format, Object... objects) {
        log(VERBOSE, tag, format, objects);
    }

    public static void d(String str) {
        d(TAG, str);
    }

    public static void d(Object tag, String str) {
        d(tag, str, (Throwable) null);
    }

    public static void d(Object tag, String msg, Throwable tr) {
        log(DEBUG, tag, msg, tr);
    }

    public static void d(Object tag, String format, Object... objects) {
        log(DEBUG, tag, format, objects);
    }

    public static void w(String str) {
        w(TAG, str);
    }

    public static void w(Object tag, String str) {
        w(tag, str, (Throwable) null);
    }

    public static void w(Object tag, String msg, Throwable tr) {
        log(WARN, tag, msg, tr);
    }

    public static void w(Object tag, String format, Object... objects) {
        log(WARN, tag, format, objects);
    }

    private static void log(String type, Object obj, String msg, Throwable tr) {
        if (!Debug) {
            return;
        }
        String tag = String.valueOf(obj);
        if (WARN.equals(type)) {
            Log.w(tag, msg, tr);
        } else if (ERROR.equals(type)) {
            Log.e(tag, msg, tr);
        } else if (DEBUG.equals(type)) {
            Log.d(tag, msg, tr);
        } else if (INFO.equals(type)) {
            Log.i(tag, msg, tr);
        } else if (VERBOSE.equals(type)) {
            Log.v(tag, msg, tr);
        }

        if (enableWrite) {
            writeLogFile(getLogStyle(type, tag, msg, tr));
        }
    }

    private static void log(String type, Object obj, String format, Object... objects) {
        if (!Debug) {
            return;
        }
        String msg = String.format(format, objects);
        String tag = String.valueOf(obj);
        if (WARN.equals(type)) {
            Log.w(tag, msg);
        } else if (ERROR.equals(type)) {
            Log.e(tag, msg);
        } else if (DEBUG.equals(type)) {
            Log.d(tag, msg);
        } else if (INFO.equals(type)) {
            Log.i(tag, msg);
        } else if (VERBOSE.equals(type)) {
            Log.v(tag, msg);
        }
        if (enableWrite) {
            writeLogFile(getLogStyle(type, tag, msg, null));
        }
    }

    public static String getLogStyle(String type, Object tag, String msg, Throwable tr) {
        StringBuilder log = new StringBuilder();
        String date = DateFormat.format("yyyy-MM-dd aa hh:mm:ss", System.currentTimeMillis())
                                .toString();
        log.append("[");
        log.append(date);
        log.append("]");
        log.append("[");
        log.append(type);
        log.append("]");
        log.append("[");
        log.append(tag);
        log.append("]");
        log.append(msg);
        if (tr != null) {
            log.append(" ");
            log.append(getStackTraceString(tr));
        }
        return log.toString();
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }

    public static void Toast(String str, Context context) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void Toast(int resId, Context context) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 字符集的测试,在乱码状态下使用
     * 
     * @param datastr
     *            <p>
     *            传入需要测试的字符串
     *            </p>
     * */
    public static void testCharset(String datastr) {
        try {
            String temp = new String(datastr.getBytes(), "GBK");
            L.v("****** getBytes() -> GBK ******\n" + temp);

            temp = new String(datastr.getBytes("GBK"), "UTF-8");
            L.v("****** GBK -> UTF-8 *******\n" + temp);

            temp = new String(datastr.getBytes("GBK"), "ISO-8859-1");
            L.v("****** GBK -> ISO-8859-1 *******\n" + temp);

            temp = new String(datastr.getBytes("ISO-8859-1"), "UTF-8");
            L.v("****** ISO-8859-1 -> UTF-8 *******\n" + temp);

            temp = new String(datastr.getBytes("ISO-8859-1"), "GBK");
            L.v("****** ISO-8859-1 -> GBK *******\n" + temp);

            temp = new String(datastr.getBytes("UTF-8"), "GBK");
            L.v("****** UTF-8 -> GBK *******\n" + temp);

            temp = new String(datastr.getBytes("UTF-8"), "ISO-8859-1");
            L.v("****** UTF-8 -> ISO-8859-1 *******\n" + temp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将byte数组以16进制形式输出
     * */
    public synchronized static void writeLog(byte log[]) {
        String slog = "";
        for (int i = 0; i < log.length; i++) {
            slog += " " + L.toHex(log[i]);
        }
        L.writeLogFile(slog);
    }

    /**
     * 写入调试日志
     * 
     * @param log
     *            要写入的调试内容
     * */
    public synchronized static void writeLogFile(String log) {
        long currentTime = System.currentTimeMillis();
        String logFileName = DateFormat.format("yyyy-MM-dd_hh_mm", currentTime).toString();
        writeLogFile(logFileName + "." + LOG_SUFFIX, log);
    }

    /**
     * 写入调试日志
     * 
     * @param filename
     *            要保存的文件名
     * @param content
     *            要写入的调试内容
     * */
    public synchronized static void writeLogFile(String filename, String content) {
        try {
            File saveLogDir = new File(mLogDir);
            if (!saveLogDir.exists()) {
                saveLogDir.mkdirs();
            }
            File fileName = new File(saveLogDir, filename);
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将byte转为16进制
     * 
     * @param b
     * @return
     * */
    public static final String toHex(byte b) {
        return ("" + "0123456789ABCDEF".charAt(0xf & b >> 4) + "0123456789ABCDEF".charAt(b & 0xf));
    }

    public synchronized static void buildLogMessage(Throwable ex) {
        StringBuilder builder = new StringBuilder();
        if (ex != null) {
            builder.append("\n\n").append("------ 程序错误信息 -------").append("\n")
                   .append(L.getLogStyle(L.ERROR, TAG, "", ex));
        }
        long currentTime = System.currentTimeMillis();
        String logFileName = "mark_"
                + DateFormat.format("yyyy-MM-dd_hh_mm", currentTime).toString();
        writeLogFile(logFileName + "." + LOG_SUFFIX, builder.toString());
    }
}
