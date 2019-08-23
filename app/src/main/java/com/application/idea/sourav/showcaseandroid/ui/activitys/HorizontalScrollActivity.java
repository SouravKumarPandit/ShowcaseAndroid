package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.CardSliderItemDTO;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.CardInfo0Adapter;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.CardInfo1Adapter;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.CardInfo2Adapter;
import com.application.idea.sourav.showcaseandroid.utils.Utils;
import com.application.idea.sourav.showcaseandroid.utils.StateBackgroundDrawable;

import java.util.ArrayList;


public class HorizontalScrollActivity extends BaseActivity {

    ArrayList<CardSliderItemDTO> dtoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setUpData();
        setContentView(getDrawerView());
        Utils.setLightStatusBar(this);


    }

    public View getDrawerView()
    {
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
        linearLayout.setBackgroundColor(getResources().getColor(R.color.white_300));
        linearLayout.setPadding(0, Utils.dpToPixel(50), 0, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        String[] naviationOption = new String[]{"Home", "Profile", "Series", "Downloads", "Recent ", "History", "About  Me", "Exit"};
        for (int i = 0; i < naviationOption.length; i++)
        {

            linearLayout.addView(getNavigationContent(naviationOption[i]));
        }


        navigationViewList.addView(linearLayout);
        navigationViewList.setLayoutParams(navigationViewList_param);
        navigationViewList.setNavigationItemSelectedListener(new HomeActivityListener());

        AppBarLayout appbar = new AppBarLayout(this);
        ViewGroup.LayoutParams appbar_param = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        appbar.setLayoutParams(appbar_param);
        Toolbar toolbar = new Toolbar(this);
        LinearLayout.LayoutParams toolbar_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white_300));
        toolbar.setLayoutParams(toolbar_param);
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setContentInsetsAbsolute(0, 0);

        toolbar.addView(getToobarLayout());
        LinearLayout clLinearLayout = new LinearLayout(this);
        DrawerLayout.LayoutParams clLinearLayout_param = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        clLinearLayout.setOrientation(LinearLayout.VERTICAL);
        clLinearLayout.setPadding(0,Utils.dpToPixel(12),0,0);
        clLinearLayout.setGravity(Gravity.CENTER);
        clLinearLayout.setLayoutParams(clLinearLayout_param);


        clLinearLayout.addView(toolbar);
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(getResources().getColor(R.color.white_300));
        scrollView.addView(getContentLayout());
        clLinearLayout.addView(scrollView);

        drawerLayout.addView(clLinearLayout);
        drawerLayout.addView(navigationViewList);
        return drawerLayout;
    }

    public View getTopboxLinear()
    {
        LinearLayout topboxLinear = new LinearLayout(this);
        LinearLayout.LayoutParams topboxLinear_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 70);
        topboxLinear.setBackgroundColor(getResources().getColor(R.color.pure_white));
        topboxLinear.setOrientation(LinearLayout.HORIZONTAL);
        topboxLinear.setGravity(Gravity.CENTER_VERTICAL);
        //        topboxLinear.setPadding(20,20,20,20);
        topboxLinear.setLayoutParams(topboxLinear_param);


        TextView tv_NumberItem = new TextView(this);
        LinearLayout.LayoutParams tv_NumberItem_param = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 5));
        tv_NumberItem.setLayoutParams(tv_NumberItem_param);
        tv_NumberItem.setText("0 Items");
        tv_NumberItem.setPadding(50, 0, 0, 0);
        tv_NumberItem.setTextSize(16);
        tv_NumberItem.setGravity(Gravity.BOTTOM);
        tv_NumberItem.setTextColor(Color.WHITE);

        TextView tv_DoneText = new TextView(this);
        //        tv_DoneText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 2));
        LinearLayout.LayoutParams tv_DoneText_param = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 5));

        tv_DoneText.setText("Done");
        tv_DoneText.setPadding(0, 0, 50, 0);
        tv_DoneText.setTextSize(16);
        tv_DoneText.setGravity(Gravity.RIGHT);
        tv_DoneText.setTextColor(Color.WHITE);
        tv_DoneText.setLayoutParams(tv_DoneText_param);

        View view_split = new View(this);
        LinearLayout.LayoutParams view_param = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 7);
        view_split.setLayoutParams(view_param);

        topboxLinear.addView(tv_NumberItem);
        topboxLinear.addView(view_split);
        topboxLinear.addView(tv_DoneText);


        return topboxLinear;
    }


    private void setUpData()
    {

        dtoArrayList = new ArrayList<CardSliderItemDTO>();
        for (int i = 0; i <= 2; i++)
        {
            CardSliderItemDTO cardSliderItemDTO0 = new CardSliderItemDTO(0xb9f14646, 0xfff14646);
            CardSliderItemDTO cardSliderItemDTO1 = new CardSliderItemDTO(0xa854c3d4, 0xff54c3d4);
            CardSliderItemDTO cardSliderItemDTO2 = new CardSliderItemDTO(0xb964ca45, 0xff64ca45);
            CardSliderItemDTO cardSliderItemDTO3 = new CardSliderItemDTO(0xbd9941a1, 0xff9941a1);
            CardSliderItemDTO cardSliderItemDTO4 = new CardSliderItemDTO(0xc4bca93c, 0xffbca93c);
            CardSliderItemDTO cardSliderItemDTO5 = new CardSliderItemDTO(0xb75d4ab1, 0xff5d4ab1);
            CardSliderItemDTO cardSliderItemDTO6 = new CardSliderItemDTO(0xbf4991af, 0xff4991af);
            CardSliderItemDTO cardSliderItemDTO7 = new CardSliderItemDTO(0xbdd1894a, 0xffd1894a);
            CardSliderItemDTO cardSliderItemDTO8 = new CardSliderItemDTO(0xc13bab78, 0xff3bab78);
            dtoArrayList.add(cardSliderItemDTO0);
            dtoArrayList.add(cardSliderItemDTO1);
            dtoArrayList.add(cardSliderItemDTO2);
            dtoArrayList.add(cardSliderItemDTO3);
            dtoArrayList.add(cardSliderItemDTO4);
            dtoArrayList.add(cardSliderItemDTO5);
            dtoArrayList.add(cardSliderItemDTO6);
            dtoArrayList.add(cardSliderItemDTO7);
            dtoArrayList.add(cardSliderItemDTO8);

        }
    }

    public LinearLayout getContentLayout()
    {
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setBackgroundColor(getResources().getColor(R.color.white_300));
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        CardInfo0Adapter cardInfoAdapter0 = new CardInfo0Adapter(this, dtoArrayList);
        CardInfo1Adapter cardInfoAdapter1 = new CardInfo1Adapter(this, dtoArrayList);
        CardInfo2Adapter cardInfoAdapter2 = new CardInfo2Adapter(this, dtoArrayList);
        contentLayout.addView(setLabeledHeader(cardInfoAdapter0, "Series", R.id.series, R.drawable.ic_seriese, R.drawable.ic_right_arrow));
        contentLayout.addView(setLabeledHeader(cardInfoAdapter1, "Session", R.id.session, R.drawable.ic_search_black_24dp, R.drawable.ic_right_arrow));
        contentLayout.addView(setLabeledHeader(cardInfoAdapter2, "Feature Series", R.id.feature_series, R.drawable.ic_edit_black_24dp, R.drawable.ic_right_arrow));
        return contentLayout;
    }

    private LinearLayout setLabeledHeader(RecyclerView.Adapter adapter, String sLable, int lableId, int drawableLeft, int drawableRight)
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //        linearLayout.setPadding(0, 0, 0, 30);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        TextView headText = new TextView(this);
        headText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        headText.setGravity(Gravity.CENTER_VERTICAL);
        headText.setId(lableId);
        headText.setText(sLable);
        headText.setOnClickListener(new HomeActivityListener());
        headText.setCompoundDrawablePadding(Utils.dpToPixel(8));
        headText.setTextColor(getResources().getColor(R.color.text_color));
        headText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        headText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        headText.setPadding(Utils.dpToPixel(10), 0, Utils.dpToPixel(10), 0);
        headText.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(drawableLeft), null, getResources().getDrawable(drawableRight), null);


        RecyclerView recyclerView = new RecyclerView(this);

        LinearLayout.LayoutParams recyclerViewParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        recyclerViewParam.setMargins(0, 0, 0, 30);
        recyclerView.setLayoutParams(recyclerViewParam);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(adapter);

        linearLayout.addView(headText);
        linearLayout.addView(recyclerView);

        return linearLayout;


    }

    public LinearLayout getToobarLayout()
    {
        LinearLayout toobarLayout = new LinearLayout(this);
        toobarLayout.setGravity(Gravity.CENTER);
        toobarLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_hamburger);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ((DrawerLayout) findViewById(R.id.drawer_layout)).openDrawer(Gravity.START);
            }
        });
        imageView.setPadding(Utils.dpToPixel(10), Utils.dpToPixel(10), Utils.dpToPixel(10), Utils.dpToPixel(10));
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView headText = new TextView(this);
        //        headText.setPadding(dpToPixel(10f), dpToPixel(8f), dpToPixel(10f), dpToPixel(8f));
        headText.setPadding(0, 0, 18 * 2, 0);
        headText.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        headText.setGravity(Gravity.CENTER);
        headText.setText("Home");
        headText.setMaxLines(1);
        headText.setCompoundDrawablePadding(Utils.dpToPixel(8));
        headText.setTextColor(getResources().getColor(R.color.text_color));
        headText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        headText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        //        headText.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_hamburger), null, null, null);

        ImageView serchIcon = new ImageView(this);
        serchIcon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        serchIcon.setImageResource(R.drawable.ic_search_black_24dp);
        serchIcon.setPadding(Utils.dpToPixel(4), Utils.dpToPixel(4), Utils.dpToPixel(4), Utils.dpToPixel(4));


        toobarLayout.addView(imageView);
        toobarLayout.addView(headText);
        toobarLayout.addView(serchIcon);
        return toobarLayout;
    }

    public LinearLayout getNavigationContent(final String sLable)
    {

        final LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(0, 5, 0, 5);
        layout.setLayoutParams(layoutParam);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(HorizontalScrollActivity.this, "" + sLable, Toast.LENGTH_SHORT).show();
            }
        });
        layout.setBackground(Utils.getSelectorDrawable(0x9F9E9E9E));
        layout.setPadding(0, 20, 0, 20);
        layout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(this);
        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setText(sLable);
        textView.setAllCaps(true);
        textView.setTextColor(StateBackgroundDrawable.getColorStateListDrawable(0xFF938C8C,0xFFFFFFFF));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setLayoutParams(textParam);
        layout.addView(textView);

        return layout;
    }


    private class HomeActivityListener implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
        {
            return false;
        }

        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.series:
                    Intent activityIntent = new Intent(HorizontalScrollActivity.this, PofileActivity.class);
                    startActivity(activityIntent);
                    break;
                case R.id.feature_series:
                    Intent activityInt = new Intent(HorizontalScrollActivity.this, PofileActivity.class);
                    startActivity(activityInt);
                    break;
                case R.id.session:
                    Intent activityIn = new Intent(HorizontalScrollActivity.this, PofileActivity.class);
                    startActivity(activityIn);
                    break;

            }

        }
    }
}
