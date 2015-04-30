package com.fengdai.android.widget;

import com.fengdai.android.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * PageIndicator for ViewPager.
 */
public class PageIndicator extends LinearLayout {

    private ViewPager mViewPager;

    private int mCurrentItem = 0;

    private int mHorizontalSpacing;

    private int mIndicatorSelectorResId = -1;

    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            setCurrentItem(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    public PageIndicator(Context context) {
        this(context, null);
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.PageIndicator);
        mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.PageIndicator_horizontalSpacing,
                0);
        mIndicatorSelectorResId = a.getResourceId(R.styleable.PageIndicator_indicatorSelector,
                -1);
        a.recycle();

    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;

        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException(
                    "ViewPager does not have adapter instance.");
        }

        mViewPager.setOnPageChangeListener(mOnPageChangeListener);

        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        setCount(mViewPager.getAdapter().getCount());
        setCurrentItem(mViewPager.getCurrentItem());
    }

    private void setCount(int count) {
        int currentSize = getChildCount();
        if (count > currentSize) {
            ImageView view = null;
            for (int i = currentSize; i < count; i++) {
                view = new ImageView(getContext());
                if (mIndicatorSelectorResId != -1) {
                    view.setImageResource(mIndicatorSelectorResId);
                }
                if (i == 0) {
                    addView(view);
                } else {
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    lp.setMargins(mHorizontalSpacing, 0, 0, 0);
                    addView(view, lp);
                }
            }
        } else if (count < currentSize) {
            for (int i = currentSize - 1; i >= count; i--) {
                removeViewAt(i);
            }
        }
    }

    private void setCurrentItem(int item) {
        if (getChildCount() == 0) {
            return;
        }
        View currentSelectedChild = getChildAt(mCurrentItem);
        if (currentSelectedChild != null) {
            currentSelectedChild.setSelected(false);
        }
        currentSelectedChild = getChildAt(item);
        if (currentSelectedChild != null) {
            currentSelectedChild.setSelected(true);
        }
        mCurrentItem = item;
    }
}
