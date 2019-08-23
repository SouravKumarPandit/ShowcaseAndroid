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
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.CardSliderItemDTO;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;


public class CardInfo2Adapter extends RecyclerView.Adapter<CardInfo2Adapter.CardData> {
    private final Context mContext;
    ArrayList<CardSliderItemDTO> cardSliderItemDTO;


    public CardInfo2Adapter(Activity mContext, ArrayList<CardSliderItemDTO> dtoArrayList) {
        this.mContext = mContext;
        this.cardSliderItemDTO =dtoArrayList;

    }


    @NonNull
    @Override
    public CardData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        int radii = Utils.dpToPixel(15);
        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(mContext);
        AbsListView.LayoutParams layoutParams=new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        shadowRectLayout.setLayoutParams(layoutParams);
        shadowRectLayout.setClipToPadding(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            shadowRectLayout.setClipToOutline(false);
        }
        shadowRectLayout.setClipChildren(false);

        shadowRectLayout.setOffSetY(15);
        shadowRectLayout.setOffSetX(0);
        shadowRectLayout.setImgGradientColor1(0xDE057912);
        shadowRectLayout.setImgGradientColor2(0xDE57CA4A);
        shadowRectLayout.setId(R.id.shadowrectLayout);
        shadowRectLayout.setResDrawable(R.drawable.metting_img);
//        shadowRectLayout.setShadowColorAuto(true);
        shadowRectLayout.setShadowRadius(20);
        shadowRectLayout.setRoundCornerRadius(30);
        shadowRectLayout.setShadowColor(0xDE057912);



        LinearLayout roundLinearLayout = new LinearLayout(mContext);
        roundLinearLayout.setPadding(Utils.dpToPixel(15), Utils.dpToPixel(25), Utils.dpToPixel(8), Utils.dpToPixel(8));
        roundLinearLayout.setOrientation(LinearLayout.VERTICAL);
        roundLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(250), Utils.dpToPixel(160)));

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
//        RoundLinearLayout linearLayout=shadowRectLayout.getRoundLinearLayout();
//        roundLinearLayout.setBackground(Utils.getGradiantDrawable(mContext,radii,0xDE057912, 0xDE57CA4A));
//        linearLayout.addView(roundLinearLayout);

        shadowRectLayout.addView(roundLinearLayout);

        CardData cardData = new CardData(shadowRectLayout);
        return cardData;
    }

    @Override
    public void onBindViewHolder(@NonNull CardInfo2Adapter.CardData cardData, int i) {

        cardData.shadowRectLayout.setImgGradientColor1(cardSliderItemDTO.get((2+i)%8).getiColor1());
        cardData.shadowRectLayout.setImgGradientColor2(cardSliderItemDTO.get((2+i)%8).getiColor2());
//        cardData.shadowRectLayout.setShadowColor(cardSliderItemDTO.get((i+2 )% 8).getiColor3());
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
