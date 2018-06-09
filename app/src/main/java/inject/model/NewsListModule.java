package inject.model;

import com.chad.library.adapter.base.BaseQuickAdapter;

import adapter.NewsMultiListAdapter;
import dagger.Module;
import dagger.Provides;
import inject.PreFragment;
import model.base.IBasePresenter;
import model.news.newslist.NewsListFragment;
import model.news.newslist.NewsListPresenter;

/**
 * Created by lenovo on 2018/6/9.
 */

@Module
public class NewsListModule {
    private final NewsListFragment mNewsListView;
    private final String mNewsId;

    public NewsListModule(NewsListFragment view, String mNewsId) {
        this.mNewsListView = view;
        this.mNewsId = mNewsId;
    }

    @PreFragment
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsListPresenter(mNewsListView, mNewsId);
    }
    @PreFragment
    @Provides
    public BaseQuickAdapter provideAdapter(){
        return  new NewsMultiListAdapter(null);
    }
}
