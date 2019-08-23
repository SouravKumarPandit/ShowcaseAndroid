package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.utils.CircleImageView;
import com.application.idea.sourav.showcaseandroid.utils.LabelTextView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

public class NestCoordinateActivity extends BaseActivity {

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

        AppBarLayout appBarLayout = new AppBarLayout(this);
        CoordinatorLayout.LayoutParams appBarLayoutParam = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayout.setLayoutParams(appBarLayoutParam);

        CollapsingToolbarLayout collapsingToolbarLayout = new CollapsingToolbarLayout(this);
        AppBarLayout.LayoutParams collapsParam = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPixel(180));
        collapsParam.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        collapsingToolbarLayout.setLayoutParams(collapsParam);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.sw5_colorPrimaryDark));
        collapsingToolbarLayout.setExpandedTitleMarginBottom(Utils.dpToPixel(95));
        collapsingToolbarLayout.setExpandedTitleMarginStart(Utils.dpToPixel(48));
        collapsingToolbarLayout.setExpandedTitleMarginEnd(Utils.dpToPixel(64));
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsingTextAppearance_Inverse);
//        collapsingToolbarLayout.setExpandedTitleTextColor(Utils.getColorStateListDrawable(Color.BLACK,Color.WHITE));
        collapsingToolbarLayout.setTitle("Nicholas Jenkins");
//        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT); // transperent color = #00000000
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE); //Color of your title

//        final Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/Larke_Regular.ttf");
//        collapsingToolbarLayout.setCollapsedTitleTypeface(tf);
//        collapsingToolbarLayout.setExpandedTitleTypeface(tf);

        ShadowRectLayout collapsImage=new ShadowRectLayout(this);

        collapsImage.setResDrawable(R.drawable.umbrala_image);
        collapsImage.setShadowRadius(0);
        collapsImage.setOffSetX(0);
        collapsImage.setOffSetY(0);
        collapsImage.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        collapsImage.setImgGradientColor1(0x8028307b);
        collapsImage.setImgGradientColor2(0xB50D37A6);

        Toolbar toolbar=new Toolbar(this);
        CollapsingToolbarLayout.LayoutParams toolbarParam=new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPixel(55));
        toolbarParam.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
        toolbar.setLayoutParams(toolbarParam);
        toolbar.setBackground(null);
        Drawable backArrow=Utils.getIcon(this,R.drawable.ic_arrow_back_black_24dp,Color.WHITE);
        toolbar.setNavigationIcon(backArrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
//        toolbar.getContext().setTheme(R.style.ToolBarWithNavigationBack);
        NestedScrollView nestedScrollView=new NestedScrollView(this);
        CoordinatorLayout.LayoutParams nestedScrollViewParam=new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        nestedScrollViewParam.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        AppBarLayout.ScrollingViewBehavior behavior =(AppBarLayout.ScrollingViewBehavior) nestedScrollViewParam.getBehavior();
        assert behavior != null;
        behavior.setOverlayTop(Utils.dpToPixel(70)); // Note: in pixels important
        nestedScrollView.setLayoutParams(nestedScrollViewParam);
        nestedScrollView.setVerticalScrollBarEnabled(false);
        nestedScrollView.setHorizontalScrollBarEnabled(false);

//        nestedScrollView.setScrollBarSize(0);
        nestedScrollView.setFillViewport(true);

        LinearLayout contentLayout=new LinearLayout(this);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        contentLayout.requestLayout();

        RelativeLayout relativeLayout=new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ShadowRectLayout shadowRectLayout=new ShadowRectLayout(this);
        RelativeLayout.LayoutParams shadowRectParam=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        shadowRectParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        shadowRectLayout.setId(R.id.shadow_profilimg);
        shadowRectParam.setMarginStart(Utils.dpToPixel(30));
        shadowRectLayout.setLayoutParams(shadowRectParam);
        shadowRectLayout.setOffSetX(0);
        shadowRectLayout.setOffSetY(0);
        shadowRectLayout.setShadowRadius(40);
        shadowRectLayout.setShadowColor(0x9C666666);

        shadowRectLayout.setRoundCornerRadius(i3dp);
        ImageView userImage=new ImageView(this);
        userImage.setLayoutParams(new ShadowRectLayout.LayoutParams(Utils.dpToPixel(90),Utils.dpToPixel(90)));
        userImage.setPadding(i5dp,i5dp,i5dp,i5dp);
        userImage.setImageResource(R.drawable.profile_1);
        userImage.setId(R.id.user_image);
        shadowRectLayout.addView(userImage);

        LabelTextView tvUserName=new LabelTextView(this);
        RelativeLayout.LayoutParams userNameParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        userNameParam.addRule(RelativeLayout.BELOW,R.id.shadow_profilimg);
        userNameParam.setMarginStart(Utils.dpToPixel(55));
        tvUserName.setLayoutParams(userNameParam);
        tvUserName.setText("Nicholas Jenkins");
        tvUserName.setId(R.id.user_name5);
        tvUserName.setTextColor(Color.BLACK);
        tvUserName.setTypeface(Typeface.DEFAULT_BOLD);
        tvUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
        tvUserName.setMandatory(true,null,getResources().getColor(R.color.base_color_sw3),Utils.dpToPixel(5),LabelTextView.LEFT_TOP);

        TextView tvDesignation=new TextView(this);
        RelativeLayout.LayoutParams tvDesigParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvDesignation.setId(R.id.user_designation);
        tvDesigParam.addRule(RelativeLayout.BELOW,R.id.user_name5);
        tvDesigParam.addRule(RelativeLayout.ALIGN_START,R.id.user_name5);
        tvDesignation.setPadding(0,i3dp,0,i5dp);
        tvDesignation.setLayoutParams(tvDesigParam);
        tvDesignation.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);

        tvDesignation.setTextColor(getResources().getColor(R.color.gray_500));
        tvDesignation.setText("Lead Product Designer");



        TextView tvDescription=new TextView(this);
        RelativeLayout.LayoutParams tvDescriptionwParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvDescriptionwParam.addRule(RelativeLayout.BELOW,R.id.user_designation);
        tvDescriptionwParam.addRule(RelativeLayout.ALIGN_START,R.id.user_name5);
        tvDescription.setPadding(0,i3dp,0,i10dp);
        tvDescription.setId(R.id.user_description);
        tvDescription.setLayoutParams(tvDescriptionwParam);
        tvDesignation.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        tvDescription.setTextColor(getResources().getColor(R.color.gray_700));
