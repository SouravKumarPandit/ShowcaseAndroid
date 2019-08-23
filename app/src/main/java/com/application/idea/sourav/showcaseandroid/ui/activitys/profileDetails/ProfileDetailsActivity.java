package com.application.idea.sourav.showcaseandroid.ui.activitys.profileDetails;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.components.actionLayout.CLActionLayoutView;
import com.application.idea.sourav.showcaseandroid.ui.dialogs.CLActionDialog;
import com.application.idea.sourav.showcaseandroid.utils.CircleImageView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;


public class ProfileDetailsActivity extends BaseActivity implements CLActionDialog.OnActionPerformed {


    private int i10dp;
    private int i20dp;
    private int i3dp;
    private int i5dp;
    private int i15dp;
    private Context clContext;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clContext = this;
        i3dp =  Utils.dpToPx(3);
        i5dp =  Utils.dpToPx(5);
        i10dp = Utils.dpToPx(10);
        i15dp = Utils.dpToPx(15);
        i20dp = Utils.dpToPx(20);

        Intent intent = getIntent();


        FrameLayout FrameLayout = new FrameLayout(clContext);
        FrameLayout.setId(R.id.profile_frame_layout);
        FrameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        FrameLayout.addView(coordinatorView());
        setContentView(FrameLayout);
    }


    public CoordinatorLayout coordinatorView() {
        CoordinatorLayout coordinatorLayout = new CoordinatorLayout(clContext);
        coordinatorLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        AppBarLayout appBarLayout = new AppBarLayout(clContext);
        CoordinatorLayout.LayoutParams appBarLayoutParam = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        appBarLayout.setId(R.id.profile_appbarlayout);
        appBarLayout.setLayoutParams(appBarLayoutParam);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBarLayout.setStateListAnimator(null);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = new CollapsingToolbarLayout(clContext);
        AppBarLayout.LayoutParams collapsParam = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        collapsParam.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        collapsingToolbarLayout.setLayoutParams(collapsParam);
        collapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        collapsingToolbarLayout.setTitleEnabled(false);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Sourav Kr .p");
        CollapsingToolbarLayout.LayoutParams toolbarParam = new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPx(55));
        toolbarParam.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
        toolbar.setLayoutParams(toolbarParam);
        toolbar.inflateMenu(R.menu.menu_scrolling);

        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(getResources().getColor(R.color.gray_900));
        toolbar.setSubtitle("Sourav Kumar Pandit");
        toolbar.setId(R.id.profile_home_toolbar);
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.gray_900));

        Drawable backArrow = Utils.getIcon(clContext, R.drawable.ic_arrow_back_black_24dp, Color.WHITE);
        toolbar.setNavigationIcon(backArrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Utils.hideKeyboard(ProfileDetailsActivity.this);
                ProfileDetailsActivity.this.finish();
            }
        });
