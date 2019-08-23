package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

/**
 * Created by Sourav on 2018
 */
public class DrawableTestActivity extends BaseActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutFrame());
    }

    private View getLayoutFrame()
    {
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int i15=Utils.dpToPixel(15);
        int i2dp=Utils.dpToPixel(2);

        FrameLayout frameLayout=new FrameLayout(this);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,Utils.dpToPx(250)));
        frameLayout.setBackground(Utils.getWaveDrawable());
        linearLayout.addView(frameLayout);
        return linearLayout;
    }

}
