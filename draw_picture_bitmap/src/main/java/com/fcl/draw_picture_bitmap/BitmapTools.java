package com.fcl.draw_picture_bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.support.v4.util.LruCache;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Bitmap 图片处理
 * Created by derik on 17-5-2.
 */

public class BitmapTools {

    // 软引用
    private Map<String, SoftReference<Bitmap>> imageCacheSof = null;
    // LruCache
    private LruCache<String, Bitmap> imageCacheLru = null;

    private Bitmap bitmap = null;

    private static BitmapTools bitmapTools = null;

    public static BitmapTools getBitmapTools() {
        if (bitmapTools == null) {
            synchronized (BitmapTools.class) {
                if (bitmapTools == null) {
                    bitmapTools = new BitmapTools();
                }
            }
        }
        return bitmapTools;
    }

    // KB
    private int getMaxMemory() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory/1024 + "MB");
        return maxMemory;
    }

    // 图片缩小技术处理之一：先依据组件宽高，计算出缩小比例
    private int calInSampleSize(Context ctx, int resId, int reqWidth, int reqHeight) {
        int scale = 1;

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 将这个参数的inJustDecodeBounds属性设置为true就可以让解析方法禁止为bitmap分配内存，
            // 返回值也不再是一个Bitmap对象，而是null。虽然Bitmap是null了，但是
            // BitmapFactory.Options类对象的outWidth、outHeight和outMimeType属性都会被赋值
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(ctx.getResources(), resId, options);
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;

            if (reqWidth > 0 && reqHeight > 0) {
                if (imageHeight > reqHeight || imageWidth > reqWidth) {
                    // 计算出实际宽高和目标宽高的比率
                    final int heightRatio = Math.round((float) imageHeight / (float) reqHeight);
                    final int widthRatio = Math.round((float) imageWidth / (float) reqWidth);

                    // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
                    // 取较小時，小的刚好，大的按小比例缩小，缩小的更少，像素会大于目标组件要求像素，一定都会大于等于目标的宽和高
                    // 取较大时，大的刚好，小的按大比例缩小，缩小的更多，像素会小于目标组件要求像素
                    scale = widthRatio > heightRatio ? widthRatio : heightRatio;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("scale", "" + scale);
        return scale;
    }


    // 图片缩小技术处理之一：先依据组件宽高，计算出缩小比例
    public PointF calScale(Context ctx, int resId, int reqWidth, int reqHeight) {
        PointF pointF = new PointF();
        pointF.set(1.0f, 1.0f);

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 将这个参数的inJustDecodeBounds属性设置为true就可以让解析方法禁止为bitmap分配内存，
            // 返回值也不再是一个Bitmap对象，而是null。虽然Bitmap是null了，但是
            // BitmapFactory.Options类对象的outWidth、outHeight和outMimeType属性都会被赋值
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(ctx.getResources(), resId, options);
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;

            if (reqWidth > 0 && reqHeight > 0) {
//                if (imageHeight > reqHeight || imageWidth > reqWidth) {
                    // 计算出实际宽高和目标宽高的比率
                    final float heightRatio = (float)reqHeight / (float)imageHeight ;
                    final float widthRatio = (float)reqWidth / (float)imageWidth ;

                    // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
                    // 取较小時，小的刚好，大的按小比例缩小，缩小的更少，像素会大于目标组件要求像素，一定都会大于等于目标的宽和高
                    // 取较大时，大的刚好，小的按大比例缩小，缩小的更多，像素会小于目标组件要求像素
//                    scale = widthRatio > heightRatio ? widthRatio : heightRatio;
                System.out.println("widthRatio:"+widthRatio+"; heightRatio:"+heightRatio);
                pointF.set(widthRatio, heightRatio);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("pointF", "" + pointF);
        return pointF;
    }

    private int calInSampleSize(String filePath, int reqWidth, int reqHeight) {
        int scale = 1;

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 将这个参数的inJustDecodeBounds属性设置为true就可以让解析方法禁止为bitmap分配内存，
            // 返回值也不再是一个Bitmap对象，而是null。虽然Bitmap是null了，但是
            // BitmapFactory.Options类对象的outWidth、outHeight和outMimeType属性都会被赋值
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;

            if (reqWidth > 0 && reqHeight > 0) {
                if (imageHeight > reqHeight || imageWidth > reqWidth) {
                    // 计算出实际宽高和目标宽高的比率
                    final int heightRatio = Math.round((float) imageHeight / (float) reqHeight);
                    final int widthRatio = Math.round((float) imageWidth / (float) reqWidth);

                    // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
                    // 取较小時，小的刚好，大的按小比例缩小，缩小的更少，像素会大于目标组件要求像素，一定都会大于等于目标的宽和高
                    // 取较大时，大的刚好，小的按大比例缩小，缩小的更多，像素会小于目标组件要求像素
                    scale = widthRatio < heightRatio ? widthRatio : heightRatio;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("scale", "" + scale);
        return scale;
    }

    /**
     * 使Bitmap缩小为原来的inSampleSize分之一，降低使用内存
     *
     * @param context   上下文
     * @param resId     资源Id
     * @param reqWidth  要求宽
     * @param reqHeight 要求高
     * @return 返回bitmap
     */
    public Bitmap inSampleSize(Context context, int resId, Bitmap.Config config, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false; // 默认false
        if (config == null) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//            options.inPurgeable = true; //允许系统在必要时清除，避免在UI响应要求快的地方使用
//            options.inInputShareable = true; //允许保存输入数据的引用，以重建bitmap
        }

        options.inSampleSize = calInSampleSize(context, resId, reqWidth, reqHeight);

        try {
            //解析并分配内存给bitmap对象
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }

        return bitmap;
    }

    public Bitmap inSampleSize(String filePath, Bitmap.Config config, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false; // 默认false

        if (config == null) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888; //降低像素使用内存
//            options.inPurgeable = true; //允许系统在必要时清除，避免在UI响应要求快的地方使用
//            options.inInputShareable = true; //允许保存输入数据的引用，以重建bitmap
        }

        options.inSampleSize = calInSampleSize(filePath, reqWidth, reqHeight);

        try {
            //解析并分配内存给bitmap对象
            bitmap = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 功能描述：使Bitmap从默认的ARGB-8888转为RGB-565，降低内存使用
     * ALPHA_8：每个像素占用1byte内存
     * ARGB_4444：每个像素占用2byte内存
     * ARGB_8888：每个像素占用4byte内存 （默认）
     * RGB_565：每个像素占用2byte内存
     *
     * @param context 上下文
     * @param resId   资源Id
     * @return 返回Bitmap
     */
    public Bitmap decodeResource(Context context, int resId, Bitmap.Config config) {
        BitmapFactory.Options opt = new BitmapFactory.Options();

        if (config == null) {
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
//        opt.inPurgeable = true; //允许系统在必要时清除，避免在UI响应要求快的地方使用
//        opt.inInputShareable = true; //允许保存输入数据的引用，以重建bitmap
        }

        //获取资源图片
        try {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId, opt);

        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
        return bitmap;
    }

    //使用LruCache作缓存之取
    public Bitmap getBitmapFromLru(String url) {

        return imageCacheLru != null ? imageCacheLru.get(url) : null;
    }

    //使用LruCache作缓存之存
    public void putBitmapToLru(String url, Bitmap bitmap) {
        if (imageCacheLru == null) {
            int maxMemory = getMaxMemory(); // kb
            int maxSize = maxMemory / 10; // 十分之一作缓存
            imageCacheLru = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024; //单位 kb
                }
            };
        }
        imageCacheLru.put(url, bitmap);
    }


    // 软引用作缓存之存
    public void putBitmapToSof(String path, Bitmap bitmap) {
        try {
            if (imageCacheSof == null) {
                imageCacheSof = new HashMap<>();
            }
            // 软引用的Bitmap对象
            SoftReference<Bitmap> softBitmap = new SoftReference<>(bitmap);
            // 添加该对象到Map中使其缓存
            imageCacheSof.put(path, softBitmap);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }

    }

    // 软引用作缓存之取
    public Bitmap getBitmapFromSof(String path) {
        if (imageCacheSof != null) {
            // 从hashMap中取出软引用
            SoftReference<Bitmap> softReference = imageCacheSof.get(path);
            // 判断是否存在软引用
            if (softReference == null) {
                return null;
            }
            // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
            bitmap = softReference.get();
        } else {
            return null;
        }
        return bitmap;
    }

    public Bitmap imageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int wh = w > h ? h : w;// 裁切后所取的正方形区域边长

        int retX = w > h ? (w - h) / 2 : 0;//基于原图，取正方形左上角x坐标
        int retY = w > h ? 0 : (h - w) / 2;

        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, wh, wh, null, false);
    }

    /**
     * 使用完后复制使用此段代码，recycle bitmap
     */
    public void recycle() {
        // 先判断是否已经回收
        if (bitmap != null && !bitmap.isRecycled()) {
            // 回收并且置为null
            System.out.println("回收...");
            bitmap.recycle();
            bitmap = null;

        }
        System.gc();

    }

    public void destroy() {
        recycle();
        if (imageCacheSof != null) {
            imageCacheSof.clear();
            imageCacheSof = null;
        }
        if (imageCacheLru != null) {
            imageCacheLru = null;
        }
    }

}
