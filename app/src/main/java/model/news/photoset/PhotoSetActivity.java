package model.news.photoset;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import api.bean.PhotoSetInfo;
import cn.urovo.com.mvpnews.R;
import model.base.BaseActivity;
import model.base.IBasePresenter;

public class PhotoSetActivity extends BaseActivity<IBasePresenter> implements IPhotoSetView {
    private static final String PHOTO_SET_KEY = "PhotoSetKey";
    private String mPhotoSetId;














    public static void launch(Context context, String photoId) {
        Intent intent = new Intent(context, PhotoSetActivity.class);
        intent.putExtra(PHOTO_SET_KEY, photoId);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_photo_set;
    }
    @Override
    protected void initInjector() {
        mPhotoSetId = getIntent().getStringExtra(PHOTO_SET_KEY);



    }


    @Override
    public void loadData(PhotoSetInfo photoSetBean) {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photo_set);
//    }
}
