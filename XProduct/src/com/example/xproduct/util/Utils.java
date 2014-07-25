package com.example.xproduct.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {

	public static int getWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public static float getDensity(Context con) {
		return con.getResources().getDisplayMetrics().density;
	}
}
