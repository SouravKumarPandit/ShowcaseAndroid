package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.KeyValueDTO;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.GridDashboardAdapter;
import com.application.idea.sourav.showcaseandroid.utils.Utils;
import com.application.idea.sourav.showcaseandroid.utils.SpacesItemDecoration;

import java.util.ArrayList;

public class GridCoordinateActivity extends BaseActivity {
    private int i10dp;
    private int i20dp;
    private int i3dp;
    private int i5dp;
    private int i15dp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);

        setContentView(coordinatorView());

    }

    public CoordinatorLayout coordinatorView()
    {
        CoordinatorLayout coordinatorLayout = new CoordinatorLayout(this);
        coordinatorLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.sw6_backGround));

        AppBarLayout appBarLayout = new AppBarLayout(this);
        CoordinatorLayout.LayoutParams appBarLayoutParam = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayout.setLayoutParams(appBarLayoutParam);

        CollapsingToolbarLayout collapsingToolbarLayout = new CollapsingToolbarLayout(this);
        AppBarLayout.LayoutParams collapsParam = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPixel(250));
        collapsParam.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        collapsingToolbarLayout.setLayoutParams(collapsParam);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.sw5_colorPrimaryDark));
        collapsingToolbarLayout.setExpandedTitleMarginBottom(Utils.dpToPixel(95));
        collapsingToolbarLayout.setExpandedTitleMarginStart(Utils.dpToPixel(48));
        collapsingToolbarLayout.setExpandedTitleMarginEnd(Utils.dpToPixel(64));
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsingTextAppearance_Inverse);
        collapsingToolbarLayout.setExpandedTitleTextColor(Utils.getColorStateListDrawable(Color.BLACK, Color.WHITE));
        collapsingToolbarLayout.setTitle("Your Activity");
        //        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT); // transperent color = #00000000
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE); //Color of your title

//        final Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/Larke_Regular.ttf");
//        collapsingToolbarLayout.setCollapsedTitleTypeface(tf);
//        collapsingToolbarLayout.setExpandedTitleTypeface(tf);

        ShadowRectLayout collapsImage = new ShadowRectLayout(this);

        collapsImage.setResDrawable(R.drawable.umbrala_image);
        collapsImage.setShadowRadius(0);
        collapsImage.setOffSetX(0);
        collapsImage.setOffSetY(0);
        collapsImage.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //        collapsImage.setImgGradientColor1(0x8028307b);
        //        collapsImage.setImgGradientColor2(0xB50D37A6);

        Toolbar toolbar = new Toolbar(this);
        CollapsingToolbarLayout.LayoutParams toolbarParam = new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPixel(55));
        toolbarParam.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
        toolbar.setLayoutParams(toolbarParam);
        toolbar.setBackground(null);
        Drawable backArrow = Utils.getIcon(this, R.drawable.ic_arrow_back_black_24dp, Color.WHITE);
        toolbar.setNavigationIcon(backArrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        //        NestedScrollView nestedScrollView = new NestedScrollView(this);
        CoordinatorLayout.LayoutParams nestedScrollViewParam = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        nestedScrollViewParam.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        AppBarLayout.ScrollingViewBehavior behavior = (AppBarLayout.ScrollingViewBehavior) nestedScrollViewParam.getBehavior();
        assert behavior != null;
        behavior.setOverlayTop(Utils.dpToPixel(200)); // Note: in pixels important
        ArrayList<KeyValueDTO> keyValueDTOS =new ArrayList<>();
        keyValueDTOS.add(new KeyValueDTO("Goals",    R.drawable.agoal));
        keyValueDTOS.add(new KeyValueDTO("Finance",  R.drawable.acost));
        keyValueDTOS.add(new KeyValueDTO("Health",   R.drawable.asolution));
        keyValueDTOS.add(new KeyValueDTO("Comfort",  R.drawable.aplanning));
        keyValueDTOS.add(new KeyValueDTO("Education",R.drawable.abusiness_plan));
        keyValueDTOS.add(new KeyValueDTO("Profile",  R.drawable.aproduction));
        keyValueDTOS.add(new KeyValueDTO("Growth",   R.drawable.agrowth));
        keyValueDTOS.add(new KeyValueDTO("Debt",   R.drawable.adebt));
        keyValueDTOS.add(new KeyValueDTO("Idea",   R.drawable.aidea));
        keyValueDTOS.add(new KeyValueDTO("Location",   R.drawable.alocation));
        keyValueDTOS.add(new KeyValueDTO("Teamwork",   R.drawable.anegotiation));
        final GridDashboardAdapter dashboardAdapter = new GridDashboardAdapter(this, keyValueDTOS);
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.requestLayout();


        //        LinearLayout.LayoutParams recyclerViewParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        recyclerView.setLayoutParams(nestedScrollViewParam);
        recyclerView.setPadding(i5dp, 0, i5dp, 1);
        GridLayoutManager layoutManager;
        if (Utils.isTablet(this))
            layoutManager = new GridLayoutManager(this, 3);
        else
            layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position)
            {
                switch (dashboardAdapter.getItemViewType(position))
                {
                    case GridDashboardAdapter.TYPE_FIRST_ITEM:
                        if (Utils.isTablet(GridCoordinateActivity.this))
                            return 3;
                        else
                            return 2;
                    case GridDashboardAdapter.TYPE_ITEM:
                        return 1;
                    default:
                        return -1;
                }
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(Utils.dpToPixel(i3dp)));
        recyclerView.setAdapter(dashboardAdapter);

        collapsingToolbarLayout.addView(collapsImage);
        collapsingToolbarLayout.addView(toolbar);
        appBarLayout.addView(collapsingToolbarLayout);
        coordinatorLayout.addView(appBarLayout);
        coordinatorLayout.addView(recyclerView);

        return coordinatorLayout;
    }



    private class ActivityTouchListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {

        }
    }
}
