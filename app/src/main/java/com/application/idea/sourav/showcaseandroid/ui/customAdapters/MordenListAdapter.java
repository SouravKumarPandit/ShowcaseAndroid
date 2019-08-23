package com.application.idea.sourav.showcaseandroid.ui.customAdapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.utils.LabelTextView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

public class MordenListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  int[] imgResources = new int[]{R.drawable.profile_0,R.drawable.profile_1, R.drawable.profile_2, R.drawable.profile_3, R.drawable.profile_4};
    private final Context clContext;
    private final ArrayList<String> profileNames;
    private int i3dp;
    private int i5dp;
    private int i10dp;
    private int i15dp;
    private int i20dp;
    private int i40dp;


    public MordenListAdapter(Context clContext,ArrayList<String> profileNames) {
        this.clContext = clContext;
        this.profileNames = profileNames;

        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
    }

    class ViewHolderFirst extends RecyclerView.ViewHolder {

        public ViewHolderFirst(View itemView) {
            super(itemView);

        }
    }

    class ViewHolderDefault extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView  ivImage;
        float lastScale = 0;

        public ViewHolderDefault(View itemView, TextView textView, ImageView ivImage) {
            super(itemView);
            this.txtName = textView;
            this.ivImage=ivImage;
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        switch (viewType) {
            case 0:

                View firstView = getFirstView();
                return new ViewHolderFirst(firstView);
            default:

                View defaultView = getFloatingView();
                TextView txtname = defaultView.findViewById(R.id.user_name);
                ImageView ivImage =  defaultView.findViewById(R.id.user_image);
                return new ViewHolderDefault(defaultView, txtname,ivImage);

        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderFirst viewHolderFirst = (ViewHolderFirst) holder;
                break;

            case 2:
                ViewHolderDefault viewHolderDefault = (ViewHolderDefault) holder;
                viewHolderDefault.txtName.setText(profileNames.get(position-1));
                viewHolderDefault.ivImage.setImageResource(imgResources[position%5]);

                break;
        }

    }


    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (position == 0)
            return 0;
        else
            return 2;


    }


    @Override
    public int getItemCount() {
        return profileNames.size()+1;
    }



    public View getFloatingView() {


        LinearLayout llContainerLayout = new LinearLayout(clContext);
        LinearLayout.LayoutParams leftLinearLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llContainerLayout.setLayoutParams(leftLinearLayoutParam);
        llContainerLayout.setId(R.id.ll_ContentLayout);
        llContainerLayout.setPadding(i5dp,i5dp,i10dp,i20dp);


        LinearLayout rightLinerlayout = new LinearLayout(clContext);
        rightLinerlayout.setPadding(i15dp, 0, 0, 0);
        rightLinerlayout.setGravity(Gravity.CENTER);
        rightLinerlayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams RightLinerlayoutParam = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 8);
        rightLinerlayout.setId(R.id.at_rightLayout);
        rightLinerlayout.setLayoutParams(RightLinerlayoutParam);


        TextView nameText = new TextView(clContext);
        nameText.setGravity(Gravity.START);
        RelativeLayout.LayoutParams nameTextparam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameText.setLayoutParams(nameTextparam);
        nameText.setId(R.id.user_name);
        nameText.setTypeface(Typeface.DEFAULT_BOLD);
        nameText.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        nameText.setText("Name");


        TextView profileDetail = new TextView(clContext);
        profileDetail.setGravity(Gravity.START);
        RelativeLayout.LayoutParams profileDetailparam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        profileDetail.setLayoutParams(profileDetailparam);
        profileDetail.setPadding(0,i3dp,0,0);
        profileDetail.setId(R.id.user_name);
        profileDetail.setText("12 Following");


        ImageView profilePic = new ImageView(clContext);
        profilePic.setId(R.id.user_image);
        RelativeLayout.LayoutParams profilePicParam = new RelativeLayout.LayoutParams(Utils.dpToPixel(55), Utils.dpToPixel(55));
        profilePicParam.addRule(RelativeLayout.CENTER_VERTICAL);
        profilePicParam.setMargins(i10dp, 0, 0, 0);


        profilePic.setLayoutParams(profilePicParam);
        profilePic.setImageDrawable(clContext.getResources().getDrawable(R.drawable.profile_1));


        rightLinerlayout.addView(nameText);
        rightLinerlayout.addView(profileDetail);

        llContainerLayout.addView(profilePic);
        llContainerLayout.addView(rightLinerlayout);
        return llContainerLayout;
    }

    public View getFirstView() {
        LinearLayout listHeading = new LinearLayout(clContext);
        ViewGroup.LayoutParams topBlockParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //        topBlock.setBackgroundColor(Color.DKGRAY);
        listHeading.setOrientation(LinearLayout.VERTICAL);
        listHeading.setLayoutParams(topBlockParam);

        LabelTextView tvHeading = new LabelTextView(clContext);
        tvHeading.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvHeading.setGravity(Gravity.START);
        tvHeading.setPadding(i15dp,i5dp, 0, i3dp);
        tvHeading.setText("F O L L O W E R   L I S T");
        tvHeading.setMandatory(true,null,0xFF70B2EE,Utils.dpToPixel(3),24);
        tvHeading.setTextColor(Color.BLACK);
        tvHeading.setTypeface(Typeface.DEFAULT_BOLD);
        tvHeading.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);

        TextView matchesName = new TextView(clContext);
        matchesName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        matchesName.setGravity(Gravity.END);
        matchesName.setText("24 Matches");
        matchesName.setTextColor(Color.GRAY);
        matchesName.setPadding(0, 0, i5dp,i3dp);
        matchesName.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

        View divider = new View(clContext);
        LinearLayout.LayoutParams dividerParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
        dividerParam.setMargins(i40dp, i3dp, i3dp, i5dp);
        divider.setLayoutParams(dividerParam);
        divider.setBackgroundColor(clContext.getResources().getColor(R.color.gray_500));

        listHeading.addView(tvHeading);
        listHeading.addView(matchesName);
        listHeading.addView(divider);


        return listHeading;
    }


}