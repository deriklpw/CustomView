package com.fcl.draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by derik on 17-4-29 15:55.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class DrawTextView extends View {

    private Paint mPaint = new Paint();

    public DrawTextView(Context context) {
        super(context);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint.setColor(Color.BLACK);        // 设置颜色
        mPaint.setStyle(Paint.Style.FILL);   // 设置样式
        mPaint.setTextSize(50);              // 设置字体大小
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        String str = "ABCDEFG";
//        // 参数分别为 (文本 基线x 基线y 画笔)
//        canvas.drawText(str,1,3,200,600,mPaint);


//        String str = "SLOOP";
//        char[] sf = str.toCharArray();
//        canvas.drawPosText(sf,1,3,new float[]{
//                100,100,    // 第一个字符位置
//                200,200,    // 第二个字符位置
//                300,300,    // ...
//                400,400,
//                500,500
//        }, mPaint);

    }
}
