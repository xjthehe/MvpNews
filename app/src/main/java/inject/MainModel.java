package inject;

import android.widget.Button;

import cn.urovo.com.mvpnews.MainActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/6/7.
 */
@Module
public class MainModel {
    MainActivity mainActivity;
    public MainModel(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }
}
