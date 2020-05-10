package com.fcl.draw_fill_type;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by derik on 17-5-3 09:52.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class DrawFillType2 extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public DrawFillType2(Context context) {
        this(context, null);
    }

    public DrawFillType2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.WHITE);
        canvas.translate(mWidth / 2, mHeight / 2);          // 移动画布(坐标系)

        Path path = new Path();                                     // 创建Path
//        path.setFillType(Path.FillType.EVEN_ODD);                 // 设置Path填充模式为 奇偶规则
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);            // 反奇偶规则

        path.addRect(-200,-200,200,200, Path.Direction.CW);         // 给Path中添加一个矩形
        canvas.drawPath(path, mPaint);
    }
}
