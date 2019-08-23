package com.application.idea.sourav.showcaseandroid.utils.CalendarView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.application.idea.sourav.showcaseandroid.R;

import java.util.Date;

public class CalenderPagerView extends ViewPager {
    private static final String TAG = "CalenderView";

    private CalenderBean[] calenderBeans;
    private CalenderItemView[] calenderItemViews;
    //头部字体颜色
    private int headerTextColor;
    //头部字体大小
    private float headerTextSize;
    //日期字体颜色
    private int dateTextColor;
    //日期字体大小
    private float dateTextSize;
    //选中背景色
    private int selectBackColor;
    //选中字体颜色
    private int selectTextColor;
    //选中字体大小
    private float selectTextSize;

    public CalenderPagerView(Context context) {
        this(context, null);
    }

    public CalenderPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        initAttrs(attrs);
        initCalenderView();
        setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                position = position % 3;
                CalenderItemView calenderItemView = calenderItemViews[position];
                calenderItemView.setDate(calenderBeans[position].getYear(), calenderBeans[position].getMonth());
                calenderItemView.setSelectDate(-1);
                if (calenderItemView.getParent() != null) {
                    container.removeView(calenderItemView);
                }
                container.addView(calenderItemView);
                return calenderItemView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
            }
        });
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPositionCalender(calenderBeans[position % 3], position);
                getAdapter().notifyDataSetChanged();
                if (onCalenderPageChangeListener != null) {
                    onCalenderPageChangeListener.onChange(getCurrentCalender());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        int currentPosition = Integer.MAX_VALUE / 2;
        if (currentPosition % 3 == 2) {
            currentPosition++;
        } else if (currentPosition % 3 == 1) {
            currentPosition--;
        }
        setCurrentItem(currentPosition);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CalenderPagerView);
        headerTextColor = typedArray.getColor(R.styleable.CalenderPagerView_headerTextColorCV, Color.parseColor("#ffffff"));
        headerTextSize = typedArray.getFloat(R.styleable.CalenderPagerView_headerTextSizeCV, 10);
        dateTextColor = typedArray.getColor(R.styleable.CalenderPagerView_dateTextColorCV, Color.parseColor("#f9f9ff"));
        dateTextSize = typedArray.getFloat(R.styleable.CalenderPagerView_dateTextSizeCV, 12);
        selectBackColor = typedArray.getColor(R.styleable.CalenderPagerView_selectBackColorCV, 0x40FFFFFF);
        selectTextColor = typedArray.getColor(R.styleable.CalenderPagerView_selectBackColorCV, Color.WHITE);
        selectTextSize = typedArray.getFloat(R.styleable.CalenderPagerView_dateTextSizeCV, dateTextSize);
        typedArray.recycle();
    }

    private void initCalenderView() {
        CalenderBean calenderBean = CalenderUtil.getCalender(new Date());
        calenderBean = CalenderUtil.getCalender(calenderBean.getYear(), calenderBean.getMonth());
        calenderBeans = new CalenderBean[]{calenderBean, CalenderUtil.getNextCalender(calenderBean.getYear(), calenderBean.getMonth()), CalenderUtil.getPreCalender(calenderBean.getYear(), calenderBean.getMonth())};
        calenderItemViews = new CalenderItemView[calenderBeans.length];
        for (int i = 0; i < calenderItemViews.length; i++) {
            CalenderItemView calenderItemView = calenderItemViews[i] == null ? new CalenderItemView(getContext()) : calenderItemViews[i];
            calenderItemView.setHeaderTextColor(headerTextColor);
            calenderItemView.setHeaderTextSize(headerTextSize);
            calenderItemView.setDateTextColor(dateTextColor);
            calenderItemView.setDateTextSize(dateTextSize);
            calenderItemView.setSelectBackColor(selectBackColor);
            calenderItemView.setSelectTextColor(selectTextColor);
            calenderItemView.setSelectTextSize(selectTextSize);
            calenderItemView.setOnItemSelectListener(new CalenderItemView.OnItemSelectListener() {
                @Override
                public void onSelect(CalenderBean calenderBean) {
                    if (onItemSelectListener != null) {
                        onItemSelectListener.onSelect(calenderBean);
                    }
                }
            });
            calenderItemViews[i] = calenderItemView;
        }
        /*for (int i = 0; i < list.size(); i++) {
            CalenderBean calenderBean = viewList.get(i).getCalenderBean();
            viewList.get(i).setDate(calenderBean.getYear(), calenderBean.getMonth());
        }*/
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = 0;
        if (getAdapter() != null) {
            CalenderItemView calenderItemView = (CalenderItemView) getChildAt(0);
            if (calenderItemView != null) {
                height = calenderItemView.getMeasuredHeight();
            }
        }
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    /**
     * 设置日历数组数据
     *
     * @param calenderBean
     * @param position
     */
    private void setPositionCalender(CalenderBean calenderBean, int position) {
        position = position % 3;
        calenderBeans[position] = calenderBean;
        calenderBeans[(position + 1) % 3] = CalenderUtil.getNextCalender(calenderBean.getYear(), calenderBean.getMonth());
        calenderBeans[(position - 1 + 3) % 3] = CalenderUtil.getPreCalender(calenderBean.getYear(), calenderBean.getMonth());
        for (int i = 0; i < calenderBeans.length; i++) {
            calenderItemViews[i].setDate(calenderBeans[i].getYear(), calenderBeans[i].getMonth());
            calenderItemViews[i].setSelectDate(-1);
        }
    }

    /**
     * 获取当前显示日历
     *
     * @return
     */
    private CalenderBean getCurrentCalender() {
        return calenderBeans[getCurrentItem() % 3];
    }

    /**
     * 设置当前显示日历
     *
     * @param calenderBean
     */
    private void setCurrentCalender(CalenderBean calenderBean) {
        calenderBean = CalenderUtil.getCalender(calenderBean.getYear(), calenderBean.getMonth());
        int result = calenderBean.compareTo(CalenderUtil.getCalender(getCurrentCalender().getYear(), getCurrentCalender().getMonth()));
        if (result != 0) {
            calenderBeans[(getCurrentItem() + result) % 3] = calenderBean;
            setPositionCalender(calenderBean, getCurrentItem() + result);
            setCurrentItem(getCurrentItem() + result);
        }
    }

    /**
     * 获取选中日期
     *
     * @return
     */
    public CalenderBean getSelectDate() {
        CalenderBean calenderBean = calenderBeans[getCurrentItem() % 3];
        calenderBean.setDay(calenderItemViews[getCurrentItem() % 3].getSelectDate());
        if (calenderBean.getDay() == -1) {
            return null;
        }
        return calenderBean;
    }

    public void setSelectDate(CalenderBean calender) {
        setCurrentCalender(calender);
        calenderItemViews[getCurrentItem() % 3].setSelectDate(calender.getDay());
    }

    private CalenderItemView.OnItemSelectListener onItemSelectListener;

    public void setOnItemSelectListener(CalenderItemView.OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    private OnCalenderPageChangeListener onCalenderPageChangeListener;

    public void setOnCalenderPageChangeListener(OnCalenderPageChangeListener onCalenderPageChangeListener) {
        this.onCalenderPageChangeListener = onCalenderPageChangeListener;
    }

    public interface OnCalenderPageChangeListener {
        void onChange(CalenderBean calenderBean);
    }
}