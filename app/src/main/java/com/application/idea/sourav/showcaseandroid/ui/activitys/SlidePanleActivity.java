package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.ChatUserDTO;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.ArchiveAdapter;
import com.application.idea.sourav.showcaseandroid.utils.CircleImageView;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

public class SlidePanleActivity extends AppCompatActivity {
    private int i10dp;
    private int i20dp;
    private int i40dp;
    private int i3dp;
    private int i5dp;
    private int i15dp;
    private View selectectLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Utils.setLightStatusBar(this);
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);
        SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
        slidingPaneLayout.setLayoutParams(new SlidingPaneLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);
        slidingPaneLayout.addView(getNavigationOptionView());
        slidingPaneLayout.addView(getContentLayoout());
        setContentView(slidingPaneLayout);
    }

    public View getNavigationOptionView()
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setPadding(2, 0, 0, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.WHITE);

        LinearLayout llProfileLayout = new LinearLayout(this);
        llProfileLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        llProfileLayout.setGravity(Gravity.CENTER_VERTICAL);
        llProfileLayout.setPadding(i5dp, i40dp, i5dp, i10dp);


        CircleImageView circleImageView = new CircleImageView(this);
        LinearLayout.LayoutParams circleImageViewPararm = new LinearLayout.LayoutParams(Utils.dpToPixel(50), Utils.dpToPixel(50));
        circleImageView.setLayoutParams(circleImageViewPararm);
        circleImageView.setImageResource(R.drawable.profile_1);

        TextView textView = new TextView(this);
        textView.setText("Amanda Hawkins");
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setPadding(i5dp, 0, 0, 0);
        llProfileLayout.addView(circleImageView);
        llProfileLayout.addView(textView);
        linearLayout.addView(llProfileLayout);
        linearLayout.addView(getActionIcon(R.drawable.ic_box_document_paper, R.id.box_archive, "Archive"));
        linearLayout.addView(getActionIcon(R.drawable.ic_chat_bubble, R.id.chat_message, "Chats"));
        linearLayout.addView(getActionIcon(R.drawable.ic_sent_mail, R.id.sent_maile, "Sent Mail"));
        linearLayout.addView(getActionIcon(R.drawable.ic_garbage_2, R.id.deleted_item, "Deleted"));

        return linearLayout;
    }

    public View getActionIcon(@DrawableRes int imageResouce, int layoutId, String sLable)
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(i5dp, i5dp, i5dp, i5dp);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setId(layoutId);
        linearLayout.setOnClickListener(new SlideEventListener());
        if (layoutId == R.id.box_archive)
        {
            linearLayout.setBackground(getResources().getDrawable(R.drawable.ic_selected_side_line));
            selectectLayout = linearLayout;
        }
        ImageView ivIconImage = new ImageView(this);
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(Utils.dpToPixel(50), Utils.dpToPixel(50));
        ivIconImage.setLayoutParams(imgParams);
        ivIconImage.setPadding(i5dp, i5dp, i5dp, i5dp);
        ivIconImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ivIconImage.setImageDrawable(Utils.getIcon(this, imageResouce, getResources().getColor(R.color.icon_color)));
        TextView tvIconLable = new TextView(this);
        tvIconLable.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tvIconLable.setGravity(Gravity.CENTER_VERTICAL);
        tvIconLable.setText(sLable);
        //        tvIconLable.setAllCaps(true);
        tvIconLable.setTextColor(getResources().getColor(R.color.gray_600));
        tvIconLable.setPadding(i5dp, i3dp, i5dp, i3dp);
        linearLayout.addView(ivIconImage);
        linearLayout.addView(tvIconLable);
        return linearLayout;
    }

    public View getContentLayoout()
    {
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setId(R.id.contelayout_sw3);
        LinearLayout.LayoutParams contentParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentParam.weight = 1f;
        contentParam.setMargins(Utils.dpToPixel(50) + i10dp, 0, 0, 0);
        contentLayout.setPadding(i5dp, i40dp, i5dp, i3dp);
        contentLayout.setLayoutParams(contentParam);
        contentLayout.setBackgroundColor(getResources().getColor(R.color.slide_background));
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        contentLayout.setGravity(Gravity.END);


        contentLayout.addView(getSearchBar());
        contentLayout.addView(getListCardItems());
        return contentLayout;
    }

    public View getListCardItems()
    {
        ArrayList<ChatUserDTO> chatUserDTOS = new ArrayList<>();
        chatUserDTOS.add(new ChatUserDTO("Kim Boston", "5m ago", false, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Interface Maker", 0));
        chatUserDTOS.add(new ChatUserDTO("Elika Simpsion", "1h ago", true, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Product Analysis", 1));
        chatUserDTOS.add(new ChatUserDTO("Robin Emile", "3h ago", false, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Programmer", 2));
        chatUserDTOS.add(new ChatUserDTO("Raven Hank", "5h ago", true, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "UI Tester", 3));
        chatUserDTOS.add(new ChatUserDTO("Cristin Soliman", "6h ago", false, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Production Manager", 4));
        chatUserDTOS.add(new ChatUserDTO("Lucy Wane", "10h ago", false, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Seller", 5));
        chatUserDTOS.add(new ChatUserDTO("Tiffny Burnner", "11h ago", true, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Product Verifiers", 6));
        chatUserDTOS.add(new ChatUserDTO("Zemaan Malika", "13h ago", false, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Frelancer", 7));
        chatUserDTOS.add(new ChatUserDTO("Asline Wade", "16h ago", false, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Idea Provider", 8));
        chatUserDTOS.add(new ChatUserDTO("Lily Gray", "23h ago", true, "But i must explain to you how all this mistaken idea of de nouncing pleasure and prasising explain to you how nouncing", "Trouble Maker", 9));

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setPadding(0, i5dp, 0, i3dp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ArchiveAdapter(this, chatUserDTOS));
        return recyclerView;
    }

    public View getSearchBar()
    {
        EditText etSearchBar = new EditText(this);
        etSearchBar.setPadding(i5dp, 0, 0, 0);
        LinearLayout.LayoutParams etSearchBarParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPixel(40));
        etSearchBar.setHintTextColor(getResources().getColor(R.color.gray_600));

        //        etSearchBar.setFocusable(false);
        Drawable searchIcon = Utils.getIcon(this, R.drawable.ic_search_black_24dp, getResources().getColor(R.color.gray_600));
        etSearchBar.setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null);
        etSearchBar.setSingleLine(true);
        etSearchBar.setHint("Search");
        etSearchBar.setCompoundDrawablePadding(i5dp);
        etSearchBar.setEms(25);
        //        etSearchBarParam.setMargins(i5dp, 0, i5dp, 0);
        etSearchBar.setLayoutParams(etSearchBarParam);
        etSearchBar.setTextColor(getResources().getColor(R.color.gray_700));

        GradientDrawable searchBackground = new GradientDrawable();
        searchBackground.setColor(getResources().getColor(R.color.search_background));
        searchBackground.setCornerRadius(Utils.dpToPixel(32));
        etSearchBar.setBackground(searchBackground);
        return etSearchBar;
    }

    private View chatBoxLayout()
    {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);

        scrollView.setBackgroundColor(getResources().getColor(R.color.slide_background3));
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.addView(getHeaderBar());
        linearLayout.addView(getDividerLine());
        linearLayout.addView(getChatLayout(0, "Dani Marshall", "Hey Thomas!", "11:00 am"));
        linearLayout.addView(getChatLayout(1, "Kaile Thomas", "Hello Dani Marshall!", "11:02 am"));
        linearLayout.addView(getChatLayout(0, "Dani Marshall", "I would like to request revision on you subject", "11:03 am"));
        linearLayout.addView(getChatLayout(1, "Kaile Thomas", "Send it to what you want, i'll revision with your subject and will play with the Rev hahaha", "11:04 am"));
        linearLayout.addView(getChatLayout(1, "Kaile Thomas", "How to apply in that module", "11:04 am"));
        linearLayout.addView(getChatLayout(0, "Dani Marshall", "why not't sure , i will inform you when its done.", "11:04 am"));
        linearLayout.addView(getChatLayout(1, "Kaile Thomas", "Send me Tomorrow i am quite bussy right now, Is it ok for you ", "11:04 am"));
        linearLayout.addView(getChatLayout(0, "Dani Marshall", "ok no Problem, I will let you know the subject.", "11:06 am"));


        scrollView.addView(linearLayout);
        return scrollView;

    }

    private LinearLayout getChatLayout(int iType, String senderName, String msg, String timeAgo)
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setPadding(0, i3dp, 0, i5dp);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        if (iType == 0)
        {
            linearLayout.setGravity(Gravity.START);
            linearLayout.addView(getLeftSideView(iType));
            linearLayout.addView(getRightSideView(iType, senderName, msg, timeAgo));
        } else
        {
            linearLayout.setGravity(Gravity.END);
            linearLayout.addView(getRightSideView(iType, senderName, msg, timeAgo));
            linearLayout.addView(getLeftSideView(iType));
        }

        return linearLayout;
    }

    private View getLeftSideView(int iType)
    {
        CircleImageView ivSenderImg = new CircleImageView(this);
        ivSenderImg.setId(R.id.iv_sender_icon);
        LinearLayout.LayoutParams ivSenderImgPararm = new LinearLayout.LayoutParams(Utils.dpToPixel(40), Utils.dpToPixel(40));
        ivSenderImg.setLayoutParams(ivSenderImgPararm);
        if (iType==0)
        ivSenderImg.setImageResource(R.drawable.profile_3);
        else
        ivSenderImg.setImageResource(R.drawable.profile_1);
        return ivSenderImg;
    }

    private LinearLayout getRightSideView(int iType, String senderName, String msg, String timeAgo)
    {
        LinearLayout rightSideView = new LinearLayout(this);
        rightSideView.setOrientation(LinearLayout.VERTICAL);
        if (iType == 0)
            rightSideView.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        else
            rightSideView.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        rightSideView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rightSideView.setPadding(i5dp, 0, i5dp, 0);

        TextView tvTimeAgo = new TextView(this);
        LinearLayout.LayoutParams timeAgoParam=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvTimeAgo.setText(timeAgo);
        tvTimeAgo.setId(R.id.time_ago);
        tvTimeAgo.setPadding(0, i5dp, 0, 0);
        tvTimeAgo.setTextColor(getResources().getColor(R.color.gray_600));
        tvTimeAgo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        TextView tvSenderName = new TextView(this);
        tvSenderName.setText(senderName);
        tvSenderName.setId(R.id.user_role);
        tvSenderName.setTextColor(Utils.getColorStateListDrawable(this.getResources().getColor(R.color.gray_600), Color.WHITE));
        tvSenderName.setPadding(0,0,0,i3dp);
        tvSenderName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvSenderName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView tvMessage = new TextView(this);
        tvMessage.setId(R.id.mail_description);
        tvMessage.setText(msg);
        LinearLayout.LayoutParams tvMessageParam=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);

        if (iType == 0)
        {
            tvTimeAgo.setGravity(Gravity.END);
            gradientDrawable.setColor(Color.WHITE);
            gradientDrawable.setCornerRadii(new float[]{0, 0, i10dp, i10dp, i10dp, i10dp, i10dp, i10dp});
            tvMessageParam.setMargins(0,0,i40dp,0);
            timeAgoParam.setMargins(0,0,i40dp+i5dp,0);
            tvMessage.setPadding(i15dp, i10dp, i10dp, i10dp);
            tvMessage.setTextColor(this.getResources().getColor(R.color.gray_500));

        } else
        {
            tvTimeAgo.setGravity(Gravity.START);
            gradientDrawable.setColor(getResources().getColor(R.color.base_color_sw3));
            gradientDrawable.setCornerRadii(new float[]{i10dp, i10dp, 0, 0, i10dp, i10dp, i10dp, i10dp});
            tvMessageParam.setMargins(Utils.dpToPixel(50),0,0,0);
            timeAgoParam.setMargins(Utils.dpToPixel(50)+i5dp,0,0,0);
            tvMessage.setPadding(i10dp, i10dp, i15dp, i10dp);
            tvMessage.setTextColor(this.getResources().getColor(R.color.white_300));
        }
        tvMessage.setLayoutParams(tvMessageParam);
        tvMessage.setBackground(gradientDrawable);
        tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        tvTimeAgo.setLayoutParams(timeAgoParam);


        rightSideView.addView(tvSenderName);
        rightSideView.addView(tvMessage);
        rightSideView.addView(tvTimeAgo);

        return rightSideView;
    }


    public View getHeaderBar()
    {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setPadding(0, i10dp, 0, i10dp);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView tvName = new TextView(this);
        tvName.setText("Dani Marshall");
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tvName.setTextColor(getResources().getColor(R.color.gray_600));

        TextView tvLastSeen = new TextView(this);
        tvLastSeen.setText("Last seen 10 hr ago");
        tvLastSeen.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tvLastSeen.setPadding(0, i5dp, 0, 0);
        tvLastSeen.setTextColor(getResources().getColor(R.color.gray_500));
        linearLayout.addView(tvName);
        linearLayout.addView(tvLastSeen);
        return linearLayout;
    }

    public View getDividerLine()
    {
        View dividerLine = new View(this);
        dividerLine.setBackgroundColor(getResources().getColor(R.color.gray_400));
        dividerLine.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        return dividerLine;
    }


    private class SlideEventListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {
            if (view.getId() == R.id.box_archive || view.getId() == R.id.sent_maile || view.getId() == R.id.chat_message || view.getId() == R.id.deleted_item)
            {
                if (selectectLayout == view)
                {
                    selectectLayout = view;
                } else
                {

                    selectectLayout.setBackground(null);
                    selectectLayout = (LinearLayout) view;
                    selectectLayout.setBackground(getResources().getDrawable(R.drawable.ic_selected_side_line));

                }

                if (view.getId() == R.id.box_archive)
                {
                    Toast.makeText(SlidePanleActivity.this, "Archive", Toast.LENGTH_SHORT).show();
                    LinearLayout linearLayout = findViewById(R.id.contelayout_sw3);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.slide_background));
                    linearLayout.setPadding(i5dp, i40dp, i5dp, i3dp);
                    linearLayout.removeAllViews();
                    linearLayout.addView(getSearchBar());
                    linearLayout.addView(getListCardItems());
                } else if (view.getId() == R.id.sent_maile)
                {
//                    Toast.makeText(SlidePanleActivity.this, "Sent Mail", Toast.LENGTH_SHORT).show();
                    Toast.makeText(SlidePanleActivity.this, "Sent mail", Toast.LENGTH_SHORT).show();
                    LinearLayout linearLayout = findViewById(R.id.contelayout_sw3);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.slide_background3));
                    linearLayout.removeAllViews();
                    linearLayout.setPadding(i5dp, i20dp, i5dp, i3dp);
                    linearLayout.addView(getEmailLayout());

                } else if (view.getId() == R.id.chat_message)

                {
                    Toast.makeText(SlidePanleActivity.this, "Sent Chat", Toast.LENGTH_SHORT).show();
                    LinearLayout linearLayout = findViewById(R.id.contelayout_sw3);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.slide_background3));
                    linearLayout.removeAllViews();
                    linearLayout.setPadding(i5dp, i20dp, i5dp, i3dp);
                    linearLayout.addView(chatBoxLayout());

                } else if (view.getId() == R.id.deleted_item)
                {
                    Toast.makeText(SlidePanleActivity.this, "Sent mail", Toast.LENGTH_SHORT).show();
                    LinearLayout linearLayout = findViewById(R.id.contelayout_sw3);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.slide_background3));
                    linearLayout.removeAllViews();
                    linearLayout.setPadding(i5dp, i20dp, i5dp, i3dp);
                    linearLayout.addView(getDeletedLayout());
                } /*else
                {
                }*/

            }

        }
    }

    private View getEmailLayout() {
        LinearLayout layout=new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//        layout.setBackgroundColor(0xffcfcfcf);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        TextView infoText=new TextView(this);
        infoText.setGravity(Gravity.CENTER);
        infoText.setText("we will let you know");
        infoText.setTypeface(Typeface.DEFAULT_BOLD);
        infoText.setPadding(0,0,0,i10dp);
        layout.addView(infoText);
        CardView cardView=new CardView(this);
        cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        TextClock textView=new TextClock(this);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,45);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setPadding(i20dp,i20dp,i20dp,i20dp);

        cardView.addView(textView);
        layout.addView(cardView);
        return layout;
    }
    private View getDeletedLayout() {
        LinearLayout layout=new LinearLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        CardView cardView=new CardView(this);
        cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageView binImage=new ImageView (this);
        binImage.setPadding(i20dp,i20dp,i20dp,i20dp);
        binImage.setImageResource(R.drawable.ic_garbage_2);

        cardView.addView(binImage);
        layout.addView(cardView);
        return layout;
    }

}
