package com.application.idea.sourav.showcaseandroid.ui.activitys.recycleSelection;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends DividerItemDecoration {
    private int space;

    public SpacesItemDecoration(Context clContext,int oriantation,int space) {
        super(clContext,oriantation);
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect,view,parent,state);
        int position = parent.getChildAdapterPosition(view);
        boolean isLast = position == state.getItemCount()-1;
        if(isLast){
            outRect.bottom = space;
            outRect.top = 0; //don't forget about recycling...
        }
       /* if(position == 0){
            outRect.top = space;
            // don't recycle bottom if first item is also last
            // should keep bottom padding set above
            if(!isLast)
                outRect.bottom = 0;
        }*/
    }
}