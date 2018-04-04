package com.liwenpeng.topnews.util;

import android.os.Looper;

/**
 * Created by liwenpeng on 18-4-3.
 */

public class ToolUtils {
    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

}
