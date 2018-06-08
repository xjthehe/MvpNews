package model;

import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by lenovo on 2018/2/26.
 */

public interface IBaseView {
    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误，modify 对网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    void showNetError();

    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();


}
