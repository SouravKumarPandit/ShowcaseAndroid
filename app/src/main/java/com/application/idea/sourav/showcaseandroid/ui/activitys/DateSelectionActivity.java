package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.ui.fragments.PendingTaskFragment;
import com.application.idea.sourav.showcaseandroid.ui.fragments.SocialPlanFragment;
import com.application.idea.sourav.showcaseandroid.utils.CalendarView.CalenderBean;
import com.application.idea.sourav.showcaseandroid.utils.CalendarView.CalenderItemView;
import com.application.idea.sourav.showcaseandroid.utils.CalendarView.CalenderPagerView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;
import com.application.idea.sourav.showcaseandroid.utils.StateBackgroundDrawable;

public class DateSelectionActivity extends BaseActivity {

    private int i3dp;
    private int i5dp;
    private int i10dp;
    private int i15dp;
    private int i20dp;
    private int i40dp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        Utils.setLightStatusBar(this);
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(getDrawerView());

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_framlayout, new PendingTaskFragment(this));
        ft.commit();
    }

    public DrawerLayout getDrawerView() {
        DrawerLayout drawerLayout = new DrawerLayout(this);
        drawerLayout.setId(R.id.drawer_layout);
        ViewGroup.LayoutParams drawerLayout_param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        drawerLayout.setFitsSystemWindows(true);
        //        drawerLayout.openDrawer(GravityCompat.START);
        drawerLayout.setLayoutParams(drawerLayout_param);

        NavigationView navigationViewList = new NavigationView(this);
        navigationViewList.setId(R.id.navigation_view);

        int[][] states = new int[][]{new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[]{Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};

        ColorStateList myList = new ColorStateList(states, colors);

        navigationViewList.setItemIconTintList(myList);
        DrawerLayout.LayoutParams navigationViewList_param = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        navigationViewList_param.gravity = Gravity.START;
        navigationViewList.setFitsSystemWindows(true);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setBackgroundColor(0xff545a63);
        linearLayout.setPadding(0, Utils.dpToPixel(50), 0, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        String[] navigationOption = new String[]{"Dashboard", "Profile", "Downloads", "Recent ", "History", "Exit"};
        for (int i = 0; i < navigationOption.length; i++) {

            linearLayout.addView(getNavigationContent(navigationOption[i]));
        }


        navigationViewList.addView(linearLayout);
        navigationViewList.setLayoutParams(navigationViewList_param);
//        navigationViewList.setNavigationItemSelectedListener(new DateSelctionListener());
        ShadowRectLayout rectLayout = new ShadowRectLayout(this);
        ViewGroup.LayoutParams appbar_param = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rectLayout.setLayoutParams(appbar_param);
        rectLayout.setShadowColor(0xff9d84c8);
//        rectLayout.setRoundCornerRadius(Utils.dpToPixel(45));
        rectLayout.setShadowLeft(false);
        rectLayout.setShadowRight(false);
        rectLayout.setShadowTop(false);
//        rectLayout.setShadowBottom(true);
        rectLayout.setShadowRadius(Utils.dpToPixel(15));
        rectLayout.setOffSetX(0);
        rectLayout.setOffSetY(Utils.dpToPixel(12));

        LinearLayout topHeadLayout = new LinearLayout(this);
        topHeadLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        topHeadLayout.setBackground(getResources().getDrawable(R.drawable.gradiant_layout));
        topHeadLayout.setOrientation(LinearLayout.VERTICAL);
        topHeadLayout.addView(getToolbarLayout());
        topHeadLayout.setPadding(0, i20dp + i5dp, 0, 0);
        LinearLayout calnderLayout = new LinearLayout(this);
        calnderLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        calnderLayout.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        calnderLayout.setId(R.id.calender_layout);
        calnderLayout.setOrientation(LinearLayout.HORIZONTAL);
        calnderLayout.setPadding(i10dp, i3dp, i10dp, i15dp);
        CalenderPagerView calendarView = new CalenderPagerView(this);
        calendarView.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(200), Utils.dpToPixel(180)));
        calendarView.setOnItemSelectListener(new DateSelctionListener());
        calnderLayout.addView(getDateSeletedView());
        calnderLayout.addView(calendarView);
        topHeadLayout.addView(calnderLayout);
        rectLayout.addView(topHeadLayout);
        LinearLayout clLinearLayout = new LinearLayout(this);
        DrawerLayout.LayoutParams clLinearLayout_param = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clLinearLayout.setOrientation(LinearLayout.VERTICAL);
        clLinearLayout.setGravity(Gravity.TOP);
        clLinearLayout.setLayoutParams(clLinearLayout_param);
        clLinearLayout.addView(rectLayout);


        NestedScrollView scrollView = new NestedScrollView(this);
        LinearLayout.LayoutParams scrollParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(scrollParam);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(getResources().getColor(R.color.white_300));
        scrollView.addView(getContentLayout());
        clLinearLayout.addView(scrollView);

        CoordinatorLayout coordinatorLayout = new CoordinatorLayout(this);
        coordinatorLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        coordinatorLayout.setFitsSystemWindows(true);
        coordinatorLayout.addView(clLinearLayout);

        ImageButton fbImageButton = new ImageButton(this);
        CoordinatorLayout.LayoutParams fbParam = new CoordinatorLayout.LayoutParams(Utils.dpToPixel(54), Utils.dpToPixel(54));
        fbParam.gravity = Gravity.BOTTOM | Gravity.END;
        fbImageButton.setLayoutParams(fbParam);
        fbParam.setMargins(0, 0, i20dp, i20dp);
        fbImageButton.setImageResource(R.drawable.ic_pencil_edit_button);
        fbImageButton.setBackground(getResources().getDrawable(R.drawable.fb_drawable_sw7));
        fbImageButton.setOnClickListener(new DateSelctionListener());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fbImageButton.setTranslationZ(Utils.dpToPixel(6));
//            fbImageButton.setElevation(6);
        }
        coordinatorLayout.addView(fbImageButton);
        drawerLayout.addView(navigationViewList);
        drawerLayout.addView(coordinatorLayout);
        return drawerLayout;
    }

    public LinearLayout getToolbarLayout() {
        LinearLayout toolbarLayout = new LinearLayout(this);
        toolbarLayout.setGravity(Gravity.CENTER_VERTICAL);
        toolbarLayout.setPadding(i5dp, i5dp, i10dp, i40dp);
        toolbarLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_menu_black_24dp);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DrawerLayout) findViewById(R.id.drawer_layout)).openDrawer(Gravity.START);
            }
        });
        imageView.setPadding(Utils.dpToPixel(5),Utils. dpToPixel(5),Utils. dpToPixel(5),Utils. dpToPixel(5));
        imageView.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(35), Utils.dpToPixel(35)));

        TextView headText = new TextView(this);
        headText.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        headText.setText("Social Plans");
        headText.setTextColor(getResources().getColor(R.color.white_300));
        headText.setPadding(i5dp,0,0,0);
        headText.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
