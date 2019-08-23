package com.application.idea.sourav.showcaseandroid.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.application.idea.sourav.showcaseandroid.R;

public class SimpleHomeGrid extends ViewGroup {

    private static final int DEFAULT_COUNT_COLUMNS = 2;
    private static final int DEFAULT_COUNT_ROWS = 3;
    private boolean isOutLineBox = false;

    private Paint mGridPaint;

    private int mColumnCount;
    private int mRowsCount;
    private int mMaxChildren;

    public SimpleHomeGrid(Context context)
    {
        this(context, null);
    }

    public SimpleHomeGrid(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SimpleHomeGrid(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SimpleHomeGrid, 0, defStyle);

        int strokeWidth = a.getDimensionPixelSize(R.styleable.SimpleHomeGrid_separatorWidth, 0);
        int strokeColor = a.getColor(R.styleable.SimpleHomeGrid_separatorColor, Color.WHITE);
        mColumnCount = a.getInteger(R.styleable.SimpleHomeGrid_numColumns, DEFAULT_COUNT_COLUMNS);
        mRowsCount = a.getInteger(R.styleable.SimpleHomeGrid_numRows, DEFAULT_COUNT_ROWS);
        isOutLineBox = a.getBoolean(R.styleable.SimpleHomeGrid_isOutlineBoxed, false);
        boolean isTabmatter = a.getBoolean(R.styleable.SimpleHomeGrid_isTabmatter, true);
        mMaxChildren = mRowsCount * mColumnCount;
        if (isTabmatter)
        if (isTablet(context))
            mColumnCount = +1;
        a.recycle();

        mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGridPaint.setStyle(Paint.Style.STROKE);
        mGridPaint.setColor(strokeColor);
        mGridPaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthSize, heightSize;

        //Get the width based on the measure specs
        widthSize = getDefaultSize(0, widthMeasureSpec);

        //Get the height based on measure specs
        heightSize = getDefaultSize(0, heightMeasureSpec);

        int majorWidthDimension = Math.min(widthSize, heightSize);
        int majorHeightDimension = Math.min(widthSize, heightSize);
        //Measure all child views
        int blockColDimension = majorWidthDimension / mColumnCount;
        int blockRowDimension = majorWidthDimension / mRowsCount;
        int blockSpec = MeasureSpec.makeMeasureSpec(blockColDimension, MeasureSpec.EXACTLY);
        measureChildren(blockSpec, blockSpec);

        //MUST call this to save our own dimensions
        setMeasuredDimension(widthSize, blockSpec * mRowsCount);
    }

    public static boolean isTablet(Context context)
    {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int row, col, left, top;
        for (int i = 0; i < getChildCount(); i++) {
            row = i / mColumnCount;
            col = i % mColumnCount;
            View child = getChildAt(i);
            left = col * child.getMeasuredWidth();
            top = row * child.getMeasuredHeight();

            child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
        }
    }


    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        //Let the framework do its thing
        super.dispatchDraw(canvas);

        //Draw the grid lines
        for (int i = 0, j = 0; i <= getWidth(); j++, i += (getWidth() / mColumnCount)) {
            if (i == 0 || j == mColumnCount) {
                if (isOutLineBox)
                    canvas.drawLine(i, 0, i, getHeight(), mGridPaint);

            } else
                canvas.drawLine(i, 0, i, getHeight(), mGridPaint);

        }
        for (int i = 0, j = 0; i <= getHeight(); j++, i += (getHeight() / mRowsCount)) {
            if (i == 0 || j == mRowsCount) {
                if (isOutLineBox)
                    canvas.drawLine(0, i, getWidth(), i, mGridPaint);

            } else
                canvas.drawLine(0, i, getWidth(), i, mGridPaint);
        }
    }

    @Override
    public void addView(View child)
    {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + " direct children");
        }

        super.addView(child);
    }

    @Override
    public void addView(View child, int index)
    {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + " direct children");
        }

        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, LayoutParams params)
    {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + " direct children");
        }

        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, LayoutParams params)
    {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + " direct children");
        }

        super.addView(child, params);
    }

    @Override
    public void addView(View child, int width, int height)
    {
        if (getChildCount() > mMaxChildren - 1) {
            throw new IllegalStateException("BoxGridLayout cannot have more than " + mMaxChildren + " direct children");
        }

        super.addView(child, width, height);
    }
}
