package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.animation.AnimatorInflater;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

public class ScrollAnimActivity extends BaseActivity {
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
        setContentView(getScrollContentView());

    }
    private View getScrollContentView() {
        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams coordinatorLayoutMain_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutMode(ViewGroup.LAYOUT_MODE_CLIP_BOUNDS);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.gray_400));
        linearLayout.setLayoutParams(coordinatorLayoutMain_param);

        AppBarLayout abTabtool = new AppBarLayout(this);
        LinearLayout.LayoutParams ab_tabtool_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        abTabtool.setLayoutParams(ab_tabtool_param);
        abTabtool.setPadding(0, Utils.dpToPixel(12), 0, 0);
        abTabtool.setBackground(getResources().getDrawable(R.drawable.gradiant_actionbar2));


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

        abTabtool.addView(toolbar);
        linearLayout.addView(abTabtool);

        GradientDrawable roundCornerDrawable=new GradientDrawable();
        roundCornerDrawable.setCornerRadius(i20dp);
        roundCornerDrawable.setColor(Color.WHITE);
        roundCornerDrawable.setShape(GradientDrawable.RECTANGLE);


        LinearLayout  contentView=new LinearLayout(this);
        LinearLayout.LayoutParams contentParam=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentParam.setMargins(i10dp,i10dp,i10dp,i10dp);
        contentView.setLayoutParams(contentParam);
        contentView.setOrientation(LinearLayout.VERTICAL);
        FrameLayout frameLayout=new FrameLayout(this);
        frameLayout.setId(R.id.frame_elivation);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView textView=new TextView(this);
        textView.setText("Location");
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setId(R.id.text_elivation);
        textView.setBackground(new ColorDrawable(Color.YELLOW));
        textView.setPadding(i10dp,i10dp,i10dp,i10dp);
        textView.setTextColor(getResources().getColor(android.R.color.darker_gray));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            frameLayout.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this,
                    R.drawable.layout_smooth_shadow));
        }

        contentView.setBackground(roundCornerDrawable);

        frameLayout.addView(textView);
        contentView.addView(frameLayout);
        contentView.addView(getContentView());
        linearLayout.addView(contentView);


        return linearLayout;
    }

    public ViewGroup getContentView() {


        final ScrollView scrollView=new ScrollView(this);
        scrollView.setFillViewport(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            /*scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener()
            {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3)
                {
                    if ( scrollView.canScrollVertically(-1))
                        (findViewById(R.id.frame_elivation)).setPressed(true);
                    else
                        (findViewById(R.id.frame_elivation)).setPressed(false);


                }
            });*/
        }

        LinearLayout  contentView=new LinearLayout(this);
        LinearLayout.LayoutParams contentParam=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        contentParam.setMargins(i10dp,i10dp,i10dp,i10dp);
        contentView.setLayoutParams(contentParam);
        contentView.setPadding(i10dp,i10dp,i10dp,i10dp);
        contentView.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 20; i++) {

            contentView.addView(getTextContent());
        }

        scrollView.addView(contentView);
        return scrollView;
    }

    public View getTextContent() {
        TextView textContent=new TextView(this);
        textContent.setText("some Random Text");
//        textContent.setGravity(Gravity.CENTER);
        textContent.setTextColor(Color.LTGRAY);
        textContent.setPadding(0,i5dp,i5dp,i5dp);
        return textContent;
    }
}
