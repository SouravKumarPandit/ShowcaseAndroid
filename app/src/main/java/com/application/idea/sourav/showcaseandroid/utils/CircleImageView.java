package com.application.idea.sourav.showcaseandroid.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import com.application.idea.sourav.showcaseandroid.R;

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {
    private static final ScaleType SCALE_TYPE;
    private static final Bitmap.Config BITMAP_CONFIG;
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_FILL_COLOR = 0;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;
    private final RectF mDrawableRect;
    private final RectF mBorderRect;
    private final Matrix mShaderMatrix;
    private final Paint mBitmapPaint;
    private final Paint mBorderPaint;
    private final Paint mFillPaint;
    private int mBorderColor;
    private int mBorderWidth;
    private int mFillColor;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private float mDrawableRadius;
    private float mBorderRadius;
    private ColorFilter mColorFilter;
    private boolean mReady;
    private boolean mSetupPending;
    private boolean mBorderOverlay;
    private boolean mDisableCircularTransformation;

    public CircleImageView(Context context)
    {
        super(context);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        this.mFillColor = 0;
        this.init();
    }

    public CircleImageView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        this.mFillColor = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
        this.mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_civ_border_width, 0);
        this.mBorderColor = a.getColor(R.styleable.CircleImageView_civ_border_color, -16777216);
        this.mBorderOverlay = a.getBoolean(R.styleable.CircleImageView_civ_border_overlay, false);
        this.mFillColor = a.getColor(R.styleable.CircleImageView_civ_fill_color, 0);
        a.recycle();
        this.init();
    }

    private void init()
    {
        super.setScaleType(SCALE_TYPE);
        this.mReady = true;
        if (this.mSetupPending)
        {
            this.setup();
            this.mSetupPending = false;
        }

    }

    public ScaleType getScaleType()
    {
        return SCALE_TYPE;
    }

    public void setScaleType(ScaleType scaleType)
    {
        if (scaleType != SCALE_TYPE)
        {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }

    public void setAdjustViewBounds(boolean adjustViewBounds)
    {
        if (adjustViewBounds)
        {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    protected void onDraw(Canvas canvas)
    {
        if (this.mDisableCircularTransformation)
        {
            super.onDraw(canvas);
        } else if (this.mBitmap != null)
        {
            if (this.mFillColor != 0)
            {
                canvas.drawCircle(this.mDrawableRect.centerX(), this.mDrawableRect.centerY(), this.mDrawableRadius, this.mFillPaint);
            }

            canvas.drawCircle(this.mDrawableRect.centerX(), this.mDrawableRect.centerY(), this.mDrawableRadius, this.mBitmapPaint);
            if (this.mBorderWidth > 0)
            {
                canvas.drawCircle(this.mBorderRect.centerX(), this.mBorderRect.centerY(), this.mBorderRadius, this.mBorderPaint);
            }

        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        this.setup();
    }

    public void setPadding(int left, int top, int right, int bottom)
    {
        super.setPadding(left, top, right, bottom);
        this.setup();
    }

    public void setPaddingRelative(int start, int top, int end, int bottom)
    {
        super.setPaddingRelative(start, top, end, bottom);
        this.setup();
    }

    public int getBorderColor()
    {
        return this.mBorderColor;
    }

    public void setBorderColor(@ColorInt int borderColor)
    {
        if (borderColor != this.mBorderColor)
        {
            this.mBorderColor = borderColor;
            this.mBorderPaint.setColor(this.mBorderColor);
            this.invalidate();
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setBorderColorResource(@ColorRes int borderColorRes)
    {
        this.setBorderColor(this.getContext().getResources().getColor(borderColorRes));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public int getFillColor()
    {
        return this.mFillColor;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setFillColor(@ColorInt int fillColor)
    {
        if (fillColor != this.mFillColor)
        {
            this.mFillColor = fillColor;
            this.mFillPaint.setColor(fillColor);
            this.invalidate();
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setFillColorResource(@ColorRes int fillColorRes)
    {
        this.setFillColor(this.getContext().getResources().getColor(fillColorRes));
    }

    public int getBorderWidth()
    {
        return this.mBorderWidth;
    }

    public void setBorderWidth(int borderWidth)
    {
        if (borderWidth != this.mBorderWidth)
        {
            this.mBorderWidth = borderWidth;
            this.setup();
        }
    }

    public boolean isBorderOverlay()
    {
        return this.mBorderOverlay;
    }

    public void setBorderOverlay(boolean borderOverlay)
    {
        if (borderOverlay != this.mBorderOverlay)
        {
            this.mBorderOverlay = borderOverlay;
            this.setup();
        }
    }

    public boolean isDisableCircularTransformation()
    {
        return this.mDisableCircularTransformation;
    }

    public void setDisableCircularTransformation(boolean disableCircularTransformation)
    {
        if (this.mDisableCircularTransformation != disableCircularTransformation)
        {
            this.mDisableCircularTransformation = disableCircularTransformation;
            this.initializeBitmap();
        }
    }

    public void setImageBitmap(Bitmap bm)
    {
        super.setImageBitmap(bm);
        this.initializeBitmap();
    }

    public void setImageDrawable(Drawable drawable)
    {
        super.setImageDrawable(drawable);
        this.initializeBitmap();
    }

    public void setImageResource(@DrawableRes int resId)
    {
        super.setImageResource(resId);
        this.initializeBitmap();
    }

    public void setImageURI(Uri uri)
    {
        super.setImageURI(uri);
        this.initializeBitmap();
    }

    public void setColorFilter(ColorFilter cf)
    {
        if (cf != this.mColorFilter)
        {
            this.mColorFilter = cf;
            this.applyColorFilter();
            this.invalidate();
        }
    }

    public ColorFilter getColorFilter()
    {
        return this.mColorFilter;
    }

    private void applyColorFilter()
    {
        if (this.mBitmapPaint != null)
        {
            this.mBitmapPaint.setColorFilter(this.mColorFilter);
        }

    }

    private Bitmap getBitmapFromDrawable(Drawable drawable)
    {
        if (drawable == null)
        {
            return null;
        } else if (drawable instanceof BitmapDrawable)
        {
            return ((BitmapDrawable) drawable).getBitmap();
        } else
        {
            try
            {
                Bitmap e;
                if (drawable instanceof ColorDrawable)
                {
                    e = Bitmap.createBitmap(2, 2, BITMAP_CONFIG);
                } else
                {
                    e = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
                }

                Canvas canvas = new Canvas(e);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return e;
            } catch (Exception var4)
            {
                var4.printStackTrace();
                return null;
            }
        }
    }

    private void initializeBitmap()
    {
        if (this.mDisableCircularTransformation)
        {
            this.mBitmap = null;
        } else
        {
            this.mBitmap = this.getBitmapFromDrawable(this.getDrawable());
        }

        this.setup();
    }

    private void setup()
    {
        if (!this.mReady)
        {
            this.mSetupPending = true;
        } else if (this.getWidth() != 0 || this.getHeight() != 0)
        {
            if (this.mBitmap == null)
            {
                this.invalidate();
            } else
            {
                this.mBitmapShader = new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                this.mBitmapPaint.setAntiAlias(true);
                this.mBitmapPaint.setShader(this.mBitmapShader);
                this.mBorderPaint.setStyle(Paint.Style.STROKE);
                this.mBorderPaint.setAntiAlias(true);
                this.mBorderPaint.setColor(this.mBorderColor);
                this.mBorderPaint.setStrokeWidth((float) this.mBorderWidth);
                this.mFillPaint.setStyle(Paint.Style.FILL);
                this.mFillPaint.setAntiAlias(true);
                this.mFillPaint.setColor(this.mFillColor);
                this.mBitmapHeight = this.mBitmap.getHeight();
                this.mBitmapWidth = this.mBitmap.getWidth();
                this.mBorderRect.set(this.calculateBounds());
                this.mBorderRadius = Math.min((this.mBorderRect.height() - (float) this.mBorderWidth) / 2.0F, (this.mBorderRect.width() - (float) this.mBorderWidth) / 2.0F);
                this.mDrawableRect.set(this.mBorderRect);
                if (!this.mBorderOverlay && this.mBorderWidth > 0)
                {
                    this.mDrawableRect.inset((float) this.mBorderWidth - 1.0F, (float) this.mBorderWidth - 1.0F);
                }

                this.mDrawableRadius = Math.min(this.mDrawableRect.height() / 2.0F, this.mDrawableRect.width() / 2.0F);
                this.applyColorFilter();
                this.updateShaderMatrix();
                this.invalidate();
            }
        }
    }

    private RectF calculateBounds()
    {
        int availableWidth = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        int availableHeight = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        int sideLength = Math.min(availableWidth, availableHeight);
        float left = (float) this.getPaddingLeft() + (float) (availableWidth - sideLength) / 2.0F;
        float top = (float) this.getPaddingTop() + (float) (availableHeight - sideLength) / 2.0F;
        return new RectF(left, top, left + (float) sideLength, top + (float) sideLength);
    }

    private void updateShaderMatrix()
    {
        float dx = 0.0F;
        float dy = 0.0F;
        this.mShaderMatrix.set((Matrix) null);
        float scale;
        if ((float) this.mBitmapWidth * this.mDrawableRect.height() > this.mDrawableRect.width() * (float) this.mBitmapHeight)
        {
            scale = this.mDrawableRect.height() / (float) this.mBitmapHeight;
            dx = (this.mDrawableRect.width() - (float) this.mBitmapWidth * scale) * 0.5F;
        } else
        {
            scale = this.mDrawableRect.width() / (float) this.mBitmapWidth;
            dy = (this.mDrawableRect.height() - (float) this.mBitmapHeight * scale) * 0.5F;
        }

        this.mShaderMatrix.setScale(scale, scale);
        this.mShaderMatrix.postTranslate((float) ((int) (dx + 0.5F)) + this.mDrawableRect.left, (float) ((int) (dy + 0.5F)) + this.mDrawableRect.top);
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }

    static
    {
        SCALE_TYPE = ScaleType.CENTER_CROP;
        BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    }
}
