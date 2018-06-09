package model.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.urovo.com.mvpnews.R;
import widget.EmptyLayout;

/**
 * Created by lenovo on 2018/6/9.
 * 碎片基类
 */
public abstract class BaseFragment <T extends IBasePresenter> extends RxFragment implements IBaseView, EmptyLayout.OnRetryListener{
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    protected Context mContext;
    //缓存Fragment view
    private View mRootView;
    private boolean mIsMulti = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if(mRootView==null){
              mRootView=inflater.inflate(attachLayoutRes(),null);
                ButterKnife.bind(this,mRootView);
                initInjector();
                initViews();
                initSwipeRefresh();
            }
            ViewGroup parent= (ViewGroup) mRootView.getParent();
            if(parent!=null){
                parent.removeView(mRootView);
            }
            return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }


    @Override
    public void showLoading(){
        if(mEmptyLayout!=null){
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if(mEmptyLayout!=null){
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }

    @Override
    public void onRetry() {
        updateViews(false);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void finishRefresh() {
//        if (mSwipeRefresh != null) {
//            mSwipeRefresh.setRefreshing(false);
//        }
    }

    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh() {
//        if (mSwipeRefresh != null) {
//            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    updateViews(true);
//                }
//            });
//        }
    }





    /**
     * 绑定布局文件
     * @return  布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();
    /**
     * 初始化视图控件
     */
    protected abstract void initViews();
    /**
     * 更新视图控件
     * @param isRefresh 新增参数，用来判断是否为下拉刷新调用，下拉刷新的时候不应该再显示加载界面和异常界面
     */
    protected abstract void updateViews(boolean isRefresh);
}
