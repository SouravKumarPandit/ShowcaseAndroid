package com.application.idea.sourav.showcaseandroid.utils.CalendarView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Date;

@SuppressWarnings("ALL")
public class CalenderItemView extends View {
    private static final String TAG = "CalenderItemView";

    public CalenderItemView(Context context) {
        this(context, null);
    }

    public CalenderItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalenderItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int year, month;
    private int itemWidth;

    private int itemHeight =0;
    private final int rows = 7;
    private final int cols = 6;
    private int preSelectDate = -1;
    private int selectDate = -1;
    private final int[][] dates = new int[cols][rows];

    //头部字体颜色
    private int headerTextColor;
    //头部字体大小
    private float headerTextSize;
    //头部画笔
    private Paint headerPaint;

    //日期字体颜色
    private int dateTextColor;
    //日期字体大小
    private float dateTextSize;
    //日期画笔
    private Paint datePaint;

    //背景色
    private int backColor;
    //选中背景色
    private int selectBackColor;
    //选中字体颜色
    private int selectTextColor;
    //选中字体大小
    private float selectTextSize;
    //选中项画笔
    private Paint selectItemPaint;

    private void init() {
        initAttrs();
        initTools();
    }

    private void initAttrs() {
        headerTextColor = Color.parseColor("#666666");
        headerTextSize = 10;

        dateTextColor = Color.parseColor("#333333");
        dateTextSize = 12;

        backColor = getDrawingCacheBackgroundColor();
        selectBackColor = Color.parseColor("#dcbc94");
        selectTextColor = Color.WHITE;
        selectTextSize = dateTextSize;

        CalenderBean calenderBean = CalenderUtil.getCalender(new Date());
        this.year = calenderBean.getYear();
        this.month = calenderBean.getMonth();
        setDate(calenderBean.getYear(), calenderBean.getMonth());
    }

    private void initTools() {
        //初始化头部画笔
        headerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        headerPaint.setColor(headerTextColor);
        headerPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        headerPaint.setTextSize(sp2px(headerTextSize));

        //初始化具体日期画笔
        datePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        datePaint.setColor(dateTextColor);
        datePaint.setTextSize(sp2px(dateTextSize));

        //初始化选中画笔
        selectItemPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectItemPaint.setColor(selectBackColor);
        selectItemPaint.setStrokeWidth(selectTextColor);
        selectItemPaint.setTextSize(sp2px(selectTextSize-1));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY));
        itemWidth = width / rows;
        Rect bounds = new Rect();
        datePaint.getTextBounds("00", 0, 2, bounds);
        itemHeight= (int) (bounds.height()*2);
        setMeasuredDimension(itemWidth * rows, (int) (itemHeight +datePaint.getTextSize()/2)* (cols +1));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHeader(canvas);
        drawDayItem(canvas);
    }

    /**
     * 头部绘制
     *
     * @param canvas
     */
    private void drawHeader(Canvas canvas) {
        drawOneText(canvas, "S", itemWidth / 2,                 itemWidth / 3, headerPaint);
        drawOneText(canvas, "M", itemWidth / 2 + itemWidth,     itemWidth / 3, headerPaint);
        drawOneText(canvas, "T", itemWidth / 2 + itemWidth * 2, itemWidth / 3, headerPaint);
        drawOneText(canvas, "W", itemWidth / 2 + itemWidth * 3, itemWidth / 3, headerPaint);
        drawOneText(canvas, "T", itemWidth / 2 + itemWidth * 4, itemWidth / 3, headerPaint);
        drawOneText(canvas, "F", itemWidth / 2 + itemWidth * 5, itemWidth / 3, headerPaint);
        drawOneText(canvas, "S", itemWidth / 2 + itemWidth * 6, itemWidth / 3, headerPaint);
    }

    /**
     * 日期绘制
     *
     * @param canvas
     */
    private void drawDayItem(Canvas canvas) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (dates[i][j] == preSelectDate) {
                    drawSelectItem(canvas, String.valueOf(dates[i][j]), itemWidth * j + itemWidth / 2, itemHeight * (i + 1) + itemHeight / 2, false);
                }
                if (dates[i][j] == selectDate) {
                    drawSelectItem(canvas, String.valueOf(dates[i][j]), itemWidth * j + itemWidth / 2, itemHeight * (i + 1) + itemHeight / 2, true);
                } else if (dates[i][j] > 0) {
                    drawOneText(canvas, String.valueOf(dates[i][j]), itemWidth * j + itemWidth / 2, itemHeight * (i + 1) + itemHeight / 2, datePaint);
                }
            }
        }
    }

    /**
     * 绘制选中项
     *
     * @param canvas
     * @param text
     * @param centerX
     * @param centerY
     * @param isSelect
     */
    private void drawSelectItem(Canvas canvas, String text, int centerX, int centerY, boolean isSelect) {
        selectItemPaint.setColor(isSelect ? selectBackColor : backColor);
        //TODO 界面要求选中半径
//        canvas.drawCircle(centerX, centerY, Math.min(itemWidth, itemHeight) / 2, selectItemPaint);
        canvas.drawCircle(centerX, centerY, sp2px(11.5f), selectItemPaint);
        if (isSelect) {
            selectItemPaint.setColor(selectTextColor);
            drawOneText(canvas, text, centerX, centerY, selectItemPaint);
        }
    }

    /**
     * 绘制一个文字
     *
     * @param canvas
     * @param text
     * @param centerX
     * @param centerY
     * @param paint
     */
    private void drawOneText(Canvas canvas, String text, int centerX, int centerY, Paint paint) {
        float textWidth = paint.measureText(text);
        canvas.drawText(text, centerX - textWidth / 2, centerY - (paint.descent() + paint.ascent()) / 2, paint);
    }

    private PointF startPoint;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startPoint = new PointF(event.getX(), event.getY());
