package com.application.idea.sourav.showcaseandroid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class LabelTextView extends TextView {
    private TextPaint labelPanit;
    private Rect lableBounds;
    private String sSymbol;
    private boolean isLabeled;
    private float symbolSize;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int RIGHT_TOP = 11;
    public static final int MID_TOP = 12;
    public static final int LEFT_TOP = 13;
    public static final int RIGHT_MID = 21;
    public static final int LEFT_MID = 23;
    public static final int RIGHT_BOTTOM = 31;
    public static final int BOTTOM_MID = 32;
    public static final int LEFT_BOTTOM = 33;

    public int getLabelAt() {
        return labelAt;
    }

    private int labelAt = LEFT_TOP;

    public int getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(@ColorInt int labelColor) {
        this.labelColor = labelColor;
        if (isLabeled) {
            labelPanit.setColor(labelColor);
        }
        postInvalidate();
    }

    private int labelColor;

    public LabelTextView(Context context) {
        this(context, 0, 0, false);
    }

    public LabelTextView(Context context, AttributeSet attrs) {
        this(context, 0, 0, false);
    }

    public LabelTextView(Context context, AttributeSet attrs, int defStyle) {
        this(context, 0, 0, false);
    }

    public LabelTextView(Context context, String sText) {
        this(context, 0, 0, false);
        this.setText(sText);
    }

    public LabelTextView(Context context, int iResId) {
        this(context, 0, iResId, false);
    }

    public LabelTextView(Context context, int iViewId, int iResId) {
        this(context, iViewId, iResId, false);
    }

    public LabelTextView(Context context, int iViewId, int iResId, boolean isHeading) {
        super(context);
        if (iViewId > 0) {
            this.setId(iViewId);
        }

        this.setTextProperties(context, iResId, isHeading);
        symbolSize = getTextSize();
    }

    private void setTextProperties(Context context, int iResId, boolean isHeading) {
        this.setGravity(16);
        this.setSingleLine();
        this.setEllipsize(TextUtils.TruncateAt.END);
        if (iResId > 0) {
            this.setText(iResId);
        }

        if (isHeading) {
            this.setTypeface((Typeface) null, 1);
        }

    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isLabeled) {

            switch (labelAt) {
                case RIGHT_TOP:
                    canvas.drawText(sSymbol, 0, lableBounds.height(), labelPanit);
                    break;
                case RIGHT_MID:
                    canvas.drawText(sSymbol, 0, (canvas.getHeight() + lableBounds.height()) / 2, labelPanit);
                    break;
                case RIGHT_BOTTOM:
                    canvas.drawText(sSymbol, 0, canvas.getHeight() * 0.9f, labelPanit);
                    break;
                case MID_TOP:
                    canvas.drawText(sSymbol, (canvas.getWidth() - lableBounds.width()) / 2, (float) (lableBounds.height()), labelPanit);
                    break;
                case LEFT_TOP:
                    canvas.drawText(sSymbol, canvas.getWidth() - lableBounds.width() - 2, (float) (lableBounds.height()), labelPanit);
                    break;
                case LEFT_MID:
                    canvas.drawText(sSymbol, canvas.getWidth() - lableBounds.width() - 2, (canvas.getHeight() + lableBounds.height())/2, labelPanit);
                    break;
                case LEFT_BOTTOM:
                    canvas.drawText(sSymbol, canvas.getWidth() - lableBounds.width() - 2, canvas.getHeight() * 0.9f, labelPanit);
                    break;
                case BOTTOM_MID:
                    canvas.drawText(sSymbol, (canvas.getWidth() - lableBounds.width()) / 2, canvas.getHeight() - lableBounds.height() / 3, labelPanit);
                    break;
                case RIGHT:
                    canvas.drawText(sSymbol, 0, lableBounds.height(), labelPanit);
                    break;
                case LEFT:
                    canvas.drawText(sSymbol, (canvas.getWidth() - lableBounds.width()) / 2, canvas.getHeight() - lableBounds.height() / 3, labelPanit);
                    break;

                default:
                    canvas.drawText(sSymbol, canvas.getWidth() - lableBounds.width() - 2, (float) (lableBounds.height()), labelPanit);


            }
        }
    }


    public boolean isLabeled() {
        return isLabeled;
    }

    /*use this method after the setting text size or else it will give default siZe of 16*/
    public void setMandatory(boolean mandatory, String sSymbol, @ColorInt int iColor) {
        setMandatory(mandatory, sSymbol, iColor, getTextSize() , LEFT_TOP);
    }

    public void setMandatory(boolean mandatory, String sSymbol, @ColorInt int iColor, int labelAt) {
        setMandatory(mandatory, sSymbol, iColor, getTextSize() , labelAt);
    }

    public void setMandatory(boolean mandatory, String sSymbol, @ColorInt int iLabelColor, float symbolSize, int labelAt) {
        this.isLabeled = mandatory;
        this.labelColor = iLabelColor;
        if (isLabeled) {
            labelPanit = new TextPaint();
            float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, symbolSize, getResources().getDisplayMetrics());
            this.symbolSize = symbolSize;
            labelPanit.setTextSize(textSize);
            labelPanit.setColor(iLabelColor);
            lableBounds = new Rect();
            if (sSymbol == null || sSymbol.equals("")) {
                sSymbol = "⬤";
            }
            labelPanit.getTextBounds(sSymbol, 0, sSymbol.length(), lableBounds);
            this.sSymbol = sSymbol;
            this.labelAt = labelAt;
            int textWidth = (int) (lableBounds.width() * 1.3f);
            int textHeight= lableBounds.height() ;
            int spacing=0;
            switch (labelAt) {
                case RIGHT_TOP:
                case RIGHT_MID:
                case RIGHT_BOTTOM:
                    setPadding(textWidth , spacing+textHeight, spacing, spacing);
                    break;
                case MID_TOP:
                    setPadding(spacing, (int) (lableBounds.height()*1.2f), spacing, spacing);
                    break;
                case BOTTOM_MID:
                    setPadding(spacing, spacing, spacing, (int) (lableBounds.height()*1.2f));
                    break;
                case LEFT_TOP:
                case LEFT_MID:
                case LEFT_BOTTOM:
                    setPadding(spacing, spacing, textWidth, spacing);
                    break;
                case RIGHT:
                case LEFT:
                    setPadding(spacing, (int) (lableBounds.height()*1.2f), spacing, spacing);
                    break;
                default:
                    setPadding(spacing, spacing, textWidth, spacing);


            }

        } else {
            lableBounds = null;
            labelPanit = null;
            this.sSymbol = null;
            return;
        }
        invalidate();
    }

    public float getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(float symbolSize) {
        this.symbolSize = symbolSize;
        if (isLabeled)
            setMandatory(true, sSymbol, labelPanit.getColor(), symbolSize, labelAt);
    }
}
