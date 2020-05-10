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

public class DrawFillType extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public DrawFillType(Context context) {
        this(context, null);
    }

    public DrawFillType(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
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
        canvas.drawColor(Color.TRANSPARENT);
        canvas.translate(mWidth/2, mHeight/2);
        Path path = new Path();                                     // 创建Path

        // 添加小正方形 (通过这两行代码来控制小正方形边的方向,从而演示不同的效果)
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);

// 添加大正方形
        path.addRect(-300, -300, 300, 300, Path.Direction.CW);

        path.setFillType(Path.FillType.INVERSE_WINDING);                    // 设置Path填充模式为非零环绕规则

        canvas.drawPath(path, mPaint);

//        非零环绕时：
//        若path绘制方向一致，填充最大path的内部；不一致，则填充最大path内部，但不包含其内部path的部分
//        反非零环绕：
//        若path绘制方向一致，填充最大path的外部；不一致，则填充最大path外部，及其内部path的内部
    }
}
