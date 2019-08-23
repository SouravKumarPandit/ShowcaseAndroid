package com.application.idea.sourav.showcaseandroid.ui.activitys.loginSignp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.*;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

public class SingUpActivity extends BaseActivity {
    private int i10dp;
    private int i20dp;
    private int i40dp;
    private int i3dp;
    private int i5dp;
    private int i15dp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        Utils.setStatusBarColor(this, Color.WHITE);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);

        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setPadding(i40dp, i40dp, i40dp, i20dp);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.login_background2));
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView ivbackarrow = new ImageView(this);
        ivbackarrow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Drawable backArrow = Utils.getIcon(this, R.drawable.ic_arrow_back_black_24dp, Color.WHITE);
        ivbackarrow.setScaleType(ImageView.ScaleType.FIT_START);
        ivbackarrow.setId(R.id.back_arrow);
        ivbackarrow.setOnClickListener(new ViewEventClickListener());
        ivbackarrow.setImageDrawable(backArrow);

        TextView tvLogIn = new TextView(this);
        tvLogIn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        tvLogIn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvLogIn.setGravity(Gravity.START);
        tvLogIn.setText("Log In");
        tvLogIn.setTypeface(Typeface.DEFAULT_BOLD);
        tvLogIn.setTextColor(Color.WHITE);
        //        tvCompName.setShadowLayer(10, 5.0f, 5.0f, Color.GRAY);

        TextView tvError = new TextView(this);
        tvError.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvError.setVisibility(View.INVISIBLE);
        tvError.setId(R.id.tv_loginError);
        tvError.setGravity(Gravity.CENTER);
        tvError.setTypeface(Typeface.DEFAULT_BOLD);
        tvError.setText("Invalid Email / Password");
        tvError.setTextColor(getResources().getColor(R.color.red_error_color));


        SpannableString span1 = new SpannableString("First Time Here ? ");
        SpannableString span2 = new SpannableString("Sign Up");

        span1.setSpan(new RelativeSizeSpan(0.85f), 0, span1.length(), 0);
        span1.setSpan(new ForegroundColorSpan(Color.WHITE), 0, span1.length(), 0);
        span2.setSpan(new RelativeSizeSpan(1.2f), 0, span2.length(), 0);
        span2.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, span2.length(), 0);
//        span2.setSpan(new UnderlineSpan(), 0, span2.length(), 0);
        span2.setSpan(new ForegroundColorSpan(0xff9ab7c3), 0, span2.length(), 0);


        TextView tvSingUpPage = new TextView(this);
        tvSingUpPage.setId(R.id.singup_layout);
        tvSingUpPage.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvSingUpPage.setOnClickListener(new ViewEventClickListener());
        tvSingUpPage.setText(TextUtils.concat(span1, " ", span2));


        linearLayout.addView(ivbackarrow);
        linearLayout.addView(getGapView(15));
        linearLayout.addView(tvLogIn);
        linearLayout.addView(getGapView(30));
        linearLayout.addView(getEditFeilds(R.drawable.ic_user_profile, "Username", R.id.user_name));
        linearLayout.addView(getGapView(20));
        linearLayout.addView(getEditFeilds(R.drawable.ic_pass_lock, "Password", R.id.user_password));
        linearLayout.addView(getGapView(5));
        linearLayout.addView(tvError);
        linearLayout.addView(getGapView(20));
        linearLayout.addView(getSignInButton());
        linearLayout.addView(getGapView(10));
        linearLayout.addView(tvSingUpPage);


        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayout);
        setContentView(scrollView);
    }

    public LinearLayout getEditFeilds(@DrawableRes int iconEditText, String hintEditText, int edittextId)
    {


        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setPadding(i3dp, i5dp, i3dp, i5dp);
        linearLayout.setBackground(getResources().getDrawable(R.drawable.focus_bottom_line_false));

        ImageView ivEditIcon = new ImageView(this);
        ivEditIcon.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(24), Utils.dpToPixel(24)));
        ivEditIcon.setImageResource(iconEditText);

        EditText etEditFields = new EditText(this);
        etEditFields.setLayoutParams(new LinearLayout.LayoutParams(Utils.getScreenWidth() / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        etEditFields.setHint(hintEditText);
        etEditFields.setId(edittextId);
        etEditFields.setMaxLines(1);
        etEditFields.setOnFocusChangeListener(new ViewEventClickListener());
        etEditFields.setTextColor(getResources().getColor(R.color.white_300));
        etEditFields.setTextSize(16);
        etEditFields.setSingleLine(true);
        etEditFields.setHintTextColor(getResources().getColor(R.color.gray_300));
        etEditFields.setBackground(null);
        if (edittextId == R.id.user_password)
            etEditFields.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        else
            etEditFields.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);


        linearLayout.addView(ivEditIcon);
        linearLayout.addView(etEditFields);

        return linearLayout;
    }

    public View getGapView(int iHeight)
    {
        View gapView = new View(this);
        gapView.setLayoutParams(new LinearLayout.LayoutParams(0, Utils.dpToPixel(iHeight)));
        return gapView;
    }

    public ShadowRectLayout getSignInButton()
    {
        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(this);
        shadowRectLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        shadowRectLayout.setOffSetY(15);
        shadowRectLayout.setOffSetX(0);
        shadowRectLayout.setShadowRadius(20);
        shadowRectLayout.setImgGradientColor1(getResources().getColor(R.color.pure_black));
        shadowRectLayout.setShadowColor(getResources().getColor(R.color.shaow_black));
        shadowRectLayout.setId(R.id.singin_layout);
        shadowRectLayout.setOnClickListener(new ViewEventClickListener());
        shadowRectLayout.setRoundCornerRadius(i40dp);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(Utils.getScreenWidth() / 2 + Utils.dpToPixel(40), ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("Log In");
        //        textView.setAllCaps(true);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setTextColor(Utils.getColorStateListDrawable(Color.WHITE, getResources().getColor(R.color.pure_black)));
        textView.setPadding(0, i15dp, 0, i15dp);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        shadowRectLayout.addView(textView);
        return shadowRectLayout;
    }



    class ViewEventClickListener implements View.OnClickListener, View.OnFocusChangeListener {
        @Override
        public void onClick(View view)
        {
            if (view.getId() == R.id.back_arrow)
            {
                finish();
            } else if (view.getId() == R.id.singin_layout)
            {

                final TextView textView = findViewById(R.id.tv_loginError);
                textView.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        textView.setVisibility(View.INVISIBLE);
                    }
                }, 1500);


            } else if (view.getId() == R.id.singup_layout)
            {

//                Toast.makeText(SingUpActivity.this, "Todo", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SingUpActivity.this,CleanTapLoginActivtiy.class);
                startActivity(intent);
            }

        }

        @Override
        public void onFocusChange(View view, boolean b)
        {
            LinearLayout layout = (LinearLayout) view.getParent();
            if (b)
            {
                layout.setBackground(getResources().getDrawable(R.drawable.focus_bottom_line_true));
            } else
            {
                ViewParent parent = view.getParent();
                layout.setBackground(getResources().getDrawable(R.drawable.focus_bottom_line_false));


            }
        }
    }
}
