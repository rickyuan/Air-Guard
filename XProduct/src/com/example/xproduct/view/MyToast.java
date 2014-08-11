package com.example.xproduct.view;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MyToast {

	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
		}
	};

	public static void makeText(Context mContext, String text, int duration) {

		int durat = 1000;
		if (duration == 0) {
			durat = 500;
		}
		mHandler.removeCallbacks(r);
		if (mToast != null)
			mToast.setText(text);
		else
			mToast = Toast.makeText(mContext, text, 1);
		mHandler.postDelayed(r, durat);

		mToast.show();
	}

	public static void makeText(Context mContext, int resId, int duration) {
		makeText(mContext, mContext.getResources().getString(resId), duration);
	}

}
