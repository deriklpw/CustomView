package com.fcl.motion_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import static android.view.WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;

public class MainActivity extends AppCompatActivity {

    private OutSideView sideView;
    private int w;
    private int h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(FLAG_WATCH_OUTSIDE_TOUCH);
        sideView = (OutSideView) findViewById(R.id.outside);


    }

}