//        headText.setTypeface(Typeface.DEFAULT_BOLD);

        ImageView ivIcon1 = new ImageView(this);
        ivIcon1.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(35), Utils.dpToPixel(35)));
        ivIcon1.setImageResource(R.drawable.ic_calendar);
        ivIcon1.setId(R.id.iv_calender);
        ivIcon1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ivIcon1.setBackground(getResources().getDrawable(R.drawable.focus_bottom_whiteline_true));
        ivIcon1.setOnClickListener(new DateSelctionListener());
        ImageView ivIcon2 = new ImageView(this);
        ivIcon2.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(35), Utils.dpToPixel(35)));
        ivIcon2.setImageResource(R.drawable.ic_message_sw);
        ivIcon2.setId(R.id.iv_message);
        ivIcon2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ivIcon2.setOnClickListener(new DateSelctionListener());

        toolbarLayout.addView(imageView);
        toolbarLayout.addView(headText);
        toolbarLayout.addView(ivIcon1);
        toolbarLayout.addView(ivIcon2);
        return toolbarLayout;
    }


    public LinearLayout getNavigationContent(final String slable) {

        final LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(0, 5, 0, 5);
        layout.setLayoutParams(layoutParam);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DateSelectionActivity.this, "" + slable, Toast.LENGTH_SHORT).show();
            }
        });
        layout.setBackground(Utils.getSelectorDrawable(0x8D97A7BF));
        layout.setPadding(0, 20, 0, 20);
        layout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(this);
        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setText(slable);
        textView.setAllCaps(true);
        textView.setTextColor(StateBackgroundDrawable.getColorStateListDrawable(0xFFFFFFFF, 0xFF938C8C));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setLayoutParams(textParam);
        layout.addView(textView);

        return layout;
    }

    public LinearLayout getDateSeletedView() {
        LinearLayout dateSeletedLayout = new LinearLayout(this);
        dateSeletedLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        dateSeletedLayout.setOrientation(LinearLayout.VERTICAL);
        dateSeletedLayout.setGravity(Gravity.CENTER);
        dateSeletedLayout.setPadding(i20dp, i20dp, i20dp, i20dp);
//        dateSeletedLayout.setPadding(i10dp,i15dp,i10dp,i10dp);
        LinearLayout ratioLinearLayout = new LinearLayout(this);
        if (Utils.isTablet(this))
            ratioLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(150),Utils.dpToPixel(150)));
        else
            ratioLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(90),Utils.dpToPixel(90)));
