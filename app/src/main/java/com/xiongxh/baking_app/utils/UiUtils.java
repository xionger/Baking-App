package com.xiongxh.baking_app.utils;


import android.util.DisplayMetrics;

import com.xiongxh.baking_app.BakingApp;
import com.xiongxh.baking_app.R;

public class UiUtils {
    public static boolean isTablet() {
        return BakingApp.get().getResources().getBoolean(R.bool.isTablet);
    }

    public static boolean isLandscape() {
        return BakingApp.get().getResources().getBoolean(R.bool.isLandscape);
    }

    public static int getCoulumnNumber() {
        if (isTablet()) {

            int itemWidthDp = 150;
            double multiplier = (isTablet()) ? .5 : 1;
            DisplayMetrics displayMetrics = BakingApp.get().getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            dpWidth *= multiplier;
            int res = (int) (dpWidth / itemWidthDp);

            return (res == 0) ? 1 : res;
        } else if (isLandscape()) {
            return 2;
        } else {
            return 1;
        }
    }
}