//        tvDescription.setTypeface(Typeface.DEFAULT_BOLD);
        tvDescription.setText("A lot of things you see as a child remain with you and me. you spend a lot of your life trying to recpture the experience.A lot of things you see as a child remain with ,you spend a lot of your life trying to recpture the experience.A lot of things you see as a child remain with ,you spend a lot of your life trying to recpture the experience.A lot of things you see as a child remain with ");
        Utils.makeTextViewResizable(tvDescription,3,"More",true);


        GradientDrawable buttonBackground1=new GradientDrawable();
        buttonBackground1.setCornerRadius(i10dp);
        buttonBackground1.setStroke(2,getResources().getColor(R.color.sw5_baseColor));
        buttonBackground1.setColor(getResources().getColor(R.color.sw5_baseColor));
        GradientDrawable buttonBackground2=new GradientDrawable();
        buttonBackground2.setCornerRadius(i10dp);
        buttonBackground2.setStroke(2,getResources().getColor(R.color.gray_400));
        buttonBackground2.setColor(getResources().getColor(R.color.white_300));

        Button tvCallButton=new Button(this);
        RelativeLayout.LayoutParams callbutParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvCallButton.setId(R.id.call_button);
        tvCallButton.setMinHeight(0);
        tvCallButton.setMinWidth(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            tvCallButton.setElevation(0);
            tvCallButton.setStateListAnimator(null);
        }

        tvCallButton.setMinimumHeight(0);
        tvCallButton.setMinimumWidth(0);
        callbutParam.addRule(RelativeLayout.BELOW,R.id.user_description);
        callbutParam.addRule(RelativeLayout.ALIGN_START,R.id.user_name5);
        tvCallButton.setLayoutParams(callbutParam);
        tvCallButton.setText("Call");
        tvCallButton.setPadding(i20dp,i10dp,i20dp,i10dp);
        tvCallButton.setTextColor(Utils.getColorStateListDrawable(Color.WHITE,getResources().getColor(R.color.sw5_baseColor)));
        Drawable callIcon=Utils.getIcon(this,R.drawable.ic_call_pressed_24dp,getResources().getColor(R.color.sw5_baseColor));
        Drawable callIcon1=Utils.getIcon(this,R.drawable.ic_call_unpressed_24dp,Color.WHITE);
        tvCallButton.setCompoundDrawablesRelativeWithIntrinsicBounds(Utils.getDrawableListState(callIcon1,callIcon),null,null,null);
        tvCallButton.setCompoundDrawablePadding(i5dp);
        tvCallButton.setOnClickListener(new ActivityTouchListener());
        tvCallButton.setBackground(Utils.getDrawableListState(buttonBackground1,buttonBackground2));



        Button tvEmailButton=new Button(this);
        RelativeLayout.LayoutParams tvEmailParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvEmailButton.setMinHeight(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            tvEmailButton.setStateListAnimator(null);
            tvEmailButton.setElevation(0);
        }
        tvEmailButton.setMinWidth(0);
        tvEmailButton.setMinimumHeight(0);
        tvEmailButton.setMinimumWidth(0);
        tvEmailParam.addRule(RelativeLayout.BELOW,R.id.user_description);
        tvEmailParam.addRule(RelativeLayout.END_OF,R.id.call_button);
        tvEmailParam.setMarginStart(i15dp);
