package com.fcl.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArcView arcView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arcView = (ArcView) findViewById(R.id.arc_view);

        ArrayList<PieData> lists= new ArrayList<>();
        lists.add(new PieData("test1", 10));
        lists.add(new PieData("test2", 20));
        lists.add(new PieData("test3", 30));
//        lists.add(new PieData("test4", 30));
//        lists.add(new PieData("test5", 20));
//        lists.add(new PieData("test6", 20));
//        lists.add(new PieData("test7", 20));
//        lists.add(new PieData("test8", 20));
//        lists.add(new PieData("test9", 20));
//        lists.add(new PieData("test10", 20));
//        lists.add(new PieData("test11", 20));
//        lists.add(new PieData("test12", 20));

        arcView.setData(lists);
        arcView.setStartAngle(30);
    }
}
