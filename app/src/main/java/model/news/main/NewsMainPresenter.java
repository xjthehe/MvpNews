package model.news.main;

import com.orhanobut.logger.Logger;

import java.util.List;

import local.table.NewsTypeInfo;
import local.table.NewsTypeInfoDao;
import model.base.IRxBusPresenter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rxbus.RxBus;

/**
 * Created by rowen on 2018/6/9.
 */

public class NewsMainPresenter implements IRxBusPresenter{

    private final INewsMainView mView;
    private final NewsTypeInfoDao mDbDao;
    private final RxBus mRxBus;

    public NewsMainPresenter(INewsMainView mView, NewsTypeInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
        this.mRxBus = mRxBus;
    }

    @Override
    public void getData(boolean isRefresh) {
        mDbDao.queryBuilder().rx().list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewsTypeInfo>>() {
                    @Override
                    public void call(List<NewsTypeInfo> newsTypeBeen) {
                        mView.loadData(newsTypeBeen);
                    }
                });
    }

    @Override
    public void getMoreData() {

    }
    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Logger.e(throwable.toString());
            }
        });
        mRxBus.addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }


}
