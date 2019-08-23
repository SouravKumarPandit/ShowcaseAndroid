package com.application.idea.sourav.showcaseandroid.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.*;
import android.os.Build;

public class StateBackgroundDrawable
{
    public static final byte BORDER_TOP = 0;
    public static final byte BORDER_BOTTOM = 1;
    public static final byte BORDER_LEFT = 2;
    public static final byte BORDER_RIGHT = 3;

    public static final byte TOP_TO_BOTTOM = 0;
    public static final byte BOTTOM_TO_TOP = 1;
    public static final byte LEFT_TO_RIGHT = 2;
    public static final byte RIGHT_TO_LEFT = 3;

    public static Drawable getLinearDrawable(int iBackgroundColor, int iLayerColor, int iLayerType)
    {

        GradientDrawable.Orientation iOrientation = null;
        iOrientation = iLayerType == TOP_TO_BOTTOM ? GradientDrawable.Orientation.TOP_BOTTOM :
                iLayerType == BOTTOM_TO_TOP ? GradientDrawable.Orientation.BOTTOM_TOP :
                        iLayerType == LEFT_TO_RIGHT ? GradientDrawable.Orientation.LEFT_RIGHT :
                                iLayerType == RIGHT_TO_LEFT ? GradientDrawable.Orientation.RIGHT_LEFT : null;

        GradientDrawable clBackground = null;
        if (iOrientation != null)
        {
            clBackground = new GradientDrawable(iOrientation, new int[]{iBackgroundColor, iBackgroundColor, iLayerColor});//new int[]{startColor, centerColor, endColor}
            clBackground.setGradientCenter(0.5f, 0.5f);
            clBackground.setShape(GradientDrawable.RECTANGLE);
        }


        return clBackground;

    }


    public static Drawable getLayerListDrawable(Context clContext, int iBackgroundColor, int iLayerColor, int iLayerType,int iLayerHeight)
    {


        if(!(iLayerHeight>0))
            iLayerHeight = Utils.dpToPixel(10);

        GradientDrawable clBackground = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{iBackgroundColor, iLayerColor});
        clBackground.setShape(GradientDrawable.RECTANGLE);
        clBackground.setColor(iBackgroundColor);

        GradientDrawable clBackgroundLayer = new GradientDrawable();
        clBackgroundLayer.setShape(GradientDrawable.RECTANGLE);
        clBackgroundLayer.setColor(iLayerColor);

        Drawable[] clLayerList = {clBackgroundLayer, clBackground};
        LayerDrawable clLinearDrawable = new LayerDrawable(clLayerList);

        //setLayerInset(layer, leftOffset, topOffset, rightOffset, bottomOffset)

        clLinearDrawable.setLayerInset(0, 0, 0, 0, 0);
        clLinearDrawable.setLayerInset(1,
                iLayerType == BORDER_LEFT ? iLayerHeight : 0,
                iLayerType == BORDER_TOP ? iLayerHeight : 0,
                iLayerType == BORDER_RIGHT ? iLayerHeight : 0,
                iLayerType == BORDER_BOTTOM ? iLayerHeight : 0);


        return clBackground;

    }
    public static Drawable getReportBorderDrawable(Context clContext, int iBackgroundColor, int iLayerColor, int iLayerType,int iLayerHeight)
    {


        if(!(iLayerHeight>0))
            iLayerHeight = Utils.dpToPixel(10);

        GradientDrawable clBackground = new GradientDrawable();
        clBackground.setShape(GradientDrawable.RECTANGLE);
        clBackground.setColor(iBackgroundColor);

        GradientDrawable clBackgroundLayer = new GradientDrawable();
        clBackgroundLayer.setShape(GradientDrawable.RECTANGLE);
        clBackgroundLayer.setColor(iLayerColor);

        Drawable[] clLayerList = {clBackgroundLayer, clBackground};
        LayerDrawable clLinearDrawable = new LayerDrawable(clLayerList);


        return clLinearDrawable;

    }


    public static Drawable getLayerListDrawable(Context clContext, int iBackgroundColor, ColorStateList clColorStateList, int iLayerType,int iBorderWidth)
    {

        if(iBorderWidth<0)
            iBorderWidth=Utils.dpToPixel(5);

        GradientDrawable clBackground = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{iBackgroundColor, clColorStateList.getDefaultColor()});
        clBackground.setShape(GradientDrawable.RECTANGLE);
        clBackground.setColor(iBackgroundColor);

        GradientDrawable clBackgroundLayer = new GradientDrawable();
        clBackgroundLayer.setShape(GradientDrawable.RECTANGLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            clBackgroundLayer.setColor(clColorStateList);
        }

        Drawable[] clLayerList = {clBackgroundLayer, clBackground};
        LayerDrawable clLinearDrawable = new LayerDrawable(clLayerList);

        //setLayerInset(layer, leftOffset, topOffset, rightOffset, bottomOffset)

        clLinearDrawable.setLayerInset(0, 0, 0, 0, 0);
        clLinearDrawable.setLayerInset(1,
                iLayerType == BORDER_LEFT ? iBorderWidth : 0,
                iLayerType == BORDER_TOP ? iBorderWidth : 0,
                iLayerType == BORDER_RIGHT ? iBorderWidth : 0,
                iLayerType == BORDER_BOTTOM ? iBorderWidth : 0);


        return clLinearDrawable;

    }



    public static StateListDrawable getSelectorDrawable(int color) {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(color));
        res.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(color));
        res.addState(new int[]{android.R.attr.state_activated}, new ColorDrawable(color));
        res.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        return res;
    }

    public static StateListDrawable getRPLEffectPreLOP1(int iSelectedColor,int iNormalColor) {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{android.R.attr.state_checked}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{}, new ColorDrawable(iNormalColor));
        return res;
    }

    public static StateListDrawable getRPLEffectPreLOP(int iSelectedColor,int iNormalColor)
    {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{android.R.attr.state_activated}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{}, new ColorDrawable(iNormalColor));
        return res;
    }

    public static ColorStateList colorStateList(int[] colors)
    {

        int[][] states = new int[][]{new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_pressed}  // pressed
        };
        if (colors == null||colors.length!=4)
            colors = new int[]{Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};

        return new ColorStateList(states, colors);

    }
    public static  ColorStateList getColorStateListDrawable(int iNormalColor, int iSelectedColor)
    {

        int[] pressed = new int[]{android.R.attr.state_pressed};
        int[] focused = new int[]{android.R.attr.state_focused};
        int[] enabled = new int[]{android.R.attr.state_enabled};
        int[] selected = new int[]{android.R.attr.state_selected};


        return new ColorStateList(new int[][]{pressed, focused, selected, enabled}, new int[]{iSelectedColor, iSelectedColor, iSelectedColor, iNormalColor});

    }


}