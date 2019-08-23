package com.application.idea.sourav.showcaseandroid.ui.activitys.inputStyleEditor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("ViewConstructor")
public class CLWebLinkView extends WebView {
    private String sData;
    private boolean isHtmlType;
    private final Context clContext;
    boolean isTouched = false;


    public CLWebLinkView(final Context clContext, String sData, boolean isHtmlType,boolean isHeaderReq)
    {
        super(clContext);
        this.clContext = clContext;
        this.sData = sData;
        this.isHtmlType = isHtmlType;
        this.setPadding(10, 10, 0, 0);

        this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        this.getSettings().setSupportZoom(true);
        this.getSettings().setBuiltInZoomControls(true);
        this.getSettings().setDisplayZoomControls(false);
        this.setInitialScale(0);
        this.getSettings().setUseWideViewPort(true);
        this.setWebViewClient(new WebViewClient());
        setsData(sData, isHtmlType,isHeaderReq);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                // Disallow ScrollView to intercept touch events.
                this.getParent().requestDisallowInterceptTouchEvent(true);

                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                // Allow ScrollView to intercept touch events.
                if (event.getX() > getWidth() * 0.3 && event.getX() < getWidth() * 0.7)
                {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    requestDisallowInterceptTouchEvent(true);
                }
                else
                    this.getParent().requestDisallowInterceptTouchEvent(false);

                break;
            }
            case MotionEvent.ACTION_UP:
            {

            }
        }
        return super.onTouchEvent(event);
    }


    public void setsData(String sData, boolean isHtmlType,boolean isHeadersReq)
    {

        this.isHtmlType = isHtmlType;
        if (isHtmlType)
        {
            this.loadData(sData, "text/html", "UTF-8");

/*
            this.sData = sData.replaceAll("\\s+", "");
            this.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title)
                {
                    super.onReceivedTitle(view, title);

                    Pattern p = Pattern.compile("<title>(\\S+)</title>");
                    Matcher m = p.matcher(CLWebLinkView.this.sData);
                    if (m.find())
                    {                            // get the matching group
                        String headTitle = m.group(1);
                        TextView textView = new TextView(clContext);
                        ViewGroup.LayoutParams textParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        textView.setLayoutParams(textParam);
                        textView.setTextColor(Color.DKGRAY);
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                        textView.setPadding(10, 2, 10, 2);
                        GradientDrawable gradientDrawable = new GradientDrawable();
                        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                        gradientDrawable.setColor(Color.WHITE);
                        gradientDrawable.setStroke(1, Color.DKGRAY);
                        gradientDrawable.setCornerRadius(2);
                        textView.setBackground(gradientDrawable);
                        textView.setText(headTitle);
                        CLWebLinkView.this.addView(textView);

                    }

                }
            });*/
        } else
        {
            if(!isHeadersReq)
            this.loadUrl(sData);
            else
            {
                Map<String, String> clHeaders = new HashMap<>();
                this.loadUrl(sData, clHeaders);
            }
            this.sData = sData;
        }
        postInvalidate();
    }

    public void setHtmlType(boolean htmlType)
    {
        isHtmlType = htmlType;
    }

    public String getsData()
    {
        return sData;
    }

    public boolean isHtmlType()
    {
        return isHtmlType;
    }

}
