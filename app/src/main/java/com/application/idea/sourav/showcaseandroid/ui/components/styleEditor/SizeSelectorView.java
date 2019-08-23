package com.application.idea.sourav.showcaseandroid.ui.components.styleEditor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.application.idea.sourav.showcaseandroid.utils.Utils;


public class SizeSelectorView extends View
{
    public int iSelectedPosition = 3;
    int requestId;
    private boolean hideDecimal;
//    String tickMark = "✔";

    public SizeSelectorView(Context context)
    {
        this(context, null);
    }

    public SizeSelectorView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SizeSelectorView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //for touch calculation
    private PointF startPoint;
    private int itemWidth;
    private int itemHeight;

    private float[] sizeSelectionArray = new float[]{
            8f,
            10f,
            12f,
            16f,
            18f,
            20f,
            22f,
            24f
    };
    private Paint optionColorPaint;
    private Paint selectItemPaint;


    private void init(Context context)
    {
        initTools(context);
    }

    private void initTools(Context context)
    {

        optionColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectItemPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectItemPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (sizeSelectionArray != null)
        {
//            int width = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY));
//            int height = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY));

            itemWidth = Utils.dpToPx(40);
            itemHeight = itemWidth;
            selectItemPaint.setTextSize(itemWidth / 3);
            setMeasuredDimension(itemWidth * sizeSelectionArray.length, itemHeight);
        }
        else
        {
            setMeasuredDimension(0, 0);
        }

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (sizeSelectionArray != null)
        {
            for (int j = 0; j < sizeSelectionArray.length; j++)
            {

                int itempWidth = itemWidth * j + itemWidth / 2;
                String textVal;
                if (hideDecimal)
                {
                    textVal = (int) sizeSelectionArray[j] + "";
                }
                else
                {
                    textVal = sizeSelectionArray[j] + "";
                }

                if (iSelectedPosition == j)
                {
                    selectItemPaint.setColor(Color.BLACK);
                    selectItemPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    canvas.drawText(textVal, itempWidth, itemHeight / 2 + selectItemPaint.getTextSize() / 4, selectItemPaint);
                }
                else
                {
                    selectItemPaint.setColor(Color.DKGRAY);
                    selectItemPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    canvas.drawText(textVal, itempWidth, itemHeight / 2 + selectItemPaint.getTextSize() / 4, selectItemPaint);
                }

            }
        }

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            startPoint = new PointF(event.getX(), event.getY());
            return true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP)
        {
            float x = event.getX();
            float y = event.getY();
            if (sizeSelectionArray != null && Math.abs(startPoint.x - x) < 20 && Math.abs(startPoint.y - y) < 20)
            {
                for (int j = 0; j < sizeSelectionArray.length; j++)
                {
                    if (x > itemWidth * j && x < itemWidth * (j + 1) && y > 0 && y < itemHeight)
                    {
                        iSelectedPosition = j;
                        if (onSizePicked != null)
                        {
                            onSizePicked.onValueSelectionChange(sizeSelectionArray[j], iSelectedPosition, requestId);
                        }
                        invalidate();
                    }
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public int getRequestId()
    {
        return requestId;
    }

    public void setRequestId(int requestId)
    {
        this.requestId = requestId;
    }


    public float[] getSizeSelectionArray()
    {
        return sizeSelectionArray;
    }

    public void setSizeSelectionArray(float[] sizeSelectionArray)
    {
        setSizeSelectionArray(sizeSelectionArray, false);
    }

    public void setSizeSelectionArray(float[] sizeSelectionArray, boolean hideDecimal)
    {
        this.sizeSelectionArray = sizeSelectionArray;
        this.hideDecimal = hideDecimal;
        postInvalidate();
    }

    public void setiSelectedPosition(int iSelectedPosition)
    {
        this.iSelectedPosition = iSelectedPosition;
        invalidate();
    }
    public void setHideDecimal(boolean hideDecimal) {
        this.hideDecimal = hideDecimal;
        postInvalidate();
    }
    private OnSizePicked onSizePicked;

    public void setOnSizePicked(OnSizePicked onSizePicked)
    {
        this.onSizePicked = onSizePicked;
    }


    public interface OnSizePicked
    {
        void onValueSelectionChange(float selectedValue, int iSelectedPosition, int requestId);

    }


}