//        toolbar.setOnMenuItemClickListener(new CLUserProfileListener());
        NestedScrollView nestedScrollView = new NestedScrollView(clContext);
        CoordinatorLayout.LayoutParams nestedScrollViewParam = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        nestedScrollViewParam.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        nestedScrollView.setLayoutParams(nestedScrollViewParam);
        nestedScrollView.setVerticalScrollBarEnabled(false);
        nestedScrollView.setHorizontalScrollBarEnabled(false);
        nestedScrollView.setFillViewport(true);
        nestedScrollView.addView(getDetailsContent());
        collapsingToolbarLayout.addView(getCollapseContent());
        collapsingToolbarLayout.addView(toolbar);
        appBarLayout.addView(collapsingToolbarLayout);
        coordinatorLayout.addView(appBarLayout);
        coordinatorLayout.addView(nestedScrollView);

        return coordinatorLayout;
    }


    public LinearLayout getDetailsContent() {
        LinearLayout contentLayout = new LinearLayout(clContext);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setId(R.id.profile_details_layout);
        contentLayout.setPadding(0, 0, 0, i20dp);
        LinearLayout actionLinearLayout = new LinearLayout(clContext);
        actionLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        actionLinearLayout.setMinimumHeight(Utils.dpToPx(55));
        actionLinearLayout.setId(R.id.profile_action_layout);
        actionLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        actionLinearLayout.setBackground(Utils.getStorkBottomDrawable(Color.LTGRAY,Utils.dpToPx(1)));

//        actionLinearLayout.setPadding(0,i10dp,0,i10dp);
        LinearLayout contactInfoLinearLayout = new LinearLayout(clContext);
        contactInfoLinearLayout.setOrientation(LinearLayout.VERTICAL);
        contactInfoLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        contactInfoLinearLayout.setMinimumHeight(Utils.dpToPx(60));
        contactInfoLinearLayout.setId(R.id.profile_contact_info_layout);

        int iUserType=0;
        addActionsForProfile(actionLinearLayout, iUserType);
        addDetailsForProfile(contactInfoLinearLayout, iUserType);
        contentLayout.addView(actionLinearLayout);
        contentLayout.addView(contactInfoLinearLayout);

        return contentLayout;
    }

    private void addDetailsForProfile(LinearLayout contactInfoLinearLayout, int iUserType) {
        int i16dp = Utils.dpToPx(12);
        Drawable phoneDrawable = Utils.getIcon(this,  R.drawable.ic_call_pressed_24dp, Color.GRAY);
        Drawable emailDrawable = Utils.getIcon(this,  R.drawable.ic_email_pressed_24dp, Color.GRAY);
        Drawable AddressDrawable = Utils.getIcon(this, R.drawable.ic_my_location_black_24dp, Color.GRAY);

        contactInfoLinearLayout.addView(getActionLayoutView(clContext, phoneDrawable, "+91 7216 5421 45", "Phone", IAConstants.IActions.CALL_USER, R.id.profile_phone));
        contactInfoLinearLayout.addView(getDivider());

        contactInfoLinearLayout.addView(getActionLayoutView(clContext, emailDrawable, " someemail@gmail.com", "Email", IAConstants.IActions.EMAIL_USER, R.id.profile_email));
        contactInfoLinearLayout.addView(getDivider());

        contactInfoLinearLayout.addView(getActionLayoutView(clContext, AddressDrawable, " India", "Address",    IAConstants.IActions.LOCATE_USER, R.id.profile_address));
        contactInfoLinearLayout.addView(getDivider());

    }

    private CLActionLayoutView getActionLayoutView(Context clContext, Drawable activeDrawable, String sLable, String sInfo, short markAsActive, int iLayoutId) {
        CLActionLayoutView clActionLayoutView = new CLActionLayoutView(clContext, activeDrawable, sLable, sInfo, markAsActive, true, null);
        clActionLayoutView.getIconImage().setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPx(25), Utils.dpToPx(25)));
        clActionLayoutView.setId(iLayoutId);
        return clActionLayoutView;
    }

    private void addActionsForProfile(LinearLayout actionLinearLayout, int iUserType) {
        int i30dp = Utils.dpToPx(30);

        actionLinearLayout.addView(getLableActionIcon(Utils.getIcon(this, R.drawable.ic_call_pressed_24dp, Color.GRAY), "Call", R.id.edit_user));
        actionLinearLayout.addView(getLableActionIcon(Utils.getIcon(this, R.drawable.ic_chat_bubble, Color.GRAY),     "Chat", R.id.mark_as_active));
        actionLinearLayout.addView(getLableActionIcon(Utils.getIcon(this, R.drawable.ic_email_pressed_24dp, Color.GRAY), "Email", R.id.disconnect_users));
        actionLinearLayout.addView(getLableActionIcon(Utils.getIcon(this, R.drawable.ic_garbage_2, Color.GRAY),   "Delete", R.id.profile_reset_pwd));

//        actionLinearLayout.setBackground(Utils.getLineDrawableListState(Utils.dpToPx(1), getResources().getColor(R.color.gray_300), getResources().getColor(R.color.gray_300)));


    }

    /*for action icon*/
    private LinearLayout getLableActionIcon(Drawable actionDrawable, String actionLable, int iActionId) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        linearLayout.setId(iActionId);
//        LinearLayout.setOnClickListener(new CLUserProfileListener());
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackground(Utils.getSelectorDrawable(0xff458435));
        linearLayout.setPadding(0, Utils.dpToPx(15), 0, Utils.dpToPx(15));

        LinearLayout layout = new LinearLayout(this);
        layout.setPadding(5, 5, 5, 5);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);
//        layout.setBackground(Utils.getLineDrawableListState(Utils.dpToPx(1), getResources().getColor(R.color.gray_300), getResources().getColor(R.color.gray_300)));
        ImageView profileImage = new ImageView(this);
        profileImage.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPx(35), Utils.dpToPx(35)));
        profileImage.setImageDrawable(actionDrawable);
        int i5dp = Utils.dpToPixel(5);
        profileImage.setPadding(i5dp, i5dp, i5dp, i5dp);


        TextView actionText = new TextView(this);
        actionText.setPadding(0, this.i5dp, 0, i3dp);
        actionText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        actionText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        actionText.setTextColor(getResources().getColor(R.color.gray_700));
        actionText.setText(actionLable);
        layout.addView(profileImage);
        layout.addView(actionText);
        linearLayout.addView(layout);
        return linearLayout;
    }

    public LinearLayout getCollapseContent() {

        LinearLayout lLayoutCollapsing = new LinearLayout(clContext);
        CollapsingToolbarLayout.LayoutParams clCollapsingLayoutParams1 = new CollapsingToolbarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clCollapsingLayoutParams1.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX);
        clCollapsingLayoutParams1.setParallaxMultiplier(0.2f);

        lLayoutCollapsing.setLayoutParams(clCollapsingLayoutParams1);
        lLayoutCollapsing.setMinimumHeight(Utils.dpToPx(220));
        lLayoutCollapsing.setPadding(0, Utils.dpToPx(60), 0, i5dp);
        lLayoutCollapsing.setOrientation(LinearLayout.VERTICAL);
        lLayoutCollapsing.setGravity(Gravity.CENTER);
