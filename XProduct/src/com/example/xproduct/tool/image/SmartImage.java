package com.example.xproduct.tool.image;

import android.content.Context;
import android.graphics.Bitmap;

public interface SmartImage {

	public abstract Bitmap getBitmap(Context context);

	public abstract String getImageKey();

	public abstract boolean isFromCache();
}
