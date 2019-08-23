package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.SectionPagerAdapter;
import com.application.idea.sourav.showcaseandroid.ui.fragments.ProfileAchFragment;
import com.application.idea.sourav.showcaseandroid.utils.Utils;


public class IconTabActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingStatusBarTransparent();
        setContentView(iconTabView());

        setupTabIcons();
        TabLayout tabLayout = findViewById(R.id.about_tablayout);
        ViewPager aboutViewpager = findViewById(R.id.about_viewpager);
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(aboutViewpager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(IconTabActivity.this, R.color.tabSelsw8);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(IconTabActivity.this, R.color.white_300);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
    }

    private void settingStatusBarTransparent() {

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.BLACK);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //setStatusBarTranslucent(true);
        }
    }

    private View iconTabView() {
        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams coordinatorLayoutMain_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutMode(ViewGroup.LAYOUT_MODE_CLIP_BOUNDS);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.about_background));
        linearLayout.setLayoutParams(coordinatorLayoutMain_param);

        AppBarLayout abTabtool = new AppBarLayout(this);
        LinearLayout.LayoutParams ab_tabtool_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        abTabtool.setLayoutParams(ab_tabtool_param);
        abTabtool.setPadding(0, Utils.dpToPixel(12), 0, 0);
        abTabtool.setBackground(getResources().getDrawable(R.drawable.gradiant_actionbar2));

        TabLayout tablayout = new TabLayout(this);
        LinearLayout.LayoutParams tab_layout_param = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tablayout.setMinimumWidth(0);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);  // scorllable tab
        tablayout.setTabGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM);
        tablayout.setSelectedTabIndicatorColor(Color.BLACK);
        tablayout.setTabIndicatorFullWidth(false);
        tablayout.setId(R.id.about_tablayout);
        tablayout.setLayoutParams(tab_layout_param);
        tablayout.setHapticFeedbackEnabled(true);
        tablayout.setMinimumHeight(0);
        tablayout.setMinimumWidth(0);
        tablayout.setBackground(null);


        Toolbar toolbar = new Toolbar(this);
        LinearLayout.LayoutParams toolbar_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbar.setBackground(null);
        toolbar.setLayoutParams(toolbar_param);
        TextView toolTitle = new TextView(this);
        toolTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        toolTitle.setText("PROFILE");
        toolTitle.setMaxLines(1);
        toolTitle.setTextColor(getResources().getColor(R.color.white_300));
        toolTitle.setCompoundDrawablePadding(Utils.dpToPixel(8));
        toolTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        toolbar.addView(toolTitle);
//        toolbar.setTitle("PROFILE");

        ViewPager viewPager = new ViewPager(this);
        tablayout.setupWithViewPager(viewPager);
        viewPager.setId(R.id.about_viewpager);
        setupViewPager(viewPager);
        LinearLayout.LayoutParams viewPager_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(viewPager_param);

        abTabtool.addView(toolbar);
        abTabtool.addView(tablayout);
        linearLayout.addView(abTabtool);
        linearLayout.addView(viewPager);


        return linearLayout;
    }

    private void setupTabIcons() {
        TabLayout tabLayout = findViewById(R.id.about_tablayout);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            int iconId = -1;
            switch (i) {
                case 0:
                    iconId = R.drawable.ic_web_home;
                    break;
                case 1:
                    iconId = R.drawable.ic_shopping_cart;
                    break;
                case 2:
                    iconId = R.drawable.ic_presentation;
                    break;
                case 3:
                    iconId = R.drawable.ic_calendar;
                    break;
                case 4:
                    iconId = R.drawable.ic_user_profile;
                    break;
                default:
                    iconId = R.drawable.ic_close_24dp;
                    break;
            }
            tabLayout.getTabAt(i).setIcon(iconId);

        }
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tabSelsw8));
    }


    private void setupViewPager(ViewPager viewPager) {

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(IconTabActivity.this.getSupportFragmentManager());
        sectionPagerAdapter.addFrag(new ProfileAchFragment(this), "About");
        sectionPagerAdapter.addFrag(new ProfileAchFragment(this), "Portfolio");
        sectionPagerAdapter.addFrag(new ProfileAchFragment(this), "Lab");
        sectionPagerAdapter.addFrag(new ProfileAchFragment(this), "Contact");
        sectionPagerAdapter.addFrag(new ProfileAchFragment(this), "Contact");
        viewPager.setAdapter(sectionPagerAdapter);
    }
}