//
        tvEmailButton.setLayoutParams(tvEmailParam);
        tvEmailButton.setText("Email");
        tvEmailButton.setPadding(i20dp,i10dp,i20dp,i10dp);
        tvEmailButton.setTextColor(Utils.getColorStateListDrawable(getResources().getColor(R.color.sw5_baseColor),Color.WHITE));
        Drawable emailIcon=Utils.getIcon(this,R.drawable.ic_email_pressed_24dp,getResources().getColor(R.color.sw5_baseColor));
        Drawable emailIcon1=Utils.getIcon(this,R.drawable.ic_email_unpressed_24dp,Color.WHITE);
        tvEmailButton.setCompoundDrawablesRelativeWithIntrinsicBounds(Utils.getDrawableListState(emailIcon,emailIcon1),null,null,null);
        tvEmailButton.setId(R.id.email_button);
        tvEmailButton.setCompoundDrawablePadding(i5dp);
        tvEmailButton.setOnClickListener(new ActivityTouchListener());
        tvEmailButton.setBackground(Utils.getDrawableListState(buttonBackground2,buttonBackground1));
        relativeLayout.addView(shadowRectLayout);
        relativeLayout.addView(tvUserName);
        relativeLayout.addView(tvDesignation);
        relativeLayout.addView(tvDescription);
        relativeLayout.addView(tvCallButton);
        relativeLayout.addView(tvEmailButton);
        relativeLayout.addView(getUserDetails());

        contentLayout.addView(relativeLayout);
        contentLayout.addView(Utils.getMarginalDivider(this,getResources().getColor(R.color.gray_300),1,0,0,i10dp,0));
        contentLayout.addView(getInfoLayout(R.drawable.ic_vpn_lock_black_24dp,"nichojens.com"));
        contentLayout.addView(Utils.getMarginalDivider(this,getResources().getColor(R.color.gray_300),1,Utils.dpToPixel(55),0,0,0));
        contentLayout.addView(getInfoLayout(R.drawable.ic_email_pressed_24dp,"hello@nicholas.com"));
        contentLayout.addView(Utils.getMarginalDivider(this,getResources().getColor(R.color.gray_300),1,Utils.dpToPixel(55),0,0,0));
        contentLayout.addView(getInfoLayout(R.drawable.ic_call_pressed_24dp,"+38(064) 242 8513"));
        contentLayout.addView(Utils.getMarginalDivider(this,getResources().getColor(R.color.gray_300),1,0,0,0,0));

        contentLayout.addView(getAddressLayout(R.drawable.ic_my_location_black_24dp,"718 Bryant St \nSan Francisco, CA,\nU S A"));

        nestedScrollView.addView(contentLayout);
        collapsingToolbarLayout.addView(collapsImage);
        collapsingToolbarLayout.addView(toolbar);
        appBarLayout.addView(collapsingToolbarLayout);
        coordinatorLayout.addView(appBarLayout);
        coordinatorLayout.addView(nestedScrollView);

        return coordinatorLayout;
    }

    public LinearLayout getCollapseContent() {

        Context clContext=this;
        LinearLayout lLayoutCollapsing = new LinearLayout(clContext);
        CollapsingToolbarLayout.LayoutParams clCollapsingLayoutParams1=new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clCollapsingLayoutParams1.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX);
        clCollapsingLayoutParams1.setParallaxMultiplier(0.5f);
        lLayoutCollapsing.setLayoutParams(clCollapsingLayoutParams1);
        lLayoutCollapsing.setMinimumHeight(Utils.dpToPixel(200));
        lLayoutCollapsing.setPadding(0, Utils.dpToPixel(45), 0, 0);
        lLayoutCollapsing.setOrientation(LinearLayout.VERTICAL);
        lLayoutCollapsing.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout clLinearLayout = new LinearLayout(clContext);
        clLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        clLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        clLinearLayout.setGravity(Gravity.CENTER_VERTICAL);
        clLinearLayout.setPadding(i5dp, i20dp, i5dp, i20dp);


        CircleImageView circleImageView = new CircleImageView(clContext);