//        ratioLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ratioLinearLayout.setGravity(Gravity.CENTER);
        ratioLinearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView tvDate = new TextView(this);
        tvDate.setText("9");
        tvDate.setId(R.id.tv_date);
        tvDate.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        tvDate.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        GradientDrawable roundGradiantDrawable = new GradientDrawable();
        roundGradiantDrawable.setColor(0xffff71ca);
        roundGradiantDrawable.setShape(GradientDrawable.OVAL);
        tvDate.setGravity(Gravity.CENTER);
        tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//        tvDate.setTypeface(Typeface.DEFAULT_BOLD);
        tvDate.setAllCaps(true);
        tvDate.setTextColor(Color.WHITE);

        TextView tvMonth = new TextView(this);
        tvMonth.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvMonth.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvMonth.setText("September");
        tvMonth.setTextColor(Color.WHITE);
        tvMonth.setId(R.id.tv_months);


        TextView tvEventInfo = new TextView(this);
        tvEventInfo.setPadding(0, i10dp, 0, i20dp);
        tvEventInfo.setGravity(Gravity.CENTER);
        tvEventInfo.setTextColor(Color.WHITE);
        tvEventInfo.setTypeface(Typeface.DEFAULT_BOLD);
        tvEventInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvEventInfo.setText("No Events Today");


        ratioLinearLayout.setBackground(roundGradiantDrawable);
        ratioLinearLayout.addView(tvDate);
        ratioLinearLayout.addView(tvMonth);
        dateSeletedLayout.addView(ratioLinearLayout);
        dateSeletedLayout.addView(tvEventInfo);
        return dateSeletedLayout;
    }

    public LinearLayout getContentLayout() {
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentLayout.setId(R.id.content_framlayout);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setBackground(new ColorDrawable(0xFFF7F8FA));
        contentLayout.setPadding(i3dp, 0, i3dp, i40dp);

        return contentLayout;

    }
    private class DateSelctionListener implements CalenderItemView.OnItemSelectListener, View.OnClickListener {


        @Override
        public void onSelect(CalenderBean calenderBean) {
            ((TextView) findViewById(R.id.tv_date)).setText(calenderBean.getDay() + "");
            ((TextView) findViewById(R.id.tv_months)).setText(MonthsName.values()[calenderBean.getMonth() - 1] + "");
        }

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.iv_calender) {
                ((ImageView) findViewById(R.id.iv_message)).setBackground(null);
                if (view.getBackground() == null) {
                    ((ImageView) findViewById(R.id.iv_calender)).setBackground(getResources().getDrawable(R.drawable.focus_bottom_whiteline_true));
                    findViewById(R.id.calender_layout).setVisibility(View.VISIBLE);
                    // Begin the transaction
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
                    ft.replace(R.id.content_framlayout, new PendingTaskFragment(DateSelectionActivity.this));
                    ft.commit();
                }

            } else if (view.getId() == R.id.iv_message) {
                ((ImageView) findViewById(R.id.iv_calender)).setBackground(null);
                if (view.getBackground() == null) {
                    ((ImageView) findViewById(R.id.iv_message)).setBackground(getResources().getDrawable(R.drawable.focus_bottom_whiteline_true));
                    findViewById(R.id.calender_layout).setVisibility(View.GONE);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.exit_to_left, R.anim.enter_from_right);
                    ft.replace(R.id.content_framlayout, new SocialPlanFragment(DateSelectionActivity.this));
                    ft.commit();
                }
            }
        }
    }

    private enum MonthsName {
        January, February, March, April, May, June, July, August, September, October, November, December;
    }
}
