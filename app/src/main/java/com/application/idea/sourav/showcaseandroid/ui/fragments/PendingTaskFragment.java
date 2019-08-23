package com.application.idea.sourav.showcaseandroid.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.utils.LabelTextView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

@SuppressLint("ValidFragment")
public class PendingTaskFragment extends Fragment {


    private final Context clContext;
    private int i3dp;
    private int i10dp;
    private int i5dp;
    private int i20dp;
    private int i15dp;
    private int i40dp;

    public PendingTaskFragment(Context clContext) {
        this.clContext = clContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        LinearLayout contentLayout = new LinearLayout(clContext);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentLayout.setId(R.id.content_framlayout);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setBackground(new ColorDrawable(0xFFF7F8FA));
        contentLayout.setPadding(i3dp, 0, i3dp, i40dp);
        contentLayout.addView(getHeadList());
        contentLayout.addView(getBottomList());
        contentLayout.addView(getBottomList());
        return contentLayout;

    }
    public ShadowRectLayout getHeadList() {
        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(clContext);
        shadowRectLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        shadowRectLayout.setImgGradientColor1(0xffffffff);
//        shadowRectLayout.setResDrawable(R.drawable.address_background);
        shadowRectLayout.setOffSetX(5);
        shadowRectLayout.setOffSetY(5);
        shadowRectLayout.setShadowRadius(i5dp);
        shadowRectLayout.setShadowColor(0xFFE8E8EA);
        shadowRectLayout.setRoundCornerRadius(i5dp);
        LinearLayout linearLayout = new LinearLayout(clContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setPadding(i5dp, i5dp, i5dp, i5dp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout lefLayout = new LinearLayout(clContext);
        lefLayout.setPadding(i5dp, i5dp, i5dp, i5dp);
        lefLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lefLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        lefLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout rightLayout = new LinearLayout(clContext);
        rightLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        rightLayout.setGravity(Gravity.START);
        rightLayout.setPadding(i5dp, i5dp, i5dp, i5dp);
        rightLayout.setOrientation(LinearLayout.VERTICAL);

        TextView tvTodoSub = new TextView(clContext);
        tvTodoSub.setText("Buy Grocery Items");
        tvTodoSub.setPadding(0, 0, 0, i3dp);
        tvTodoSub.setTextColor(getResources().getColor(R.color.gray_600));
        tvTodoSub.setTypeface(Typeface.DEFAULT_BOLD);
        tvTodoSub.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);


        TextView tvTimeLeft = new TextView(clContext);
        tvTimeLeft.setText("5:00 Hr Left");
        tvTimeLeft.setTextColor(getResources().getColor(R.color.gray_500));
        tvTimeLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvTimeLeft.setTypeface(Typeface.DEFAULT_BOLD);

        LabelTextView tvAddress = new LabelTextView(clContext);
        tvAddress.setText("Seven Eleven \nStreet 69 , new York ,USA");
        tvAddress.setMandatory(true, null, getResources().getColor(R.color.green_basic),Utils.dpToPixel(3), 20);
        tvAddress.setTextColor(getResources().getColor(R.color.gray_500));
        tvAddress.setSingleLine(false);
        tvAddress.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvAddress.setTypeface(Typeface.DEFAULT_BOLD);


        TextView tvDayTime = new TextView(clContext);
        tvDayTime.setText("Today");
        tvDayTime.setPadding(0, 0, 0, i3dp);
        tvDayTime.setTextColor(getResources().getColor(R.color.gray_600));
        tvDayTime.setTypeface(Typeface.DEFAULT_BOLD);
        tvDayTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        TextView tvHrTime = new TextView(clContext);
        tvHrTime.setText("5:00 PM");
        tvHrTime.setTextColor(getResources().getColor(R.color.gray_500));
        tvHrTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvHrTime.setTypeface(Typeface.DEFAULT_BOLD);

        LabelTextView tvStatus = new LabelTextView(clContext);
        tvStatus.setText("Done");
        tvStatus.setMandatory(true, null, getResources().getColor(R.color.sw7_colorAccent2),Utils.dpToPixel(5),LabelTextView.RIGHT_MID);
        tvStatus.setTextColor(getResources().getColor(R.color.gray_500));
        tvStatus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvStatus.setTypeface(Typeface.DEFAULT_BOLD);

        lefLayout.addView(tvDayTime);
        lefLayout.addView(tvHrTime);
        lefLayout.addView(tvStatus);
        rightLayout.addView(tvTodoSub);
        rightLayout.addView(tvTimeLeft);
        rightLayout.addView(tvAddress);


        View dividerLayout = new LinearLayout(clContext);
        dividerLayout.setLayoutParams(new LinearLayout.LayoutParams(3, ViewGroup.LayoutParams.MATCH_PARENT));
        dividerLayout.setBackground(Utils.getRoundDrawable(getResources().getColor(R.color.gray_300), i3dp));
        linearLayout.addView(lefLayout);
        linearLayout.addView(dividerLayout);
        linearLayout.addView(rightLayout);

        shadowRectLayout.addView(linearLayout);
        return shadowRectLayout;
    }


    public ShadowRectLayout getBottomList() {

        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(getActivity());
        shadowRectLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        shadowRectLayout.setOffSetX(3);
        shadowRectLayout.setOffSetY(3);
        shadowRectLayout.setShadowRadius(i5dp);
        shadowRectLayout.setShadowColor(0xFFE8E8EA);
        shadowRectLayout.setRoundCornerRadius(i5dp);


        LinearLayout vertLaout = new LinearLayout(getActivity());
        vertLaout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        vertLaout.setOrientation(LinearLayout.VERTICAL);
        vertLaout.setPadding(i10dp, i10dp, i10dp, i10dp);

        TextView tvTodoSub = new TextView(getActivity());
        tvTodoSub.setText("UPCOMING PLANS (3)");
        tvTodoSub.setGravity(Gravity.START);
        tvTodoSub.setPadding(0, 0, 0, i3dp);
        tvTodoSub.setTextColor(getResources().getColor(R.color.gray_700));
        tvTodoSub.setTypeface(Typeface.DEFAULT_BOLD);
        tvTodoSub.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);


        vertLaout.addView(tvTodoSub);
        vertLaout.addView(Utils.getMarginalDivider(getActivity(), getResources().getColor(R.color.gray_300), 2, 0, 0, i3dp, i5dp));
        vertLaout.addView(getUpcomingPlanView("Discuss with Adrin Begi ", "call adrian at the right time ", "6:00 pm", R.drawable.ic_chat_bubble, 0xffff8d88, 0xffffb666));
        vertLaout.addView(Utils.getMarginalDivider(getActivity(), getResources().getColor(R.color.gray_300), 2, i5dp, i5dp, i3dp, i5dp));
        vertLaout.addView(getUpcomingPlanView("Video calling with Boss ", "I hate but have to talk with my ceo", "9:27 pm", R.drawable.ic_call_pressed_24dp, 0xffff8790, 0xffff73b7));
        vertLaout.addView(Utils.getMarginalDivider(getActivity(), getResources().getColor(R.color.gray_300), 2, i5dp, i5dp, 0, i3dp));
        vertLaout.addView(getUpcomingPlanView("UEFE Champion Leage Final", "Real Madrid vs arsenal", "1:00 pm", R.drawable.ic_dribble_big_logo, 0xff6693ff, 0xff67c7ff));
        vertLaout.addView(Utils.getMarginalDivider(getActivity(), getResources().getColor(R.color.gray_300), 2, i5dp, i5dp, 0, i3dp));
        vertLaout.addView(getUpcomingPlanView("Camping with Friends", "call Friends at the right time ", "6:00 pm", R.drawable.ic_burn, 0xFF00bf4b, 0xff00ff7f));
        shadowRectLayout.addView(vertLaout);

        return shadowRectLayout;
    }

