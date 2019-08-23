package com.application.idea.sourav.showcaseandroid.ui.components.styleEditor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.application.idea.sourav.showcaseandroid.utils.Utils;


public class ColorSelectorView extends View
{

    public  int iSelectedPosition;
int requestId;
    String tickMark = "✔";

    public ColorSelectorView(Context context) {
        this(context, null);
    }

    public ColorSelectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //for touch calculation
    private PointF startPoint;
    private int itemWidth;
    private int itemHeight;

    private int[] colorArray = new int[]{
            0xff000000,
            0xffd75f5f,
            0xff7378d7,
            0xff51ce48,
            0xffd18e37,
    };
    private Paint optionColorPaint;
    private Paint selectItemPaint;


    private void init(Context context) {
        initTools(context);
    }

    private void initTools(Context context) {

        optionColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectItemPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectItemPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (colorArray != null) {
//            int width = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY));
//            int height = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY));

            itemWidth= Utils.dpToPx(45);
            itemHeight = itemWidth;
            selectItemPaint.setTextSize(itemWidth / 2);


            setMeasuredDimension(itemWidth * colorArray.length, itemHeight);
        }else
            setMeasuredDimension(0, 0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (colorArray != null)
            for (int j = 0; j < colorArray.length; j++) {
                optionColorPaint.setColor(colorArray[j]);
                int itempWidth = itemWidth * j + itemWidth / 2;
                canvas.drawCircle(itempWidth, itemHeight / 2, itemWidth / 3, optionColorPaint);
                if (iSelectedPosition == j) {
                    selectItemPaint.setColor(0x3D3D3D3D);
                    canvas.drawCircle(itempWidth, itemHeight / 2, itemWidth / 4, selectItemPaint);
                    selectItemPaint.setColor(Color.WHITE);
                    canvas.drawText(tickMark, itempWidth /*- selectItemPaint.getTextSize() / 4*/, itemHeight / 2 + selectItemPaint.getTextSize() / 4, selectItemPaint);
                }

            }

    }

    /*@Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setTranslationY(-CLViewUtil.dpToPx(35));
        animate().translationY(0).setDuration(150).setInterpolator(new LinearInterpolator());
    }*/

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startPoint = new PointF(event.getX(), event.getY());
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();
            if (colorArray != null && Math.abs(startPoint.x - x) < 20 && Math.abs(startPoint.y - y) < 20) {
                for (int j = 0; j < colorArray.length; j++) {
                    if (x > itemWidth * j && x < itemWidth * (j + 1) && y > 0 && y < itemHeight) {
                        iSelectedPosition = j;
                        if (onColorSelected != null) {
                            onColorSelected.onColorSelectionChange(colorArray[j],iSelectedPosition,requestId);
                        }
                        invalidate();
                    }
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }



    public int[] getColorArray() {
        return colorArray;
    }

    public void setColorArray(int[] colorArray) {
        this.colorArray = colorArray;
        postInvalidate();
    }

    private OnColorSelected onColorSelected;

    public void setOnColorSelected(OnColorSelected onColorSelected) {
        this.onColorSelected = onColorSelected;
    }

    public void setiSelectedPosition(int iSelectedPosition) {
        this.iSelectedPosition = iSelectedPosition;
        invalidate();
    }

    public interface OnColorSelected {
        void onColorSelectionChange(int selectedSize, int selectionPosition, int requestId);

    }


}