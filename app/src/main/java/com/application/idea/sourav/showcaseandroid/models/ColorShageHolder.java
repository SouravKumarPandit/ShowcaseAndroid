package com.application.idea.sourav.showcaseandroid.models;

import android.graphics.Color;

public class ColorShageHolder {

    private final int iColor1;
    private final int iColor2;
    private final int iColor3;


    private final int iconResouce;


    public ColorShageHolder(int iColor2, int iColor3, int iconResouce)

    {
        this.iColor2 = iColor2;
        this.iColor1 = getDarkerColor(iColor2);
        this.iColor3 = iColor3;
        this.iconResouce = iconResouce;


    }

    public int getiColor1()
    {
        return iColor1;
    }

    public int getiColor3()
    {
        return iColor3;
    }


    public int getiColor2()
    {
        return iColor2;
    }

    public int getIconResouce()
    {
        return iconResouce;
    }


    public int getDarkerColor(int color)
    {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] + 0.1f;
        hsv[2] = hsv[2] - 0.1f;
        int darkerColor = Color.HSVToColor(hsv);
        return darkerColor;
    }
}
