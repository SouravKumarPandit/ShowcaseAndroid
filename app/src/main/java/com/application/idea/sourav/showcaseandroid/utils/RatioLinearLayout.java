package com.application.idea.sourav.showcaseandroid.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RatioLinearLayout extends LinearLayout {


    private double WIDTH_RATIO = 3;
    private double HEIGHT_RATIO = 4;

    public RatioLinearLayout(Context context)
    {
        super(context);
    }

    public RatioLinearLayout(Context context, int widthRation, int heightRation)
    {
        super(context);
        WIDTH_RATIO = widthRation;
        HEIGHT_RATIO = heightRation;
    }


    public RatioLinearLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public RatioLinearLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = (int) (HEIGHT_RATIO / WIDTH_RATIO * widthSize);
        int newHeightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightSpec);
    }
}