package com.application.idea.sourav.showcaseandroid.ui.components.inlineSelector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class SingleLineSelectorView extends View {
    public int iSelectedPosition = -1;
    int requestId = -1;
    private boolean isMandatory;
    private boolean isEmpety;
    private float textSize = 16f;
    private int paddingTopBottom;
    String selectedValue;
    private int selectedColor;
    private int selectedTextColor;
    private int itemTextColor;
    protected String[] emptyValue ;


    private int wordPadding;
    Rect bounds;

    public SingleLineSelectorView(Context context) {
        this(context, null);
    }

    public SingleLineSelectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleLineSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //for touch calculation
    private PointF startPoint;
    private int itemHeight;

    protected int[] valTextWidthArray;

    private String[] valSelectionArray;
    private Paint selectionBackgroud;
    private TextPaint selectItemPaint;


    private void init(Context context) {
        paddingTopBottom = sp2px(textSize);
        wordPadding = paddingTopBottom;
        selectedColor = 0xff786ed5;
        selectedTextColor = Color.WHITE;
        itemTextColor = Color.GRAY;
        emptyValue = new String[]{"( empty )"};
        initTools(context);
    }

    private void initTools(Context context) {

        selectionBackgroud = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectionBackgroud.setColor(selectedColor);

        selectItemPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        selectItemPaint.setTextSize(sp2px(textSize));
        bounds = new Rect();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemHeight = sp2px(textSize) + paddingTopBottom;
        if (valSelectionArray != null && valSelectionArray.length > 1) {

            int itemWidth = 0;
            for (int i = 0; i < valSelectionArray.length; i++) {
                selectItemPaint.getTextBounds(valSelectionArray[i], 0, valSelectionArray[i].length(), bounds);
                int text_width = bounds.width();
//                int text_height = bounds.height();
                valTextWidthArray[i] = text_width + wordPadding;
                itemWidth = itemWidth + text_width + wordPadding;
            }
            setMeasuredDimension(itemWidth + wordPadding, itemHeight);
        }
        if (valSelectionArray == null || valSelectionArray.length == 1) {
            if (valSelectionArray == null) {
                selectedTextColor = Color.LTGRAY;
                isEmpety=true;
                iSelectedPosition=-1;
                setValueSelectionArray(emptyValue);
            }
            int itemWidth = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY));
            selectItemPaint.getTextBounds(valSelectionArray[0], 0, valSelectionArray[0].length(), bounds);
            int text_width = bounds.width();
            valTextWidthArray[0] = text_width + wordPadding;
            setMeasuredDimension(itemWidth, itemHeight);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int itempWidth = wordPadding;
        if (valSelectionArray != null && valSelectionArray.length > 1)
            for (int j = 0; j < valSelectionArray.length; j++) {
                if (iSelectedPosition == j) {
                    selectItemPaint.setColor(selectedTextColor);
                    selectItemPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    if (selectedColor != 0)
                        canvas.drawRect(itempWidth - wordPadding / 2, 0, itempWidth + valTextWidthArray[j] - wordPadding / 2, getHeight(), selectionBackgroud);
                    canvas.drawText(valSelectionArray[j], itempWidth, itemHeight / 2 + selectItemPaint.getTextSize() / 3, selectItemPaint);
                } else {
                    selectItemPaint.setColor(itemTextColor);
                    selectItemPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    canvas.drawText(valSelectionArray[j], itempWidth, itemHeight / 2 + selectItemPaint.getTextSize() / 3, selectItemPaint);
                }
                itempWidth = itempWidth + valTextWidthArray[j];

            }
        else if (valSelectionArray != null && valSelectionArray.length == 1) {
            selectItemPaint.getTextBounds(valSelectionArray[0], 0, valSelectionArray[0].length(), bounds);
            if (iSelectedPosition == 0&&!isEmpety) {
                selectItemPaint.setColor(selectedTextColor);
                selectItemPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                canvas.drawRect(wordPadding / 2, 0, getWidth() - wordPadding / 2, getHeight(), selectionBackgroud);
            } else {
                selectItemPaint.setColor(itemTextColor);
                selectItemPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
            canvas.drawText(valSelectionArray[0], getWidth() / 2 - bounds.width() / 2, itemHeight / 2 + selectItemPaint.getTextSize() / 3, selectItemPaint);

        }

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startPoint = new PointF(event.getX(), event.getY());
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();
            if (valSelectionArray != null && Math.abs(startPoint.x - x) < 20 && Math.abs(startPoint.y - y) < 20) {
                int xStart = 0;
                int xEnd = 0;
                if (valSelectionArray.length == 1&&!isEmpety) {
                    if (!isMandatory && iSelectedPosition == 0) {
                        iSelectedPosition = -1;
                    } else
                        iSelectedPosition = 0;
                    if (onValueClicked != null) {
                        if (iSelectedPosition >= 0)
                            onValueClicked.onValueSelectionChange(valSelectionArray[0], iSelectedPosition, requestId);
                        else {
                            onValueClicked.onNothingSelected();
                        }
                    }
                    invalidate();

                } else
                    for (int j = 0; j < valSelectionArray.length; j++) {
                        xEnd = xEnd + valTextWidthArray[j];
                        if (x > xStart && x < xEnd && y > 0 && y < itemHeight) {
                            if (!isMandatory && iSelectedPosition == j) {
                                iSelectedPosition = -1;
                            } else
                                iSelectedPosition = j;
                            if (onValueClicked != null) {
                                if (iSelectedPosition >= 0){

                                    onValueClicked.onValueSelectionChange(valSelectionArray[j], iSelectedPosition, requestId);
                                    onValueClicked.onSelectionPosition(xStart,xEnd);
                                }
                                else {
                                    onValueClicked.onNothingSelected();
                                }
                            }
                            selectedValue = valSelectionArray[j];
                            invalidate();
                        }
                        xStart = xEnd;

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


    public String[] getSizeSelectionArray() {
        return valSelectionArray;
    }


    public void setValueSelectionArray(String[] valSelectionArray) {
        this.valSelectionArray = valSelectionArray;
        valTextWidthArray = new int[valSelectionArray.length];
        postInvalidate();
    }

    public void setiSelectedPosition(int iSelectedPosition) {
        this.iSelectedPosition = iSelectedPosition;
        postInvalidate();
    }


    public void setIsMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
        if (isMandatory)
            iSelectedPosition = 0;
        else iSelectedPosition = -1;
        postInvalidate();
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        selectionBackgroud.setColor(selectedColor);
        postInvalidate();
    }

    public void setSelectedTextColor(int selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
        postInvalidate();
    }

    public void setItemTextColor(int itemTextColor) {
        this.itemTextColor = itemTextColor;
        postInvalidate();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        selectItemPaint.setTextSize(sp2px(textSize));
        invalidate();
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setPaddingTopBottom(int paddingTopBottom) {
        this.paddingTopBottom = dpToPx(paddingTopBottom);
        postInvalidate();
    }

    public void setWordPadding(int wordPadding) {
        this.wordPadding = dpToPx(wordPadding);
        postInvalidate();
    }

    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    private OnValuePickedlistener onValueClicked;

    public void setOnValueClicked(OnValuePickedlistener onValueClicked) {
        this.onValueClicked = onValueClicked;
    }


    public interface OnValuePickedlistener {
        void onValueSelectionChange(String selectedValue, int iSelectedPosition, int requestId);

        void onNothingSelected();

        void onSelectionPosition(int xStartPoint, int xEndPoint);


    }

    protected static int spToPx(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    protected int sp2px(float sp) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (sp * scale);
    }
}