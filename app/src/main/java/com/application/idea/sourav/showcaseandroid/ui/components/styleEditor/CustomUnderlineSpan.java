package com.application.idea.sourav.showcaseandroid.ui.components.styleEditor;

import android.text.TextPaint;
import android.text.style.UnderlineSpan;

public class CustomUnderlineSpan extends UnderlineSpan
{// extend ClickableSpan
    public CustomUnderlineSpan() {
        super();
    }
    public void updateDrawState(TextPaint ds) {// override updateDrawState
        ds.setUnderlineText(true); // set to false to remove underline
    }
//    public int getStyleType() {
//        return 4;
//    }
}