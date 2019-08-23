package com.application.idea.sourav.showcaseandroid.ui.activitys.recycleSelection;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.UserItemModel;
import com.application.idea.sourav.showcaseandroid.ui.components.customSnackBar.SelectionSnackBarMenu;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MultiRecyclerActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemSelectionEnable, View.OnClickListener, SelectionSnackBarMenu.OnSnackSelectionItemActions {
    FrameLayout frameLayout;
    SelectionSnackBarMenu selectionSnackBarMenu;
    private List<UserItemModel> mModelList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private String[] userNames = {"Yoda", "Storm Trooper", "Aragorn", "Harry Potter", "Jon Snow", "Clone Commander Cody", "Boba Fett", "Frodo", "Rubeus Hagrid", "Qui-Gon Jinn", "Count Dooku", "Gandalf", "Remus Lupin", "Obi-Wan Kenobi", "General Grievous", "Gimli", "Lucius Malfoy", "R2-D2", "Jango Fett", "Gollum", "Draco Malfoy", "Luke Skywalker", "Darth Maul", "Legolas", "Sirius Black", "Han Solo", "Palpatine", "Sam", "Voldemort", "Chewbacca", "Dath Vader", "Saruman", "Albus Dumbledore", "Lando Calrissian", "Sith Lord", "Elrond", "Severus Snape", "Griffin", "Dragon", "Ghost", "Centaur", "Wizard", "Zombie", "Minotaur", "Dwarf", "Werewolf", "Cyclope", "Elf", "Vampire", "Serpent", "Leprechaun", "Ghoul", "Satyr", "Ogre", "Warlock", "Poseidon", "Goblin", "Shapeshiffter", "Hades", "Basilisk", "Archangel", "Zeus", "Yeti", "Mummy", "Titan", "Troll", "Druid"};
    private String[] userDesignations =
            {"Major", "Deranged", "Dagger", "Game", "The", "Sergeant", "Noob", "Gernade", "Tec", "First", "Admiral", "Punk", "Cannon", "Droi", "The Only", "Corporal", "Wise", "M4", "Playe", "His",
                    "Specialist", "Omni", "Blade", "Use", "Master", "Private", "Fear", "Sword",
                    "Compute", "Sir", "Officer", "Fire", "Spea", "Craf", "Doc", "Lieutenant"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(getActivityLayout());
    }

    private View getActivityLayout() {
        frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        frameLayout.setBackground(SUtils.getTilteDrawable());

        mRecyclerView = new RecyclerView(this);
        mRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayoutManager manager = new LinearLayoutManager(MultiRecyclerActivity.this);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(this, manager.getOrientation(),Utils.dpToPixel(100)));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);

        mAdapter = new RecyclerViewAdapter(MultiRecyclerActivity.this, getListData());
        ((RecyclerViewAdapter) mAdapter).setOnItemSelectionEnable(this);
        mRecyclerView.setAdapter(mAdapter);
        frameLayout.addView(mRecyclerView);
        return frameLayout;
    }

    private List<UserItemModel> getListData() {
        mModelList = new ArrayList<>();
        for (int i = 0; i < userNames.length; i++) {
            UserItemModel userItemModel = new UserItemModel();
            userItemModel.setUsername(userNames[i]);

            userItemModel.setDesignation(userDesignations[i % 35]);
            mModelList.add(userItemModel);
        }
        return mModelList;
    }

    @SuppressLint("Range")
    @Override
    public void onItemSelected(View holderView, UserItemModel userItemModel) {
        if (selectionSnackBarMenu == null || !selectionSnackBarMenu.isShown()) {
            selectionSnackBarMenu = SelectionSnackBarMenu.make(MultiRecyclerActivity.this, frameLayout, SelectionSnackBarMenu.LENGTH_INDEFINITE);
            selectionSnackBarMenu.getView().setBackground(new ColorDrawable(0));
            Drawable viewIcon = ((ImageView) ((LinearLayout) holderView).getChildAt(0)).getDrawable();
            selectionSnackBarMenu.addSelectionContent(userItemModel.getUsername(), viewIcon);
            selectionSnackBarMenu.onSnackOptionSelectionListener(this);
            selectionSnackBarMenu.show();
        } else {
            Drawable viewIcon = ((ImageView) ((LinearLayout) holderView).getChildAt(0)).getDrawable();
            selectionSnackBarMenu.addSelectionContent(userItemModel.getUsername(), viewIcon);
        }
    }

    @Override
    public void onItemDeselected(View holderView, UserItemModel userItemModel) {
        if (selectionSnackBarMenu != null && selectionSnackBarMenu.isShown()) {
            selectionSnackBarMenu.removeSelectionContent(userItemModel.getUsername());
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.snack_bar_done) {
            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSnackMenuItemSelected(MenuItem menuItem) {
        if (menuItem.getTitle() != null)
            Toast.makeText(this, "menu selected position - " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        if (menuItem.getItemId() == R.id.action_action1)
            if (selectionSnackBarMenu != null && selectionSnackBarMenu.isShown()) {
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                ((RecyclerViewAdapter) mAdapter).setmModelList(getListData());
                mAdapter.notifyDataSetChanged();

            }
    }

    @Override
    public void removedSnackSelectedView(View view, String sUserName) {
        View viewWithTag = mRecyclerView.findViewWithTag(sUserName);
        int childAdapterPosition = mRecyclerView.getChildLayoutPosition(viewWithTag);
        if (childAdapterPosition >= 0) {
            ((RecyclerViewAdapter) mAdapter).getModelList().get(childAdapterPosition).setSelected(false);
            mAdapter.notifyItemChanged(childAdapterPosition);
        }

    }
}
