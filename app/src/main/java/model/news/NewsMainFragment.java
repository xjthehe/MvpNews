package model.news;

import java.util.List;

import local.table.NewsTypeInfo;
import model.base.BaseFragment;
import model.base.IRxBusPresenter;
import model.news.main.INewsMainView;

/**
 * Created by rowen on 2018/6/9.
 * 新闻主页面
 */

public class NewsMainFragment extends BaseFragment<IRxBusPresenter> implements INewsMainView{


    @Override
    protected int attachLayoutRes() {
        return 0;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }


    @Override
    public void loadData(List<NewsTypeInfo> checkList) {

    }
    @Override
    protected void updateViews(boolean isRefresh) {

    }

}
