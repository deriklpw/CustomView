package com.fcl.motion_event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by derik on 17-5-19 09:42.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class OutSideView extends android.support.v7.widget.AppCompatImageView {

    public OutSideView(Context context) {
        this(context, null);
    }

    public OutSideView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OutSideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
    }

    //需要View是可点击的，才能处理所有事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pointId;
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("down", "size:" +  event.getSize());
                System.out.println("OutSideView.onTouchEvent:" +event.getAction());
                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("move", "" + true);

                break;
            case MotionEvent.ACTION_POINTER_INDEX_SHIFT:
                Log.e("up", "" +  true);

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.e("pointer down", "size:"+event.getSize(event.getActionIndex()));
                pointId = event.getPointerId(event.getActionIndex());
                Log.e("pointer down, pointId:",pointId+"");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.e("pointer up", event.getActionIndex()+ ";" +  true);
                pointId = event.getPointerId(event.getActionIndex());
                Log.e("pointer up, pointId:",pointId+"");
                break;
        }
        return true;
    }

    private float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
