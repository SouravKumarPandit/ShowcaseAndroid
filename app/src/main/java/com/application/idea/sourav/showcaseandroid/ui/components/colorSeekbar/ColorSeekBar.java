package com.application.idea.sourav.showcaseandroid.ui.components.colorSeekbar;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ColorSeekBar extends View {

    public ColorSeekBar(Context context) {
        super(context, null);
    }

    public ColorSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs, -1);
    }

    public ColorSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // (context: Context, attributeSet: AttributeSet): View(context, attributeSet)
    private float minThumbRadius = 16f;
    private int[] colorSeeds = new int[]{Color.parseColor("#000000"), Color.parseColor("#FF5252"), Color.parseColor("#FFEB3B"), Color.parseColor("#00C853"), Color.parseColor("#00B0FF"), Color.parseColor("#D500F9"), Color.parseColor("#8D6E63")};
    private int canvasHeight = 30;
    private int barHeight = 8;
    private RectF rectf = new RectF();
    private Paint rectPaint = new Paint();
    private Paint thumbBorderPaint = new Paint();

    private Paint thumbPaint = new Paint();
    private LinearGradient colorGradient;
    private float thumbX = 24f;
    private float thumbY = canvasHeight / 2;
    private float thumbBorder = 2f;
    private float thumbRadius = 10;
    private float thumbBorderRadius = thumbRadius + thumbBorder;
    private int thumbBorderColor = Color.BLACK;
    private float paddingStart = 15f;
    private float paddingEnd = 15f;
    private float barCornerRadius = 8f;
    private float oldThumbRadius = thumbRadius;
    private float oldThumbBorderRadius = thumbBorderRadius;


    private OnColorChangeListener colorChangeListener = null;

    public void init() {
        rectPaint.setAntiAlias(true);
        thumbBorderPaint.setAntiAlias(true);
        thumbBorderPaint.setColor(thumbBorderColor);
        thumbPaint.setAntiAlias(true);
        thumbRadius = (barHeight / 2);
        thumbBorderRadius = thumbRadius + thumbBorder;
        canvasHeight = (int) (thumbBorderRadius);
        thumbY = (canvasHeight / 2);

        oldThumbRadius = thumbRadius;
        oldThumbBorderRadius = thumbBorderRadius;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //color bar position
        float barLeft = paddingStart;
        float barRight = getWidth() - paddingEnd;
        float barTop = ((canvasHeight / 2) - (barHeight / 2));
        float barBottom = ((canvasHeight / 2) + (barHeight / 2));

        //draw color bar
        rectf.set(barLeft, barTop, barRight, barBottom);
        canvas.drawRoundRect(rectf, barCornerRadius, barCornerRadius, rectPaint);

        if (thumbX < barLeft) {
            thumbX = barLeft;
        } else if (thumbX > barRight) {
            thumbX = barRight;
        }
        int color = pickColor(thumbX, getWidth());
        thumbPaint.setColor(color);
        // draw color bar thumb
        canvas.drawCircle(thumbX, thumbY, thumbBorderRadius, thumbBorderPaint);
        canvas.drawCircle(thumbX, thumbY, thumbRadius, thumbPaint);
    }

    private int pickColor(float position, int canvasWidth) {
        float value = (position - paddingStart) / (canvasWidth - (paddingStart + paddingEnd));

        if (value <= 0.0) {
            return colorSeeds[0];
        } else if (value >= 1) {
            return colorSeeds[colorSeeds.length - 1];
        } else {
            int colorPosition = (int) (value * (colorSeeds.length - 1));
            int i = colorPosition;
            colorPosition -= i;
            int c0 = colorSeeds[i];
            int c1 = colorSeeds[i + 1];

            int alpha=255;
            int red = mix(Color.red(c0), Color.red(c1), colorPosition);
            int green = mix(Color.green(c0), Color.green(c1), colorPosition);
            int blue = mix(Color.blue(c0), Color.blue(c1), colorPosition);
            return (alpha & 0xff) << 24| (red & 0xff) << 16 | (green & 0xff) << 8 | (blue & 0xff);
        }

    }

    private int mix(int start, int end, float position) {
        return start + Math.round(position * (end - start));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        colorGradient = new LinearGradient(0f, 0f, w, 0f, colorSeeds, null, Shader.TileMode.CLAMP);
        rectPaint.setShader(colorGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, canvasHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (colorChangeListener != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    thumbBorderRadius = (float) (oldThumbBorderRadius * 1.2);
                    thumbRadius = (float) (oldThumbRadius * 1.2);
                    break;
                case MotionEvent.ACTION_MOVE:
                    getParent().requestDisallowInterceptTouchEvent(true);
                    thumbX = event.getX();
                    invalidate();
                    colorChangeListener.onColorChangeListener(selectedColor());

                    break;
                case MotionEvent.ACTION_UP:

                    thumbBorderRadius = oldThumbBorderRadius;
                    thumbRadius = oldThumbRadius;
                    invalidate();

            }
        }
        return true;
    }

    public int selectedColor() {
        return thumbPaint.getColor();
    }

    public void setThumbPaint(Paint thumbPaint) {
        this.thumbPaint = thumbPaint;
    }

    public void setColorChangeListener(OnColorChangeListener colorChangeListener) {
        this.colorChangeListener = colorChangeListener;
    }

    interface OnColorChangeListener {

        void onColorChangeListener(int color);
    }
}