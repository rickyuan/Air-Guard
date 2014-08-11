package com.example.xproduct.tool.image;

import java.io.ByteArrayOutputStream;


import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

public class LocalImage implements SmartImage {

	private static android.graphics.BitmapFactory.Options options;
	private static WebImageCache webImageCache;

	private boolean fromCache;
	private int maxHeight;
	private int maxWidth;
	private String url;
	private Context context;

	public LocalImage(String s) {
		maxHeight = 0;
		maxWidth = 0;
		fromCache = false;
		url = s;
	}

	public LocalImage(String s, int i, int j) {
		maxHeight = 0;
		maxWidth = 0;
		fromCache = false;
		url = s;
		maxHeight = j;
		maxWidth = i;
	}

	private Bitmap decodeBitmap(byte abyte0[], int i, int j) {
		Bitmap bitmap;
		if (abyte0 == null) {
			bitmap = null;
		} else {
			android.graphics.BitmapFactory.Options options1 = getBitmapFactoryOptions();
			if (j > 0 || i > 0) {
				options1.inJustDecodeBounds = true;

				BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length,
						options1);
				int l;
				int i1;
				int j1;

				if (j > 0)
					l = options1.outHeight / j;
				else
					l = 1;
				if (i > 0)
					i1 = options1.outWidth / i;
				else
					i1 = 1;
				if (i1 > l)
					j1 = i1;
				else
					j1 = l;
				options1.inSampleSize = j1;
				options1.inJustDecodeBounds = false;
			}
			else{
				options1.inSampleSize = computeSampleSize(options1, -1, 256*256);  
				//System.out.println("options1.inSampleSize ==="+options1.inSampleSize);
				options1.inJustDecodeBounds = false;
			}
			// k1 = ;
			bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length,
					options1);
		}
		return bitmap;
	}
	public static int computeSampleSize(BitmapFactory.Options options,  
	        int minSideLength, int maxNumOfPixels) {  
	    int initialSize = computeInitialSampleSize(options, minSideLength,maxNumOfPixels);  
	  
	    int roundedSize;  
	    if (initialSize <= 8 ) {  
	        roundedSize = 1;  
	        while (roundedSize < initialSize) {  
	           roundedSize <<= 1;  
	        }  
	    } else {  
	        roundedSize = (initialSize + 7) / 8 * 8;  
	    }  
	  
	    return roundedSize;  
	}  
	  
	private static int computeInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {  
	    double w = options.outWidth;  
	    double h = options.outHeight;  
	  
	    int lowerBound = (maxNumOfPixels == -1) ? 1 :  
	            (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));  
	    int upperBound = (minSideLength == -1) ? 128 :  
	            (int) Math.min(Math.floor(w / minSideLength),  
	            Math.floor(h / minSideLength));  
	  
	    if (upperBound < lowerBound) {  
	        // return the larger one when there is no overlapping zone.   
	        return lowerBound;  
	    }  
	  
	    if ((maxNumOfPixels == -1) &&  
	            (minSideLength == -1)) {  
	        return 1;  
	    } else if (minSideLength == -1) {  
	        return lowerBound;  
	    } else {  
	        return upperBound;  
	    }  
	}  
	private android.graphics.BitmapFactory.Options getBitmapFactoryOptions() {
		options = new android.graphics.BitmapFactory.Options();
		options.inPurgeable = true;
		options.inDither = false;
		options.inInputShareable = true;
		return options;
	}

	private byte[] getImageData(String s) {
		Bitmap bitmap;
		try {
			bitmap = ((BitmapDrawable) context.getPackageManager().getApplicationIcon(s)).getBitmap();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

			return baos.toByteArray();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void removeFromCache(String s) {
		if (webImageCache == null) {
			return;
		} else {
			webImageCache.remove(s);
			return;
		}
	}

	public Bitmap getBitmap(Context context) {
		if (webImageCache == null)
			webImageCache = new WebImageCache(context);
		 this.context = context;
		byte abyte0[] = null;
		if (url != null) {
			abyte0 = webImageCache.get(url);
			if (abyte0 == null) {
				abyte0 = getImageData(url);

				if (abyte0 == null) {
					return null;
				} else {
					webImageCache.put(url, abyte0);
				}
			} else {
				fromCache = true;
			}
		}

		return decodeBitmap(abyte0, maxWidth, maxHeight);
	}

	public String getImageKey() {
		StringBuilder stringbuilder = (new StringBuilder()).append("WebImage_");
		String s = url;
		return stringbuilder.append(s).toString();
	}

	public boolean isFromCache() {
		return fromCache;
	}

}
