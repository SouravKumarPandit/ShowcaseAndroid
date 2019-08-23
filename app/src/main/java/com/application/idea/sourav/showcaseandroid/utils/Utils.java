package com.application.idea.sourav.showcaseandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.application.idea.sourav.showcaseandroid.R;


public class Utils
{
    /*

               android:configChanges="orientation|screenSize|keyboardHidden"
               android:theme="@style/Theme.AppCompat.Light.NoActionBar"
               android:windowSoftInputMode="stateHidden|adjustPan"

               */
// The public static function which can be called from other classes
    public static void darkenStatusBar(Activity activity, int color)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {

            activity.getWindow().setStatusBarColor(
                    darkenColor(
                            ContextCompat.getColor(activity, color)));
        }

    }



    // Code to darken the color supplied (mostly color of toolbar)
    public static int darkenColor(int color)
    {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }






    public static StateListDrawable getSelectorDrawable(int prssedColor)
    {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(prssedColor));
        res.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(prssedColor));
        res.addState(new int[]{android.R.attr.state_activated}, new ColorDrawable(prssedColor));
        res.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        return res;
    }

    public static Drawable getDrawableLine(Context context, int color1)
    {
        LayerDrawable layerDrawable = (LayerDrawable) context.getResources().getDrawable(R.drawable.field_bottom_line);
        GradientDrawable strokeDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.item_bottom_stroke);
        strokeDrawable.setStroke(dpToPx(2), color1);
        return layerDrawable;
    }

    public static void setLightStatusBar(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.GRAY); // optional
        }
    }

    public static void setStatusBarColor(Activity activity, @ColorInt int statusBarColor)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusBarColor);
        }
    }

    public static void clearLightStatusBar(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; // use XOR here for remove LIGHT_STATUS_BAR from flags
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.GREEN); // optional
        }
    }





    public static Drawable getStorkBottomDrawable(@ColorInt int colorStrok, int iSize)
    {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setStroke(iSize, colorStrok);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable});
        layerDrawable.setLayerInset(0, -iSize, -iSize, -iSize, 0);
        return layerDrawable;
    }

    public static Drawable getRoundDrawable(@ColorInt int normalColor, float normalRounded)
    {

        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColor(normalColor);
        normalDrawable.setCornerRadius(normalRounded);

        return normalDrawable;
    }

    public static Drawable getRoundGradientDrawable(@ColorInt int[] colorArray, float normalRounded)
    {

        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColors(colorArray);
        normalDrawable.setCornerRadius(normalRounded);

        return normalDrawable;
    }
    /*<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/item_bottom_stroke"
        android:left="-6dp"
        android:right="-6dp"
        android:top="-6dp">
        <shape>
            <solid android:color="#00ffffff" />
            <stroke
                android:width="2dp"
                android:color="@color/gray_500" />
        </shape>
    </item>
</layer-list>*/





    public static Drawable getIcon(Context context, String sResourceId, int iSize, int iColor, boolean isDrawableIcon)
    {

        ///iColor

        if (isDrawableIcon)
            return getIcon(context, Integer.parseInt(sResourceId), iColor);
        else
            return FontIconUtil.getFontDrawable2(context, iSize, sResourceId, iColor);
    }
