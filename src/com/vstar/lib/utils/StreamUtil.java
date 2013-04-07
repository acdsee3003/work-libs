// Copyright (c) 2003-2011, Jodd Team (jodd.org). All Rights Reserved.

package com.vstar.lib.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Optimized byte and character stream utilities.
 */
public class StreamUtil {
    /**
     * Default IO buffer size (32 KB).
     */
    public static int ioBufferSize = 32768;

    // ---------------------------------------------------------------- silent
    // close

    /**
     * Closes an input stream and releases any system resources associated with
     * this stream. No exception will be thrown if an I/O error occurs.
     */
    public static void close(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ioe) {
                // ignore
            }
        }
    }

    /**
     * Closes an output stream and releases any system resources associated with
     * this stream. No exception will be thrown if an I/O error occurs.
     */
    public static void close(OutputStream out) {
        if (out != null) {
            try {
                out.flush();
            } catch (IOException ioex) {
                // ignore
            }
            try {
                out.close();
            } catch (IOException ioe) {
                // ignore
            }
        }
    }

    /**
     * Closes a character-input stream and releases any system resources
     * associated with this stream. No exception will be thrown if an I/O error
     * occurs.
     */
    public static void close(Reader in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ioe) {
                // ignore
            }
        }
    }

    /**
     * Closes a character-output stream and releases any system resources
     * associated with this stream. No exception will be thrown if an I/O error
     * occurs.
     */
    public static void close(Writer out) {
        if (out != null) {
            try {
                out.flush();
            } catch (IOException ioex) {
                // ignore
            }
            try {
                out.close();
            } catch (IOException ioe) {
                // ignore
            }
        }
    }

    // ---------------------------------------------------------------- copy

    /**
     * Copies input stream to output stream using buffer. Streams dont have to
     * be wrapped to buffered, since copying is already optimizied.
     */
    public static int copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[ioBufferSize];
        int count = 0;
        int read;
        while (true) {
            read = input.read(buffer, 0, ioBufferSize);
            if (read == -1) {
                break;
            }
            output.write(buffer, 0, read);
            count += read;
        }
        return count;
    }
}
