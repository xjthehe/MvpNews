package model;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import widget.EmptyLayout;

/**
 * Created by lenovo on 2018/6/8.
 */

public class BaseActivity extends AppCompatActivity implements IBaseView , EmptyLayout.OnClickListener{


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void onClick(View v) {

    }
}
