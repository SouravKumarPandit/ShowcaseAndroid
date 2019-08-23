package com.application.idea.sourav.showcaseandroid.ui.components.inlineSelector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;


public class SelectorHorizontalScrollView extends HorizontalScrollView implements SingleLineSelectorView.OnValuePickedlistener {

    public ValuePickedListener valuePickedlistener;
    private int xEndPoint;
    private int xStartPoint;
    private boolean scrollMagnifying;


    private boolean scrollToSelection;
    private Handler timerHandler;
    private Runnable scrollRunable;

    public SelectorHorizontalScrollView(Context context) {
        super(context);
        initScrollView(context);

    }

    public SelectorHorizontalScrollView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        initScrollView(context);
    }

    public SelectorHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScrollView(context);
    }

    private void initScrollView(Context context) {
        scrollMagnifying = true;
        scrollToSelection = true;
        timerHandler = new Handler();
        scrollRunable = new Runnable() {
            @Override
            public void run() {
                smoothScrollTo(xStartPoint, 0);

            }
        };
        LayoutParams relativeParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(relativeParam);
        setFillViewport(true);
        setHorizontalScrollBarEnabled(false);
        SingleLineSelectorView selectorView = new SingleLineSelectorView(context);
        addView(selectorView);

    }

    public void setValueSelectionArray(String[] valueSelectionArray) {
        getSingleLineSelector().setValueSelectionArray(valueSelectionArray);

    }

    public void setSelectionListener(ValuePickedListener selectionListener) {
        getSingleLineSelector().setOnValueClicked(this);
        valuePickedlistener = selectionListener;
    }

    public SingleLineSelectorView getSingleLineSelector() {
        return (SingleLineSelectorView) getChildAt(0);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

//
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (scrollMagnifying) {
                setScaleX(1.13f);
                setScaleY(1.13f);
            }
            if (scrollToSelection)
                timerHandler.removeCallbacks(scrollRunable);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (scrollMagnifying) {
                setScaleX(1.0f);
                setScaleY(1.0f);
            }
            if (scrollToSelection)
                timerHandler.postDelayed(scrollRunable, 3000);

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (scrollToSelection)
                timerHandler.removeCallbacks(scrollRunable);
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        timerHandler = null;
        scrollRunable = null;
    }


    @Override
    public void onValueSelectionChange(String selectedValue, int iSelectedPosition, int requestId) {
        if (valuePickedlistener != null) {
            valuePickedlistener.onValueSelection(selectedValue, iSelectedPosition, requestId);
        }
    }

    @Override
    public void onNothingSelected() {
        if (valuePickedlistener != null) {
            valuePickedlistener.onNothingSelected();
        }
    }

    public void setScrollMagnifying(boolean scrollMagnifying) {
        this.scrollMagnifying = scrollMagnifying;
    }

    public void setScrollToSelection(boolean scrollToSelection) {
        this.scrollToSelection = scrollToSelection;
        if (!scrollToSelection) {
            timerHandler.removeCallbacks(scrollRunable);
        }
    }

    @Override
    public void onSelectionPosition(int xStartPoint, int xEndPoint) {
        this.xStartPoint = xStartPoint;
        this.xEndPoint = xEndPoint;
    }

    public interface ValuePickedListener {

        void onValueSelection(String selectedValue, int iSelectedPosition, int requestId);

        void onNothingSelected();

    }
}