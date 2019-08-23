package com.application.idea.sourav.showcaseandroid.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.SocialPlanInfoDTO;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.SocialPlanListAdapter;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class SocialPlanFragment extends Fragment {
    private final Context clContext;
    private int i3dp;
    private int i10dp;
    private int i5dp;
    private int i20dp;
    private int i15dp;
    private int i40dp;
    ArrayList<SocialPlanInfoDTO> profileName;

    public SocialPlanFragment(Context context) {
        clContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        i3dp = Utils.dpToPixel(3);
        i5dp = Utils.dpToPixel(5);
        i10dp = Utils.dpToPixel(10);
        i15dp = Utils.dpToPixel(15);
        i20dp = Utils.dpToPixel(20);
        i40dp = Utils.dpToPixel(40);

        prepareListItems();
        RecyclerView recyclerView = new RecyclerView(clContext);
        RelativeLayout.LayoutParams recyclerViewParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerView.setPadding(i5dp, 0, i5dp, 0);
        recyclerViewParam.addRule(RelativeLayout.BELOW, R.id.gradiant_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(clContext);
        recyclerView.setLayoutManager(layoutManager);
        SocialPlanListAdapter myRecyclerAdapter = new SocialPlanListAdapter(clContext, profileName);
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.setLayoutParams(recyclerViewParam);
        return recyclerView;

    }

    private void prepareListItems() {
        profileName = new ArrayList<SocialPlanInfoDTO>();

        profileName.add(new SocialPlanInfoDTO("George Hamilton", "Planing for  new event at Vagas club with buffet dinner on comming week.", "just now"));
        profileName.add(new SocialPlanInfoDTO("Clara Rossie", "We are planning to viesit Hollywood city for have some fun...", "2 hrs"));
        profileName.add(new SocialPlanInfoDTO("Lynn Morancie", "Hey! I am organising a school fund for help poor buying winter cloths...", "7 hrs"));
        profileName.add(new SocialPlanInfoDTO("Willaim Cohen", "I am going to arrange a rock metal band party towmorrow let me know...", "1 day"));
        profileName.add(new SocialPlanInfoDTO("Madeline Cooper", "I am bored to see all above plant! Lets go to moon and have some advantrue ", "4 days"));
        profileName.add(new SocialPlanInfoDTO("George Hamilton", "Planing for  new event at Vagas club with buffet dinner on comming week.", "just now"));
        profileName.add(new SocialPlanInfoDTO("Clara Rossie", "We are planning to viesit Hollywood city for have some fun...", "2 hrs"));
        profileName.add(new SocialPlanInfoDTO("Lynn Morancie", "Hey! I am organising a school fund for help poor buying winter cloths...", "7 hrs"));
        profileName.add(new SocialPlanInfoDTO("Willaim Cohen", "I am going to arrange a rock metal band party towmorrow let me know...", "1 day"));
        profileName.add(new SocialPlanInfoDTO("Madeline Cooper", "I am bored to see all above plant! Lets go to moon and have some advantrue ", "4 days"));


    }
}
