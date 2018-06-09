package adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import adapter.item.NewsMultiItem;
import cn.urovo.com.mvpnews.R;

/**
 * Created by lenovo on 2018/6/9.
 */

public class NewsMultiListAdapter extends BaseMultiItemQuickAdapter<NewsMultiItem,BaseViewHolder>{

    public NewsMultiListAdapter(List<NewsMultiItem> data) {
        super(data);
        //添加条目类型
        addItemType(NewsMultiItem.ITEM_TYPE_NORMAL, R.layout.adapter_news_list);
        addItemType(NewsMultiItem.ITEM_TYPE_PHOTO_SET, R.layout.adapter_news_photo_set);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsMultiItem item) {
        switch (item.getItemType()) {
            case NewsMultiItem.ITEM_TYPE_NORMAL:
                if(item.getmNewsBean()!=null)
//                    _handleNewsNormal(holder, item.getmNewsBean());
                break;
            case NewsMultiItem.ITEM_TYPE_PHOTO_SET:
                if(item.getmNewsBean()!=null)
//                    _handleNewsPhotoSet(holder, item.getmNewsBean());
                break;
        }
    }




}
