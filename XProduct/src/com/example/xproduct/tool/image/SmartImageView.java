package com.example.xproduct.tool.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartImageView extends ImageView {
	private static final int LOADING_THREADS = 4;

	private static ExecutorService threadPool = Executors
			.newFixedThreadPool(LOADING_THREADS);

	private SmartImageTask currentTask;
	private boolean everAnimated;
	private String loadedImageKey;

	public SmartImageView(Context context) {
		super(context);
		everAnimated = false;
	}

	public SmartImageView(Context context, AttributeSet attributeset) {
		super(context, attributeset);
		everAnimated = false;
	}

	public SmartImageView(Context context, AttributeSet attributeset, int i) {
		super(context, attributeset, i);
		everAnimated = false;
	}

	public static void cancelAllTasks() {
		threadPool.shutdownNow();
		threadPool = Executors.newFixedThreadPool(LOADING_THREADS);
	}

	public void setImage(SmartImage smartimage) {
		setImage(smartimage, null, null);
	}

	public void setImage(SmartImage smartimage, Integer resource) {
		setImage(smartimage, resource, resource, false);
	}

	public void setImage(SmartImage smartimage, Integer fallbackResource,
			Integer loadResource) {
		setImage(smartimage, fallbackResource, loadResource, false);
	}

	public void setImage(final SmartImage image,
			final Integer fallbackResource, Integer loadResource,
			final boolean animateOnLoad) {
		if (loadResource != null) {
			if (loadedImageKey == null || image == null
					|| !image.getImageKey().equals(loadedImageKey)) {
				setImageResource(loadResource);
			}
		}

		if (currentTask != null) {
			currentTask.cancel();
			currentTask = null;
		}

		currentTask = new SmartImageTask(getContext(), image);

		currentTask
				.setOnCompleteHandler(new SmartImageTask.OnCompleteHandler() {

					public void onComplete(Bitmap bitmap, boolean flag) {
						if (bitmap == null) {
							if (fallbackResource != null) {
								setImageResource(fallbackResource);
							}
						} else {
							setImageBitmap(bitmap);

							loadedImageKey = image.getImageKey();
						}

						if (animateOnLoad && !everAnimated && !flag) {
							AlphaAnimation alphaanimation = new AlphaAnimation(
									0F, 1F);
							alphaanimation.setDuration(500L);
							startAnimation(alphaanimation);

							everAnimated = true;
						}
					}

				});

		threadPool.execute(currentTask);
	}

	public void setImageContact(long l) {
		ContactImage contactimage = new ContactImage(l);
		setImage(contactimage);
	}

	public void setImageContact(long l, Integer integer) {
		ContactImage contactimage = new ContactImage(l);
		setImage(contactimage, integer);
	}

	public void setImageContact(long l, Integer integer, Integer integer1) {
		ContactImage contactimage = new ContactImage(l);
		setImage(contactimage, integer, integer);
	}

	public void setImageUrl(String url) {
		if (url != null) {
			WebImage webimage = new WebImage(url);
			setImage(webimage);
		}
	}

	public void setLocalImage(String packName) {
		if (packName != null) {
			LocalImage localImage = new LocalImage(packName);
			setImage(localImage);
		}
	}

	public void setImageUrl(String s, Integer integer) {
		WebImage webimage = new WebImage(s);
		setImage(webimage, integer);
	}

	public void setImageUrl(String s, Integer integer, Integer integer1) {
		WebImage webimage = new WebImage(s);
		setImage(webimage, integer, integer1);
	}

}
