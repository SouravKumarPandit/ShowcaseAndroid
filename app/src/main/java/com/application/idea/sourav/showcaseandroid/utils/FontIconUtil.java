package com.application.idea.sourav.showcaseandroid.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontIconUtil
{
    public static final String ROOT = "fonts/";
           // FONTAWESOME = ROOT + "fontawesome-webfont.ttf",FONTMOON=ROOT+"icomoon.ttf";

    public static  final String DEFAULT_FONT=ROOT+"Larke_Regular.ttf";
    public static  final String NEW_FONT=ROOT+"Larke_Regular.ttf";

    /**
     * To mark all the TextViews in the container with Font Icon TypeFace
     * @param v
     * @param typeface
     */
    public static void markAsIconContainer(View v, Typeface typeface)
    {
        //View childd;
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View  child= vg.getChildAt(i);
                      markAsIconContainer(child,typeface);
            }
        } else if (v instanceof TextView) {
            ((TextView) v).setTypeface(typeface);
        }
    }

    public static Typeface getTypeface(Context context, String font) {

        return Typeface.createFromAsset(context.getAssets(), font);
    }

    public static Drawable getFontDrawable(Context context,int size,String sResIconId,int iColor)
    {
        FontIconDrawable faIcon = new FontIconDrawable(context);
        faIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        faIcon.setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
        faIcon.setTextColor(iColor);
        faIcon.setTypeface(FontIconUtil.getTypeface(context, FontIconUtil.DEFAULT_FONT));
        faIcon.setText(sResIconId);
        return faIcon;
    }


    public static Drawable getFontDrawable2(Context context,int size,String sResIconId,int iColor)
    {
        FontIconDrawable faIcon = new FontIconDrawable(context);
        faIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        faIcon.setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
        faIcon.setTextColor(iColor);
        faIcon.setTypeface(FontIconUtil.getTypeface(context, FontIconUtil.NEW_FONT));
        faIcon.setText(sResIconId);
        return faIcon;
    }
    public static Drawable getFontDrawable(Context context,int size,String sResIconId)
    {
       return getFontDrawable(context,size,sResIconId,0xffdebdee);
    }


}