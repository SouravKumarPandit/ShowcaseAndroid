package com.application.idea.sourav.showcaseandroid.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

@SuppressLint("ValidFragment")
public class ProfileInfoFragment extends Fragment {


    private final Context clContext;
    private int i3dp;
    private int i10dp;
    private int i5dp;
    private int i20dp;
    private int i40dp;

    public ProfileInfoFragment(Context clContext)
    {
        this.clContext = clContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return getProfileView();

    }

    public View getProfileView()
    {
        LinearLayout profileLayout = new LinearLayout(clContext);
        profileLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        profileLayout.setOrientation(LinearLayout.VERTICAL);

        profileLayout.addView(getScrollableContent());
        profileLayout.addView(getIcontLayout());
        return profileLayout;
    }

    public ScrollView getScrollableContent()
    {
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        ScrollView scrollableContent = new ScrollView(clContext);
        scrollableContent.setFillViewport(true);
        scrollableContent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        LinearLayout linearLayout = new LinearLayout(clContext);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.about_background));
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setPadding(i10dp, i10dp, i10dp, i20dp);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ImageView ivProfile = new ImageView(clContext);
        ivProfile.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(200), Utils.dpToPixel(200)));
        ivProfile.setImageResource(R.drawable.ic_profile_icon);

        TextView tvHeading = new TextView(clContext);
        tvHeading.setText("Independent Product Designer");
        tvHeading.setTextColor(Color.DKGRAY);
        tvHeading.setPadding(i3dp,0,0,i3dp);
        tvHeading.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        tvHeading.setTypeface(Typeface.DEFAULT_BOLD);

        TextView tvDiscription = new TextView(clContext);
//        tvDiscription.setText(getResources().getString(R.string.about_page_text));
        tvDiscription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvDiscription.setPadding(i3dp,0,0,i3dp);
        tvDiscription.setTextColor(Color.GRAY);
        //        tvDiscription.setTypeface(Typeface.DEFAULT_BOLD);
        tvDiscription.setText(getResources().getString(R.string.about_page_text));
        makeTextViewResizable(tvDiscription, 4, "See More", true);

        TextView tvKnowMore = new TextView(clContext);
        tvKnowMore.setText("Know More");
        tvKnowMore.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvKnowMore.setTypeface(Typeface.DEFAULT_BOLD);
        tvKnowMore.setOnClickListener(new DialogEventClickListener());
        tvKnowMore.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvKnowMore.setTextColor(Utils.getColorStateListDrawable(Color.GRAY, Color.DKGRAY));




        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(clContext);
        shadowRectLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        shadowRectLayout.setOffSetY(15);
        shadowRectLayout.setOffSetX(0);
        shadowRectLayout.setShadowRadius(20);
        shadowRectLayout.setShadowColor(0xbfaaa5fc);
        shadowRectLayout.setImgGradientColor1(getResources().getColor(R.color.about_purple));
        shadowRectLayout.setId(R.id.hire_me_layout);
        //        shadowRectLayout.setImgGradientColor2(getResources().getColor(R.color.about_purple));
        shadowRectLayout.setOnClickListener(new DialogEventClickListener());
        shadowRectLayout.setRoundCornerRadius(i40dp);
        TextView textView = new TextView(clContext);
        textView.setText("Hire Me");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setTextColor(Color.WHITE);
        textView.setPadding(i40dp, i10dp, i40dp, i10dp);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        shadowRectLayout.addView(textView);

        linearLayout.addView(ivProfile);
        linearLayout.addView(tvHeading);
        linearLayout.addView(tvDiscription);
        linearLayout.addView(shadowRectLayout);
        linearLayout.addView(tvKnowMore);


        scrollableContent.addView(linearLayout);
        return scrollableContent;
    }


    public LinearLayout getIcontLayout()
    {
        LinearLayout linearLayout = new LinearLayout(clContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setPadding(i10dp, i10dp, i10dp, i20dp);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setBackgroundColor(Color.WHITE);
        linearLayout.addView(getIconSet(R.drawable.ic_dribble_big_logo, 5.5f));
        linearLayout.addView(getIconSet(R.drawable.ic_instagram_logo, 15.5f));
        linearLayout.addView(getIconSet(R.drawable.ic_twitter, 1.6f));
        linearLayout.addView(getIconSet(R.drawable.ic_facebook, 1.3f));
        return linearLayout;
    }

    public LinearLayout getIconSet(@DrawableRes int imgResource, float fFloat)
    {
        LinearLayout linearLayout = new LinearLayout(clContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams linearLayoutpram = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutpram.setMargins(i10dp, i10dp, i10dp, i10dp);
        linearLayout.setLayoutParams(linearLayoutpram);

        TextView tvFollower = new TextView(clContext);
        tvFollower.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvFollower.setTextColor(Color.GRAY);
        //        tvFollower.setTypeface(Typeface.DEFAULT_BOLD);
        tvFollower.setGravity(Gravity.CENTER);
        tvFollower.setText(String.valueOf(fFloat) + "K");

        ImageView ivIcon = new ImageView(clContext);
        LinearLayout.LayoutParams iconIvParam = new LinearLayout.LayoutParams(Utils.dpToPixel(42), Utils.dpToPixel(42));
        ivIcon.setPadding(i5dp, i5dp, i5dp, i5dp);
        Drawable icon = Utils.getIcon(clContext, imgResource, Color.DKGRAY);
        ivIcon.setImageDrawable(icon);
        ivIcon.setBackground(Utils.getRoundDrawableListState(Color.TRANSPARENT, 32f, getResources().getColor(R.color.about_text), 32));
        ivIcon.setOnClickListener(new ProfileInfoFragment.DialogEventClickListener());
        ivIcon.setLayoutParams(iconIvParam);
        linearLayout.addView(ivIcon);
        linearLayout.addView(tvFollower);

        return linearLayout;
    }
    public void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }
    private SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv, final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false){
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, ".. See More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }
    public class MySpannable extends ClickableSpan {

        private boolean isUnderline = true;

        /**
         * Constructor
         */
        public MySpannable(boolean isUnderline) {
            this.isUnderline = isUnderline;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(isUnderline);
            ds.setColor(Color.parseColor("#1b76d3"));
        }

        @Override
        public void onClick(View widget) {


        }
    }

    private class DialogEventClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {
            if (view.getId() == R.id.hire_me_layout)
            {

                AboutExtraDialog clNewNoteDialog = new AboutExtraDialog(clContext);
                FragmentManager clFragmentManager = ((AppCompatActivity) clContext).getSupportFragmentManager();
                clNewNoteDialog.show(clFragmentManager, "About");
            }

        }
    }
}