//    --------------------------------------------------------------------------------------------------------

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore)
    {

        if (tv.getTag() == null)
        {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout()
            {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0)
                {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine)
                {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else
                {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    public static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv, final int maxLine, final String spanableText, final boolean viewMore)
    {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText))
        {


            ssb.setSpan(new MySpannable(false)
            {
                @Override
                public void onClick(View widget)
                {
                    if (viewMore)
                    {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else
                    {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, ".. See More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }

    public static class MySpannable extends ClickableSpan
    {

        private boolean isUnderline = true;

        /**
         * Constructor
         */
        public MySpannable(boolean isUnderline)
        {
            this.isUnderline = isUnderline;
        }

        @Override
        public void updateDrawState(TextPaint ds)
        {
            ds.setUnderlineText(isUnderline);
            ds.setColor(Color.parseColor("#1b76d3"));
        }

        @Override
        public void onClick(View widget)
        {


        }
    }

    public Drawable getCurvedDrawable()
    {

        final Path mPath = new Path();
        Shape shape = new Shape()
        {
            @Override
            public void draw(Canvas canvas, Paint paint)
            {

                paint.setShader(new LinearGradient(0, 0, canvas.getWidth() / 2, getHeight(), 0xff9fa3fd, 0xff7475dc, Shader.TileMode.MIRROR));

                int canvasWeight = canvas.getWidth();
                int canvasHeight = canvas.getHeight();

                mPath.reset();
                mPath.moveTo(canvasWeight / 2, 0);
                mPath.lineTo(0, 0);
                mPath.lineTo(0, canvasHeight * 0.8f);
                mPath.cubicTo(0, canvasHeight,
                        canvasWeight / 2, canvasHeight * 1.2f,
                        canvasWeight * 0.6f, canvasHeight * 0.55f);

//                mPath.moveTo(canvasWeight*0.6f,canvasHeight*0.6f);
                mPath.cubicTo(canvasWeight * 0.6f, canvasHeight * 0.6f,
                        canvasWeight * 0.6f, canvasHeight * 0.2f,
                        canvasWeight, canvasHeight * 0.2f);

                mPath.lineTo(canvasWeight, 0);
//                mPath.lineTo(canvasWeight, canvasHeight);
//                mPath.lineTo(0, canvasHeight);
                mPath.close();

                canvas.drawPath(mPath, paint);
            }
        };
        shape.getHeight();
        return new ShapeDrawable(shape);
    }

    /*public static Drawable getTilteDrawable()
    {

        final Path mPath = new Path();
        final Path mPath1 = new Path();
        Shape shape = new Shape()
        {
            @Override
            public void draw(Canvas canvas, Paint paint)
            {

//                paint.setShader(new LinearGradient(0, 0, canvas.getWidth()/2, getHeight(), 0xff9fa3fd, 0xff7475dc, Shader.TileMode.MIRROR));
                paint.setColor(0xf09fa3fd);
                int canvasWeight = canvas.getWidth();
                int canvasHeight = canvas.getHeight();

                mPath.reset();
                mPath1.reset();

                mPath.moveTo(canvasWeight, canvasHeight);
                mPath.lineTo(canvasWeight, 0);
                mPath.lineTo(0, 0);
                mPath.lineTo(0, canvasHeight * 0.80f);

                mPath1.moveTo(canvasWeight, canvasHeight);
                mPath1.lineTo(canvasWeight, 0);
                mPath1.lineTo(0, 0);
                mPath1.lineTo(0, canvasHeight * 0.9f);

                canvas.drawPath(mPath1, paint);
                paint.setColor(0xff7475dc);
                canvas.drawPath(mPath, paint);
            }
        };
        shape.getHeight();
        return new ShapeDrawable(shape);
    }
*/
    public static Drawable getWaveDrawable()
    {

        final Path mPath = new Path();
        Shape shape = new Shape()
        {
            @Override
            public void draw(Canvas canvas, Paint paint)
            {

                paint.setColor(0xff7475dc);
                int canvasWeight = canvas.getWidth();
                int canvasHeight = canvas.getHeight();

                mPath.reset();

                mPath.moveTo(canvasWeight, canvasHeight * 0.8f);
                mPath.lineTo(canvasWeight, 0);
                mPath.lineTo(0, 0);
                mPath.lineTo(0, canvasHeight * 0.7f);
                mPath.cubicTo(0, canvasHeight * 0.7f, canvasWeight * 0.2f, canvasHeight * 0.85f, canvasWeight * 0.3f, canvasHeight * 0.6f);
                mPath.cubicTo(canvasWeight * 0.3f, canvasHeight * 0.6f, canvasWeight * 0.5f, canvasHeight * 0.15f, canvasWeight * 0.7f, canvasHeight * 0.8f);
                mPath.cubicTo(canvasWeight * 0.7f, canvasHeight * 0.8f, canvasWeight * 0.8f, canvasHeight * 1.2f, canvasWeight, canvasHeight * 0.8f);

                canvas.drawPath(mPath, paint);
            }
        };
        shape.getHeight();
        return new ShapeDrawable(shape);
    }
    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }

    public static float spToPixel(float spSize) {
        return spSize * Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Drawable getIcon(Context context, int iResId, int iColor) {
        Drawable drawable = context.getResources().getDrawable(iResId);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, iColor);// CLThemeUtil.getMenuViewColors(context, R.styleable.CLNavigationView_item_imageColor));
        return drawable;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    private Drawable getTextRoundImageDrawable(final int position, final String substring, final float iTextSize) {
        Shape shape = new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {
                paint.setColor(Color.BLUE);
                canvas.drawCircle(dpToPx(45) / 2, dpToPx(45) / 2, dpToPx(45) / 2, paint);
                paint.setTextSize(iTextSize);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2, paint);
                canvas.drawText(substring, canvas.getWidth() / 3, canvas.getHeight() / 3, paint);
            }
        };
        return new ShapeDrawable(shape);
    }

    public static StateListDrawable getColorDrawableListState(@ColorInt int normalColor, @ColorInt int pressedColor) {

        StateListDrawable arrowImgStates = new StateListDrawable();
        arrowImgStates.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(pressedColor));
        arrowImgStates.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(pressedColor));
        arrowImgStates.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(pressedColor));
        arrowImgStates.addState(new int[]{}, new ColorDrawable(normalColor));

        return arrowImgStates;
    }

    public static StateListDrawable getLineDrawableListState(int iSize, @ColorInt int normalColor, @ColorInt int pressedColor) {

        StateListDrawable arrowImgStates = new StateListDrawable();
        Drawable pressedDrawable = getStorkBottomDrawable(pressedColor, iSize);
        Drawable normalDrawable = getStorkBottomDrawable(normalColor, iSize);
        arrowImgStates.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        arrowImgStates.addState(new int[]{android.R.attr.state_focused}, pressedDrawable);
        arrowImgStates.addState(new int[]{android.R.attr.state_selected}, pressedDrawable);
        arrowImgStates.addState(new int[]{}, normalDrawable);

        return arrowImgStates;
    }



    public static Drawable getOnlineDrawable2(final int iSize, final int iDotColor) {

        Shape shape = new Shape() {

            @Override
            public void draw(Canvas canvas, Paint paint) {
                int pad = dpToPx(iSize * 2);
                int radii = dpToPx(iSize);
                paint.setColor(Color.WHITE);
//                paint.setShadowLayer(25, 0, 15, 0x80dedede);
                canvas.drawCircle(canvas.getWidth() - pad, pad / 2, radii, paint);
                radii = dpToPx(iSize - 2);
                paint.setColor(iDotColor);
                canvas.drawCircle(canvas.getWidth() - pad, pad / 2, radii, paint);
                paint.setColor(Color.WHITE);
                paint.setStrokeWidth(dpToPixel(3));
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawLine(canvas.getWidth() - pad + 1 - radii / 2, pad / 2, canvas.getWidth() - pad / 2 - radii, pad / 2, paint);
            }
        };
        return new ShapeDrawable(shape);
    }

    public static Drawable getTextToDrawable(final String sText, final float textSize, final int textColor, final int bgColor) {

        Shape shape = new Shape() {

            @Override
            public void draw(Canvas canvas, Paint paint) {
                paint.setTextSize(spToPixel(textSize));
                paint.setTextAlign(Paint.Align.LEFT);
                int ivImageSize = (int) spToPixel(textSize+4);
                float baseline = -paint.ascent(); // ascent() is negative
                int width = (int) (paint.measureText(sText)); // round
                int height = (int) (baseline + paint.descent());
                Bitmap image = Bitmap.createBitmap(ivImageSize, (int) (ivImageSize), Bitmap.Config.ARGB_8888);
                canvas.drawBitmap(image, ivImageSize, ivImageSize, paint);
                paint.setColor(bgColor);
                if (sText != null) {
                    if (sText.length() < 3) {

                        canvas.drawCircle(ivImageSize / 2, ivImageSize / 2, ivImageSize / 2, paint);
                        paint.setColor(textColor);
                        canvas.drawText(sText, (ivImageSize - width) / 2, (height + baseline) / 2, paint);
                    } else {
                        paint.setTextSize(spToPixel(textSize-2));
                        canvas.drawRect(0, 0, ivImageSize, height, paint);
                        paint.setColor(textColor);
                        canvas.drawText(sText, (ivImageSize - width) / 2, baseline, paint);

                    }
                }
            }
        };
        return new ShapeDrawable(shape);
    }

    public static View getGapView(Context clContext, int iHeight) {
        View gapView = new View(clContext);
        gapView.setLayoutParams(new LinearLayout.LayoutParams(0, dpToPixel(iHeight)));
        return gapView;
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor, int bgColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
//        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 5.0f); // round
        int height = (int) (baseline + paint.descent() + 5.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        paint.setColor(bgColor);
        canvas.drawCircle(width, width / 2, height / 2, paint);
        paint.setColor(textColor);
        canvas.drawText(text, width, baseline, paint);
        return image;
    }

    public static StateListDrawable getDrawableListState(Drawable normalDrawable, Drawable pressedDrawable) {

        StateListDrawable arrowImgStates = new StateListDrawable();
        arrowImgStates.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        arrowImgStates.addState(new int[]{android.R.attr.state_focused}, pressedDrawable);
        arrowImgStates.addState(new int[]{android.R.attr.state_selected}, pressedDrawable);
        arrowImgStates.addState(new int[]{}, normalDrawable);
        return arrowImgStates;
    }

    public static Drawable removeUserIcon(Drawable userIconDrawable, int dotColor, int dotSize, int paddingRight, int paddingTop) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.BLUE);
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setStroke(2, Color.RED);
//        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{userIconDrawable,getOnlineDrawable2(dotSize,dotColor)});
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{userIconDrawable, gradientDrawable});
//        layerDrawable.setLayerInset(1, -dotSize,dotSize,0,0);
        return layerDrawable;
    }

    public static Drawable labeledIconDrawable(Drawable bigIcon, Drawable smallIcon, int smallIconSize, int cornerAt) {
        smallIconSize = dpToPx(smallIconSize);
//        userIconDrawable.setBounds(0, 0, i45dp, i45dp);
        LayerDrawable layerDrawable;
        if (smallIcon == null) {
            layerDrawable = new LayerDrawable(new Drawable[]{bigIcon});

        } else {

            layerDrawable = new LayerDrawable(new Drawable[]{bigIcon, smallIcon});
            //top left =0 /top right =default/ bottom right =2 /bottom left =3
            if (cornerAt == 0) {
                layerDrawable.setLayerInset(1, 0, 0, bigIcon.getMinimumWidth() - smallIconSize, bigIcon.getIntrinsicHeight() - smallIconSize);
            } else if (cornerAt == 2) {
                layerDrawable.setLayerInset(1, bigIcon.getIntrinsicHeight() - smallIconSize, bigIcon.getIntrinsicHeight() - smallIconSize, 0, 0);
            } else if (cornerAt == 3) {
                layerDrawable.setLayerInset(1, 0, bigIcon.getIntrinsicHeight() - smallIconSize, bigIcon.getMinimumWidth() - smallIconSize, 0);
            } else {
                layerDrawable.setLayerInset(1, bigIcon.getIntrinsicHeight() - smallIconSize, 0, 0, bigIcon.getIntrinsicHeight() - smallIconSize);
            }
        }

        return layerDrawable;
    }

    public static StateListDrawable getDrawableListState(Context clContext, int normalDrawable, int pressedDrawable) {
        StateListDrawable arrowImgStates = new StateListDrawable();
        arrowImgStates.addState(new int[]{android.R.attr.state_pressed}, clContext.getResources().getDrawable(pressedDrawable));
        arrowImgStates.addState(new int[]{android.R.attr.state_focused}, clContext.getResources().getDrawable(normalDrawable));
        arrowImgStates.addState(new int[]{}, clContext.getResources().getDrawable(normalDrawable));

        return arrowImgStates;
    }

    public static StateListDrawable getRoundDrawableListState(@ColorInt int normalColor, float normalRounded, @ColorInt int pressedColor, float pressedRounded) {

        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColor(normalColor);
        normalDrawable.setCornerRadius(normalRounded);


        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setCornerRadius(pressedRounded);
        pressedDrawable.setColor(pressedColor);

        StateListDrawable arrowImgStates = new StateListDrawable();
        arrowImgStates.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        arrowImgStates.addState(new int[]{android.R.attr.state_focused}, pressedDrawable);
        arrowImgStates.addState(new int[]{android.R.attr.state_selected}, pressedDrawable);
        arrowImgStates.addState(new int[]{}, normalDrawable);

        return arrowImgStates;
    }


    public static StateListDrawable getRoundStrokeDrawableListState(@ColorInt int normalColor, float normalRounded, @ColorInt int pressedColor, float pressedRounded, @ColorInt int strokeColor, int strokewidth) {

        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setColor(normalColor);
        normalDrawable.setStroke(strokewidth, strokeColor);
        normalDrawable.setCornerRadius(normalRounded);


        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setCornerRadius(pressedRounded);
        pressedDrawable.setColor(pressedColor);

        StateListDrawable arrowImgStates = new StateListDrawable();
        arrowImgStates.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        arrowImgStates.addState(new int[]{android.R.attr.state_focused}, pressedDrawable);
        arrowImgStates.addState(new int[]{android.R.attr.state_selected}, pressedDrawable);
        arrowImgStates.addState(new int[]{}, normalDrawable);

        return arrowImgStates;
    }

    public static ColorStateList getColorStateListDrawable(int iNormalColor, int iSelectedColor) {

        int[] pressed = new int[]{android.R.attr.state_pressed};
        int[] focused = new int[]{android.R.attr.state_focused};
        int[] enabled = new int[]{android.R.attr.state_enabled};
        int[] selected = new int[]{android.R.attr.state_selected};


        return new ColorStateList(new int[][]{pressed, focused, selected, enabled}, new int[]{iSelectedColor, iSelectedColor, iSelectedColor, iNormalColor});

    }


    public static View getMarginalDivider(Context clContext, @ColorInt int iColor, int iDividerheight, int iLeftMargin, int iRightMargin, int topPading, int bottomPaidng) {
        View marginalDivider = new View(clContext);
        LinearLayout.LayoutParams marginalParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, iDividerheight);
        marginalDivider.setBackgroundColor(iColor);
        marginalParam.setMargins(iLeftMargin, topPading, iRightMargin, bottomPaidng);
        marginalDivider.setLayoutParams(marginalParam);

        return marginalDivider;
    }


    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }


    public static Drawable getTilteDrawable() {

        final Path mPath = new Path();
        final Path mPath1 = new Path();
        Shape shape = new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {

//                paint.setShader(new LinearGradient(0, 0, canvas.getWidth()/2, getHeight(), 0xff9fa3fd, 0xff7475dc, Shader.TileMode.MIRROR));
                paint.setColor(0xFFB3B3DE);
                int canvasWeight = canvas.getWidth();
                int canvasHeight = canvas.getHeight() / 2;

                mPath.reset();
                mPath1.reset();

                mPath.moveTo(canvasWeight, canvasHeight);
                mPath.lineTo(canvasWeight, 0);
                mPath.lineTo(0, 0);
                mPath.lineTo(0, canvasHeight * 0.80f);

                mPath1.moveTo(canvasWeight, canvasHeight);
                mPath1.lineTo(canvasWeight, 0);
                mPath1.lineTo(0, 0);
                mPath1.lineTo(0, canvasHeight * 0.9f);

                canvas.drawPath(mPath1, paint);
                paint.setColor(0xFFA5A5D2);
                canvas.drawPath(mPath, paint);
            }
        };
        shape.getHeight();
        return new ShapeDrawable(shape);
    }
    /*public static Drawable getCurvedDrawable()
    {

        final Path mPath = new Path();
        Shape shape = new Shape()
        {
            @Override
            public void draw(Canvas canvas, Paint paint)
            {

                paint.setShader(new LinearGradient(0, 0, canvas.getWidth() / 2, getHeight(), 0xff9fa3fd, 0xff7475dc, Shader.TileMode.MIRROR));

                int canvasWeight = canvas.getWidth();
                int canvasHeight = canvas.getHeight();

                mPath.reset();
                mPath.moveTo(canvasWeight / 2, 0);
                mPath.lineTo(0, 0);
                mPath.lineTo(0, canvasHeight * 0.8f);
                mPath.cubicTo(0, canvasHeight,
                        canvasWeight / 2, canvasHeight * 1.2f,
                        canvasWeight * 0.6f, canvasHeight * 0.55f);

//                mPath.moveTo(canvasWeight*0.6f,canvasHeight*0.6f);
                mPath.cubicTo(canvasWeight * 0.6f, canvasHeight * 0.6f,
                        canvasWeight * 0.6f, canvasHeight * 0.2f,
                        canvasWeight, canvasHeight * 0.2f);

                mPath.lineTo(canvasWeight, 0);
//                mPath.lineTo(canvasWeight, canvasHeight);
//                mPath.lineTo(0, canvasHeight);
                mPath.close();

                canvas.drawPath(mPath, paint);
            }
        };
        shape.getHeight();
        return new ShapeDrawable(shape);
    }*/

    /*public static Drawable drawbg(Context mContext) {
        float radius = 100.0f;

        float[] m_arrfTopHalfOuterRadii =
                new float[]{radius, radius, radius, radius, 0, 0, 0, 0};
        float[] m_arrfBottomHalfOuterRadii =
                new float[]{0, 0, 0, 0, radius, radius, radius, radius};

        int m_nTopColor = 0x65FF6309;
        int m_nBottomColor = 0x65aa6309;

        int m_nCellHeight = 150;

        Drawable drawable=mContext.getResources().getDrawable(R.drawable.metting_img);
        Bitmap bitmap= BitmapFactory.decodeResource(mContext.getResources(),R.drawable.metting_img);


        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), bitmap);
        final float roundPx = (float) bitmap.getWidth() * radius;
        roundedBitmapDrawable.setCornerRadius(roundPx);

        Drawable imageDrawable=roundedBitmapDrawable;

        RoundRectShape top_round_rect =
                new RoundRectShape(m_arrfTopHalfOuterRadii, null, null);
        ShapeDrawable top_shape_drawable = new ShapeDrawable(top_round_rect);
        top_shape_drawable.getPaint().setColor(m_nTopColor);

        RoundRectShape bottom_round_rect =
                new RoundRectShape(m_arrfBottomHalfOuterRadii, null, null);
        ShapeDrawable bottom_shape_drawable = new ShapeDrawable(bottom_round_rect);
        bottom_shape_drawable.getPaint().setColor(m_nBottomColor);

        Drawable[] drawarray = {imageDrawable,top_shape_drawable, bottom_shape_drawable};
        LayerDrawable layerdrawable = new LayerDrawable(drawarray);

        int _nHalfOfCellHeight = m_nCellHeight / 2;
        layerdrawable.setLayerInset(0, 0, 0, 0, 0); //top half
        layerdrawable.setLayerInset(1, 0, 0, 0, _nHalfOfCellHeight); //top half
        layerdrawable.setLayerInset(2, 0, _nHalfOfCellHeight, 0, 0); //bottom half

        return layerdrawable;
    }
*/



    /*public static Drawable getGradiantDrawable(Context mContext,float fRadius,int color1,int color2 ) {
        float radius = fRadius;

        float[] m_arrfTopHalfOuterRadii =
                new float[]{radius, radius, radius, radius, 0, 0, 0, 0};

//        int m_nCellHeight = 150;

        Bitmap bitmap= BitmapFactory.decodeResource(mContext.getResources(),R.drawable.metting_img);
//        bitmap.setHeight(100);
//        bitmap.setWidth(200);



        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), bitmap);
        final float roundPx = (float) bitmap.getWidth() * radius;
        roundedBitmapDrawable.setCornerRadius(radius);

        Drawable imageDrawable=roundedBitmapDrawable;


        GradientDrawable roundGradiantDrawable = new GradientDrawable();
//        gradientDrawable.setOrientation(GradientDrawable.Orientation.BL_TR);
        roundGradiantDrawable.setShape(GradientDrawable.LINEAR_GRADIENT);
        roundGradiantDrawable.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius,radius,radius});
        roundGradiantDrawable.setColors(new int[]{color1, color2});


        Drawable[] drawarray = {imageDrawable, roundGradiantDrawable};
        LayerDrawable layerdrawable = new LayerDrawable(drawarray);

//        int _nHalfOfCellHeight = m_nCellHeight / 2;
        layerdrawable.setLayerInset(0, 0, 0, 0, 0); //top half
        layerdrawable.setLayerInset(1, 0, 0, 0, 0); //top half
//        layerdrawable.setLayerInset(2, 0, _nHalfOfCellHeight, 0, 0); //bottom half

        return layerdrawable;
    }*/
}