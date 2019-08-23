package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.*;
import android.widget.*;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.MordenListAdapter;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

public class ModernListActivity extends BaseActivity {

    private int i3dp;
    private int i5dp;
    private int i10dp;
    private int i15dp;
    private int i20dp;
    private int i40dp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        settingStatusBarTransparent();
        setContentView(activityFlatLayout());


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

    private View activityFlatLayout() {
        RelativeLayout staticRlLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams staticRlLayoutParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        staticRlLayout.setLayoutParams(staticRlLayoutParam);
        staticRlLayout.setBackgroundColor(getResources().getColor(R.color.gray_100));


        ImageView backgroudImage = new ImageView(this);
        RelativeLayout.LayoutParams backgroudImageParam = new RelativeLayout.LayoutParams(Utils.getScreenHeight(), ViewGroup.LayoutParams.MATCH_PARENT);
        backgroudImageParam.setMargins(Utils.dpToPixel(25), -Utils.dpToPixel(35), Utils.dpToPixel(25), -Utils.dpToPixel(35));
        backgroudImage.setBackground(getResources().getDrawable(R.drawable.shadow_background));
        backgroudImageParam.addRule(RelativeLayout.CENTER_IN_PARENT);
        backgroudImage.setLayoutParams(backgroudImageParam);

        AppBarLayout appbar = new AppBarLayout(this);
        RelativeLayout.LayoutParams appbar_param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        appbar.setLayoutParams(appbar_param);
        appbar_param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        appbar.setId(R.id.appbar_layout);
        appbar.addView(getGradiantlayout());


        Button followButton = new Button(this);
        RelativeLayout.LayoutParams followButtonParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        followButtonParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        followButtonParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        followButtonParam.setMargins(0, 0, i20dp, i20dp);
        followButton.setPadding(i15dp,Utils.dpToPixel(8),i15dp,Utils.dpToPixel(8));
//        followButton.setPadding(0,0,0,0);
        followButton.setLayoutParams(followButtonParam);
        followButton.setMinimumHeight(0);
        followButton.setMinimumWidth(0);
        followButton.setMinHeight(0);
        followButton.setMinWidth(0);
        followButton.setText("Follow Now");
        followButton.setTextColor(Color.WHITE);
        followButton.setTypeface(Typeface.DEFAULT_BOLD);
        followButton.setBackground(getResources().getDrawable(R.drawable.gradiant_actionbar));

        staticRlLayout.addView(backgroudImage);
        staticRlLayout.addView(appbar);
        staticRlLayout.addView(getRecyclerContent());
        staticRlLayout.addView(followButton);
        return staticRlLayout;
    }

    ArrayList<String> profileName;

    private void prepareListItems() {
        profileName = new ArrayList<String>();
        profileName.add("Dominic White");
        profileName.add("Samantha Walker");
        profileName.add("Sourav Pandit");
        profileName.add("Ted Lidia");
        profileName.add("Lucy Martin");
        profileName.add("Madhav Reddy");
        profileName.add("Ramesh Ambani");
        profileName.add("Dominic White");
        profileName.add("Samantha Walker");
        profileName.add("Sourav Pandit");
        profileName.add("SpiderMan HomeComing");
        profileName.add("Ted Lidia");
        profileName.add("Lucy Martin");
        profileName.add("Madhav Reddy");
        profileName.add("Ramesh Ambani");
        profileName.add("Dominic White");
        profileName.add("Samantha Walker");
        profileName.add("Sourav Pandit");
        profileName.add("SpiderMan HomeComing");
        profileName.add("Ted Lidia");
        profileName.add("Lucy Martin");
        profileName.add("Madhav Reddy");
        profileName.add("Ramesh Ambani");

    }

    public RelativeLayout getRecyclerContent() {
        prepareListItems();
        RelativeLayout listProfile = new RelativeLayout(this);
        RelativeLayout.LayoutParams listProfileParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        listProfileParam.addRule(RelativeLayout.BELOW, R.id.appbar_layout);
        listProfile.setLayoutParams(listProfileParam);


        RecyclerView recyclerView = new RecyclerView(this);
        RelativeLayout.LayoutParams recyclerViewParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerView.setPadding(i5dp,0,i5dp,0);
        recyclerViewParam.addRule(RelativeLayout.BELOW, R.id.gradiant_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MordenListAdapter myRecyclerAdapter = new MordenListAdapter(this, profileName);
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.setLayoutParams(recyclerViewParam);
        listProfile.addView(recyclerView);
        return listProfile;


    }

    ;

    public LinearLayout getGradiantlayout() {
        LinearLayout gradiantlayout = new LinearLayout(this);
        ViewGroup.LayoutParams gradiantlayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPixel(100));
        gradiantlayout.setGravity(Gravity.BOTTOM | Gravity.END);
        gradiantlayout.setId(R.id.gradiant_layout);
        gradiantlayout.setLayoutParams(gradiantlayoutParam);
        gradiantlayout.setBackground(getResources().getDrawable(R.drawable.gradiant_actionbar));

        ImageView plusIcon = new ImageView(this);
        LinearLayout.LayoutParams plusIconParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        plusIconParam.setMargins(10, 0, 20, 20);
        plusIcon.setPadding(10, 0, 20, 20);
        plusIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_twitter));
        plusIcon.setLayoutParams(plusIconParam);


        ImageView bellIcon = new ImageView(this);
        LinearLayout.LayoutParams bellIconParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bellIconParam.setMargins(10, 0, 10, 20);
        bellIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_dribble_big_logo));
        bellIcon.setPadding(10, 0, 10, 20);
        bellIcon.setLayoutParams(bellIconParam);

        ImageView serchIcon = new ImageView(this);
        LinearLayout.LayoutParams serchIconParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        serchIconParam.setMargins(10, 0, 10, 20);
        serchIcon.setPadding(10, 0, 10, 20);
        serchIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_instagram_logo));
        serchIcon.setLayoutParams(serchIconParam);

        TextView appbarText = new TextView(this);
        SpannableString spannableString = new SpannableString( "R E L \n  @  A T E");
        Drawable d = Utils.getIcon(this,R.drawable.ic_drop,Color.WHITE);
//        Drawable d = getResources().getDrawable(R.drawable.acost);
        d.setBounds(0,0,i20dp,i20dp);
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(span, spannableString.toString().indexOf("@"),  spannableString.toString().indexOf("@")+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        appbarText.setText(TextUtils.concat(spannableString,""));
        appbarText.setText(spannableString);
//        appbarText.setText("R E L \n    A T E");
//        appbarText.setText(str);
        appbarText.setTextSize(16);
        appbarText.setTextColor(Color.WHITE);
        appbarText.setTypeface(Typeface.DEFAULT_BOLD);
        LinearLayout.LayoutParams appbarTextParam = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        appbarText.setPadding(i5dp, 0, i5dp, i5dp);
        appbarText.setLayoutParams(appbarTextParam);


        gradiantlayout.addView(appbarText);
        gradiantlayout.addView(serchIcon);
        gradiantlayout.addView(bellIcon);
        gradiantlayout.addView(plusIcon);
        return gradiantlayout;
    }
}
