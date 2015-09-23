package cn.trinea.android.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * SystemUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-15
 */
public class SystemUtils {

    /** recommend default thread pool size according to system available processors, {@link #getDefaultThreadPoolSize()} **/
    public static final int DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();

    /**
     * get recommend default thread pool size
     * 
     * @return if 2 * availableProcessors + 1 less than 8, return it, else return 8;
     * @see {@link #getDefaultThreadPoolSize(int)} max is 8
     */
    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    /**
     * get recommend default thread pool size
     * 
     * @param max
     * @return if 2 * availableProcessors + 1 less than max, return it, else return max;
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }
    
    /**
     * return app current versionCode
     * 
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        PackageInfo info;
        if(context == null){
            return 0;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if(packageManager !=null){
                info = packageManager.getPackageInfo(context.getPackageName(), 0);
                versionCode = info.versionCode;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return versionCode;
    }
}
