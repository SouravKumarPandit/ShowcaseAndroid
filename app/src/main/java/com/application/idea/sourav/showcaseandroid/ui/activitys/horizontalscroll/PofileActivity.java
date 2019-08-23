package com.application.idea.sourav.showcaseandroid.ui.activitys.horizontalscroll;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.ColorShageHolder;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.ProfileGridAdapter;
import com.application.idea.sourav.showcaseandroid.utils.CircleImageView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

public class PofileActivity extends BaseActivity implements View.OnClickListener {
    ArrayList<ColorShageHolder> dtoArrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpData();
        setContentView(getProfileView());
        Utils.setLightStatusBar(this);


    }
    public void setLightStatusBar(View view,Activity activity){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }
    private void setUpData()
    {

        dtoArrayList = new ArrayList<ColorShageHolder>();
        for (int i = 0; i <= 2; i++) {
            ColorShageHolder colorShageHolder0 =new ColorShageHolder(0x20f14646,0xfff14646,R.drawable.ic_burn);
            ColorShageHolder colorShageHolder1 =new ColorShageHolder(0x2054c3d4,0xff54c3d4,R.drawable.ic_water);
            ColorShageHolder colorShageHolder2 =new ColorShageHolder(0x2064ca45,0xff64ca45,R.drawable.ic_grass);
            ColorShageHolder colorShageHolder3 =new ColorShageHolder(0x209941a1,0xff9941a1,R.drawable.ic_wind);
            ColorShageHolder colorShageHolder4 =new ColorShageHolder(0x20bca93c,0xffbca93c,R.drawable.ic_burn);
            ColorShageHolder colorShageHolder5 =new ColorShageHolder(0x205d4ab1,0xff5d4ab1,R.drawable.ic_water);
            ColorShageHolder colorShageHolder6 =new ColorShageHolder(0x204991af,0xff4991af,R.drawable.ic_grass);
            ColorShageHolder colorShageHolder7 =new ColorShageHolder(0x20d1894a,0xffd1894a,R.drawable.ic_wind);
            ColorShageHolder colorShageHolder8 =new ColorShageHolder(0x203bab78,0xff3bab78,R.drawable.ic_grass);
            dtoArrayList.add(colorShageHolder0);
            dtoArrayList.add(colorShageHolder1);
            dtoArrayList.add(colorShageHolder2);
            dtoArrayList.add(colorShageHolder3);
            dtoArrayList.add(colorShageHolder4);
            dtoArrayList.add(colorShageHolder5);
            dtoArrayList.add(colorShageHolder6);
            dtoArrayList.add(colorShageHolder7);
            dtoArrayList.add(colorShageHolder8);

        }
    }

    public View getProfileView() {
        Toolbar toolbar = new Toolbar(this);
        LinearLayout.LayoutParams toolbar_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbar.setBackgroundColor(getResources().getColor(R.color.pure_white));
        toolbar.setLayoutParams(toolbar_param);
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.addView(getToobarLayout());

        LinearLayout clLinearLayout = new LinearLayout(this);
        clLinearLayout.setPadding(0,Utils.dpToPixel(12),0,0);
        DrawerLayout.LayoutParams clLinearLayout_param = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        clLinearLayout.setOrientation(LinearLayout.VERTICAL);
        clLinearLayout.setGravity(Gravity.CENTER);
        clLinearLayout.setLayoutParams(clLinearLayout_param);
        clLinearLayout.addView(toolbar);
        clLinearLayout.addView(getContentLayout());
        return clLinearLayout;
    }


    public LinearLayout getToobarLayout() {
        LinearLayout toobarLayout = new LinearLayout(this);
        toobarLayout.setGravity(Gravity.CENTER);
        toobarLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_arrow_back_black_24dp);
        imageView.setId(R.id.back_arrow);
        imageView.setPadding(Utils.dpToPixel(10), Utils.dpToPixel(10), Utils.dpToPixel(10), Utils.dpToPixel(10));
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setOnClickListener(this);

        TextView headText = new TextView(this);
