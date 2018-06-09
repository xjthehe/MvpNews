package inject.model;

import adapter.ViewPagerAdapter;
import dagger.Module;
import dagger.Provides;
import inject.PreFragment;
import local.table.DaoSession;
import model.base.IRxBusPresenter;
import model.news.NewsMainFragment;
import model.news.main.NewsMainPresenter;
import rxbus.RxBus;

/**
 * Created by lenovo on 2018/6/9.
 */
@Module
public class NewsMainModule{
    private final NewsMainFragment mView;
    public NewsMainModule(NewsMainFragment mView){
        this.mView = mView;
    }

    @PreFragment
    @Provides
    public IRxBusPresenter provideMainPresenter(DaoSession daoSession, RxBus rxBus){
        return new NewsMainPresenter(mView,daoSession.getNewsTypeInfoDao(),rxBus);
    }

    @PreFragment
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter(){
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }
}
