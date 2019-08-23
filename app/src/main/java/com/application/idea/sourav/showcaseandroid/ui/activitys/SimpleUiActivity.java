package com.application.idea.sourav.showcaseandroid.ui.activitys;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import com.application.idea.sourav.showcaseandroid.ui.base.BaseActivity;
import com.application.idea.sourav.showcaseandroid.utils.Utils;

/**
 * Created by Sourav on 2018
 */
public class SimpleUiActivity extends BaseActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
    }

    private View getContentLayout()
    {
        LinearLayout linearLayout = new LinearLayout(this);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setGravity(Gravity.TOP);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(Utils.dpToPx(10), Utils.dpToPx(10), Utils.dpToPx(10), Utils.dpToPx(10));

        final LinearLayout boxLayout=new LinearLayout(this);
        boxLayout.setBackground(getNavigationDrawable());
        LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dpToPx(250));
        layoutParams.setMargins(Utils.dpToPx(5),Utils.dpToPx(5),Utils.dpToPx(5),Utils.dpToPx(5));
        boxLayout.setLayoutParams(layoutParams);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
                ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setConvexPath(mPath);
//                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), view.getHeight() / 2);
                }
            };
            boxLayout.setOutlineProvider(viewOutlineProvider); //Notice I have used inheritance
//            boxLayout.setClipToOutline(true);
            boxLayout.setElevation(5f);
        }


        linearLayout.addView(boxLayout);
//        linearLayout.addView(outlinedTextView);
        return linearLayout;
    }
    final Path mPath = new Path();

    public Drawable getNavigationDrawable()
    {

        Shape shape = new Shape()
        {
            @Override
            public void draw(Canvas canvas, Paint paint)
            {

//                paint.setShader(new LinearGradient(0, 0, canvas.getWidth()/2, getHeight(), CLThemeUtil.getThemePrimaryColor(CLHomeActivity.this), CLThemeUtil.getThemePrimaryColor(CLHomeActivity.this), Shader.TileMode.MIRROR));
                paint.setColor(0xff23B96B);
                int canvasWeight = canvas.getWidth();
                int canvasHeight = canvas.getHeight();

                mPath.reset();
                mPath.moveTo(canvasWeight / 2, 0);
                mPath.lineTo(0, 0);
                mPath.lineTo(0, canvasHeight * 0.8f);
                mPath.cubicTo(0, canvasHeight,
                        canvasWeight / 2, canvasHeight * 1.2f,
                        canvasWeight * 0.6f, canvasHeight * 0.55f);
                mPath.cubicTo(canvasWeight * 0.6f, canvasHeight * 0.6f,
                        canvasWeight * 0.6f, canvasHeight * 0.2f,
                        canvasWeight, Utils.dpToPixel(45));
                mPath.lineTo(canvasWeight, 0);
                mPath.close();
                canvas.drawPath(mPath, paint);
            }
        };
        shape.getHeight();
        return new ShapeDrawable(shape);
    }


}
