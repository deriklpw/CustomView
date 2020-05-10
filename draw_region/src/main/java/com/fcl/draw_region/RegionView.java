package com.fcl.draw_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by derik on 17-5-24 09:33.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class RegionView extends View {

    private Path circlePath;
    private Region circleRegion;
    private Paint mPaint = new Paint();

    public RegionView(Context context) {
        this(context, null);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        mPaint.setColor(0xFF4E5268);
        circlePath = new Path();
        circleRegion = new Region();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        circlePath.addCircle(w/2, h/2, 100, Path.Direction.CW);
        Log.e("width", ""+w);
        Log.e("height", ""+h);
        Region globalRegion = new Region();
        globalRegion.set(0, 0, w, h);

        //circleRegion区域是circlePath和globalRegion的交集
        circleRegion.setPath(circlePath, globalRegion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();

                // ▼点击区域判断
                if (circleRegion.contains(x,y)){
                    Toast.makeText(this.getContext(),"圆被点击",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path  path = circlePath;
        canvas.drawPath(path, mPaint);
    }
}
