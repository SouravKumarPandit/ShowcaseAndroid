package com.application.idea.sourav.showcaseandroid.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;

public class OutlinedTextView extends android.support.v7.widget.AppCompatTextView
{
    /* ===========================================================
     * Constants
     * =========================================================== */
    private static final float OUTLINE_PROPORTION = 0.1f;

    /* ===========================================================
     * Members
     * =========================================================== */
    private final Paint mStrokePaint = new Paint();
    private final Rect mTextBounds = new Rect();

    private int mOutlineColor = Color.RED;

    /* ===========================================================
     * Constructors
     * =========================================================== */
    public OutlinedTextView(Context context) {
        super(context);
        this.setupPaint();
    }
    public OutlinedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setupPaint();
        this.setupAttributes(context, attrs);
    }
    public OutlinedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setupPaint();
        this.setupAttributes(context, attrs);
    }

    /* ===========================================================
     * Overrides
     * =========================================================== */
    @Override
    protected void onDraw(Canvas canvas) {
        // Get the text to print
        final float textSize = super.getTextSize();
        final String text = super.getText().toString();

        // setup stroke
        mStrokePaint.setColor(mOutlineColor);
        mStrokePaint.setStrokeWidth(textSize * OUTLINE_PROPORTION);
        mStrokePaint.setTextSize(textSize);
        mStrokePaint.setFlags(super.getPaintFlags());
        mStrokePaint.setTypeface(super.getTypeface());

        // Figure out the drawing coordinates
        super.getPaint().getTextBounds(text, 0, text.length(), mTextBounds);

        // draw everything
        canvas.drawText(text,
                super.getWidth() * 0.5f, (super.getHeight() + mTextBounds.height()) * 0.5f,
                mStrokePaint);
        super.onDraw(canvas);
    }

    /* ===========================================================
     * Private/Protected Methods
     * =========================================================== */
    private final void setupPaint() {
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setTextAlign(Paint.Align.CENTER);
    }
    private final void setupAttributes(Context context, AttributeSet attrs) {
//        final TypedArray array = context.obtainStyledAttributes(attrs,
//                R.styleable.OutlinedTextView);
//        mOutlineColor = array.getColor(
//                R.styleable.OutlinedTextView_outlineColor, 0x00000000);
//        array.recycle();

        // Force this text label to be centered
        super.setGravity(Gravity.CENTER_HORIZONTAL);
    }
    public int getOutlineColor()
    {
        return mOutlineColor;
    }

    public void setOutlineColor(int mOutlineColor)
    {
        this.mOutlineColor = mOutlineColor;
        invalidate();
    }


}