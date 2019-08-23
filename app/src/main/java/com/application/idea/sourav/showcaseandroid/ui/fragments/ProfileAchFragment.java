package com.application.idea.sourav.showcaseandroid.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
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
import com.application.idea.sourav.showcaseandroid.utils.CircleImageView;
import com.application.idea.sourav.showcaseandroid.utils.LabelTextView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

@SuppressLint("ValidFragment")
public class ProfileAchFragment extends Fragment {
    private final Context clContext;
    private int i3dp;
    private int i10dp;
    private int i5dp;
    private int i20dp;
    private int i40dp;

    public ProfileAchFragment(Context clContext) {
        this.clContext = clContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        LinearLayout linearLayout = new LinearLayout(clContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setPadding(i3dp, i20dp, i3dp, i5dp);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(0xfffbfcfd);

        CircleImageView circleImageView = new CircleImageView(clContext);
        circleImageView.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(80), Utils.dpToPixel(80)));
        circleImageView.setImageResource(R.drawable.profile_4);


        TextView userName = new TextView(clContext);
        userName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        userName.setGravity(Gravity.CENTER);
        userName.setText("Marion cotillard");
        userName.setAllCaps(true);
//        userName.setCompoundDrawablePadding(dpToPixel(8));
        userName.setTextColor(getResources().getColor(R.color.gray_900));
        userName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        userName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        userName.setPadding(0, i10dp, 0, i10dp);

        linearLayout.addView(circleImageView);
        linearLayout.addView(userName);


