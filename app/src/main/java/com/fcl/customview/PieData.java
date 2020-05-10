package com.fcl.customview;

import android.support.annotation.NonNull;

/**
 * Created by derik on 17-4-29 11:01.
 * Email：Derik.LP.Wu@mail.foxconn.com
 */

public class PieData {
    private String name;
    private float value;

    //根据value和计算，各个占比
    private float percentage;

    private int color = 0;      // 颜色
    private float angle = 0;    // 角度

    public PieData(@NonNull String name, @NonNull float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
