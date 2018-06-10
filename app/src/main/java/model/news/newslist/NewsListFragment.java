package model.news.newslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.slider.library.SliderLayout;

import java.util.List;

import javax.inject.Inject;

import adapter.item.NewsMultiItem;
import api.NewsInfo;
import butterknife.BindView;
import cn.urovo.com.mvpnews.R;
import inject.components.DaggerNewListComponent;
import inject.model.NewsListModule;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import model.base.BaseFragment;
import model.base.IBasePresenter;
import model.news.NewsMainFragment;
import utils.RecyclerViewHelper;
import utils.SliderHelper;

/**
 * Created by rowen on 2018/6/9.
 */

public class NewsListFragment extends BaseFragment<IBasePresenter> implements INewsListView{
    private static final String NEWS_TYPE_KEY = "NewsTypeKey";
    @BindView(R.id.rv_news_list)
    RecyclerView mRvNewsList;
    @Inject
    BaseQuickAdapter mAdapter;
    private SliderLayout mAdSlider;
    private String mNewsId;

    public static NewsListFragment newInstance(String newsId){
        NewsListFragment newsListFragment=new NewsListFragment();
        Bundle bundle=new Bundle();
        bundle.putString(NEWS_TYPE_KEY,newsId);
        newsListFragment.setArguments(bundle);
        return newsListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            if(getArguments()!=null){
                mNewsId = getArguments().getString(NEWS_TYPE_KEY);
            }
        }
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initInjector() {
        DaggerNewListComponent.builder()
                .applicationComponent(getAppComponent())
                .newsListModule(new NewsListModule(this,mNewsId))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        SlideInRightAnimationAdapter animAdapter = new SlideInRightAnimationAdapter(mAdapter);
        RecyclerViewHelper.initRecyclerViewV(mContext, mRvNewsList, true, new AlphaInAnimationAdapter(animAdapter));
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(List<NewsMultiItem> data) {
        mAdapter.addData(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreData(List<NewsMultiItem> data) {
        mAdapter.addData(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadNoData() {

    }

    /**
     * 加载广告栏数据
     * @param newsBean 新闻
     */
    @Override
    public void loadAdData(NewsInfo newsBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.head_news_list, null);
        mAdSlider = (SliderLayout) view.findViewById(R.id.slider_ads);
        SliderHelper.initAdSlider(mContext, mAdSlider, newsBean);
        mAdapter.removeAllHeaderView();
        mAdapter.addHeaderView(view);
    }
}
