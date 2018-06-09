package adapter.item;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import api.NewsInfo;

/**
 * Created by lenovo on 2018/6/9.
 */

public class NewsMultiItem implements MultiItemEntity {
    private int type;
    private NewsInfo mNewsBean;
    public static final int ITEM_TYPE_NORMAL = 1;//文本类型
    public static final int ITEM_TYPE_PHOTO_SET = 2;//单图片类型

    public NewsMultiItem(int type,NewsInfo newsBean) {
        this.type = type;
        mNewsBean=newsBean;
    }

    @Override
    public int getItemType() {
        return type;
    }
    public NewsInfo getmNewsBean() {
        return mNewsBean;
    }

    public void setmNewsBean(NewsInfo mNewsBean) {
        this.mNewsBean = mNewsBean;
    }
}
