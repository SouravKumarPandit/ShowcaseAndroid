package com.application.idea.sourav.showcaseandroid.ui.activitys.recycleSelection;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.UserItemModel;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

//    private static int[] colorArray = {Color.BLACK, Color.BLUE, Color.GREEN, Color.GRAY, Color.RED};
    private static int[] colorArray={0xffea6092,0xff6b42de,0xff3fd1a0,0xffd27533,0xFFD0E11C,0xffd27533};

    private final Context mContext;


    private List<UserItemModel> mModelList;
    private OnItemSelectionEnable onItemSelectionEnable;

    public RecyclerViewAdapter(Context context, List<UserItemModel> modelList) {
        mModelList = modelList;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//       View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        LinearLayout itemView = getItemView(mContext);
        return new MyViewHolder(itemView);
    }

    private LinearLayout getItemView(Context mContext) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.CENTER);
        int i8dp = Utils.dpToPx(8);
        int i5dp = Utils.dpToPx(5);
        linearLayout.setPadding(i8dp, i8dp, i8dp, i8dp);
        ImageView ivUserImage = new ImageView(mContext);
        ivUserImage.setLayoutParams(new LinearLayout.LayoutParams(Utils.dpToPx(45), Utils.dpToPx(45)));
        ivUserImage.setId(R.id.item_image);
        ivUserImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        LinearLayout infoLayout = new LinearLayout(mContext);
        infoLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        infoLayout.setOrientation(LinearLayout.VERTICAL);
        infoLayout.setPadding(i8dp, i5dp, i5dp, i5dp);
        infoLayout.setGravity(Gravity.CENTER);
        TextView tvUserName = new TextView(mContext);
        tvUserName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvUserName.setPadding(0, 0, 0, i8dp);
        tvUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tvUserName.setTextColor(Color.BLACK);
        tvUserName.setId(R.id.item_user_name);
        tvUserName.setText("User name");


        TextView tvDesignation = new TextView(mContext);
        tvDesignation.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvDesignation.setId(R.id.item_designation);
        tvDesignation.setPadding(0, 0, 0, i5dp);
        tvDesignation.setText("Designation");
        tvDesignation.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvDesignation.setTextColor(Color.DKGRAY);

        infoLayout.addView(tvUserName);
        infoLayout.addView(tvDesignation);

        linearLayout.addView(ivUserImage);
        linearLayout.addView(infoLayout);
        return linearLayout;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final UserItemModel userItemModel = mModelList.get(position);
        holder.itemView.setTag(userItemModel.getUsername());
        holder.tvUserName.setText(userItemModel.getUsername());
        holder.tvDesignation.setText(userItemModel.getDesignation());
        holder.ivUserImage.setImageBitmap(textAsBitmap(userItemModel.getUsername().substring(0, 2), sp2px(20), Color.WHITE,colorArray[position%5]));
//        holder.ivUserImage.setBackground(SUtils.getRoundDrawableListState(colorArray[position%5], SUtils.dpToPx(22), colorArray[position%5], SUtils.dpToPx(22)));
//        holder.ivUserImage.setImageDrawable(getTextRoundImageDrawable(position,userItemModel.getUsername().substring(0,2),16));
        holder.holderView.setBackgroundColor(userItemModel.isSelected() ? 0x7DAFAFAF: Color.TRANSPARENT);

    }

    public Bitmap textAsBitmap(String text, float textSize, int textColor, int bgColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
//        paint.setColor(textColor);
        int ivImageSize = Utils.dpToPx(45);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(ivImageSize , ivImageSize , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        paint.setColor(bgColor);
        canvas.drawCircle(ivImageSize /2,ivImageSize /2,ivImageSize/2,paint);
        paint.setColor(textColor);
        canvas.drawText(text, (ivImageSize-width)/2,(ivImageSize+baseline)/2, paint);
        return image;
    }
    @Override
    public int getItemCount() {
        return mModelList == null ? 0 : mModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View holderView;
        private TextView tvUserName;
        private TextView tvDesignation;
        private ImageView ivUserImage;

        private MyViewHolder(View itemView) {
            super(itemView);
            holderView = itemView;
            tvUserName = (TextView) itemView.findViewById(R.id.item_user_name);
            tvDesignation = (TextView) itemView.findViewById(R.id.item_designation);
            ivUserImage = (ImageView) itemView.findViewById(R.id.item_image);
//            ivUserImage.setImageDrawable(getTextRoundImageDrawable(5,mModelList.get(5).getUsername().substring(0,2),16));
//            ivUserImage.setImageDrawable(SUtils.getCurvedDrawable());

            setClickListener();

        }

        private void setClickListener() {
            holderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mModelList.get(getAdapterPosition()).setSelected(!mModelList.get(getAdapterPosition()).isSelected());
                    holderView.setBackgroundColor(mModelList.get(getAdapterPosition()).isSelected() ? 0x7DAFAFAF: Color.TRANSPARENT);
                    if (onItemSelectionEnable != null)
                        if (mModelList.get(getAdapterPosition()).isSelected())
                            onItemSelectionEnable.onItemSelected(holderView,mModelList.get(getAdapterPosition()));
                        else
                            onItemSelectionEnable.onItemDeselected(holderView,mModelList.get(getAdapterPosition()));

                }
            });
        }
    }



    private int sp2px(float sp) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (sp * scale + 0.5f);
    }


    public void setmModelList(List<UserItemModel> mModelList) {
        this.mModelList = mModelList;
    }

    public List<UserItemModel> getModelList() {
        return mModelList;
    }

    public void setOnItemSelectionEnable(OnItemSelectionEnable onItemSelectionEnable) {
        this.onItemSelectionEnable = onItemSelectionEnable;
    }

    interface OnItemSelectionEnable {
        void onItemSelected(View holderView, UserItemModel userItemModel);

        void onItemDeselected(View holderView, UserItemModel userItemModel);
    }
}