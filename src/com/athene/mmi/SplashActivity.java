package com.athene.mmi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.athene.R;


public class SplashActivity extends Activity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView ivSplash = (ImageView) findViewById(R.id.iv_splash_view);
        ivSplash.setBackgroundResource(R.drawable.splash_1);
    }
}
