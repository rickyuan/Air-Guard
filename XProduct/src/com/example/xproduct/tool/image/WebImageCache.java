package com.example.xproduct.tool.image;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WebImageCache extends AndroidCache {

	public WebImageCache(Context context) {
		super(context);

		enableMemoryCache();
		enableDiskCache("web_image_cache");
		setExpirationInMinutes(20160);
	}

	public Bitmap getBitmap1(String s) {
		return super.get1(s);
	}

	public void setMemoryCacheEnabled(boolean memoryCEnabled) {
		super.setMemoryCacheEnabled(memoryCEnabled);
	}

	public void setDiskCacheEnabled(boolean diskCEnabled) {
		super.setDiskCacheEnabled(diskCEnabled);
	}

	public Bitmap getBitmap(String s) {
		byte abyte0[] = super.get(s);
		Bitmap bitmap;
		if (abyte0 == null) {
			bitmap = null;
		} else {
			int i = abyte0.length;
			bitmap = BitmapFactory.decodeByteArray(abyte0, 0, i);
		}
		return bitmap;
	}

	public void put1(String s, Bitmap bitmap) {
		super.put1(s, bitmap);
	}

	public void put(String s, Bitmap bitmap) {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		android.graphics.Bitmap.CompressFormat compressformat = android.graphics.Bitmap.CompressFormat.PNG;
		bitmap.compress(compressformat, 13, bytearrayoutputstream);
		byte abyte0[] = bytearrayoutputstream.toByteArray();
		super.put(s, abyte0);
	}
}
