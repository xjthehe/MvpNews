package model.base;

import rx.functions.Action1;

/**
 * Created by lenovo on 2018/6/9.
 */

public interface IRxBusPresenter extends IBasePresenter{
    /**
     * 注册
     * @param eventType
     * @param <T>
     */
    <T> void registerRxBus(Class<T> eventType, Action1<T> action);
    /**
     * 注销
     */
    void unregisterRxBus();
}
