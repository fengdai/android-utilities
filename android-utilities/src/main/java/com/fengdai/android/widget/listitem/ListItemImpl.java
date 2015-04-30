package com.fengdai.android.widget.listitem;

import android.os.Handler;
import android.os.Message;

@Deprecated
public class ListItemImpl<D> implements ListItem<D> {

    // The model attached by this item.
    private D mData;

    // The position of this item.
    private int mPosition;

    // Handler for item messages.
    private Handler mHandler = null;


    @Override
    public void bindData(D data, int position) {
        this.mData = data;
        this.mPosition = position;
    }

    @Override
    public D getData() {
        return mData;
    }

    @Override
    public int getPosition() {
        return mPosition;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void notifyHandler(int what) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = mData;
        notifyHandler(msg);
    }

    public void notifyHandler(Message msg) {
        if (mHandler == null) {
            throw new RuntimeException("Don't have a handler!");
        }
        mHandler.sendMessage(msg);
    }
}