        linearLayout.addView(getUserLevelLayout());
        linearLayout.addView(getPointsDetails());
        return linearLayout;
    }

    public ShadowRectLayout getPointsDetails() {

        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(getActivity());
        LinearLayout.LayoutParams layoutparam=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutparam.setMargins(0,i10dp,0,0);
        shadowRectLayout.setLayoutParams(layoutparam);
        shadowRectLayout.setOffSetX(0);
        shadowRectLayout.setOffSetY(3);
        shadowRectLayout.setShadowRadius(5);
        shadowRectLayout.setShadowColor(0xFFE8E8EA);
        shadowRectLayout.setRoundCornerRadius(i5dp);
        shadowRectLayout.setImgGradientColor1(0xfff9f7fb);
        shadowRectLayout.setImgGradientColor2(0xfff9f7fb);


        LinearLayout vertLaout = new LinearLayout(getActivity());
        vertLaout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        vertLaout.setOrientation(LinearLayout.VERTICAL);
        vertLaout.setBackgroundColor(Color.WHITE);
        vertLaout.setPadding(i10dp, i10dp, i10dp, i10dp);

        TextView tvTodoSub = new TextView(getActivity());
        tvTodoSub.setText("Breakdown");
        tvTodoSub.setGravity(Gravity.START);
        tvTodoSub.setPadding(0, 0, 0, i3dp);
        tvTodoSub.setTextColor(getResources().getColor(R.color.gray_900));
        tvTodoSub.setTypeface(Typeface.DEFAULT_BOLD);
        tvTodoSub.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);


        vertLaout.addView(tvTodoSub);
        vertLaout.addView(Utils.getMarginalDivider(getActivity(), getResources().getColor(R.color.gray_300), 2, 0, 0, i3dp, i5dp));
        vertLaout.addView(getPointView("Wastage Index", "24"));
        vertLaout.addView(Utils.getMarginalDivider(getActivity(), getResources().getColor(R.color.gray_300), 2, i5dp, i5dp, 0, i5dp));
        vertLaout.addView(getPointView("Sharing Item", "53"));
        vertLaout.addView(Utils.getMarginalDivider(getActivity(), getResources().getColor(R.color.gray_300), 2, i5dp, i5dp, 0, i3dp));
        vertLaout.addView(getPointView("Adding to inventory", "36"));
        vertLaout.addView(Utils.getMarginalDivider(getActivity(), getResources().getColor(R.color.gray_300), 2, i5dp, i5dp, 0, i3dp));
        vertLaout.addView(getPointView("Inviting Friends", "65"));
        shadowRectLayout.addView(vertLaout);

        return shadowRectLayout;
    }

    private LinearLayout getPointView(String sTitle, String sPoints) {
        LinearLayout linearLayout = new LinearLayout(clContext);
        linearLayout.setBackgroundColor(Color.WHITE);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setPadding(0,0,0,i10dp);

        TextView ltvUserLevel = new TextView(clContext);
        ltvUserLevel.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ltvUserLevel.setPadding(0, i5dp, i20dp, 0);
        ltvUserLevel.setText(sTitle);
        ltvUserLevel.setTextColor(getResources().getColor(R.color.gray_600));
        ltvUserLevel.setTypeface(Typeface.DEFAULT_BOLD);

        TextView ltvUserPoints = new TextView(clContext);
        ltvUserPoints.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        ltvUserPoints.setMandatory(true, "Points", 0xFF70B2EE, 10, LabelTextView.RIGHT_MID);
        ltvUserPoints.setTextColor(getResources().getColor(R.color.gray_600));
//        ltvUserPoints.setTypeface(Typeface.DEFAULT_BOLD);
        ltvUserPoints.setGravity(Gravity.END);
        final SpannableStringBuilder sb = new SpannableStringBuilder(sPoints+" points");

        final StyleSpan bss = new StyleSpan(Typeface.BOLD); // Span to make text bold
        sb.setSpan(bss, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
//        final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC); //Span to make text italic
//        sb.setSpan(iss, 2, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make last 2 characters Italic

        ltvUserPoints.setText(sb);

        linearLayout.addView(ltvUserLevel);
        linearLayout.addView(ltvUserPoints);
        return linearLayout;
    }

    public ViewGroup getUserLevelLayout() {
        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(clContext);
        shadowRectLayout.setOffSetX(0);
        shadowRectLayout.setOffSetY(1);
        shadowRectLayout.setShadowRadius(3);
        shadowRectLayout.setShadowColor(getResources().getColor(R.color.gray_300));
        shadowRectLayout.setRoundCornerRadius(5);

        LinearLayout linearLayout = new LinearLayout(clContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(0, 0, i20dp, 0);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);


        ImageView imageView = new ImageView(clContext);
        LinearLayout.LayoutParams imageParam = new LinearLayout.LayoutParams(Utils.dpToPixel(55), Utils.dpToPixel(55));
        imageParam.setMargins(0, 0, i10dp, 0);
        imageView.setLayoutParams(imageParam);
        imageView.setImageResource(R.drawable.ic_cup_1);
        imageView.setPadding(i10dp, i10dp, i10dp, i10dp);
        imageView.setBackgroundColor(0xff969fdb);

        LabelTextView ltvUserLevel = new LabelTextView(clContext);
        ltvUserLevel.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        ltvUserLevel.setMandatory(true, "LEVEL", Color.GRAY, 10, LabelTextView.RIGHT);
        ltvUserLevel.setPadding(0, i5dp, i20dp, 0);
        ltvUserLevel.setText("Intermediate");
        ltvUserLevel.setTextColor(getResources().getColor(R.color.gray_900));
        ltvUserLevel.setTypeface(Typeface.DEFAULT_BOLD);
        ltvUserLevel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        LabelTextView ltvUserPoints = new LabelTextView(clContext);
        ltvUserPoints.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ltvUserPoints.setMandatory(true, "POINTS", Color.GRAY, 10, LabelTextView.RIGHT);
        ltvUserPoints.setPadding(0, i5dp, i20dp, 0);
        ltvUserPoints.setText("180");
        ltvUserPoints.setTypeface(Typeface.DEFAULT_BOLD);
        ltvUserPoints.setTextColor(getResources().getColor(R.color.gray_900));
        ltvUserPoints.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        View splitDivider = new View(clContext);
        splitDivider.setBackgroundColor(getResources().getColor(R.color.gray_300));
        LinearLayout.LayoutParams divParam = new LinearLayout.LayoutParams(2, ViewGroup.LayoutParams.MATCH_PARENT);
        divParam.setMargins(i10dp, i5dp, i10dp, i5dp);
        splitDivider.setLayoutParams(divParam);


        linearLayout.addView(imageView);
        linearLayout.addView(ltvUserLevel);
        linearLayout.addView(splitDivider);
        linearLayout.addView(ltvUserPoints);
        shadowRectLayout.addView(linearLayout);

        return shadowRectLayout;
    }
}