//            Log.i(TAG,startPoint.toString());
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();
//            Log.i(TAG,x+","+y);
            if (Math.abs(startPoint.x - x) < 20 && Math.abs(startPoint.y - y) < 20) {
                for (int i = 0; i < cols; i++) {
                    for (int j = 0; j < rows; j++) {
                        if (dates[i][j] > 0 && x > itemWidth * j && x < itemWidth * (j + 1) && y > itemHeight * (i + 1) && y < itemHeight * (i + 2)) {
                            preSelectDate = selectDate;
                            selectDate = dates[i][j];
                            if (onItemSelectListener != null) {
                                onItemSelectListener.onSelect(CalenderUtil.getCalender(year, month, selectDate));
                            }
                            invalidate();
                        }
                    }
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置当前页年月
     *
     * @param year
     * @param month
     */
    public void setDate(int year, int month) {
        this.year = year;
        this.month = month;
        initDates(year, month);
        invalidate();
    }

    /**
     * 初始化年月
     *
     * @param year
     * @param month
     */
    private void initDates(int year, int month) {
        CalenderBean calenderBean = CalenderUtil.getCalender(year, month);
        int week = calenderBean.getWeek();
        int wholeMonth = calenderBean.getWholeMonth();
        int num = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if ((i == 0 && j == week) || num > 0) {
                    num++;
                }
                dates[i][j] = num;
                if (num > wholeMonth) {
                    dates[i][j] = 0;
                }
            }
        }
        setSelectDate(-1);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    /**
     * 获取当前页年月
     *
     * @return
     */
    public CalenderBean getCalenderBean() {
        return CalenderUtil.getCalender(year, month);
    }

    /**
     * 获取选中日
     *
     * @return
     */
    public int getSelectDate() {
        return selectDate;
    }

    /**
     * 设置选中日
     *
     * @param selectDate
     */
    public void setSelectDate(int selectDate) {
        this.selectDate = selectDate;
        invalidate();
    }

    private int sp2px(float sp) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (sp * scale + 0.5f);
    }

    public int getHeaderTextColor() {
        return headerTextColor;
    }

    public void setHeaderTextColor(int headerTextColor) {
        this.headerTextColor = headerTextColor;
        initTools();
        invalidate();
    }

    public float getHeaderTextSize() {
        return headerTextSize;
    }

    public void setHeaderTextSize(float headerTextSize) {
        this.headerTextSize = headerTextSize;
        initTools();
        invalidate();
    }

    public int getDateTextColor() {
        return dateTextColor;
    }

    public void setDateTextColor(int dateTextColor) {
        this.dateTextColor = dateTextColor;
        initTools();
        invalidate();
    }

    public float getDateTextSize() {
        return dateTextSize;
    }

    public void setDateTextSize(float dateTextSize) {
        this.dateTextSize = dateTextSize;
        initTools();
        invalidate();
    }

    public int getSelectBackColor() {
        return selectBackColor;
    }

    public void setSelectBackColor(int selectBackColor) {
        this.selectBackColor = selectBackColor;
        initTools();
        invalidate();
    }

    public int getSelectTextColor() {
        return selectTextColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = selectTextColor;
        initTools();
        invalidate();
    }

    public float getSelectTextSize() {
        return selectTextSize;
    }

    public void setSelectTextSize(float selectTextSize) {
        this.selectTextSize = selectTextSize;
        initTools();
        invalidate();
    }

    private OnItemSelectListener onItemSelectListener;

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    public interface OnItemSelectListener {
        void onSelect(CalenderBean calenderBean);
    }
}