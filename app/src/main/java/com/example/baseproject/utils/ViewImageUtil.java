package com.example.baseproject.utils;

/**
 * 描述：图片视图转化工具
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.example.baseproject.base.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ViewImageUtil {

    //然后View和其内部的子View都具有了实际大小，也就是完成了布局，相当与添加到了界面上。接着就可以创建位图并在上面绘制了：
    public static void layoutView(View v, int width, int height) {
        // 整个View的大小 参数是左上角 和右下角的坐标
        v.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(10000, View.MeasureSpec.AT_MOST);
        /** 当然，measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局。
         * 按示例调用layout函数后，View的大小将会变成你想要设置成的大小。
         */
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }

    public static String viewSaveToImage(View view, String child) {
        /**
         * View组件显示的内容可以通过cache机制保存为bitmap
         * 我们要获取它的cache先要通过setDrawingCacheEnable方法把cache开启，
         * 然后再调用getDrawingCache方法就可 以获得view的cache图片了
         * 。buildDrawingCache方法可以不用调用，因为调用getDrawingCache方法时，
         * 若果 cache没有建立，系统会自动调用buildDrawingCache方法生成cache。
         * 若果要更新cache, 必须要调用destoryDrawingCache方法把旧的cache销毁，才能建立新的。
         */
        //        view.setDrawingCacheEnabled(true);
        //        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        //设置绘制缓存背景颜色
        //        view.setDrawingCacheBackgroundColor(Color.WHITE);
        // 把一个View转换成图片
        Bitmap cachebmp = loadBitmapFromView(view);

//        aaa.setImageBitmap(cachebmp);//直接展示转化的bitmap

        //保存在本地 产品还没决定要不要保存在本地
        FileOutputStream fos;
        File file = null;
        try {
            // 判断手机设备是否有SD卡
            boolean isHasSDCard = Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
            if (isHasSDCard) {
                // SD卡根目录
                file = new File(BaseApplication.sdcardPath, child + ".png");
                fos = new FileOutputStream(file);
            } else
                throw new Exception("创建文件失败!");
            //压缩图片 30 是压缩率，表示压缩70%; 如果不压缩是100，表示压缩率为0
            cachebmp.compress(Bitmap.CompressFormat.PNG, 90, fos);

            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        view.destroyDrawingCache();
        if (file != null) {
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            BaseApplication.context.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
        }

        return sharePic(cachebmp, child);
    }

    private static Bitmap loadBitmapFromView(View v) {
        int left = v.getLeft();
        int top = v.getTop();
        int w = v.getWidth();
        int h = v.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        /** 如果不设置canvas画布为白色，则生成透明 */
//        c.drawColor(Color.WHITE);

        v.layout(left, top, w + left, h + top);
        v.draw(c);

        return bmp;
    }


    private static String sharePic(Bitmap cachebmp, String child) {
        final File qrImage = new File(BaseApplication.sdcardPath, child + ".jpg");
        if (qrImage.exists()) {
            try {
                qrImage.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            qrImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(qrImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (cachebmp == null) {
            return "";
        }
        try {
            cachebmp.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return qrImage.getPath();
    }

}