//        circleImageView.setLayoutParams(new LinearLayout.LayoutParams(CLViewUtil.dpToPx(110), CLViewUtil.dpToPx(110)));
        if (!Utils.isTablet(clContext))
            circleImageView.setLayoutParams(new LinearLayout.LayoutParams(0,Utils.dpToPixel(80), 3));
        else
            circleImageView.setLayoutParams(new LinearLayout.LayoutParams(0, Utils.dpToPixel(100), 4));
        circleImageView.setImageResource(R.drawable.profile_4);


        LinearLayout rightLinearLayout = new LinearLayout(clContext);
        rightLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 8));
        rightLinearLayout.setOrientation(LinearLayout.VERTICAL);
        rightLinearLayout.setPadding(i20dp, 0, 0, 0);

        TextView tvUserName = new TextView(clContext);
        tvUserName.setTypeface(Typeface.DEFAULT_BOLD);
        tvUserName.setTextColor(Color.WHITE);
        tvUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tvUserName.setText("Dividas Gorkan");


        TextView tvUserEmail = new TextView(clContext);
//        tvUserEmail.setTypeface(Typeface.DEFAULT_BOLD);
        tvUserEmail.setTextColor(getResources().getColor(R.color.gray_100));
        tvUserEmail.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvUserEmail.setText("dividasgorkan.21@gmail.com");


        TextView tvLastSeen = new TextView(clContext);
        tvLastSeen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvLastSeen.setTypeface(Typeface.DEFAULT_BOLD);
        tvLastSeen.setTextColor(getResources().getColor(R.color.gray_100));
        tvLastSeen.setGravity(Gravity.END);
        tvLastSeen.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvLastSeen.setText("Last Login - 2 hours ago");

        rightLinearLayout.addView(tvUserName);
        rightLinearLayout.addView(tvUserEmail);


        clLinearLayout.addView(circleImageView);
        clLinearLayout.addView(rightLinearLayout);
        lLayoutCollapsing.addView(clLinearLayout);
        lLayoutCollapsing.addView(tvLastSeen);
        return lLayoutCollapsing;
    }

    private LinearLayout getAddressLayout(int iconRes, String addressText)
    {
        LinearLayout addressLayout=new LinearLayout(this);
        addressLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addressLayout.setMinimumHeight(Utils.dpToPixel(200));
        addressLayout.setPadding(0,i10dp,0,i10dp);
        addressLayout.setBackgroundResource(R.drawable.address_background);
        Drawable iconDrawable=Utils.getIcon(this,iconRes,getResources().getColor(R.color.sw5_colorPrimary));

        ImageView iconImage=new ImageView(this);
        iconImage.setImageDrawable(iconDrawable);
        iconImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iconImage.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(55), Utils.dpToPixel(32)));


        TextView tvInfoTextView=new TextView(this);
        tvInfoTextView.setText(addressText);
        tvInfoTextView.setTextColor(Color.DKGRAY);
        tvInfoTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

        addressLayout.addView(iconImage);
        addressLayout.addView(tvInfoTextView);
        return addressLayout;
    }

    public LinearLayout getUserDetails()
    {
        LinearLayout userDetailslayout=new LinearLayout(this);
        RelativeLayout.LayoutParams detailLayoutParam=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        detailLayoutParam.addRule(RelativeLayout.BELOW,R.id.call_button);
        userDetailslayout.setLayoutParams(detailLayoutParam);
        return userDetailslayout;
    }

    public LinearLayout getInfoLayout(int iconRes,String infoText)
    {
        LinearLayout infoLayout=new LinearLayout(this);
        infoLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        infoLayout.setPadding(0,i10dp,0,i10dp);
        infoLayout.setGravity(Gravity.CENTER_VERTICAL);

        Drawable iconDrawable=Utils.getIcon(this,iconRes,getResources().getColor(R.color.sw5_colorPrimary));

        ImageView iconImage=new ImageView(this);
        infoLayout.addView(iconImage);
        iconImage.setImageDrawable(iconDrawable);
        iconImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iconImage.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(55), Utils.dpToPixel(32)));

        TextView tvInfoTextView=new TextView(this);
        tvInfoTextView.setText(infoText);
        tvInfoTextView.setTypeface(Typeface.DEFAULT_BOLD);
        tvInfoTextView.setTextColor(Color.DKGRAY);
        tvInfoTextView.setSingleLine(true);
        tvInfoTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);




        infoLayout.addView(tvInfoTextView);
        return infoLayout;
    }


    private class ActivityTouchListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {

        }
    }
}
