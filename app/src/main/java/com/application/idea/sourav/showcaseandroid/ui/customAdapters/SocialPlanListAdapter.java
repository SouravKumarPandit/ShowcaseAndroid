package com.application.idea.sourav.showcaseandroid.ui.customAdapters;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
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
import com.application.idea.sourav.showcaseandroid.models.SocialPlanInfoDTO;
import com.application.idea.sourav.showcaseandroid.ui.components.shadowRect.ShadowRectLayout;
import com.application.idea.sourav.showcaseandroid.utils.CircleImageView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

public class SocialPlanListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  int[] imgResources = new int[]{R.drawable.profile_0,R.drawable.profile_1, R.drawable.profile_2, R.drawable.profile_3, R.drawable.profile_4};
    private final Context clContext;
    private final ArrayList<SocialPlanInfoDTO> profileNames;
    private int i3dp;
    private int i5dp;
    private int i10dp;
    private int i15dp;
    private int i20dp;
    private int i40dp;


    public SocialPlanListAdapter(Context clContext, ArrayList<SocialPlanInfoDTO> profileNames) {
        this.clContext = clContext;
        this.profileNames = profileNames;

        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
    }


    class ViewHolderDefault extends RecyclerView.ViewHolder {
        private final TextView txtTimeAgo;
        private final TextView txtName;
        private final TextView txtUserPlan;
        private final  ImageView  ivImage;

        public ViewHolderDefault(View itemView) {
            super(itemView);
            this.txtName = itemView.findViewById(R.id.user_name);
            this.txtUserPlan = itemView.findViewById(R.id.user_plans);
            this.txtTimeAgo = itemView.findViewById(R.id.time_ago);
            this.ivImage= itemView.findViewById(R.id.user_image);;
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolderDefault(getListItems());


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

                ViewHolderDefault viewHolderDefault = (ViewHolderDefault) holder;
                viewHolderDefault.txtName.setText(profileNames.get(position).getsUserName());
                viewHolderDefault.txtUserPlan.setText(profileNames.get(position).getsUserPlans());
                viewHolderDefault.txtTimeAgo.setText(profileNames.get(position).getsTimeAgo());
                viewHolderDefault.ivImage.setImageResource(imgResources[position%5]);

    }




    @Override
    public int getItemCount() {
        return profileNames.size();
    }



    public View getListItems() {
        ShadowRectLayout shadowRectLayout = new ShadowRectLayout(clContext);
        shadowRectLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        shadowRectLayout.setOffSetX(5);
        shadowRectLayout.setOffSetY(5);
        shadowRectLayout.setShadowRadius(8);
        shadowRectLayout.setShadowColor(0xFFE8E8EA);
        shadowRectLayout.setRoundCornerRadius(i5dp);

        LinearLayout llContainerLayout = new LinearLayout(clContext);
        LinearLayout.LayoutParams leftLinearLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llContainerLayout.setLayoutParams(leftLinearLayoutParam);
        llContainerLayout.setGravity(Gravity.CENTER_VERTICAL);
        llContainerLayout.setId(R.id.ll_ContentLayout);
        llContainerLayout.setPadding(i5dp,i5dp,i5dp,i5dp);


        LinearLayout rightLinerlayout = new LinearLayout(clContext);
        rightLinerlayout.setPadding(i5dp, 0, 0, 0);
        rightLinerlayout.setGravity(Gravity.CENTER);
        rightLinerlayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams RightLinerlayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightLinerlayout.setId(R.id.at_rightLayout);

        rightLinerlayout.setLayoutParams(RightLinerlayoutParam);


        TextView nameText = new TextView(clContext);
        nameText.setGravity(Gravity.START);
        LinearLayout.LayoutParams nameTextparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameText.setLayoutParams(nameTextparam);
        nameText.setId(R.id.user_name);
        nameText.setTypeface(Typeface.DEFAULT_BOLD);
        Drawable crossHairDrawable= Utils.getIcon(clContext,R.drawable.ic_select_s, Color.LTGRAY);
        nameText.setCompoundDrawablesWithIntrinsicBounds(null,null,crossHairDrawable,null);
        nameText.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        nameText.setTextColor(clContext.getResources().getColor(R.color.sw7_textColor));
        nameText.setPadding(0,0,i15dp,0);
        nameText.setText("Name");


        TextView tvUserPlans = new TextView(clContext);
        tvUserPlans.setGravity(Gravity.START);
        LinearLayout.LayoutParams profileDetailparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvUserPlans.setPadding(0,0,i40dp,0);
        tvUserPlans.setLayoutParams(profileDetailparam);
        tvUserPlans.setId(R.id.user_plans);
        tvUserPlans.setMaxLines(2);
        tvUserPlans.setTextColor(clContext.getResources().getColor(R.color.gray_500 ));
        tvUserPlans.setText("12 Following");

        TextView tvTimeAgo = new TextView(clContext);
        LinearLayout.LayoutParams timeAgoparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvTimeAgo.setGravity(Gravity.END);
        tvTimeAgo.setPadding(0,0,i15dp,0);
        tvTimeAgo.setLayoutParams(timeAgoparam );
        tvTimeAgo.setId(R.id.time_ago);
        tvTimeAgo.setTextColor(clContext.getResources().getColor(R.color.gray_500 ));
        tvTimeAgo.setText("just Now");

        CircleImageView profilePic = new CircleImageView(clContext);
        profilePic.setId(R.id.user_image);
        LinearLayout.LayoutParams profilePicParam = new LinearLayout.LayoutParams(Utils.dpToPixel(55), Utils.dpToPixel(55));
//        profilePicParam.addRule(RelativeLayout.CENTER_VERTICAL);
        profilePicParam.setMargins(i5dp, 0, i5dp, 0);
        profilePic.setLayoutParams(profilePicParam);
        profilePic.setImageDrawable(clContext.getResources().getDrawable(R.drawable.profile_1));
        ColorFilter cf = new PorterDuffColorFilter(0xC2FFFFFF, PorterDuff.Mode.MULTIPLY);
        profilePic.setColorFilter(cf);

        rightLinerlayout.addView(nameText);
        rightLinerlayout.addView(tvUserPlans);
        rightLinerlayout.addView(tvTimeAgo);

        llContainerLayout.addView(profilePic);
        llContainerLayout.addView(rightLinerlayout);
        shadowRectLayout.addView(llContainerLayout);
        return shadowRectLayout;
    }




}