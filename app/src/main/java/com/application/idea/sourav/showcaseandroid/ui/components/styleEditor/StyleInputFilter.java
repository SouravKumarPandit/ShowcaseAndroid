package com.application.idea.sourav.showcaseandroid.ui.components.styleEditor;

import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.*;


public final class StyleInputFilter implements InputFilter
{
    private final boolean isBold;
    private final boolean isItalic;
    private final boolean isUnderLine;
    private final boolean isStrike;
    private final boolean isSized;
    private final boolean isColored;
    private final boolean isBgColored;
    private final boolean isBullet;
    private final int pickedColor;
    private final int pickedBgColor;
    private final float iPickedSize;

    public StyleInputFilter(boolean isBold, boolean isItalic, boolean isUnderLine, boolean isStrike, boolean isSized, boolean isBullet, boolean isColored, boolean isBgColored, int pickedColor, int pickedBgColor, float iPickedSize) {
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderLine = isUnderLine;
        this.isStrike = isStrike;
        this.isSized = isSized;
        this.isBullet = isBullet;
        this.isColored = isColored;
        this.isBgColored = isBgColored;
        this.pickedColor = pickedColor;
        this.pickedBgColor = pickedBgColor;
        this.iPickedSize = iPickedSize;

    }


    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {


        if (source.length() > 0) {
            if (start == end) {
                return "";
            } else if (end < dend - dstart) {
                return dest.subSequence(dstart, dend - 1);
            }
            SpannableString sp = new SpannableString(source);
            int flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;
            if (!isBold && !isItalic && !isUnderLine && !isColored && !isStrike && !isSized && !isBullet && !isBgColored) {
//                sp.setSpan(new StyleSpan(Typeface.NORMAL), start, end, flag);
                return sp;
            } else {
                if (isBold)
                    sp.setSpan(new StyleSpan(Typeface.BOLD), start, end, flag);
                if (isItalic)
                    sp.setSpan(new StyleSpan(Typeface.ITALIC), start, end, flag);
                if (isUnderLine)
                    sp.setSpan(new CustomUnderlineSpan(), start, end, flag);
                if (isSized)
                    sp.setSpan(new AbsoluteSizeSpan((int) iPickedSize, true), start, end, flag);
                if (isColored)
                    sp.setSpan(new ForegroundColorSpan(pickedColor), start, end, flag);
                if (isBgColored)
                    sp.setSpan(new BackgroundColorSpan(pickedBgColor), start, end, flag);
                if (isStrike)
                    sp.setSpan(new StrikethroughSpan(), start, end, flag);

            }

            return sp;
        } else
            return null;

    }

}
