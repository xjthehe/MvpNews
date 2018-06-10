package inject.components;

import android.app.Activity;


import dagger.Component;
import inject.PerActivity;
import inject.model.ActivityModule;

/**
 * Created by long on 2016/8/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
}
