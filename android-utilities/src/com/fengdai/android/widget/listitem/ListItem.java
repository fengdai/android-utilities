package com.fengdai.android.widget.listitem;

import android.os.Handler;
import android.os.Message;

/**
 * ListItem interface
 */
@Deprecated
public interface ListItem<D> {

    /**
     * Bind data to this item.
     *
     * @param data     The data to bind.
     * @param position The position of the item.
     */
    void bindData(D data, int position);

    /**
     * Get the bound data.
     */
    D getData();

    /**
     * Get the position of this item.
     */
    int getPosition();

    /**
     * Get the Handler.
     */
    public Handler getHandler();

    /**
     * Set a Handler, which is used to handle the item message.
     */
    public void setHandler(Handler handler);

    /**
     * Send a Message to the item Handler. The bound data will be set as the Message's obj value.
     *
     * @param what The value for {@link Message#what}.
     */
    public void notifyHandler(int what);

    /**
     * Send a Message to the item Handler.
     *
     * @param msg The Message to send.
     */
    public void notifyHandler(Message msg);
}
