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
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.ChatUserDTO;
import com.application.idea.sourav.showcaseandroid.utils.CircleImageView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder> {
    private final Context clContext;
    private final ArrayList<ChatUserDTO> chatUserDTOS;
    private int i10dp;
    private int i20dp;
    private int i40dp;
    private int i3dp;
    private int i5dp;
    private int i15dp;
    private int[] imgResources = new int[]{R.drawable.profile_0,R.drawable.profile_1, R.drawable.profile_2, R.drawable.profile_3, R.drawable.profile_4};
    private View selectedItem;

    public ArchiveAdapter(Context clContext, ArrayList<ChatUserDTO> chatUserDTOS)
    {
        this.clContext = clContext;
        this.chatUserDTOS = chatUserDTOS;
    }

    @NonNull
    @Override
    public ArchiveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        LinearLayout linearLayout = new LinearLayout(clContext);
        LinearLayout.LayoutParams linearLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParam.setMargins(0, 0, 0, i5dp);
        linearLayout.setLayoutParams(linearLayoutParam);
        linearLayout.setBackground(Utils.getRoundDrawableListState(Color.WHITE, i5dp, clContext.getResources().getColor(R.color.base_color_sw3), i5dp));
        linearLayout.setPadding(i5dp, i5dp, i5dp, i3dp);
        linearLayout.addView(getleftSideView());
        linearLayout.addView(getRightSideView());

        //        shadowRectLayout.addView(linearLayout);
        return new ArchiveViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchiveViewHolder archiveViewHolder, int i)
    {
        ChatUserDTO chatUserDTO = chatUserDTOS.get(i);
        archiveViewHolder.tvUserName.setText(chatUserDTO.getsSenderName());
        archiveViewHolder.tvUserRole.setText(chatUserDTO.getsUserRole());
        archiveViewHolder.tvTimeAgo.setText(chatUserDTO.getsTimeAgo());
        archiveViewHolder.tvDescription.setText(chatUserDTO.getsDescription());
        archiveViewHolder.ivSenderImg.setImageResource(imgResources[i % 5]);
        if (chatUserDTO.isFavorite())
            archiveViewHolder.ivFavIcon.setImageResource(R.drawable.ic_favorite_24dp);
        else
            archiveViewHolder.ivFavIcon.setImageResource(R.drawable.ic_favorite_border_24dp);

    }


    @Override
    public int getItemCount()
    {
        return chatUserDTOS.size();
    }

    private LinearLayout getleftSideView()
    {
        LinearLayout leftSideView = new LinearLayout(clContext);
        leftSideView.setOrientation(LinearLayout.VERTICAL);
        leftSideView.setGravity(Gravity.CENTER_HORIZONTAL);
        leftSideView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        CircleImageView ivSenderImg = new CircleImageView(clContext);
        ivSenderImg.setId(R.id.iv_sender_icon);
        LinearLayout.LayoutParams ivSenderImgPararm = new LinearLayout.LayoutParams(Utils.dpToPixel(40), Utils.dpToPixel(40));
        ivSenderImg.setLayoutParams(ivSenderImgPararm);
        ivSenderImg.setImageResource(R.drawable.profile_0);
        ImageView favIcon = new ImageView(clContext);
        LinearLayout.LayoutParams circleImageViewPararm = new LinearLayout.LayoutParams(Utils.dpToPixel(24), Utils.dpToPixel(24));
        circleImageViewPararm.setMargins(0, i10dp, 0, 0);
        favIcon.setLayoutParams(circleImageViewPararm);
        favIcon.setImageResource(R.drawable.ic_favorite_border_24dp);
        favIcon.setId(R.id.iv_favortie);

        leftSideView.addView(ivSenderImg);
        leftSideView.addView(favIcon);
        return leftSideView;
    }

    private LinearLayout getRightSideView()
    {
        LinearLayout rightSideView = new LinearLayout(clContext);
        rightSideView.setOrientation(LinearLayout.VERTICAL);
        rightSideView.setGravity(Gravity.CENTER_HORIZONTAL);
        rightSideView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        rightSideView.setPadding(i5dp, 0, 0, 0);

        LinearLayout nameDateLayout = new LinearLayout(clContext);
        nameDateLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView tvUserName = new TextView(clContext);
        tvUserName.setText("sender name");
        tvUserName.setId(R.id.sender_name);
        tvUserName.setTextColor(Utils.getColorStateListDrawable(clContext.getResources().getColor(R.color.base_color_sw3), Color.WHITE));
        tvUserName.setTypeface(Typeface.DEFAULT_BOLD);
        tvUserName.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        TextView tvTimeAgo = new TextView(clContext);
        tvTimeAgo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvTimeAgo.setText("time Ago");
        tvTimeAgo.setId(R.id.time_ago);
        tvTimeAgo.setPadding(0, 0, i5dp, 0);
        tvTimeAgo.setTextColor(Utils.getColorStateListDrawable(clContext.getResources().getColor(R.color.gray_400), Color.WHITE));
        tvTimeAgo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        TextView tvUserRole = new TextView(clContext);
        tvUserRole.setText("role");
        tvUserRole.setId(R.id.user_role);
        tvUserRole.setTextColor(Utils.getColorStateListDrawable(clContext.getResources().getColor(R.color.gray_600), Color.WHITE));
        tvUserName.setTypeface(Typeface.DEFAULT_BOLD);
        tvUserRole.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvUserRole.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView tvDescription = new TextView(clContext);
        tvDescription.setId(R.id.mail_description);
        tvDescription.setText("");
        tvDescription.setTextColor(Utils.getColorStateListDrawable(clContext.getResources().getColor(R.color.gray_400), Color.WHITE));
        tvDescription.setMaxLines(3);
        tvDescription.setMinLines(3);
        tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvDescription.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        nameDateLayout.addView(tvUserName);
        nameDateLayout.addView(tvTimeAgo);
        rightSideView.addView(nameDateLayout);
        rightSideView.addView(tvUserRole);
        rightSideView.addView(tvDescription);

        return rightSideView;
    }


    class ArchiveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvUserName, tvTimeAgo, tvUserRole, tvDescription;
        ImageView ivFavIcon;
        CircleImageView ivSenderImg;

        ArchiveViewHolder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            tvUserName = itemView.findViewById(R.id.sender_name);
            tvTimeAgo = itemView.findViewById(R.id.time_ago);
            tvUserRole = itemView.findViewById(R.id.user_role);
            tvDescription = itemView.findViewById(R.id.mail_description);
            ivSenderImg = itemView.findViewById(R.id.iv_sender_icon);
            ivFavIcon = itemView.findViewById(R.id.iv_favortie);
            ivFavIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (view.getId() == R.id.iv_favortie)
            {

                //                if ()
                chatUserDTOS.get(getAdapterPosition()).setFavorite(!chatUserDTOS.get(getAdapterPosition()).isFavorite());
                if (chatUserDTOS.get(getAdapterPosition()).isFavorite())
                    ivFavIcon.setImageResource(R.drawable.ic_favorite_24dp);
                else
                    ivFavIcon.setImageResource(R.drawable.ic_favorite_border_24dp);

            } else
            {
                if (selectedItem == null)
                {
                    selectedItem = view;
                } else
                {
                    if (selectedItem != view)
                    {
                        selectedItem.setSelected(false);
                        selectedItem = view;
                    }
                }
                selectedItem.setSelected(!view.isSelected());
            }

        }
    }
}
