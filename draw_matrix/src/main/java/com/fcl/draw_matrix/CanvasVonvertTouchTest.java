package com.fcl.draw_matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by derik on 17-5-24 11:07.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class CanvasVonvertTouchTest extends View {
    float down_x = -1;
    float down_y = -1;
    private Paint mPaint = new Paint();
    private int mViewWidth;
    private int mViewHeight;

    public CanvasVonvertTouchTest(Context context) {
        this(context, null);
    }

    public CanvasVonvertTouchTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // ▼ 注意此处使用 getRawX，而不是 getX
                down_x = event.getRawX();
                down_y = event.getRawY();

                Log.e("down_x", "" + down_x);
                Log.e("down_y", "" + down_y);

                Log.e("x", "" + event.getX());
                Log.e("y", "" + event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                down_x = down_y = -1;
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float[] pts = {down_x, down_y};

//        drawTouchCoordinateSpace(canvas);            // 绘制触摸坐标系，灰色
        // ▼注意画布平移
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

//        drawTranslateCoordinateSpace(canvas);        // 绘制平移后的坐标系，红色

        if (pts[0] == -1 && pts[1] == -1) return;    // 如果没有就返回

        // ▼ 获得当前矩阵的逆矩阵
        Matrix invertMatrix = new Matrix();
        canvas.getMatrix().invert(invertMatrix);

        // ▼ 使用 mapPoints 将触摸位置转换为画布坐标
        invertMatrix.mapPoints(pts);
        Log.e("transX", "" + pts[0]);
        Log.e("transY", "" + pts[1]);

        // 在触摸位置绘制一个小圆
        canvas.drawCircle(pts[0], pts[1], 20, mPaint);
    }

    /**
     *  绘制触摸坐标系，颜色为灰色，为了能够显示出坐标系，将坐标系位置稍微偏移了一点
     */
//    private void drawTouchCoordinateSpace(Canvas canvas) {
//        canvas.save();
//        canvas.translate(10,10);
//        CanvasAidUtils.set2DAxisLength(1000, 0, 1400, 0);
//        CanvasAidUtils.setLineColor(Color.GRAY);
//        CanvasAidUtils.draw2DCoordinateSpace(canvas);
//        canvas.restore();
//    }
//
//    /**
//     * 绘制平移后的坐标系，颜色为红色
//     */
//    private void drawTranslateCoordinateSpace(Canvas canvas) {
//        CanvasAidUtils.set2DAxisLength(500, 500, 700, 700);
//        CanvasAidUtils.setLineColor(Color.RED);
//        CanvasAidUtils.draw2DCoordinateSpace(canvas);
//        CanvasAidUtils.draw2DCoordinateSpace(canvas);
//    }
}
