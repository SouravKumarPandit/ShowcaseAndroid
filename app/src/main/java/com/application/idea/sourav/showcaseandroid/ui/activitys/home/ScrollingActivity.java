package com.application.idea.sourav.showcaseandroid.ui.activitys.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.models.ShowcaseClassDTO;
import com.application.idea.sourav.showcaseandroid.ui.activitys.*;
import com.application.idea.sourav.showcaseandroid.ui.activitys.horizontalscroll.HorizontalScrollActivity;
import com.application.idea.sourav.showcaseandroid.ui.activitys.infoAbout.AboutActivity;
import com.application.idea.sourav.showcaseandroid.ui.activitys.inlineSelection.ScrollSelectorActivity;
import com.application.idea.sourav.showcaseandroid.ui.activitys.inputStyleEditor.InputStyleEditorActivity;
import com.application.idea.sourav.showcaseandroid.ui.activitys.loginSignp.LoginSingUpActivity;
import com.application.idea.sourav.showcaseandroid.ui.activitys.profileDetails.ProfileDetailsActivity;
import com.application.idea.sourav.showcaseandroid.ui.activitys.recycleSelection.MultiRecyclerActivity;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.ShowcaseAdapter;
import com.application.idea.sourav.showcaseandroid.ui.customAdapters.recyclerDivider.RecyclerDividerItemDecoration;

import java.util.ArrayList;

public class ScrollingActivity extends BaseActivity
{

    private ArrayList<ShowcaseClassDTO> showCaseActivityList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setUpdata();
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView clRecyclerView = findViewById(R.id.rv_showcase_list);
//        clRecyclerView.setPadding(5, 0, 0, 0);
        clRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        clRecyclerView.setAdapter(new ShowcaseAdapter(ScrollingActivity.this, showCaseActivityList));
        clRecyclerView.addItemDecoration(new RecyclerDividerItemDecoration(this, RecyclerDividerItemDecoration.VERTICAL_LIST, getResources().getColor(R.color.gray_medium)));
        clRecyclerView.setHasFixedSize(true);


    }

    private void setUpdata()
    {

        showCaseActivityList = new ArrayList<ShowcaseClassDTO>();
        showCaseActivityList.add(getCLListDTO(HorizontalScrollActivity.class));
        showCaseActivityList.add(getCLListDTO(AboutActivity.class));
        showCaseActivityList.add(getCLListDTO(LoginSingUpActivity.class));
        showCaseActivityList.add(getCLListDTO(SlidePanleActivity.class));
        showCaseActivityList.add(getCLListDTO(NestCoordinateActivity.class));
        showCaseActivityList.add(getCLListDTO(GridCoordinateActivity.class));
        showCaseActivityList.add(getCLListDTO(ModernListActivity.class));
        showCaseActivityList.add(getCLListDTO(DateSelectionActivity.class));
        showCaseActivityList.add(getCLListDTO(IconTabActivity.class));
        showCaseActivityList.add(getCLListDTO(MultiRecyclerActivity.class));
        showCaseActivityList.add(getCLListDTO(InputStyleEditorActivity.class));
        showCaseActivityList.add(getCLListDTO(ScrollSelectorActivity.class));
        showCaseActivityList.add(getCLListDTO(ProfileDetailsActivity.class));
        showCaseActivityList.add(getCLListDTO(DrawableTestActivity.class));

    }

    public ShowcaseClassDTO getCLListDTO(Class aClass)
    {
        ShowcaseClassDTO clListDTO = new ShowcaseClassDTO();
        clListDTO.setActivityClass(aClass);
        return clListDTO;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Toast.makeText(this, "Todo Custom Toast", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
