package model.home;
import android.Manifest;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import cn.urovo.com.mvpnews.R;
import model.base.BaseActivity;
import rx.functions.Action1;
import utils.SnackbarUtils;

public class MainActivity extends BaseActivity  implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    private SparseArray<String> mSparseTags = new SparseArray<>();



    private int mItemId = -1;
    private Handler mHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case R.id.nav_news:
//                    replaceFragment(R.id.fl_container, new NewsMainFragment(), mSparseTags.get(R.id.nav_news));
                    break;
                case R.id.nav_photos:
//                    replaceFragment(R.id.fl_container, new PhotoMainFragement(), mSparseTags.get(R.id.nav_photos));
                    break;
                case R.id.nav_videos:
//                    replaceFragment(R.id.fl_container, new VideoMainFragment(), mSparseTags.get(R.id.nav_videos));
                    break;
                case R.id.nav_setting:
//                    SettingsActivity.launch(HomeActivity.this);
                    break;
            }
            mItemId = -1;
            return true;
        }
    });
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews(){
        //初始化根布局mDrawerLayout和侧边栏的布局mNavView
        _initDrawerLayout(mDrawerLayout, mNavView);

        mSparseTags.put(R.id.nav_news, "News");
        mSparseTags.put(R.id.nav_photos, "Photos");
        mSparseTags.put(R.id.nav_videos, "Videos");
        _getPermission();//权限动态申请权限
    }

    private void _getPermission() {
        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
//                            final File dir = new File(FileDownloader.getDownloadDir());
//                            if (!dir.exists() || !dir.isDirectory()) {
//                                dir.delete();
//                                dir.mkdirs();
//                            }
                        } else {
                            SnackbarUtils.showSnackbar(MainActivity.this, "获取读写文件权限失败", true);
                        }
                    }
                });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mNavView.setCheckedItem(R.id.nav_news);
    }


    private void _initDrawerLayout(DrawerLayout mDrawerLayout, NavigationView mNavView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            mDrawerLayout.setClipToPadding(false);
        }

        //侧边栏关闭
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                mHandler.sendEmptyMessage(mItemId);
            }
        });
        //naview添加监听
        mNavView.setNavigationItemSelectedListener(this);
    }


    /**
     * 获取点击item的id
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);//Navigation点击后就关闭drawlatyout，触发addDrawerListener
        if (item.isChecked()) {
            return true;
        }
        mItemId = item.getItemId();
        return true;
    }
}
