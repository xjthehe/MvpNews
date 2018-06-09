package inject.components;

import dagger.Component;
import inject.PreFragment;
import inject.model.NewsMainModule;
import model.news.NewsMainFragment;

/**
 * Created by rowen on 2018/6/9.
 */

@PreFragment
@Component(dependencies = ApplicationComponent.class,modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment newsMainFragment);
}
