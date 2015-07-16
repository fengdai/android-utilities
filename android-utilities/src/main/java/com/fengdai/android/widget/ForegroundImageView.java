package com.fengdai.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.fengdai.android.R;

/**
 * ImageView with foreground.
 */
public class ForegroundImageView extends ImageView {

    private Drawable mForeground;

    private Rect mCachedBounds = new Rect();

    public ForegroundImageView(Context context) {
        super(context);
    }

    public ForegroundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ForegroundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context
                .obtainStyledAttributes(attrs, R.styleable.ForegroundImageView,
                        defStyle, 0);
        Drawable foreground = a.getDrawable(R.styleable.ForegroundImageView_foreground);
        if (foreground != null) {
            setForeground(foreground);
        }
        a.recycle();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (mForeground != null && mForeground.isStateful()) {
            mForeground.setState(getDrawableState());
        }
    }

    @Override
    protected boolean verifyDrawable(Drawable dr) {
        return super.verifyDrawable(dr) || dr == mForeground;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mForeground == null) {
            return;
        }
        mForeground.setBounds(mCachedBounds);
        mForeground.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Cache the view bounds.
        mCachedBounds.set(0, 0, w, h);
    }

    /**
     * Returns the drawable used as the foreground of this view.
     *
     * @return A Drawable or null if no foreground was set.
     */
    public Drawable getForeground() {
        return mForeground;
    }

    /**
     * Supply a Drawable that is to be rendered on top of this view.
     *
     * @param drawable The Drawable to be drawn on top of this view.
     */
    public void setForeground(Drawable drawable) {
        if (mForeground != drawable) {

            if (mForeground != null) {
                mForeground.setCallback(null);
                unscheduleDrawable(mForeground);
            }

            mForeground = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
            }

            invalidate();
        }
    }
}
