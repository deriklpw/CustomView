package com.fcl.dispatch_event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by derik on 17-5-18 10:27.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class DispatchView extends ViewGroup {
    public DispatchView(Context context) {
        this(context, null);
    }

    public DispatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //可最大限度确定子View的布局
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(0, 0, 200, 200);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return true;
    }
}
