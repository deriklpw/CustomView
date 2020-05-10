package com.fcl.draw_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by derik on 17-5-2 11:05.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class DrawPathView extends View {


    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public DrawPathView(Context context) {
        this(context, null);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.scale(1, -1);
        //第一组
//        Path path = new Path();
//        path.lineTo(200, 200);                      // lineTo
//        path.setLastPoint(200,100);                 // setLastPoint
//        path.lineTo(200,0);                         // lineTo
//        path.close();
//        canvas.drawPath(path, mPaint);


        //第二组
//        Path path = new Path();
//        Path src = new Path();
//
//        //基本形状
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//
//        //arcTo
//        src.lineTo(-100, -100);
//        RectF oval = new RectF(0, 0, 300, 300);
//        src.arcTo(oval, 0, 270, false);  //false 将上次的最后一个点位置(-100, -100)和这次的第一个点位置()连接起来
//        //addPath
//        path.addPath(src, 0, 100); //src 在Y轴上偏移100再加入path
//        canvas.drawPath(path, mPaint);

        //第三组,isEmpty,isRect,set,offset
        Path path = new Path();
        path.lineTo(20f,50f);
        if (path.isEmpty()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        canvas.drawPath(path, mPaint);

    }
}
