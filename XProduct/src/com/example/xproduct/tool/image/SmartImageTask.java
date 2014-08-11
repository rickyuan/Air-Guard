package com.example.xproduct.tool.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

public class SmartImageTask implements Runnable {
	public static class OnCompleteHandler extends Handler {

		public void handleMessage(Message message) {
			BitmapMessage bitmapmessage = (BitmapMessage) message.obj;

			onComplete(bitmapmessage.bitmap, bitmapmessage.fromCache);
		}

		public void onComplete(Bitmap bitmap, boolean flag) {
		}

		public OnCompleteHandler() {
		}
	}

	public static class BitmapMessage {

		public Bitmap bitmap;
		public boolean fromCache;

		public BitmapMessage() {
		}
	}

	private static final int BITMAP_READY = 0;
	private boolean cancelled;
	private Context context;
	private SmartImage image;
	private OnCompleteHandler onCompleteHandler;

	public SmartImageTask(Context context1, SmartImage smartimage) {
		cancelled = false;
		image = smartimage;
		context = context1;
	}

	public void cancel() {
		cancelled = true;
	}

	public void complete(BitmapMessage bitmapmessage) {
		if (onCompleteHandler == null)
			return;

		if (!cancelled) {
			onCompleteHandler.sendMessage(onCompleteHandler.obtainMessage(
					BITMAP_READY, bitmapmessage));
		}
	}

	public void run() {
		if (image != null) {
			BitmapMessage bitmapmessage = new BitmapMessage();

			bitmapmessage.bitmap = image.getBitmap(context);

			bitmapmessage.fromCache = image.isFromCache();

			complete(bitmapmessage);
			context = null;
		}
	}

	public void setOnCompleteHandler(OnCompleteHandler oncompletehandler) {
		onCompleteHandler = oncompletehandler;
	}
}
