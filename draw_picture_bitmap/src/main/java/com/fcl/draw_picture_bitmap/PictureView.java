package com.fcl.draw_picture_bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by derik on 17-4-29 14:21.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class PictureView extends View {
    private Picture mPicture = new Picture();
    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;
    private BitmapTools bitmapTools;

    public PictureView(Context context) {
        super(context);
        init(context);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        System.out.println("PictureView.onSizeChanged: width:" + mWidth + "; " + "height:" + mHeight);
    }

    private void init(Context context) {
        // 创建一个画笔
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        //一、绘制Picture
//        recording();
        bitmapTools = BitmapTools.getBitmapTools();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //一、绘制Picture
        //必须关闭硬件加速
//        mPicture.draw(canvas); //会影响canvas状态
//        canvas.drawPicture(mPicture); //不影响canvas
//        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200)); //会根据选择区域进行缩放


//        PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
//        // 设置绘制区域 -- 所绘制的实际内容不会缩放
//        pictureDrawable.setBounds(0, 0, 250, mPicture.getHeight());
//        pictureDrawable.draw(canvas);

          // 二、绘制Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.pic_account_2);
        // 将画布坐标系移动到画布中央
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawColor(Color.BLUE);
        PointF pointF = bitmapTools.calScale(getContext(), R.mipmap.pic_account_2, 180, 180);


//        // 指定图片绘制区域(左上角的四分之一)
//        Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
//
//        // 指定图片在屏幕上显示的区域
//
//        Rect dst = new Rect(-200, -200, 200, 200);
//        canvas.drawRect(dst, mPaint);
//        // 绘制图片
//        canvas.drawBitmap(bitmap, src, dst, null);

        canvas.scale(pointF.x, pointF.y);
        canvas.drawBitmap(bitmap, -bitmap.getWidth()/2, -bitmap.getHeight()/2, mPaint);

    }

    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 100, mPaint);

        mPicture.endRecording();

    }

}
