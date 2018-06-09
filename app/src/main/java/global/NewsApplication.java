package global;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

import java.io.File;

import inject.components.ApplicationComponent;
import inject.components.DaggerApplicationComponent;
import inject.model.ApplicationModule;
import local.dao.NewsTypeDao;
import local.table.DaoMaster;
import local.table.DaoSession;
import rxbus.RxBus;
import utils.ActivityCollector;
import utils.CrashHandler;
import utils.OnSystemCrashCallback;

/**
 * Created by lenovo on 2018/6/8.
 */

public class NewsApplication  extends Application {
    private static final String DB_NAME = "news-db";
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    protected static File externalAppDir;// 该应用程序在SD卡上文件根目录
    public static DaoSession mDaoSession;
    private static ApplicationComponent sAppComponent;

    // 因为下载那边需要用，这里在外面实例化在通过 ApplicationModule 设置
    private RxBus mRxBus = new RxBus();
    @Override
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();//线程id
        externalAppDir = new File(Environment.getExternalStorageDirectory(),getPackageName());
        /**
         * 日志抓取本地保存文件
         */
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this, new OnSystemCrashCallback() {
            @Override
            public void OnSystemCrash() {
                ActivityCollector.getActivityCollector().finishAll();
            }
        });
        setupLeakCanary();//内存泄漏检测。
        _initDatabase();
        _initInjector();
//        RetrofitService.init();

    }



    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }

    public static Context getContext() {
        return context;
    }


    private void _initInjector() {
        sAppComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this,mDaoSession,mRxBus))
                .build();
    }

    private void setupLeakCanary(){
        if(LeakCanary.isInAnalyzerProcess(this)) {
            return ;
        }
        LeakCanary.install(this);
    }

    /**
     * 初始化数据库
     */
    private void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        NewsTypeDao.updateLocalData(context, mDaoSession);
//      DownloadUtils.init(mDaoSession.getBeautyPhotoInfoDao());
    }
}