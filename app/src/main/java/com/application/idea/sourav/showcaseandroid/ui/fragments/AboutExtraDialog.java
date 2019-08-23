package com.application.idea.sourav.showcaseandroid.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

@SuppressLint("ValidFragment")
public class AboutExtraDialog extends DialogFragment {

    private Context clContext;
    private int i10dp;
    private int i20dp;
    private int i40dp;
    private int i3dp;
    private int i5dp;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
    }

    public AboutExtraDialog(Context clContext)
    {
        this.clContext = clContext;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return getNoteDialogView();
    }


    public LinearLayout getNoteDialogView()
    {
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        GradientDrawable clDialogShape = new GradientDrawable();
        clDialogShape.setColor(0xFF736bfb);
        clDialogShape.setCornerRadius(12f);
        LinearLayout.LayoutParams clDialogLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout clDialogLayout = new LinearLayout(clContext);
        clDialogLayout.setOrientation(LinearLayout.VERTICAL);
        clDialogLayout.setBackground(clDialogShape);
        clDialogLayout.setLayoutParams(clDialogLayoutParams);
        clDialogLayout.setOrientation(LinearLayout.VERTICAL);
        clDialogLayout.setPadding(i10dp, i10dp, i10dp, i40dp);
        clDialogLayout.setMinimumHeight(Utils.getScreenHeight()-Utils.dpToPixel(50));
        clDialogLayout.setMinimumWidth(Utils.getScreenWidth()-Utils.dpToPixel(50));
        clDialogLayout.addView(getCloseButton());
        clDialogLayout.addView(getLableLayout());
        clDialogLayout.addView(getGradiantDivider());
        clDialogLayout.addView(getIcontLayout());
        return clDialogLayout;
    }

    public ImageView getCloseButton()
    {
        ImageView closeImg = new ImageView(getActivity());
        closeImg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPixel(32)));
        closeImg.setId(R.id.close_icon);
        closeImg.setOnClickListener(new DialogEventClickListener());
        closeImg.setScaleType(ImageView.ScaleType.FIT_END);
        closeImg.setImageResource(R.drawable.ic_close_24dp);
        return closeImg;
    }

    public LinearLayout getIcontLayout()
    {
        LinearLayout linearLayout = new LinearLayout(clContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.addView(getIconSet(R.drawable.ic_dribble_big_logo));
        linearLayout.addView(getIconSet(R.drawable.ic_instagram_logo));
        linearLayout.addView(getIconSet(R.drawable.ic_twitter));
        return linearLayout;
    }

    public LinearLayout getLableLayout()
    {
        LinearLayout lableLayout = new LinearLayout(clContext);
        lableLayout.setOrientation(LinearLayout.VERTICAL);
        lableLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        lableLayout.setPadding(0, i40dp, 0, i40dp);
        lableLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        lableLayout.addView(getTextView("About"));
        lableLayout.addView(getTextView("Portfolio"));
        lableLayout.addView(getTextView("Lab"));
        lableLayout.addView(getTextView("Contact"));
        return lableLayout;
    }

    public TextView getTextView(String sLable)
    {
        TextView textView = new TextView(clContext);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        textView.setText(sLable);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Utils.getColorStateListDrawable(getResources().getColor(R.color.about_text), Color.WHITE));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setOnClickListener(new DialogEventClickListener());
//        Typeface type = Typeface.createFromAsset(getResources().getAssets(), "fonts/Larke_Regular.ttf");
//        textView.setTypeface(type);
        return textView;
    }


    public View getGradiantDivider()
    {
        View gradiantDivider = new View(clContext);
        gradiantDivider.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        gradiantDivider.setBackground(getResources().getDrawable(R.drawable.gradiant_line));
        return gradiantDivider;
    }

    public ImageView getIconSet(@DrawableRes int imgResource)
    {
        ImageView iconIv = new ImageView(clContext);
        LinearLayout.LayoutParams iconIvParam = new LinearLayout.LayoutParams(Utils.dpToPixel(42), Utils.dpToPixel(42));
        iconIvParam.setMargins(i10dp, i10dp, i10dp, i10dp);
        iconIv.setPadding(i5dp, i5dp, i5dp, i5dp);
        Drawable icon = Utils.getIcon(clContext, imgResource, Color.WHITE);
        iconIv.setImageDrawable(icon);
        iconIv.setBackground(Utils.getRoundDrawableListState(Color.TRANSPARENT, 42f, 0xFF7D76F4, 32));
        iconIv.setOnClickListener(new DialogEventClickListener());
        iconIv.setLayoutParams(iconIvParam);
        return iconIv;
    }


    private class DialogEventClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.close_icon:
                    AboutExtraDialog.this.dismiss();
                    break;
            }

        }
    }
}
