package com.athene.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class SDCardUtils {

    /**
     * sdcard availablesize
     *
     * @return return sdcard available size unit is byte
     */
    public static long getAvailaleSize() {

        File path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径

        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSize();

        long availableBlocks = stat.getAvailableBlocks();

        return availableBlocks * blockSize;

        // (availableBlocks * blockSize)/1024 KIB 单位

        // (availableBlocks * blockSize)/1024 /1024 MIB单位

    }

    /**
     * sdcard allsize
     *
     * @return return sdcard total size ,unit is byte
     */
    public static long getAllSize() {

        File path = Environment.getExternalStorageDirectory();

        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSize();

        long availableBlocks = stat.getBlockCount();

        return availableBlocks * blockSize;

    }

    /**
     * if there is enough size for app download
     *
     * @param size download app required size,unit is byte
     * @return
     */
    public static boolean isHasEnoughSize(long size) {
        return getAvailaleSize() > size;
    }
}
