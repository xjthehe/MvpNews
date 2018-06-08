package inject.model;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import global.NewsApplication;
import local.table.DaoSession;
import rxbus.RxBus;
/**
 *
 * model
 */
@Module
public class ApplicationModule {
    private final NewsApplication mApplication;
    private final DaoSession mDaoSession;
    private final RxBus mRxBus;

    public ApplicationModule(NewsApplication mApplication, DaoSession mDaoSession, RxBus mRxBus) {
        this.mApplication = mApplication;
        this.mDaoSession = mDaoSession;
        this.mRxBus = mRxBus;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    RxBus provideRxBus(){
        return mRxBus;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(){
        return mDaoSession;
    }
}
