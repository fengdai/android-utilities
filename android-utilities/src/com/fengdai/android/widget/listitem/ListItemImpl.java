package com.fengdai.android.widget.listitem;

import android.os.Handler;
import android.os.Message;

public class ListItemImpl<D> implements ListItem<D> {

    // 关联的数据
    private D mData;

    // item在列表中所处position
    private int mPosition;

    // 处理item消息的Handler
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
