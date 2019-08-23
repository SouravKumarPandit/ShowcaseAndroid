package com.application.idea.sourav.showcaseandroid.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class StateDrawableBuilder {
    private static final int[] STATE_SELECTED = new int[]{android.R.attr.state_selected};
    private static final int[] STATE_PRESSED = new int[]{android.R.attr.state_pressed};
    private static final int[] STATE_ENABED = new int[]{android.R.attr.state_enabled};
    private static final int[] STATE_DISABED = new int[]{-android.R.attr.state_enabled};

    private Drawable normalDrawable;
    private Drawable selectedDrawable;
    private Drawable pressedDrawable;
    private Drawable disabledDrawable;

    public StateDrawableBuilder setNormalDrawable(Drawable normalDrawable)
    {
        this.normalDrawable = normalDrawable;
        return this;
    }

    public StateDrawableBuilder setPressedDrawable(Drawable pressedDrawable)
    {
        this.pressedDrawable = pressedDrawable;
        return this;
    }

    public StateDrawableBuilder setSelectedDrawable(Drawable selectedDrawable)
    {
        this.selectedDrawable = selectedDrawable;
        return this;
    }

    public StateDrawableBuilder setDisabledDrawable(Drawable disabledDrawable)
    {
        this.disabledDrawable = disabledDrawable;
        return this;
    }

    public StateListDrawable build()
    {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (this.selectedDrawable != null)
        {
            stateListDrawable.addState(STATE_SELECTED, this.selectedDrawable);
        }

        if (this.pressedDrawable != null)
        {
            stateListDrawable.addState(STATE_PRESSED, this.pressedDrawable);
        }

        if (this.normalDrawable != null)
        {
            stateListDrawable.addState(STATE_ENABED, this.normalDrawable);
        }

        if (this.disabledDrawable != null)
        {
            stateListDrawable.addState(STATE_DISABED, this.disabledDrawable);
        }
        return stateListDrawable;
    }


}