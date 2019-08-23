package com.application.idea.sourav.showcaseandroid.ui.activitys.inputStyleEditor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.application.idea.sourav.showcaseandroid.ui.components.styleEditor.StyleEditLayout;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

public class InputStyleEditorActivity extends AppCompatActivity {
    String htmlString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Email Editor");
        setContentView(getContentLayout());

    }

    private View getContentLayout() {
        LinearLayout linearLayout = new LinearLayout(this);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setGravity(Gravity.TOP);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(Utils.dpToPx(10), Utils.dpToPx(10), Utils.dpToPx(10), Utils.dpToPx(10));
        linearLayout.setBackgroundColor(0x80dedede);
        StyleEditLayout styleEditLayout;
        styleEditLayout = new StyleEditLayout(this);
        styleEditLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,1));
//        htmlString=getResources().getString(R.string.colored_text_html);
//        styleEditLayout.setHtmlString(htmlString);
        linearLayout.addView(styleEditLayout);

        linearLayout.addView(getDataShowingText(styleEditLayout));

        return linearLayout;
    }

    private LinearLayout getDataShowingText(final StyleEditLayout styleEditLayout) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        final CLWebLinkView webLinkView = new CLWebLinkView(this, htmlString, true, false);
        webLinkView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPx(200)));
        webLinkView.setBackground(Utils.getRoundDrawableListState(0x40dedede, 50, 0xffdedede, 50));
        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText("Get Text");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (styleEditLayout != null) {

                    webLinkView.setsData(styleEditLayout.getEditorHtmlText(), true, false);
                }
            }
        });

        linearLayout.addView(webLinkView);
        linearLayout.addView(button);
        return linearLayout;
    }


}