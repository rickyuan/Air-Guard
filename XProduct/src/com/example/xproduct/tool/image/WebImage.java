package com.example.xproduct.tool.image;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class WebImage implements SmartImage, Serializable {
	private static final int CONNECT_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 10000;

	private static android.graphics.BitmapFactory.Options options;
	private static WebImageCache webImageCache;

	private boolean fromCache;
	private int maxHeight;
	private int maxWidth;
	private String url;
	private boolean diskCaEnabled = false;

	public WebImage(String s) {
		maxHeight = 0;
		maxWidth = 0;
		fromCache = false;
		url = s;
	}

	/**
	 * 保证图片是否失真;
	 * @param s
	 * @param i
	 * @param j
	 */
	public WebImage(String s, int i, int j) {
		maxHeight = 0;
		maxWidth = 0;
		fromCache = false;
		url = s;
		maxHeight = j;
		maxWidth = i;
	}

	public WebImage(String s, int i, int j, boolean diskCEnabled) {
		maxHeight = 0;
		maxWidth = 0;
		fromCache = false;
		url = s;
		maxHeight = j;
		maxWidth = i;
		diskCaEnabled = diskCEnabled;
	}

	@SuppressWarnings("static-access")
	private Bitmap decodeBitmap(byte abyte0[], int i, int j) {
		Bitmap bitmap = null;
		// float scaleHeight = 0;
		// float scaleWidth = 0;
		// int iHt = 0;
		// int iWt = 0;
		if (abyte0 == null) {
			bitmap = null;
		} else {
			android.graphics.BitmapFactory.Options options1 = getBitmapFactoryOptions();
			if (j > 0 || i > 0) {
				options1.inJustDecodeBounds = true;
				BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length,
						options1);
				// int l;
				// int i1;
				// int j1;
				//
				// if (j > 0)
				// l = options1.outHeight / j;
				// else
				// l = 1;
				// if (i > 0)
				// i1 = options1.outWidth / i;
				// else
				// i1 = 1;
				// if (i1 > l)
				// j1 = i1;
				// else
				// j1 = l;
				// options1.inSampleSize = j1;
				options1.inSampleSize = computeSampleSize(options1, -1, i * j);
				options1.inJustDecodeBounds = false;
			}
			// k1 = ;
			bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length,
					options1);
			// if (bitmap != null && i > 0 && j > 0) {
			// iHt = bitmap.getHeight();
			// iWt = bitmap.getWidth();
			// if (iHt > 0 && iWt > 0) {
			// scaleHeight = j / iHt;
			// scaleWidth = i / iWt;
			// System.out.println("scaleHeight=" + scaleHeight
			// + "scaleWidth=" + scaleWidth);
			// Matrix matrix = new Matrix();
			// if (scaleHeight > 0 && scaleWidth > 0) {
			// matrix.postScale(scaleWidth, scaleHeight);
			// }
			// bitmap = Bitmap.createBitmap(bitmap, 0, 0, iWt, iHt,
			// matrix, true);
			// }
			// }
			// bitmap = Utils.convertToRoundedCorner(bitmap,4);
		}
		return bitmap;
	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		// System.out.println("roundedSize=============="+roundedSize);
		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
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
		byte abyte0[] = null;

		try {
			URLConnection urlconnection = (new URL(s)).openConnection();

			urlconnection.setConnectTimeout(CONNECT_TIMEOUT);
			urlconnection.setReadTimeout(READ_TIMEOUT);

			abyte0 = Utils.getByteArray((InputStream) urlconnection
					.getContent());
		} catch (Exception e) {
			abyte0 = null;
			e.printStackTrace();
		}

		return abyte0;
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
		if (webImageCache == null) {
			webImageCache = new WebImageCache(context);
			webImageCache.setDiskCacheEnabled(diskCaEnabled);
		}
		byte abyte0[] = null;
		if (url != null) {
			abyte0 = webImageCache.get(url);
			if (abyte0 == null) {
				abyte0 = getImageData(url);
				if (abyte0 != null) {
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
	
	@SuppressWarnings("unused")
	private Bitmap decodeBitmapNew(byte abyte0[], int i, int j) {
		Bitmap bitmap;
		if (abyte0 == null) {
			bitmap = null;
		} else {
			android.graphics.BitmapFactory.Options options1 = getBitmapFactoryOptions();
			if (j > 0 || i > 0) {
				options1.inJustDecodeBounds = true;

				BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length,
						options1);
				int ivWidth = options1.outWidth;
				int ivHeight = options1.outHeight;
				float scale = 1;
				float scalex = i / ivWidth;
				float scaley = j / ivHeight;
				if (scalex > 1 || scaley > 1) {
					scale = Math.max(scalex, scaley);
				} else {
					scale = Math.min(scalex, scaley);
				}
				options1.inSampleSize = computeSampleSize(options1, -1, i * j);
				options1.inJustDecodeBounds = false;
//				options1.inScaled = true;
//				options1.outWidth = (int) (ivWidth * scale);
//				options1.outHeight = (int) (ivHeight * scale);
			}
			bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length,
					options1);
		}
		return bitmap;
	}
}
