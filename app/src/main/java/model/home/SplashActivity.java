package model.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.urovo.com.mvpnews.R;
import rx.Subscriber;
import utils.RxHelper;
import widget.SimpleButton;


/**
 * Created by rowen on 2018/2/26.
 */
public class SplashActivity extends AppCompatActivity{
    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;
    private boolean mIsSkip = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        toHome();
    }

    /**
     * 主页面
     */
    private void toHome(){
        /////////////////////////
        Toast.makeText(this,"xxx",Toast.LENGTH_SHORT).show();

        RxHelper.countdown(5)
                .subscribe(new Subscriber<Integer>(){
                    @Override
                    public void onCompleted(){
                        _doSkip();
                    }
                    @Override
                    public void onError(Throwable e) {
                        _doSkip();
                    }
                    @Override
                    public void onNext(Integer integer) {
                        mSbSkip.setText("跳过 " + integer);
                    }
                });
    }

    @OnClick(R.id.sb_skip)
    public void onclick(){
        _doSkip();
    }

    private void _doSkip(){
        if (!mIsSkip) {
            mIsSkip = true;
            finish();
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

}
