package com.fengdai.android.widget.listitem;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Abstract RelativeLayout item.
 */
@Deprecated
public abstract class RelativeLayoutListItem<D> extends RelativeLayout implements ListItem<D> {

    private ListItemImpl<D> mImpl = new ListItemImpl<D>();

    public RelativeLayoutListItem(Context context) {
        super(context);
    }

    public RelativeLayoutListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayoutListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void bindData(D data, int position) {
        mImpl.bindData(data, position);
        onBindData(data, position);
    }

    /**
     * Call when data bound.
     */
    protected abstract void onBindData(D data, int position);

    @Override
    public D getData() {
        return mImpl.getData();
    }

    @Override
    public int getPosition() {
        return mImpl.getPosition();
    }

    @Override
    public Handler getHandler() {
        return mImpl.getHandler();
    }

    @Override
    public void setHandler(Handler handler) {
        mImpl.setHandler(handler);
    }

    @Override
    public void notifyHandler(int what) {
        mImpl.notifyHandler(what);
    }

    @Override
    public void notifyHandler(Message msg) {
        mImpl.notifyHandler(msg);
    }
}
