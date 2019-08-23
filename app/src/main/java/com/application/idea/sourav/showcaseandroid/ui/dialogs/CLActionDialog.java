package com.application.idea.sourav.showcaseandroid.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

/**
 * Created by Sourav on 12/13/2018.
 */
@SuppressLint("ValidFragment")
public class CLActionDialog extends DialogFragment {
    private Context clContext;
    private int iActionId;
    private String sTransIds;
    private CharSequence sTitle;
    private CharSequence sMessage;
    public OnActionPerformed onActionPerformed;

    public CLActionDialog(Context clContext, int iActionId, String sTransIds, CharSequence sTitle, CharSequence sMessage, OnActionPerformed onActionPerformed) {
        this.onActionPerformed = onActionPerformed;
        this.sTitle = sTitle;
        this.sMessage = sMessage;
        this.clContext = clContext;
        this.iActionId = iActionId;
        this.sTransIds = sTransIds;
    }

    public CLActionDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);
        Dialog clDialogBuilder = new Dialog(clContext);
        clDialogBuilder.setContentView(getDialogView());
        clDialogBuilder.setCancelable(false);
      /*  String sOkButton = getString(com.focus.centra.crm.R.string.done);
        if (iActionId == IAConstants.IActions.CHANGE_EMAIL)
            sOkButton = "Update";
        clDialogBuilder.setPositiveButton(sOkButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (onActionPerformed != null)
                    onActionPerformed.onConfirmDone(getDialog(), iActionId, sTransIds);
            }
        });
        clDialogBuilder.setNegativeButton(com.focus.centra.crm.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (onActionPerformed != null)
                    onActionPerformed.onDismissed(iActionId, sTransIds);
                dismiss();
            }
        });
//        clDialogBuilder.setCancelable(true);
        final Dialog clDialog = clDialogBuilder.create();
        if (clDialog.getWindow() != null) {

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                clDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            } else {
                clDialog.getWindow().setBackgroundDrawable(CLAViewUtil.getRoundDrawable(Color.WHITE, CLAViewUtil.dpToPx(10)));
            }
            clDialog.getWindow().setWindowAnimations(com.focus.centra.crm.R.style.pauseDialogAnimationBase);
            clDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }*/

        return clDialogBuilder;

    }
    @Override
    public void onResume()
    {
        super.onResume();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    private FrameLayout getDialogView() {

        FrameLayout.LayoutParams clMainParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        FrameLayout clMainLayout = new FrameLayout(getActivity());
        clMainLayout.setId(R.id.frame_layout);
        clMainLayout.setBackgroundColor(Color.TRANSPARENT);
        clMainLayout.setLayoutParams(clMainParams);
        clMainLayout.addView(getActionLayoutView(clContext));
        return clMainLayout;

    }


    public LinearLayout getActionLayoutView(Context clContext) {

        LinearLayout layout = new LinearLayout(clContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int i20dp = Utils.dpToPixel( 20);
        layout.setLayoutParams(layoutParams);
        layout.setGravity(Gravity.CENTER_VERTICAL);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(i20dp, 0, i20dp, 0);
        TextView TextView = new TextView(clContext);
        LinearLayout.LayoutParams editParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView.setTextColor(Color.GRAY);
        TextView.setLayoutParams(editParam);
        TextView.setSingleLine(false);
        TextView.setText(sMessage);
        if (sTitle!=null)
        layout.addView(getHeaderView(clContext));
        else
        TextView.setPadding(0,i20dp,0,i20dp);
        layout.addView(TextView);
        return layout;
    }

    public RelativeLayout getHeaderView(Context clContext) {
        RelativeLayout.LayoutParams clActionLayoutParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPx(50));
        RelativeLayout clHeaderLayout = new RelativeLayout(clContext);
        clHeaderLayout.setLayoutParams(clActionLayoutParam);
        RelativeLayout.LayoutParams clHeaderTitleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clHeaderTitleParams.addRule(RelativeLayout.CENTER_VERTICAL);
        TextView clTitleTextView = new TextView(clContext);
        clTitleTextView.setGravity(Gravity.CENTER);
        clTitleTextView.setLayoutParams(clHeaderTitleParams);
        clTitleTextView.setTextColor(Color.BLACK);
        clTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        clTitleTextView.setText(sTitle);
        clHeaderLayout.addView(clTitleTextView);
        return clHeaderLayout;
    }

    public interface OnActionPerformed {
        void onConfirmDone(Dialog dialog, int iActionId, String sTransIds);

        void onDismissed(int iActionId, String sTransIds);

    }
}
