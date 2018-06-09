package model.news.main;

import java.util.List;

import local.table.NewsTypeInfo;

/**
 * Created by lenovo on 2018/6/9.
 */

public interface INewsMainView {
    /**
     * 显示数据
     * @param checkList     选中栏目
     */
    void loadData(List<NewsTypeInfo> checkList);
}
