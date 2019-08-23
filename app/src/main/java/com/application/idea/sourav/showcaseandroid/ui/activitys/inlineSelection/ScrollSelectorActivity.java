package com.application.idea.sourav.showcaseandroid.ui.activitys.inlineSelection;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.ui.components.inlineSelector.SelectorHorizontalScrollView;

public class ScrollSelectorActivity extends AppCompatActivity implements SelectorHorizontalScrollView.ValuePickedListener {
    private String[] arrayString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrayString = getResources().getStringArray(R.array.countries_array);

        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentLayout.setGravity(Gravity.CENTER);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setBackgroundColor(Color.DKGRAY);
        final SelectorHorizontalScrollView horizontalScrollView = new SelectorHorizontalScrollView(this);
        horizontalScrollView.setValueSelectionArray(arrayString);
        horizontalScrollView.setBackground(new ColorDrawable(Color.WHITE));
        horizontalScrollView.setSelectionListener(this);
        horizontalScrollView.getSingleLineSelector().setIsMandatory(true);
//        horizontalScrollView.setScrollMagnifying(false);

        contentLayout.addView(horizontalScrollView);


        TextView textView = new TextView(this);
        textView.setText("Get Position");
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setPadding(0, 25, 0, 0);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float scaleX = horizontalScrollView.getScrollX();
                Toast.makeText(ScrollSelectorActivity.this, "positionX : " + scaleX, Toast.LENGTH_SHORT).show();
                if (scaleX > 600) {
                    horizontalScrollView.smoothScrollTo(600, 0);
                }
            }
        });
        contentLayout.addView(textView);
        setContentView(contentLayout);

    }

    @Override
    public void onValueSelection(String selectedValue, int iSelectedPosition, int requestId) {

        Toast.makeText(this, "selected :" + selectedValue + " position :" + iSelectedPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {
        Toast.makeText(this, "nothing Selected", Toast.LENGTH_SHORT).show();
    }


}
