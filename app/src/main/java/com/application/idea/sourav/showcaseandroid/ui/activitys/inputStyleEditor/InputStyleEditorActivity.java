package com.application.idea.sourav.showcaseandroid.ui.activitys.inputStyleEditor;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
    String htmlString="<b>Top questions about Sites</b><br><span style=\"color:#8f8f8f;\"><span style=\"color:#f436c8;\"><b><span style=\"font-size:30px\";>G</span></b></span></span><span style=\"color:#4caf50;\"><span style=\"color:#30ea9d;\"><b><span style=\"font-size:30px\";>o</span></b></span></span><b><span style=\"color:#f44336;\"><span style=\"color:#7f36f4;\"><span style=\"font-size:30px\";>o</span></span></span></b><b><span style=\"font-size:30px\";>g</span></b><b><span style=\"color:#f44336;\"><span style=\"color:#2196f3;\"><span style=\"font-size:30px\";>l</span></span></span></b><span style=\"color:#2196f3;\"><span style=\"color:#2196f3;\"><b><span style=\"font-size:30px\";>e</span></b></span></span><br><br><b>Can </b><b><span style=\"color:#000000;\"><span style=\"color:#2196f3;\">external</span></span></b><b> visitors </b><b><i><u>access</u></i></b><b> a </b><b>company</b><b> site?</b><br><br><br><span style=\"font-size:30px\";><span style=\"color:#2196f3;\"><span style=\"color:#4caf50;\">Yes</span></span></span>. People outside your company can access your site, even without a<b><span style=\"color:#2196f3;\"> G&#160;Suite</span></b> <span style=\"color:#7f36f4;\"><span style=\"color:#f44336;\"><b>account</b></span></span>. You can also opt to <span style=\"color:#7f36f4;\"><span style=\"color:#f44336;\">restrict</span></span> access through sharing settings.<br><br><b>Can I </b><b><i>still</i></b><b> use the </b><b><del><span style=\"color:#f44336;\"><span style=\"color:#7f36f4;\">previous</span></span></del></b><b> version of </b><b><u>Sites</u></b><b>?</b><br><br>If your company is using any previous version of Sites, there will be no disruption. Keep editing and sharing your Sites as you have previously.<br>";

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
//        htmlString="<b>Top questions about Sites</b><br><b><span style=\"font-size:30px\";><span style=\"color:#f44336;\"><span style=\"color:#4caf50;\">G</span></span></span></b><b><span style=\"font-size:30px\";><span style=\"color:#f44336;\">o</span></span></b><b><span style=\"font-size:30px\";><span style=\"color:#f44336;\"><span style=\"color:#7f36f4;\">o</span></span></span></b><b><span style=\"font-size:30px\";>g</span></b><b><span style=\"font-size:30px\";><span style=\"color:#f44336;\"><span style=\"color:#2196f3;\">l</span></span></span></b><b><span style=\"font-size:30px\";>e</span></b><br><br><b>Can </b><b>
// <span style=\"color:#000000;\"><span style=\"color:#2196f3;\">external</span></span></b><b> visitors </b><b><i><u>access</u></i></b><b> a </b><b><span style=\"font-size:30px\";>company</span></b><b> site?</b><br><br>Yes. <span style=\"font-size:30px\";>People</span> outside your company can access your site, even without a<b><span style=\"color:#2196f3;\"> G&#160;Suite</span></b> <span style=\"color:#7f36f4;\"><span style=\"color:#f44336;\"><b>account</b></span></span>. You can also opt to <span style=\"color:#7f36f4;\"><span style=\"color:#f44336;\">restrict</span></span> access through sharing settings.<br><br><b>
// Can I </b><b><i>still</i></b><b> use the </b><b><span style=\"color:#f44336;\"><span style=\"color:#7f36f4;\"><del>previous</del></span></span></b><b> version of </b><b><u>Sites</u></b><b>?</b><br><br>If your company is using any previous version of Sites, there will be no <span style=\"font-size:30px\";>disruption</span>. Keep editing and sharing your Sites as you have previously.<br>";
//        htmlString="<b>Top questions about Sites</b><br><b><span style=\"font-size:30px\";><span style=\"color:#f44336;\"><span style=\"color:#4caf50;\">G</span></span></span></b><b><span style=\"font-size:30px\";><span style=\"color:#f44336;\">o</span></span></b><b><span style=\"font-size:30px\";><span style=\"color:#f44336;\"><span style=\"color:#7f36f4;\">o</span></span></span></b><b><span style=\"font-size:30px\";>g</span></b><b><span style=\"font-size:30px\";><span style=\"color:#f44336;\"><span style=\"color:#2196f3;\">l</span></span></span></b><b><span style=\"font-size:30px\";>e</span></b><br><br><b>Can </b><b>
// <span style=\"color:#000000;\"><span style=\"color:#2196f3;\">external</span></span></b><b> visitors </b><b><i><u>access</u></i></b><b> a </b><b><span style=\"font-size:30px\";>company</span></b><b> site?</b><br><br>Yes. <span style=\"font-size:30px\";>People</span> outside your company can access your site, even without a<b><span style=\"color:#2196f3;\"> G&#160;Suite</span></b> <span style=\"color:#7f36f4;\"><span style=\"color:#f44336;\"><b>account</b></span></span>. You can also opt to <span style=\"color:#7f36f4;\"><span style=\"color:#f44336;\">restrict</span></span> access through sharing settings.<br><br><b>Can I </b><b><i>still</i></b><b> use the </b><b><span style=\"color:#f44336;\"><span style=\"color:#7f36f4;\"><del>previous</del></span></span></b><b> version of </b><b><u>Sites</u></b><b>?</b><br><br>If your company is using any previous version of Sites, there will be no <span style=\"font-size:30px\";>disruption</span>. Keep editing and sharing your Sites as you have previously.<br>";
        styleEditLayout.setHtmlString(htmlString);
        linearLayout.addView(styleEditLayout);

        linearLayout.addView(getDataShowingText(styleEditLayout));

        return linearLayout;
    }

    private LinearLayout getDataShowingText(final StyleEditLayout styleEditLayout) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setColor(Color.BLACK);
        gradientDrawable.setCornerRadius(Utils.dpToPixel(25));

        final CLWebLinkView webLinkView = new CLWebLinkView(this, "", true, false);
        webLinkView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPx(200)));
        webLinkView.setBackground(Utils.getRoundDrawableListState(0x40dedede, 50, 0xffdedede, 50));
//        webLinkView.setPadding(0,0,0,Utils.dpToPixel(5));
        Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,Utils.dpToPx(5),0,0);
        button.setLayoutParams(layoutParams);
        button.setBackground(gradientDrawable);
        button.setTextColor(Color.WHITE);
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