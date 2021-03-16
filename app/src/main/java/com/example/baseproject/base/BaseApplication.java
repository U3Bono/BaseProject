package com.example.baseproject.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Environment;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baseproject.utils.GlobalVar;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.io.File;
import java.util.HashMap;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BaseApplication extends Application {

    public static Context context;
    public static String sdcardPath;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        init();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)   //字体大小设置
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    protected void init() {

        initX5();   //X5浏览器初始化

        ARouter.init(this); //ARouter初始化

        initFile(); //初始化文件

    }

    protected void initX5() {
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d(TAG, " x5 init: " + arg0);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    protected void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    protected void initFile() {
        if (Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            sdcardPath = Environment.getExternalStorageDirectory().getPath() + GlobalVar.DATA_CACHE;    //创建DataCache
            File dataFile = new File(sdcardPath);
            if (!dataFile.exists()) {
                if (dataFile.mkdirs()) {
                    sdcardPath = dataFile.getAbsolutePath();
                }
            } else {
                sdcardPath = dataFile.getAbsolutePath();
            }

            sdcardPath = Environment.getExternalStorageDirectory().getPath() + GlobalVar.IMAGE_CACHE;    //创建ImageCaChe
            File imageFile = new File(sdcardPath);
            if (!imageFile.exists()) {
                imageFile.mkdirs();
            }

            sdcardPath = GlobalVar.DOWNLOAD_CACHE;
        } else {
            sdcardPath = getCacheDir().getAbsolutePath() + GlobalVar.IMAGE_CACHE;
        }
        Log.d(TAG, " sdcard path: " + sdcardPath);
    }

}