    public LinearLayout getUpcomingPlanView(String taskSub, String taskBrief, String sTime, int iDrawable, @ColorInt int color1, @ColorInt int color2) {

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setPadding(i5dp, i5dp, i5dp, i5dp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        ImageView ivIconLeft = new ImageView(getActivity());
        ivIconLeft.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(35), Utils.dpToPixel(35)));
        Drawable iconDrawable = Utils.getIcon(getActivity(), iDrawable, Color.WHITE);
        int i8dp = Utils.dpToPixel(8);
        ivIconLeft.setPadding(i8dp, i8dp, i8dp, i8dp);
//        ivIconLeft.setPadding(i5dp, i5dp, i5dp, i5dp);
        ivIconLeft.setBackground(Utils.getRoundGradientDrawable(new int[]{color1, color2}, Utils.dpToPixel(20)));
        ivIconLeft.setScaleType(ImageView.ScaleType.FIT_XY);
        ivIconLeft.setImageDrawable(iconDrawable);

        LinearLayout lefLayout = new LinearLayout(getActivity());
        lefLayout.setPadding(i5dp, i5dp, i5dp, i5dp);
        lefLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        lefLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        lefLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout rightLayout = new LinearLayout(getActivity());
        rightLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rightLayout.setGravity(Gravity.START);
        rightLayout.setPadding(i5dp, i5dp, i15dp, i5dp);
        rightLayout.setOrientation(LinearLayout.VERTICAL);


        TextView tvSubTast = new TextView(getActivity());
        tvSubTast.setText(taskSub);
        tvSubTast.setPadding(0, 0, 0, i3dp);
        tvSubTast.setTextColor(getResources().getColor(R.color.gray_600));
        tvSubTast.setTypeface(Typeface.DEFAULT_BOLD);
        tvSubTast.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        TextView tvSubBrief = new TextView(getActivity());
        tvSubBrief.setText(taskBrief);
        tvSubBrief.setTextColor(getResources().getColor(R.color.gray_500));
        tvSubBrief.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvSubBrief.setTypeface(Typeface.DEFAULT_BOLD);


        TextView tvTodoSub = new TextView(getActivity());
        tvTodoSub.setText(sTime);
        tvTodoSub.setGravity(Gravity.END);
        tvTodoSub.setPadding(0, 0, 0, i3dp);
        tvTodoSub.setTextColor(getResources().getColor(R.color.gray_600));
        tvTodoSub.setTypeface(Typeface.DEFAULT_BOLD);
        tvTodoSub.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);


        LabelTextView tvTimeLeft = new LabelTextView(getActivity());
        tvTimeLeft.setText("MEDIUM");
        tvTimeLeft.setMandatory(true, null, getResources().getColor(R.color.sw5_colorAccent),Utils.dpToPixel(5), LabelTextView.RIGHT_MID);
        tvTimeLeft.setGravity(Gravity.END);

        tvTimeLeft.setTextColor(getResources().getColor(R.color.gray_500));
        tvTimeLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        tvTimeLeft.setTypeface(Typeface.DEFAULT_BOLD);


        lefLayout.addView(tvSubTast);
        lefLayout.addView(tvSubBrief);
        rightLayout.addView(tvTodoSub);
        rightLayout.addView(tvTimeLeft);


        linearLayout.addView(ivIconLeft);
        linearLayout.addView(lefLayout);
        linearLayout.addView(rightLayout);

        return linearLayout;
    }

}
