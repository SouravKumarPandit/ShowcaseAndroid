package com.application.idea.sourav.showcaseandroid.ui.customAdapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.CardSliderItemDTO;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;


public class CardInfo1Adapter extends RecyclerView.Adapter<CardInfo1Adapter.CardData> {
    private final Context mContext;
    private ArrayList<CardSliderItemDTO> cardSliderItemDTO;

    public CardInfo1Adapter(Activity mContext, ArrayList<CardSliderItemDTO> dtoArrayList) {
        this.mContext = mContext;
        this.cardSliderItemDTO =dtoArrayList;

    }

    @NonNull
    @Override
    public CardData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        int radii = Utils.dpToPixel(15);
//        int radii = 4;
        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(mContext);
        shadowRectLayout.setClipToPadding(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            shadowRectLayout.setClipToOutline(false);
        }
        shadowRectLayout.setClipChildren(false);
        shadowRectLayout.setOffSetY(15);
        shadowRectLayout.setOffSetX(0);
        shadowRectLayout.setImgGradientColor2(0xD2F47B36);
        shadowRectLayout.setImgGradientColor1(0xDEC9691B);
        shadowRectLayout.setId(R.id.shadowrectLayout);
        shadowRectLayout.setResDrawable(R.drawable.metting_img);
//        shadowRectLayout.setShadowColorAuto(true);
        shadowRectLayout.setShadowRadius(20);
        shadowRectLayout.setRoundCornerRadius(30);
        shadowRectLayout.setShadowColor(0xDEFF6309);


        LinearLayout roundLinearLayout = new LinearLayout(mContext);
        roundLinearLayout.setPadding(Utils.dpToPixel(15), Utils.dpToPixel(25), Utils.dpToPixel(8), Utils.dpToPixel(8));
        roundLinearLayout.setOrientation(LinearLayout.VERTICAL);
        roundLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(200),Utils.dpToPixel(150)));

        TextView headLable = new TextView(mContext);
        headLable.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        headLable.setText("Focus Series");
        headLable.setTextColor(mContext.getResources().getColor(R.color.pure_white));
//        headLable.setTextColor(mContext.getResources().getColor(R.color.pure_black));
        headLable.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        headLable.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);


        TextView subText = new TextView(mContext);
        subText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        LinearLayout.LayoutParams subTextParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        subTextParam.setMargins(0, Utils.dpToPixel(5), 0, 0);
        subText.setLayoutParams(subTextParam);
        subText.setTextColor(Color.WHITE);
        subText.setText("Tweeter");
        subText.setTextColor(mContext.getResources().getColor(R.color.pure_white));
//        subText.setTextColor(mContext.getResources().getColor(R.color.pure_black));
        subText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        subText.setPadding(Utils.dpToPixel(8), Utils.dpToPixel(3), Utils.dpToPixel(8), Utils.dpToPixel(3));

        GradientDrawable textGradiantDrawable = new GradientDrawable();
        textGradiantDrawable.setCornerRadius(Utils.dpToPixel(25));
        textGradiantDrawable.setColor(0x5EFFFFFF);
        subText.setBackground(textGradiantDrawable);

        roundLinearLayout.addView(headLable);
        roundLinearLayout.addView(subText);
        shadowRectLayout.addView(roundLinearLayout);


        CardData cardData = new CardData(shadowRectLayout);
        return cardData;
    }

    @Override
    public void onBindViewHolder(@NonNull CardInfo1Adapter.CardData cardData, int i) {

        cardData.shadowRectLayout.setImgGradientColor1(cardSliderItemDTO.get((5+i)%8).getiColor1());
        cardData.shadowRectLayout.setImgGradientColor2(cardSliderItemDTO.get((5+i)%8).getiColor2());
//        cardData.shadowRectLayout.setShadowColor(cardSliderItemDTO.get((i+5 )% 8).getiColor3());
        cardData.shadowRectLayout.setShadowColorAuto(true);
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    class CardData extends RecyclerView.ViewHolder {
        ShadowRectLayout shadowRectLayout;
        public CardData(@NonNull View itemView)
        {
            super(itemView);
            shadowRectLayout=itemView.findViewById(R.id.shadowrectLayout);
        }
    }

}
