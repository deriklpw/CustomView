package com.fcl.dispatch_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v){
        Log.e("TAG, ViewGroup","get it");
    }

    public void click2(View v){
        Log.e("TAG View1 click2","get it");
    }

//    public void click3(View v){
//        Log.e("TAG View2 click3","get it");
//    }
}
