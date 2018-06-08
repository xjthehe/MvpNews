package inject;

import cn.urovo.com.mvpnews.MainActivity;
import dagger.Component;

/**
 * Created by lenovo on 2018/6/7.
 */

@Component(modules = MainModel.class)
public interface Componets {
    void injectMain(MainActivity mainActivity);
}
