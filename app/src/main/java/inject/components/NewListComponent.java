package inject.components;

import dagger.Component;
import inject.PreFragment;
import inject.model.NewsListModule;
import model.news.newslist.NewsListFragment;

/**
 * 新闻列表
 */

@PreFragment
@Component(dependencies = ApplicationComponent.class,modules = NewsListModule.class)
public interface NewListComponent {
    void inject(NewsListFragment fragment);
}
