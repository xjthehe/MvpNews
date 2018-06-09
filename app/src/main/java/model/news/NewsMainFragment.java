package model.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import adapter.ViewPagerAdapter;
import butterknife.BindView;
import cn.urovo.com.mvpnews.R;
import inject.components.DaggerNewsMainComponent;
import inject.model.NewsMainModule;
import local.table.NewsTypeInfo;
import model.base.BaseFragment;
import model.base.IRxBusPresenter;
import model.news.main.INewsMainView;
import model.news.newslist.NewsListFragment;
import rx.functions.Action1;
import rxbus.event.ChannelEvent;

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
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
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
    protected void initViews(){
        initToolBar(mToolBar,true,"新闻");
        setHasOptionsMenu(true);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //注册频道事件
        mPresenter.registerRxBus(ChannelEvent.class, new Action1<ChannelEvent>() {
            @Override
            public void call(ChannelEvent channelEvent) {
                _handleChannelEvent(channelEvent);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }


    @Override
    public void loadData(List<NewsTypeInfo> checkList) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (NewsTypeInfo bean : checkList) {
            titles.add(bean.getName());
            fragments.add(NewsListFragment.newInstance(bean.getTypeId()));//----------------------------
        }
        mPagerAdapter.setItems(fragments,titles);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterRxBus();
    }

    /**
     * 处理频道事件
     * @param channelEvent
     */
    private void _handleChannelEvent(ChannelEvent channelEvent) {
        switch (channelEvent.eventType) {
            case ChannelEvent.ADD_EVENT:
//                mPagerAdapter.addItem(NewsListFragment.newInstance(channelEvent.newsInfo.getTypeId()), channelEvent.newsInfo.getName());
                break;
            case ChannelEvent.DEL_EVENT:
                // 如果是删除操作直接切换第一项，不然容易出现加载到不存在的Fragment
//                mViewPager.setCurrentItem(0);
//                mPagerAdapter.delItem(channelEvent.newsInfo.getName());
                break;
            case ChannelEvent.SWAP_EVENT:
//                mPagerAdapter.swapItems(channelEvent.fromPos, channelEvent.toPos);
                break;
        }
    }

}
