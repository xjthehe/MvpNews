package model.news.newslist;

import com.orhanobut.logger.Logger;

import java.util.List;

import adapter.item.NewsMultiItem;
import api.NewsInfo;
import api.NewsUtils;
import api.RetrofitService;
import model.base.IBasePresenter;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 新闻列表 Presente
 */

public class NewsListPresenter implements IBasePresenter{
    private INewsListView mView;
    private String mNewsId;
    private int mPage = 0;

    public NewsListPresenter(INewsListView view, String newsId) {
        this.mView = view;
        this.mNewsId = newsId;
    }


    @Override
    public void getData(final boolean isRefresh) {
        RetrofitService.getNewsList(mNewsId,mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (!isRefresh) {
                            mView.showLoading();
                        }
                    }
                })
                .filter(new Func1<NewsInfo, Boolean>() {
                    @Override
                    public Boolean call(NewsInfo newsBean) {
                        if (NewsUtils.isAbNews(newsBean)) {
                            mView.loadAdData(newsBean);
                        }
                        return !NewsUtils.isAbNews(newsBean);                    }
                })
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e){
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }
                    @Override
                    public void onNext(List<NewsMultiItem> newsMultiItems){
                        mView.loadMoreData(newsMultiItems);
                        mPage++;
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getNewsList(mNewsId, mPage)
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<NewsMultiItem> newsList) {
                        mView.loadMoreData(newsList);
                        mPage++;
                    }
                });
    }
    /**
     * 统一变换
     */
    private Observable.Transformer<NewsInfo, List<NewsMultiItem>> mTransformer = new Observable.Transformer<NewsInfo, List<NewsMultiItem>>() {
        @Override
        public Observable<List<NewsMultiItem>> call(Observable<NewsInfo> newsInfoObservable) {
            return newsInfoObservable
                    .map(new Func1<NewsInfo, NewsMultiItem>() {
                        @Override
                        public NewsMultiItem call(NewsInfo newsBean) {
                            if (NewsUtils.isNewsPhotoSet(newsBean.getSkipType())) {
                                return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_PHOTO_SET, newsBean);
                            }
                            return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_NORMAL, newsBean);
                        }
                    })
                    .toList()
                    .compose(mView.<List<NewsMultiItem>>bindToLife());
        }
    };
}
