package com.application.idea.sourav.showcaseandroid.ui.components.customSnackBar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.utils.Utils;


public class SelectionSnackBarMenu extends BaseTransientBottomBar<SelectionSnackBarMenu> {

//    private static int actionId;
//    private static boolean hasMoreAction;

    private SelectionSnackBarMenu(ViewGroup parent, View content, ContentViewCallback callback) {
        super(parent, content, callback);
    }

    public static SelectionSnackBarMenu make(Context context, @NonNull ViewGroup parent, @Duration int duration/*, int actionId, boolean hasMoreAction*/) {

        final View contentLayout = getSelectionContentLayout(context/*, hasMoreAction*/);

        final ContentViewCallback viewCallback = new ContentViewCallback(contentLayout);
        final SelectionSnackBarMenu selectionSnackBarMenu = new SelectionSnackBarMenu(parent, contentLayout, viewCallback);
//        selectionSnackBarMenu.getView().setPadding(0, 0, 0, 0);
        selectionSnackBarMenu.setDuration(duration);
        return selectionSnackBarMenu;
    }

    private static View getSelectionContentLayout(Context mContext/*, boolean hasMoreAction*/) {

        LinearLayout containerLayout = new LinearLayout(mContext);
        containerLayout.setGravity(Gravity.CENTER);
        containerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        containerLayout.addView(getHorizontalScrollView(mContext));
        containerLayout.addView(getToolbarOption(mContext));
        containerLayout.setBackground(mContext.getResources().getDrawable(R.drawable.shadow_card_flame,mContext.getTheme()));
        return containerLayout;
    }

    private static Toolbar getToolbarOption(Context mContext) {
        Toolbar toolbar = new Toolbar(mContext);
        toolbar.setId(R.id.snack_bar_toolbar);
        toolbar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setPadding(0, 0, 0, 0);
        toolbar.inflateMenu(R.menu.snack_bar_menu);

//        todo control menu dynamically
        /*
         *    toolbar.getMenu().add(0,123, 100,"common action");
        Drawable iconDrawable = Utils.getIcon(mContext, R.drawable.ic_menu_send, Color.GRAY);
        toolbar.getMenu().getItem(0).setIcon(iconDrawable);
         */

        toolbar.setContentInsetStartWithNavigation(0);
        return toolbar;
    }


    private static HorizontalScrollView getHorizontalScrollView(Context mContext) {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(mContext);
        horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView.setFillViewport(true);

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        int i5dp = Utils.dpToPx(5);
        int i10dp = Utils.dpToPx(10);
        horizontalScrollView.setPadding(i5dp, i5dp, i5dp, i10dp);
        linearLayout.setId(R.id.snack_item_container);
        horizontalScrollView.addView(linearLayout);
        return horizontalScrollView;
    }

    public void onSnackOptionSelectionListener(final OnSnackSelectionItemActions onSnackSelectionItemListener) {
        this.onSnackSelectionItemListener = onSnackSelectionItemListener;
        Toolbar toolbar = getView().findViewById(R.id.snack_bar_toolbar);
        if (toolbar != null) {

            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (SelectionSnackBarMenu.this.onSnackSelectionItemListener != null)
                        SelectionSnackBarMenu.this.onSnackSelectionItemListener.onSnackMenuItemSelected(menuItem);
                    clearAllSelectionView();
                    SelectionSnackBarMenu.this.dismiss();
                    return false;
                }
            });
        }
    }

    public void addSelectionContent(String sUserName, Drawable viewIcon) {

        LinearLayout linearLayout = getView().findViewById(R.id.snack_item_container);
        if (linearLayout != null) {


            linearLayout.addView(getTextDisplayContent(getView().getContext(), sUserName, viewIcon), 0);
//            linearLayout.requestLayout();
        }
    }

    private TextView getTextDisplayContent(Context context, final String sUserName, Drawable viewIcon) {
        final TextView textView = new TextView(context);
        int i5dp = Utils.dpToPixel(5);
        int i45dp = Utils.dpToPixel(45);
        LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(Utils.dpToPixel(60), ViewGroup.LayoutParams.WRAP_CONTENT);
        textParam.setMargins(i5dp, 0, 0, 0);
        textView.setLayoutParams(textParam);

        Drawable iconDrawable;
        if (viewIcon != null && viewIcon.mutate().getConstantState() != null)
            iconDrawable = viewIcon.mutate().getConstantState().newDrawable();
        else
            iconDrawable = Utils.getIcon(context, R.drawable.user_remove, Color.GRAY);
        Drawable userAction=context.getResources().getDrawable(R.drawable.circule_fill_cancel);
//        Drawable userAction=Utils.getTextToDrawable("99",12,Color.YELLOW,Color.RED);
        //pass the clone looking cool
//        Drawable removeDrawable = Utils.labeledIconDrawable(iconDrawable, iconDrawable.mutate().getConstantState().newDrawable(),16,0);
        Drawable removeDrawable = Utils.labeledIconDrawable(iconDrawable, userAction,16,1);
        removeDrawable.setBounds(0, 0, i45dp, i45dp);
        textView.setCompoundDrawables(null, removeDrawable, null, null);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.DKGRAY);
        textView.setText(sUserName);
        textView.setMaxLines(2);
        textView.setMinLines(2);
        textView.setTag(sUserName);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup parent = (ViewGroup) v.getParent();
                parent.removeView(v);
                if (onSnackSelectionItemListener != null)
                    onSnackSelectionItemListener.removedSnackSelectedView(v, sUserName);
                if (parent.getChildCount() == 0) {
                    dismiss();
                }
            }
        });
        return textView;
    }

    public void clearAllSelectionView() {
        LinearLayout linearLayout = getView().findViewById(R.id.snack_item_container);
        if (linearLayout != null)
            linearLayout.removeAllViews();
    }

    public void removeSelectionContent(String sUserTag) {

        LinearLayout linearLayout = getView().findViewById(R.id.snack_item_container);
        if (linearLayout != null && linearLayout.getChildCount() > 0) {
            View view = linearLayout.findViewWithTag(sUserTag);
            if (view != null)
                linearLayout.removeView(view);
            if (linearLayout.getChildCount() == 0)
                dismiss();
        } else dismiss();
    }


    public OnSnackSelectionItemActions onSnackSelectionItemListener;

    public interface OnSnackSelectionItemActions {
        void onSnackMenuItemSelected(MenuItem menuItem);

        void removedSnackSelectedView(View view, String sUserName);

    }

    public void setOnSnackSelectionItemListener(OnSnackSelectionItemActions onSnackSelectionItemListener) {
        this.onSnackSelectionItemListener = onSnackSelectionItemListener;
    }

    private static class ContentViewCallback implements BaseTransientBottomBar.ContentViewCallback {

        private View content;

        public ContentViewCallback(View content) {
            this.content = content;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            content.setTranslationX(Resources.getSystem().getDisplayMetrics().widthPixels);
            content.animate().translationX(0).setDuration(300).setInterpolator(new AccelerateInterpolator());

        }

        @Override
        public void animateContentOut(int delay, int duration) {
//            ViewCompat.setScaleY(content, 1f);
//            ViewCompat.animate(content).scaleY(0f).setDuration(duration).setStartDelay(delay);
//            content.setTranslationX(0);
//            content.animate().translationX(Resources.getSystem().getDisplayMetrics().widthPixels).setDuration(300).setInterpolator(new AccelerateInterpolator());
        }


    }
}