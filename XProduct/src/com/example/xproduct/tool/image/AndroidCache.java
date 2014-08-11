package com.example.xproduct.tool.image;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;

public class AndroidCache {

	private static final String DISK_CACHE_PATH = "/xproduct_image_cache/";
	Context appContext;
	private boolean diskCacheEnabled;
	private String diskCachePath;
	private String diskCacheRoot;
	private int expirationInMinutes;
	private ConcurrentHashMap memoryCache;
	private boolean memoryCacheEnabled;
	private ExecutorService writeThread;

	public AndroidCache(Context context) {
		diskCacheEnabled = false;
		memoryCacheEnabled = false;
		expirationInMinutes = 10080;
		Context context1 = context.getApplicationContext();
		appContext = context1;
		String s = appContext.getCacheDir().getAbsolutePath();
		diskCacheRoot = s;
		ExecutorService executorservice = Executors.newSingleThreadExecutor();
		writeThread = executorservice;
	}

	public void setMemoryCacheEnabled(boolean memoryCEnabled) {
		memoryCacheEnabled = memoryCEnabled;
	}

	public void setDiskCacheEnabled(boolean diskCEnabled) {
		diskCacheEnabled = diskCEnabled;
	}

	public void clear() {
		memoryCache.clear();
		String s = diskCachePath;
		File file = new File(s);
		if (!file.exists())
			return;
		if (!file.isDirectory())
			return;
		File afile[] = file.listFiles();
		int i = afile.length;
		int j = 0;
		do {
			if (j >= i)
				return;
			File file1 = afile[j];
			boolean flag;
			if (file1.exists() && file1.isFile())
				flag = file1.delete();
			j++;
		} while (true);
	}

	public Bitmap get1(String s) {
		Bitmap bt = null;
		if (memoryCacheEnabled)
			bt = getFromMemory1(s);
		if (bt == null) {
			bt = getFromDisk1(s);
			if (bt != null)
				cacheToMemory(s, bt);
		}
		return bt;
	}

	public byte[] get(String s) {
		byte abyte0[] = null;
		if (memoryCacheEnabled)
			abyte0 = getFromMemory(s);
		if (abyte0 == null) {
			abyte0 = getFromDisk(s);
			if (abyte0 != null)
				cacheToMemory(s, abyte0);
		}
		return abyte0;
	}

	public void put1(String s, Bitmap bt) {
		if (memoryCacheEnabled)
			cacheToMemory(s, bt);
		if (!diskCacheEnabled) {
			return;
		} else {
			cacheToDisk(s, bt);
			return;
		}
	}

	public void put(String s, byte abyte0[]) {
		if (memoryCacheEnabled)
			cacheToMemory(s, abyte0);
		if (!diskCacheEnabled) {
			return;
		} else {
			cacheToDisk(s, abyte0);
			return;
		}
	}

	public void remove(String s) {
		if (s == null)
			return;
		Object obj = memoryCache.remove(s);
		String s1 = diskCachePath;
		File file = new File(s1, s);
		if (!file.exists())
			return;
		if (!file.isFile()) {
			return;
		} else {
			boolean flag = file.delete();
			return;
		}
	}

	private void sanitizeDiskCache() {
		File afile[] = new File(diskCachePath).listFiles();

		if (afile == null)
			return;

		for (int j = 0; j < afile.length; j++) {
			File file = afile[j];

			long l1 = (System.currentTimeMillis() - file.lastModified()) / 60000L;

			if (l1 >= expirationInMinutes)
				file.delete();
		}
	}

	protected void cacheToDisk(final String key, final byte val[]) {
		ExecutorService executorservice = writeThread;
		Runnable runnable = new Runnable() {

			public void run() {

				if (!diskCacheEnabled)
					return;

				BufferedOutputStream bufferedoutputstream1 = null;
				AndroidCache androidcache = AndroidCache.this;
				String s = key;
				String s1 = androidcache.getFilePath(s);
				File file = new File(s1);
				FileOutputStream fileoutputstream;
				try {
					fileoutputstream = new FileOutputStream(file);

					bufferedoutputstream1 = new BufferedOutputStream(
							fileoutputstream, 2048);
					byte abyte0[] = val;
					bufferedoutputstream1.write(abyte0);
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				} finally {
					if (bufferedoutputstream1 == null)
						return;

					try {
						bufferedoutputstream1.flush();
						bufferedoutputstream1.close();
					} catch (IOException ioexception5) {
					}
				}

			}

		};
		executorservice.execute(runnable);
	}

