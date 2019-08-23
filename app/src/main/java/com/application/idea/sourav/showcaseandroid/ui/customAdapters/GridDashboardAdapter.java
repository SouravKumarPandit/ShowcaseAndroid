package com.application.idea.sourav.showcaseandroid.ui.customAdapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.KeyValueDTO;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.utils.CircleImageView;
import com.application.idea.sourav.showcaseandroid.utils.RatioLinearLayout;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

public class GridDashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final ArrayList<KeyValueDTO> keyValueDTOS;

    public static final int TYPE_FIRST_ITEM = 0;
    public static final int TYPE_ITEM = 1;
    private int i3dp;
    private int i5dp;
    private int i10dp;
    private int i15dp;

    public GridDashboardAdapter(Activity mContext, ArrayList<KeyValueDTO> keyValueDTOS)
    {
        this.mContext = mContext;
        this.keyValueDTOS = keyValueDTOS;

    }


    @NonNull
    @Override
    public GridItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {


        i5dp = Utils.dpToPixel(5);
        i3dp = Utils.dpToPixel(3);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);

        switch (viewType)
        {
            case 0:
                View firstView = getHeaderLayout();
                return new HeadSectionHolder(firstView);
            default:

                View defaultView = getItemCardView();
                return new GridItemHolder(defaultView);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int iPosition)
    {
        switch (viewHolder.getItemViewType())
        {
            case 0:
                HeadSectionHolder headSectionHolder = (HeadSectionHolder) viewHolder;
                break;

            default:
                GridItemHolder viewHolderDefault = (GridItemHolder) viewHolder;
                viewHolderDefault.ivItemIcom.setImageResource(keyValueDTOS.get(iPosition-1).getValue());
                viewHolderDefault.tvLable.setText(keyValueDTOS.get(iPosition-1).getKey());
                break;
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (position == 0)
            return TYPE_FIRST_ITEM;
        else
            return TYPE_ITEM;

    }

    @Override
    public int getItemCount()
    {
        return keyValueDTOS.size()+1;
    }


    public LinearLayout getHeaderLayout()
    {
        LinearLayout headerLayout = new LinearLayout(mContext);
        headerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        headerLayout.setOrientation(LinearLayout.VERTICAL);
        headerLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        headerLayout.setPadding(0, 0, 0, i10dp);


        CircleImageView circleImageView = new CircleImageView(mContext);
        circleImageView.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPixel(85), Utils.dpToPixel(85)));
        circleImageView.setBorderColor(Color.WHITE);
        circleImageView.setImageResource(R.drawable.profile_3);
        circleImageView.setBorderWidth(i3dp);

        TextView tvProfileName = new TextView(mContext);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(0, i5dp, 0, 0);
//        tvProfileName.setTypeface(Typeface.DEFAULT_BOLD);
        tvProfileName.setLayoutParams(textParams);
        tvProfileName.setGravity(Gravity.CENTER);
        tvProfileName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tvProfileName.setText("Reeve Taylor");
        tvProfileName.setPadding(i15dp, i5dp, i15dp, i5dp);
        tvProfileName.setTextColor(Color.BLACK);
        GradientDrawable roundGradiant1 = new GradientDrawable();
        roundGradiant1.setCornerRadius(Utils.dpToPixel(25));
        roundGradiant1.setColor(0xbfecf1f4);
        tvProfileName.setBackground(roundGradiant1);

        TextView tvsubText = new TextView(mContext);
        tvsubText.setTypeface(Typeface.DEFAULT_BOLD);
        LinearLayout.LayoutParams textParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams1.setMargins(0, i5dp, 0, 0);
        tvsubText.setLayoutParams(textParams1);
        tvsubText.setText("80 PTS");
        tvsubText.setPadding(i10dp, i3dp,i10dp, i3dp);
        tvsubText.setGravity(Gravity.CENTER);
        tvsubText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvsubText.setTextColor(Color.BLACK);
        GradientDrawable roundGradiant2 = new GradientDrawable();
        roundGradiant2.setCornerRadius(Utils.dpToPixel(25));
        roundGradiant2.setColor(0xb0ecf1f4);
        tvsubText.setBackground(roundGradiant2);


        headerLayout.addView(circleImageView);
        headerLayout.addView(tvProfileName);
        headerLayout.addView(tvsubText);
        return headerLayout;
    }


    public View getItemCardView()
    {
        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(mContext);
        shadowRectLayout.setOffSetY(Utils.dpToPixel(1));
        shadowRectLayout.setOffSetX(0);
        shadowRectLayout.setShadowRadius(Utils.dpToPixel(1));
        shadowRectLayout.setId(R.id.shadowrectLayout);
        shadowRectLayout.setShadowColor(0xFFD1D4D6);
        shadowRectLayout.setRoundCornerRadius(i5dp);


        RatioLinearLayout linearLayout = new RatioLinearLayout(mContext, 4, 3);
        LinearLayout.LayoutParams linearLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParam);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setPadding(i10dp, i10dp, i10dp, i15dp);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackground(Utils.getRoundDrawableListState(Color.TRANSPARENT,i5dp,mContext.getResources().getColor(R.color.white_300),i5dp));
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });

        ImageView ivItemIcom = new ImageView(mContext);
        ivItemIcom.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPixel(80)));
        ivItemIcom.setId(R.id.card_imageview);
        ivItemIcom.setId(R.id.item_icon);
        ivItemIcom.setPadding(i5dp,i15dp,i5dp,i15dp);
        ivItemIcom.setImageResource(R.drawable.acost);
        ivItemIcom.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        TextView tvLable = new TextView(mContext);
        tvLable.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        tvProfileName.setTypeface(Typeface.DEFAULT_BOLD);
        tvLable.setId(R.id.item_lable);
        tvLable.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvLable.setText("Lable");
        tvLable.setAllCaps(true);
        tvLable.setTextColor(Color.BLACK);

        linearLayout.addView(ivItemIcom);
        linearLayout.addView(tvLable);
        shadowRectLayout.addView(linearLayout);
        return shadowRectLayout;
    }

    class GridItemHolder extends RecyclerView.ViewHolder {
        ShadowRectLayout shadowRectLayout;
        ImageView imageView;
        ImageView ivItemIcom;
        TextView tvLable;
        public GridItemHolder(@NonNull View itemView)
        {
            super(itemView);
            shadowRectLayout = itemView.findViewById(R.id.shadowrectLayout);
            imageView = itemView.findViewById(R.id.card_imageview);
            ivItemIcom=itemView.findViewById(R.id.item_icon);
            tvLable=itemView.findViewById(R.id.item_lable);
        }
    }

    private class HeadSectionHolder extends GridItemHolder {
        public HeadSectionHolder(View itemView)
        {
            super(itemView);
        }
    }
}