//        lLayoutCollapsing.setBackground(Utils.getcollapsdrawable(this, R.drawable.crm_profile_bg, Utils.dpToPx(10)));
        LinearLayout LinearLayout = new LinearLayout(clContext);
        LinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout.setGravity(Gravity.CENTER);
        if (!Utils.isTablet(this))
            LinearLayout.setPadding(i20dp, i5dp, i5dp, i10dp);
        else
            LinearLayout.setPadding(i20dp, i10dp, i5dp, i15dp);


        CircleImageView circleImageView = new CircleImageView(clContext);
        LinearLayout.LayoutParams layoutParams;
        if (!Utils.isTablet(clContext)) {
            layoutParams = new LinearLayout.LayoutParams(Utils.dpToPx(90), Utils.dpToPx(90));
            layoutParams.setMarginStart(i10dp);
        } else
            layoutParams = new LinearLayout.LayoutParams(Utils.dpToPx(110), Utils.dpToPx(110));

        circleImageView.setLayoutParams(layoutParams);
        circleImageView.setId(R.id.profile_image);
        circleImageView.setImageResource(R.drawable.metting_img);
        circleImageView.setBorderWidth(Utils.dpToPx(2));
        circleImageView.setBorderColor(getResources().getColor(R.color.blue_login2));

        LinearLayout rightLinearLayout = new LinearLayout(clContext);
        rightLinearLayout.setOrientation(LinearLayout.VERTICAL);
        rightLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (!Utils.isTablet(this)) {
            rightLinearLayout.setPadding(i10dp, 0, 0, 0);
        } else {
            rightLinearLayout.setPadding(i20dp, 0, 0, 0);
        }

        TextView tvUserName = new TextView(clContext);
        tvUserName.setId(R.id.profile_name);
        tvUserName.setTypeface(Typeface.DEFAULT_BOLD);
        tvUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tvUserName.setTextColor(Color.WHITE);
        tvUserName.setText("Sourav kumar pandit");


        TextView tvUserRole = new TextView(clContext);
        tvUserRole.setId(R.id.profile_company_name);
        tvUserRole.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvUserRole.setTextColor(getResources().getColor(R.color.gray_400));
        tvUserRole.setText("");

        TextView tvUserDesignation = new TextView(clContext);
        tvUserDesignation.setTextColor(getResources().getColor(R.color.gray_100));
        tvUserDesignation.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvUserDesignation.setText("Designations");
        tvUserDesignation.setVisibility(View.GONE);
        tvUserDesignation.setId(R.id.profile_designation);

        TextView tvLastSeen = new TextView(clContext);
        tvLastSeen.setTextColor(Color.WHITE);
        tvLastSeen.setGravity(Gravity.END);
        tvLastSeen.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        if (Utils.isTablet(this))
            tvLastSeen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        else {

            tvLastSeen.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tvLastSeen.setPadding(i10dp, i3dp, i10dp, i3dp);
            tvLastSeen.setBackground(Utils.getRoundDrawable(0x40dedede, i20dp));
        }

        tvLastSeen.setText("Last seen | a moment ago");
        tvLastSeen.setId(R.id.profile_last_seen);

        rightLinearLayout.addView(tvUserName);
        rightLinearLayout.addView(tvUserRole);
        rightLinearLayout.addView(tvUserDesignation);


        LinearLayout.addView(circleImageView);
        LinearLayout.addView(rightLinearLayout);
        lLayoutCollapsing.addView(LinearLayout);
        lLayoutCollapsing.addView(tvLastSeen);
        return lLayoutCollapsing;
    }

    public View getDivider() {
        View dividerView = new View(clContext);
        LinearLayout.LayoutParams dividerParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPx(1));
//        dividerParam.setMarginEnd(Utils.dpToPx(80));
        dividerView.setBackgroundColor(getResources().getColor(R.color.gray_300));
        dividerView.setLayoutParams(dividerParam);
        return dividerView;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    

    @Override
    protected void onResume() {
        super.onResume();
    }

    


    private void showConfirmation(int iActionId) {

      
       
    }

    @Override
    public void onConfirmDone(Dialog clDialog, int iActionId, String sTransIds) {
       
        clDialog.dismiss();
    }

    @Override
    public void onDismissed(int iActionId, String sTransIds) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }

    private enum  IAConstants {
;
        public enum IActions {
            ;
            public static short LOCATE_USER=1;
            public static short EMAIL_USER=2;
            public static short CALL_USER=3;
        }
    }
}
