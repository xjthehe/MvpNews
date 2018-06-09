package model.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.LayoutRes;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.urovo.com.mvpnews.R;
import utils.ActivityCollector;
import widget.EmptyLayout;

/**
 * Created by lenovo on 2018/6/8.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView, EmptyLayout.OnRetryListener{
    /**
     * 把 EmptyLayout 放在基类统一处理，@Nullable 表明 View 可以为 null，详细可看 ButterKnife
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;
//    /**
//     * 刷新控件，注意，资源的ID一定要一样
//     */
//    @Nullable
//    @BindView(R.id.swipe_refresh)
//    SwipeRefreshLayout mSwipeRefresh;
    /**
     * 绑定布局文件
     *
     * @return 布局文件IDfile:/E:/project/Gift/MvpNews/app/src/main/res/anim/
     */
    @LayoutRes
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
     */
    protected abstract void updateViews(boolean isRefresh);
    private ActivityCollector collector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        collector= ActivityCollector.getActivityCollector();
        collector.addActivity(this);
        Logger.init("xjt");
        Logger.t(getClass().getSimpleName()).d(getClass().getSimpleName());
        ButterKnife.bind(this);
        initInjector();//Dagger 注入
        initViews();//初始化视图控件
//        initSwipeRefresh();
        updateViews(false);
    }


//    /**
//     * 初始化下拉刷新
//     */
//    private void initSwipeRefresh() {
//        if (mSwipeRefresh != null) {
//            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    updateViews(true);
//                }
//            });
//        }
//    }
    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener((EmptyLayout.OnRetryListener) this);
        }
    }

    @Override
    public void finishRefresh() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener( this);
        }
    }

    @Override
    public void onRetry() {
        updateViews(false);
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
      FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId,fragment);
        fragmentTransaction.commit();
    }








}