	protected void cacheToDisk(final String key, final Bitmap bt) {
		ExecutorService executorservice = writeThread;
		Runnable runnable = new Runnable() {

			public void run() {

				if (!diskCacheEnabled)
					return;
				AndroidCache androidcache = AndroidCache.this;
				androidcache.cacheToDisk(key, bt);
			}

		};
		executorservice.execute(runnable);
	}

	protected void cacheToMemory(String s, Bitmap bt) {
		if (!memoryCacheEnabled) {
			return;
		} else {
			ConcurrentHashMap concurrenthashmap = memoryCache;
			SoftReference softreference = new SoftReference(bt);
			Object obj = concurrenthashmap.put(s, softreference);
			return;
		}
	}

	protected void cacheToMemory(String s, byte abyte0[]) {
		if (!memoryCacheEnabled) {
			return;
		} else {
			ConcurrentHashMap concurrenthashmap = memoryCache;
			SoftReference softreference = new SoftReference(abyte0);
			Object obj = concurrenthashmap.put(s, softreference);
			return;
		}
	}

	protected void enableDiskCache(String s) {
		Object aobj[] = new Object[3];
		String s1 = diskCacheRoot;
		aobj[0] = s1;
		aobj[1] = s;
		aobj[2] = "/";
		String s2 = String.format("%s/%s/", aobj);
		diskCachePath = s2;
		String s3 = diskCachePath;
		File file = new File(s3);

		boolean flag = file.mkdirs();
		sanitizeDiskCache();
		boolean flag1 = file.exists();
		diskCacheEnabled = flag1;
	}

	protected void enableMemoryCache() {
		ConcurrentHashMap concurrenthashmap = new ConcurrentHashMap();
		memoryCache = concurrenthashmap;
		memoryCacheEnabled = true;
	}

	protected String getFilePath(String s) {
		if (s == null)
			throw new RuntimeException("Null url passed in");

		String s4 = null;
		// try {
		// MessageDigest messagedigest = MessageDigest.getInstance("MD5");

		// String s2 = new String(Base64Coder.encode(s.getBytes(), 0));

		String s3 = s.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");

		s4 = new StringBuilder().append(diskCachePath).append(s3).toString();
		// } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
		// throw new RuntimeException(nosuchalgorithmexception);
		// }
		return s4;
	}

	protected byte[] getFromDisk(String s) {
		byte abyte0[];
		if (!diskCacheEnabled) {
			abyte0 = null;
		} else {
			byte abyte1[] = null;
			try {
				String s1 = getFilePath(s);
				File file = new File(s1);
				if (file.exists()) {
					FileInputStream fileinputstream = new FileInputStream(file);
					abyte1 = new byte[fileinputstream.available()];
					int i = fileinputstream.read(abyte1);
					long l = System.currentTimeMillis();
					boolean flag = file.setLastModified(l);
				}
			} catch (IOException ioexception) {
			}
			abyte0 = abyte1;
		}
		return abyte0;
	}

	protected Bitmap getFromDisk1(String s) {
		Bitmap bt;
		if (!diskCacheEnabled) {
			bt = null;
		} else {
			AndroidCache androidcache = AndroidCache.this;
			bt = androidcache.get1(s);
		}
		return bt;
	}

	protected Bitmap getFromMemory1(String s) {
		Bitmap bt = null;
		if (!memoryCacheEnabled) {
			bt = null;
		} else {
			SoftReference softreference = (SoftReference) memoryCache.get(s);
			if (softreference != null)
				bt = (Bitmap) softreference.get();
		}
		return bt;
	}

	protected byte[] getFromMemory(String s) {
		byte abyte0[];
		if (!memoryCacheEnabled) {
			abyte0 = null;
		} else {
			byte abyte1[] = null;
			SoftReference softreference = (SoftReference) memoryCache.get(s);
			if (softreference != null)
				abyte1 = (byte[]) softreference.get();
			abyte0 = abyte1;
		}
		return abyte0;
	}

	protected void setExpirationInMinutes(int i) {
		expirationInMinutes = i;
	}

}
