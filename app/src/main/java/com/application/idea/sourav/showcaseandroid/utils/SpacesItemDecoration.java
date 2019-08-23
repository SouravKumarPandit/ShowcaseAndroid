package com.application.idea.sourav.showcaseandroid.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration( int space)
    {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        outRect.left = space / 3;
        outRect.right = space / 3;
        outRect.bottom = space;
//        outRect.bottom = space;
        int position = parent.getChildAdapterPosition(view);
        boolean isLast = position == state.getItemCount()-1;
        if(isLast){
            outRect.bottom = Utils.dpToPixel(150); //don't forget about recycling...
        }

    }
}