//        headText.setPadding(Utils.dpToPixel(10f), Utils.dpToPixel(8f), Utils.dpToPixel(10f), Utils.dpToPixel(8f));
        headText.setPadding(0, 0, 18 * 2, 0);
        headText.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1));
        headText.setGravity(Gravity.CENTER);
        headText.setText("Profile");
        headText.setMaxLines(1);
        headText.setCompoundDrawablePadding(Utils.dpToPixel(8));
        headText.setTextColor(getResources().getColor(R.color.text_color));
        headText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        headText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//        headText.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_hamburger), null, null, null);
        ImageView editImage= new ImageView(this);
        editImage.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        editImage.setPadding(Utils.dpToPixel(10), Utils.dpToPixel(10), Utils.dpToPixel(10), Utils.dpToPixel(10));
        editImage.setImageResource(R.drawable.ic_edit_black_24dp);
        editImage.setId(R.id.edit_icon);
        editImage.setOnClickListener(this);
        toobarLayout.addView(imageView);
        toobarLayout.addView(headText);
        toobarLayout.addView(editImage);
        return toobarLayout;
    }


    public LinearLayout getContentLayout() {
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setBackgroundColor(0xfff1f4f9);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(this);
        shadowRectLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        shadowRectLayout.setShadowColor(0xFFBEBEBE);

        shadowRectLayout.setOffSetY(20);
        shadowRectLayout.setShadowTop(false);
        shadowRectLayout.setShadowLeft(false);
        shadowRectLayout.setRoundCornerRadius(50);
        shadowRectLayout.setShadowRight(false);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        linearLayoutParam.setMargins(0, -65, 0, 0);
        linearLayout.setLayoutParams(linearLayoutParam);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(0, 0, 0, 50);

        CircleImageView imageRounded = new CircleImageView(this);
        imageRounded.setLayoutParams(new LinearLayout.LayoutParams((int) (Utils.getScreenWidth() *0.3), (int) (Utils.getScreenWidth() *0.3)));
        imageRounded.setImageResource(R.drawable.profile_0);

        ShadowRectLayout shadowRectLayout1=new ShadowRectLayout(this);
        shadowRectLayout1.setLayoutParams(new LinearLayout.LayoutParams((int) (Utils.getScreenWidth() *0.3), (int) (Utils.getScreenWidth() *0.3)));
        shadowRectLayout1.setRoundCornerRadius((int) (Utils.getScreenWidth() *0.3));
        shadowRectLayout1.addView(imageRounded);
//        shadowRectLayout1.setResDrawable(R.drawable.ic_profile_icon);
        shadowRectLayout1.setOffSetY(10);
        shadowRectLayout1.setShadowRadius(10);
        shadowRectLayout1.setShadowColor(Color.LTGRAY);


        TextView tvProfileName = new TextView(this);
        tvProfileName.setTypeface(Typeface.DEFAULT_BOLD);
        tvProfileName.setGravity(Gravity.CENTER);
        tvProfileName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvProfileName.setText("LLija Maskov");
        tvProfileName.setPadding(0, 0, 15, 0);
        tvProfileName.setTextColor(Color.DKGRAY);

        TextView tvsubText = new TextView(this);
        tvsubText.setTypeface(Typeface.DEFAULT_BOLD);
        tvsubText.setText("User Interface Designer");
        tvsubText.setGravity(Gravity.CENTER);
        tvsubText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvsubText.setMinLines(2);
//        tvsubText.setPadding(0, 0, 15, 0);
        tvsubText.setTextColor(getResources().getColor(android.R.color.holo_green_light));


        linearLayout.addView(shadowRectLayout1);
        linearLayout.addView(tvProfileName);
        linearLayout.addView(tvsubText);
        shadowRectLayout.addView(linearLayout);

        ProfileGridAdapter profileGridAdapter = new ProfileGridAdapter(this,dtoArrayList);


        contentLayout.addView(shadowRectLayout);
        contentLayout.addView(setLabeledHeader(profileGridAdapter, "Statistics", R.id.session, R.drawable.ic_seriese, R.drawable.ic_right_arrow));

        return contentLayout;
    }

    private LinearLayout setLabeledHeader(RecyclerView.Adapter adapter, String sLable, int lableId, int drawableLeft, int drawableRight) {
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearLayoutParam=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        linearLayoutParam.setMargins(0,10,0,0);
        linearLayout.setLayoutParams(linearLayoutParam);
//        linearLayout.setPadding(0, 0, 0, 30);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        TextView headText = new TextView(this);
        headText.setPadding(Utils.dpToPixel(10f), 0, Utils.dpToPixel(10f), 0);
        headText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        headText.setGravity(Gravity.CENTER_VERTICAL);
        headText.setId(lableId);
        headText.setText(sLable);
        headText.setCompoundDrawablePadding(Utils.dpToPixel(8));
        headText.setTextColor(getResources().getColor(R.color.text_color));
        headText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        headText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        headText.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(drawableLeft), null, getResources().getDrawable(drawableRight), null);


        RecyclerView recyclerView = new RecyclerView(this);

        LinearLayout.LayoutParams recyclerViewParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        recyclerViewParam.setMargins(0, 0, 0, 30);
        recyclerView.setLayoutParams(recyclerViewParam);
        RecyclerView.LayoutManager layoutManager;
        if (Utils.isTablet(this))
        layoutManager = new GridLayoutManager(this, 3);
        else
        layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        linearLayout.addView(headText);
        linearLayout.addView(recyclerView);

        return linearLayout;


    }

    @Override
    public void onClick(View view)
    {
        if (view.getId()==R.id.back_arrow){
            this.finish();
        }else
        if (view.getId()==R.id.edit_icon){
            Toast.makeText(this, "e d i t", Toast.LENGTH_SHORT).show();
        }
    }
}
