package com.application.idea.sourav.showcaseandroid.ui.customAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.application.idea.sourav.showcaseandroid.models.ShowcaseClassDTO;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

public class ShowcaseAdapter extends RecyclerView.Adapter<ShowcaseAdapter.AdapterViewHolder>
{
    private final Context clContext;
    private final ArrayList<ShowcaseClassDTO> customerList;

    public ShowcaseAdapter(Context clContext, ArrayList<ShowcaseClassDTO> customerList)
    {
        this.clContext = clContext;
        this.customerList = customerList;
    }


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View customerItem = getCustomerView();
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.WHITE);
        shape.setCornerRadius(8f);
        customerItem.setBackground(shape);
        AdapterViewHolder adapter = new AdapterViewHolder(customerItem);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder adapterViewHolder, int i)
    {
        adapterViewHolder.textView.setText(customerList.get(i).getActivityName());
    }


    @Override
    public int getItemCount()
    {
        return customerList.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        LinearLayout linearLayout;
        TextView textView;

        AdapterViewHolder(@NonNull View itemView)
        {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.visit_button);
            textView = itemView.findViewById(R.id.customer_name);
            linearLayout.setOnClickListener(this);
        }


        @Override
        public void onClick(View view)
        {
            Intent intent = null;
            intent = new Intent(clContext, customerList.get(getAdapterPosition()).getActivityClass());
            clContext.startActivity(intent);
        }
    }

    public LinearLayout getCustomerView()
    {
        LinearLayout clCustomerInfo = new LinearLayout(clContext);
        //        clCustomerInfo.setPadding(i8_pad, i8_pad, i8_pad, i8_pad);
        clCustomerInfo.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams consumerLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clCustomerInfo.setLayoutParams(consumerLayoutParam);

        LinearLayout.LayoutParams cltxtParam = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 6);

        TextView className = new TextView(clContext);
        className.setPadding(Utils.dpToPixel(10),0,0,0);
        className.setGravity(Gravity.CENTER_VERTICAL);
        className.setLayoutParams(cltxtParam);
        className.setTextColor(clContext.getResources().getColor(R.color.gray_700));
        className.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        className.setId(R.id.customer_name);
        className.setText("class");

        LinearLayout clVisitLayout = new LinearLayout(clContext);
        clVisitLayout.setOrientation(LinearLayout.HORIZONTAL);
        clVisitLayout.setId(R.id.visit_button);
        LinearLayout.LayoutParams btVisitParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, Utils.dpToPixel(35));
        //                clVisitLayout.setPadding(20,0,0,0);
        btVisitParam.setMargins(0, 40, 40, 40);
        clVisitLayout.setLayoutParams(btVisitParam);
        TextView clVisittxt = new TextView(clContext);
        LinearLayout.LayoutParams txtVisitParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        txtVisitParam.setMargins(20, 0, 10, 0);
        clVisittxt.setLayoutParams(txtVisitParam);
        clVisittxt.setId(R.id.visit_button);
        clVisittxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        clVisittxt.setGravity(Gravity.CENTER);
        //        clVisittxt.setTextColor(clContext.getResources().getColor(R.color.green_light));
        clVisittxt.setTextColor(Utils.getColorStateListDrawable(clContext.getResources().getColor(R.color.green_basic), Color.WHITE));
        clVisittxt.setText("Visit   ");


        final ImageView imgArrow = new ImageView(clContext);

        ViewGroup.LayoutParams imgArrowParam = new LinearLayout.LayoutParams(Utils.dpToPixel(25), ViewGroup.LayoutParams.WRAP_CONTENT);
        imgArrow.setBackground(clContext.getResources().getDrawable(R.drawable.visit_arrow));
        imgArrow.setLayoutParams(imgArrowParam);
        imgArrow.setId(R.id.arrow_key);
        imgArrow.setImageDrawable(Utils.getDrawableListState(clContext, R.drawable.right_arrow_green, R.drawable.right_arrow_white));
        imgArrow.setPadding(8, 12, 8, 12);
        imgArrow.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //        imgArrow.setImageDrawable();


        clVisitLayout.setBackground(clContext.getResources().getDrawable(R.drawable.visit_button));
        clVisitLayout.addView(clVisittxt);
        clVisitLayout.addView(imgArrow);
        clCustomerInfo.addView(className);
        clCustomerInfo.addView(clVisitLayout);
        return clCustomerInfo;
    }

}
