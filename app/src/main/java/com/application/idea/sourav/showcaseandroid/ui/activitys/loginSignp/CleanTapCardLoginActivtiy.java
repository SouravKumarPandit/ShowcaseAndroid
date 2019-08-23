package com.application.idea.sourav.showcaseandroid.ui.activitys.loginSignp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

public class CleanTapCardLoginActivtiy extends BaseActivity {
    private int i3dp;
    private int i5dp;
    private int i10dp;
    private int i15dp;
    private int i20dp;
    private int i40dp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //        Utils.setLightStatusBar(this);
        getWindow().setNavigationBarColor(0xBF000000);

        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout bottmSection = new LinearLayout(this);
        LinearLayout.LayoutParams bottomParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 7);
        bottomParam.setMargins(0, -i40dp, 0, 0);
        bottmSection.setGravity(Gravity.CENTER_HORIZONTAL);
        bottmSection.setLayoutParams(bottomParam);
        bottmSection.addView(getBottomView());

        mainLayout.addView(getHeadView());
        mainLayout.addView(bottmSection);
        setContentView(mainLayout);
    }

    public LinearLayout getHeadView()
    {
        LinearLayout headLayout = new LinearLayout(this);
        headLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 4));
        headLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        headLayout.setBackgroundColor(getResources().getColor(R.color.sw6_colorPrimary));
        Drawable imageDrawable = Utils.getIcon(this, R.drawable.ic_drop, getResources().getColor(R.color.sw6_colorPrimary));
        ImageView ivDropImage = new ImageView(this);
        LinearLayout.LayoutParams ivDropParam = new LinearLayout.LayoutParams(Utils.getScreenWidth() / 3, Utils.getScreenWidth() / 3);
        ivDropParam.setMargins(0, i40dp, 0, 0);
        ivDropImage.setLayoutParams(ivDropParam);
        ivDropImage.setPadding(i10dp, i10dp, i10dp, i10dp);
        GradientDrawable whiteCircule = new GradientDrawable();
        whiteCircule.setColor(Color.WHITE);
        whiteCircule.setShape(GradientDrawable.OVAL);
        ivDropImage.setImageDrawable(imageDrawable);
        ivDropImage.setBackground(whiteCircule);
        headLayout.addView(ivDropImage);
        return headLayout;
    }

    public CardView getBottomView()
    {
        CardView cardView = new CardView(this);
        cardView.setRadius(i5dp);
        LinearLayout.LayoutParams cardParam = new LinearLayout.LayoutParams((int) (Utils.getScreenWidth() * 0.8f), ViewGroup.LayoutParams.WRAP_CONTENT);
        cardView.setLayoutParams(cardParam);
        LinearLayout bodyLayout = new LinearLayout(this);
        LinearLayout.LayoutParams bodyLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bodyLayout.setLayoutParams(bodyLayoutParam);
        bodyLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        bodyLayout.setBackgroundColor(Color.WHITE);
        bodyLayout.setOrientation(LinearLayout.VERTICAL);
        bodyLayout.setPadding(i15dp, i20dp, i15dp, i20dp);

        Button btLogin = new Button(this);
        btLogin.setLayoutParams(new LinearLayout.LayoutParams((int) (Utils.getScreenWidth() * 0.6), ViewGroup.LayoutParams.WRAP_CONTENT));
        btLogin.setBackground(Utils.getRoundDrawableListState(getResources().getColor(R.color.sw6_colorPrimary), i40dp, getResources().getColor(R.color.sw6_colorPrimaryDark), i40dp));
        btLogin.setText("Sing Up");
        btLogin.setTypeface(Typeface.DEFAULT_BOLD);
        btLogin.setTextColor(Color.WHITE);
        btLogin.setOnClickListener(new ActivityTouchListener());

        SpannableString span1 = new SpannableString("new users ? ");
        SpannableString span2 = new SpannableString("Sign Up");

        span1.setSpan(new RelativeSizeSpan(0.85f), 0, span1.length(), 0);
        span1.setSpan(new ForegroundColorSpan(Color.GRAY), 0, span1.length(), 0);
        span2.setSpan(new RelativeSizeSpan(1.2f), 0, span2.length(), 0);
        span2.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, span2.length(), 0);
        //        span2.setSpan(new UnderlineSpan(), 0, span2.length(), 0);
        span2.setSpan(new ForegroundColorSpan(0xff03b875), 0, span2.length(), 0);


        TextView tvSingUpPage = new TextView(this);
        tvSingUpPage.setId(R.id.singup_layout);
        tvSingUpPage.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvSingUpPage.setPadding(0, i10dp, 0, 0);
        tvSingUpPage.setOnClickListener(new ActivityTouchListener());
        tvSingUpPage.setText(TextUtils.concat(span1, " ", span2));

        TextView tvForgetPassword = new TextView(this);
        tvForgetPassword.setId(R.id.singup_layout);
        tvForgetPassword.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvForgetPassword.setPadding(0, i10dp, 0, 0);
        tvForgetPassword.setOnClickListener(new ActivityTouchListener());
        tvForgetPassword.setText("Forget Password ?");

        bodyLayout.addView(getEditTextView("Email address", R.id.et_email));
        bodyLayout.addView(getEditTextView("Password", R.id.et_password));
        bodyLayout.addView(btLogin);
        bodyLayout.addView(tvForgetPassword);
        bodyLayout.addView(tvSingUpPage);
        cardView.addView(bodyLayout);
        return cardView;
    }

    private TextInputLayout getEditTextView(String sHintTitle, int editId)
    {

        TextInputLayout textInputLayout = new TextInputLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textInputLayout.setPadding(i3dp, 0, i3dp, 0);
        textInputLayout.getContext().setTheme(R.style.EditTextHint);
        EditText textInputEditText = new EditText(this);
        textInputEditText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textInputEditText.setHint(sHintTitle);
        textInputEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textInputEditText.setTextColor(Color.GRAY);
        textInputEditText.setHintTextColor(getResources().getColor(R.color.sw6_colorPrimary));

        if (editId == R.id.et_password)
        {
            textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            layoutParams.setMargins(0, i5dp, 0, i20dp);
        } else
        {
            textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            layoutParams.setMargins(0, i5dp, 0, i15dp);

        }
        textInputEditText.setMaxLines(1);
        textInputEditText.setEms(50);
        Drawable bottomLine = Utils.getIcon(this, R.drawable.focus_bottom_grenline_false, getResources().getColor(R.color.sw6_colorPrimary));
        textInputEditText.setBackground(bottomLine);
        textInputLayout.setLayoutParams(layoutParams);
        textInputLayout.addView(textInputEditText);
        return textInputLayout;
    }

    private class ActivityTouchListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {

        }
    }
}
