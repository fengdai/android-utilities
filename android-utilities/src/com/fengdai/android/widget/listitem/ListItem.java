package com.fengdai.android.widget.listitem;

import android.os.Handler;
import android.os.Message;

/**
 * ListItem接口<BR>
 *
 * @param <D> 绑定数据类型
 * @author daifeng
 */
public interface ListItem<D> {

    /**
     * 绑定数据<BR>
     *
     * @param data     绑定的数据
     * @param position item在列表中所处position
     */
    void bindData(D data, int position);

    /**
     * 获取绑定的数据<BR>
     *
     * @return 关联的数据
     */
    D getData();

    /**
     * 获取Item在ListView中所处位置
     *
     * @return Item在ListView中所处位置
     */
    int getPosition();

    /**
     * 获取绑定的Handler
     *
     * @return 绑定的Handler
     */
    public Handler getHandler();

    /**
     * 设置一个handler, ListItem可以发送消息给这个handler处理<BR>
     *
     * @param handler handler
     */
    public void setHandler(Handler handler);

    /**
     * 将消息发送给handler处理，默认将绑定的数据作为Message的obj<BR>
     *
     * @param what 消息what
     */
    public void notifyHandler(int what);

    /**
     * 将消息发送给handler处理<BR>
     *
     * @param msg 自定义消息
     */
    public void notifyHandler(Message msg);
}
