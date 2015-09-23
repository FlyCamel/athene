package com.athene;

import android.os.Environment;

import java.io.File;

/**
 * Created by bing on 6/3/15.
 */
public class Constants {
    /** Entertainment folder **/
    private static final String GAME_CENTER_FOLDER = Environment
            .getExternalStoragePublicDirectory(
                    new StringBuilder(16).append("LeWa").append(File.separator)
                            .append("GameCenter").toString()).getAbsolutePath();

    /** download apk folder **/
    public static final String APK_FOLDER = new StringBuilder(32)
            .append("LeWa").append(File.separator).append("GameCenter")
            .append(File.separator).append("Apk").append(File.separator)
            .toString();

    /** download apk folder **/
    public static final String APK_ABSOLUTE_FOLDER = Environment
            .getExternalStoragePublicDirectory(APK_FOLDER).getAbsolutePath()
            + File.separator;
    /** icon image cache folder **/
    public static final String ICON_IMAGE_CACHE_FOLDER = new StringBuilder()
            .append(GAME_CENTER_FOLDER).append(File.separator).append("Icon")
            .toString();
    /** app image cache folder **/
    public static final String APP_IMAGE_CACHE_FOLDER = new StringBuilder()
            .append(GAME_CENTER_FOLDER).append(File.separator)
            .append("AppImage").toString();

}
