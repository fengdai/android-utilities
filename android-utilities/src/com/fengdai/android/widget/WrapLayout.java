package com.fengdai.android.widget;

import com.fengdai.android.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Layout which can wrap it's child views.
 */
public class WrapLayout extends ViewGroup {

    /**
     * Horizontal spacing between child views<br>
     * XML attribute: horizontalSpacing
     */
    private int mHorizontalSpacing = 0;

    /**
     * Vertical spacing between child views<br>
     * XML attribute: verticalSpacing
     */
    private int mVerticalSpacing = 0;

    public WrapLayout(Context context) {
        super(context);
    }

    public WrapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WrapLayout);

        mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.WrapLayout_horizontalSpacing, 0);
        mVerticalSpacing = a.getDimensionPixelSize(R.styleable.WrapLayout_verticalSpacing, 0);

        a.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();

        int currentChildLeft = paddingLeft;
        int currentChildTop = paddingTop;
        int lastChildIndex = -1;

        final int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();
                int viewWidth = getWidth();
                if (i > 0) {
                    currentChildLeft += getChildAt(lastChildIndex).getMeasuredWidth()
                            + mHorizontalSpacing;
                }
                // If the child view's right is greater than the parent's
                // unused width and it is not the first view of a row, put
                // it to the next row.
                if ((viewWidth - currentChildLeft) < childWidth + paddingRight
                        && currentChildLeft != paddingLeft) {
                    if (lastChildIndex != -1) {
                        currentChildTop += getChildAt(lastChildIndex).getMeasuredHeight()
                                + mVerticalSpacing;
                        currentChildLeft = paddingLeft;
                    }
                }
                setChildFrame(childView, currentChildLeft, currentChildTop, childWidth,
                        childHeight);
                lastChildIndex = i;
            }
        }
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minHeight = getSuggestedMinimumHeight();

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int currentChildLeft = paddingLeft;
        int currentChildTop = paddingTop;
        int currentChildBottom = 0;
        int lastChildIndex = -1;

        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));

        int childCount = getChildCount();
        int viewWidth = getMeasuredWidth();
        for (int index = 0; index < childCount; index++) {
            final View child = getChildAt(index);
            // measure
            if (child.getVisibility() != View.GONE) {
                child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                // calculate child view's left.
                if (index > 0) {
                    currentChildLeft += getChildAt(lastChildIndex).getMeasuredWidth()
                            + mHorizontalSpacing;
                }
                if ((viewWidth - currentChildLeft) < childWidth + paddingRight
                        && currentChildLeft != paddingLeft) {
                    // If the child view's right is greater than the parent's
                    // unused width and it is not the first view of a row, it'll
                    // be put to the next row.
                    if (lastChildIndex != -1) {
                        currentChildTop += getChildAt(lastChildIndex).getMeasuredHeight()
                                + mVerticalSpacing;
                        currentChildLeft = paddingLeft;
                    }
                }
                // If the child view's width is greater than the parent's.
                // Measure it's width as same as the parent's.
                if (viewWidth - paddingLeft - paddingRight < childWidth) {
                    child.measure(widthMeasureSpec - paddingLeft - paddingRight,
                            MeasureSpec.UNSPECIFIED);
                    childWidth = child.getMeasuredWidth();
                    childHeight = child.getMeasuredHeight();
                }
                // increase the parent's bottom.
                currentChildBottom = currentChildTop + childHeight + paddingBottom;
                lastChildIndex = index;
            }
        }
        // Now we know the height of the parent,
        // measure it's height with the exact height.
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                Math.max(currentChildBottom, minHeight), MeasureSpec.EXACTLY);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), newHeightMeasureSpec));
    }

    public int getHorizontalSpacing() {
        return mHorizontalSpacing;
    }

    public void setHorizontalSpacing(int horizontalSpacing) {
        if (mHorizontalSpacing != horizontalSpacing) {
            mHorizontalSpacing = horizontalSpacing;
            requestLayout();
        }
    }

    public int getVerticalSpacing() {
        return mVerticalSpacing;
    }

    public void setVerticalSpacing(int verticalSpacing) {
        if (mVerticalSpacing != verticalSpacing) {
            mVerticalSpacing = verticalSpacing;
            requestLayout();
        }
    }

}
