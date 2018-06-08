package global;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

import java.io.File;

import inject.DaggerComponets;
import local.dao.NewsTypeDao;
import local.table.DaoMaster;
import local.table.DaoSession;
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
    }

    private void _initInjector() {

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