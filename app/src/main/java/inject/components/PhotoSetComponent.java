package inject.components;

import dagger.Component;
import inject.PerActivity;
import inject.model.PhotoSetModule;
import model.news.photoset.PhotoSetActivity;

/**
 * Created by lenovo on 2018/6/10.
 *  *图集 Component
 */

@PerActivity
@Component(modules = PhotoSetModule.class)
public interface PhotoSetComponent {
    void inject(PhotoSetActivity activity);
}
