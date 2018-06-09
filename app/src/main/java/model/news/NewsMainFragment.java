package model.news;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.urovo.com.mvpnews.R;
import inject.components.DaggerNewsMainComponent;
import inject.model.NewsMainModule;
import local.table.NewsTypeInfo;
import model.base.BaseFragment;
import model.base.IRxBusPresenter;
import model.news.main.INewsMainView;

/**
 * Created by rowen on 2018/6/9.
 * 新闻主页面
 */

public class NewsMainFragment extends BaseFragment<IRxBusPresenter> implements INewsMainView{
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @Inject
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initInjector() {
        DaggerNewsMainComponent.builder()
                .applicationComponent(getAppComponent())
                .newsMainModule(new NewsMainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar,true,"新闻");
        setHasOptionsMenu(true);
        mViewPager.setAdapter();
    }

    @Override
    public void loadData(List<NewsTypeInfo> checkList) {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
