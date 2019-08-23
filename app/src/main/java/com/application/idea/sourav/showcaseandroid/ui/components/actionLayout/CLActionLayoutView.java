package com.application.idea.sourav.showcaseandroid.ui.components.actionLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

@SuppressLint("ViewConstructor")
public class CLActionLayoutView extends LinearLayout
{

    private boolean iconRight=false;
    private TextView tvContent;
    private TextView tvInfoTextView;
    private ImageView iconImage;
    private int i10dp;
    private int i20dp;
    private Context clContext;


    public OnPerformUserAction onPerformUserAction;
    public CLActionLayoutView(Context context, Drawable iconDrawable, final String actionLabel, final String actionInfo, final int actionId, boolean iconRight, final OnPerformUserAction onPerformUserAction)
    {
        super(context);
        this.iconRight=iconRight;
        initView(context, iconDrawable, actionLabel, actionInfo, actionId, onPerformUserAction);
    }
    public CLActionLayoutView(Context context, Drawable iconDrawable, final String actionLabel, final String actionInfo, final int actionId, final OnPerformUserAction onPerformUserAction)
    {
        super(context);
        initView(context, iconDrawable, actionLabel, actionInfo, actionId, onPerformUserAction);
    }

    public void initView(Context context, Drawable iconDrawable, final String actionLabel, final String actionInfo, final int actionId, final OnPerformUserAction onPerformUserAction){


        if (onPerformUserAction != null)
            this.onPerformUserAction = onPerformUserAction;
        clContext = context;
        i10dp = Utils.dpToPx(10);
        i20dp = Utils.dpToPx(20);
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.setBackground(Utils.getSelectorDrawable(0xFF45612e));
        this.setGravity(Gravity.CENTER_VERTICAL);

        tvInfoTextView = new TextView(clContext);
        tvInfoTextView.setText(actionLabel);
        tvInfoTextView.setTextColor(getResources().getColor(R.color.gray_900));
        tvInfoTextView.setSingleLine(true);
        tvInfoTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvInfoTextView.setTextIsSelectable(true);


        if (actionInfo != null)
        {

            LinearLayout clLinearLayout = new LinearLayout(clContext);

            clLinearLayout.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            clLinearLayout.setOrientation(LinearLayout.VERTICAL);
            clLinearLayout.setPadding(i10dp, 0, 0, 0);
            tvContent = new TextView(clContext);
            tvContent.setText(actionInfo);
            tvContent.setTextColor(getResources().getColor(R.color.gray_500));
            tvContent.setSingleLine(true);
            tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tvContent.setPadding(Utils.dpToPx(5), 0, 0, 0);
            tvContent.setText(actionInfo);
            tvContent.setTextIsSelectable(true);
            clLinearLayout.addView(tvInfoTextView);
            clLinearLayout.addView(tvContent);
            if (iconRight)
            {
                this.setPadding(i20dp, i10dp, i20dp+i10dp, i10dp);
                this.addView(clLinearLayout);
                if (iconDrawable != null)
                {
                    iconImage = new ImageView(clContext);
                    this.addView(iconImage);
//        iconImage.setImageResource(iconRes);
                    iconImage.setImageDrawable(iconDrawable);
                    iconImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    iconImage.setLayoutParams(new LayoutParams(Utils.dpToPx(35), Utils.dpToPx(35)));
                    iconImage.setBackground(Utils.getSelectorDrawable(0xFF45612e));
                    iconImage.setOnClickListener(new OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            if (onPerformUserAction != null)
                            {
                                onPerformUserAction.onActionOnUser(actionId, actionLabel, actionInfo);
                            }
                        }
                    });
                }
            } else
            {
                this.setPadding(i20dp, i10dp, i20dp, i10dp);
                this.setGravity(Gravity.CENTER_HORIZONTAL);

                if (iconDrawable != null)
                {
                    iconImage = new ImageView(clContext);
                    this.addView(iconImage);
                    iconImage.setImageDrawable(iconDrawable);
                    iconImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    iconImage.setLayoutParams(new LayoutParams(Utils.dpToPx(35), Utils.dpToPx(35)));
                }
                this.setBackground(Utils.getSelectorDrawable(0xFF45612e));
                this.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (onPerformUserAction != null)
                        {
                            onPerformUserAction.onActionOnUser(actionId, actionLabel, actionInfo);
                        }
                    }
                });
                this.addView(clLinearLayout);

            }


        } else
        {
            tvInfoTextView.setPadding(i10dp, 0, 0, 0);
            this.addView(tvInfoTextView);
        }
    }

    public TextView getTvContent()
    {
        return tvContent;
    }


    public ImageView getIconImage()
    {
        return iconImage;
    }

    public void setInfoText(String infoText)
    {
        if (tvContent != null) if (infoText != null && !infoText.equals(""))
            tvContent.setText(infoText);
        else{

            tvContent.setText("( empty )");
            tvContent.setTextColor(Color.LTGRAY);
        }

    }

    public void setTitleText(String titleText)
    {
        if (tvInfoTextView != null) if (titleText != null && !titleText.equals(""))
            tvInfoTextView.setText(titleText);
        else{
            tvInfoTextView.setText("( empty )");
            tvInfoTextView.setTextColor(Color.LTGRAY);
        }

    }

    public void setOnPerformUserAction(OnPerformUserAction onPerformUserAction)
    {
        this.onPerformUserAction = onPerformUserAction;
    }

    public TextView getTvInfoTextView()
    {
        return tvInfoTextView;
    }

    public void setTvInfoTextView(TextView tvInfoTextView)
    {
        this.tvInfoTextView = tvInfoTextView;
    }

    public interface OnPerformUserAction
    {
        public void onActionOnUser(int actionId, String actionLabel, String actionInfo);
    }
}