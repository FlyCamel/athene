package com.athene;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.athene.mmi.SplashActivity;
import com.athene.utils.Init;

public class MyApplication extends Application {
    private static final String KEY_IS_FIRST_RUN = "isFirstRun";

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = new MyApplication();

        Init.init();

//        if (isFirstRun()) {
//            Intent intent = new Intent(MyApplication.this, SplashActivity.class);
//            this.startActivity(intent);
//        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    private boolean isFirstRun() {
        SharedPreferences pref = this.getSharedPreferences("global", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        boolean isFirstRun = pref.getBoolean(KEY_IS_FIRST_RUN, false);
        if (!isFirstRun) {
            return false;
        }

        prefEditor.putBoolean(KEY_IS_FIRST_RUN, true);
        prefEditor.commit();

        return true;
    }
}

