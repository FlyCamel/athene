package com.athene.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing on 6/3/15.
 */
public class DiskUtils {

    /**
     * Get all files on a path
     *
     * @param path:
     *      The absolute path of all files
     *
     * @return
     *      Succeeded returns files's absolute path
     *      Failed returns null, No files returns empty array.
     */
    public static List<String> getPathFiles(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }

        List<String> files = new ArrayList<String>();
        File fPath = new File(path);
        for (File file : fPath.listFiles()) {
            if (file.isDirectory()) {
                continue;
            }

            files.add(file.getAbsolutePath());
        }

        return files;
    }
}
