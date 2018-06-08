package inject.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import inject.model.ApplicationModule;
import local.table.DaoSession;
import rxbus.RxBus;

/**
 * Created by rowen on 2018/6/8.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getContext();
    RxBus getRxBus();
    DaoSession getDaoSession();
}
