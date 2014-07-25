package com.example.xproduct.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.hp.box.xproduct.R;

public class HCHODialView extends SurfaceView implements Callback {
	private SurfaceHolder holder;
	private Paint paint;
	private Canvas canvas;
	private int screenW, screenH, imgeW, imgeH, pointImgW, pointImgH;
	private Bitmap leftDialBmp, leftPointerBmp;
	private int leftDialX, leftDialY, leftPointerX, leftPointerY, textBgX,
			textBgY;
	private Rect bgRect;
	private Bitmap textBg;
	public int dialDegrees;
	private String percentageText = "";
	private int percentageX, percentageY;

	public HCHODialView(Context context, AttributeSet attrs) {
		super(context, attrs);
		holder = getHolder();
		holder.addCallback(this);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setColor(Color.argb(255, 207, 60, 11));
		paint.setTextSize(22);
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	public void myDraw() {
		try {
			canvas = holder.lockCanvas(bgRect);
			canvas.drawColor(Color.WHITE);
			drawLeftDial();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			holder.unlockCanvasAndPost(canvas);
		}
	}

	public void drawLeftDial() {
		canvas.drawBitmap(leftDialBmp, leftDialX, leftDialY, paint);
		canvas.save();
		canvas.rotate(dialDegrees,
				leftPointerX + leftPointerBmp.getWidth() / 2, leftPointerY
						+ leftPointerBmp.getHeight() / 2);
		canvas.drawBitmap(leftPointerBmp, leftPointerX, leftPointerY, paint);
		canvas.restore();
	}

	private boolean flag;

	public void surfaceCreated(SurfaceHolder holder) {
		textBg = BitmapFactory.decodeResource(getResources(),
				R.drawable.black_box);
		leftDialBmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.signsec_dashboard);
		leftPointerBmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.signsec_pointer);
		imgeW = leftDialBmp.getWidth();
		imgeH = leftDialBmp.getHeight();
		pointImgW = leftPointerBmp.getWidth();
		pointImgH = leftPointerBmp.getHeight();
		screenH = getHeight();
		screenW = getWidth();
		bgRect = new Rect(0, 0, screenW / 2, (int) (screenH * 0.25));
		leftDialX = (screenW / 2 - imgeW) / 2;
		leftDialX = leftDialX > 0 ? leftDialX : 30;
		leftDialY = 50;
		leftPointerX = leftDialX;
		leftPointerX = leftDialBmp.getWidth() / 2 - leftPointerBmp.getWidth()
				/ 2 + leftDialX;
		leftPointerY = 50;

		textBgX = leftDialX + leftDialBmp.getWidth() / 2 - textBg.getWidth()
				/ 2;
		textBgY = leftDialY + leftDialBmp.getHeight() / 2 - textBg.getHeight()
				/ 2 - 50;
		myDraw();
		flag = true;
		// Thread td = new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// while (flag) {
		// long start = System.currentTimeMillis();
		// myDraw();
		// dialDegrees--;
		// long end = System.currentTimeMillis();
		// try {
		// if (end - start < 100)
		// Thread.sleep(100 - (end - start));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// }
		// });
		// td.start();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		flag = false;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public int getdDialDegrees() {
		return dialDegrees;
	}

	public void setDialDegrees(int dialDegrees) {
		this.dialDegrees = dialDegrees;
		myDraw();
	}

	public String getPercentageText() {
		return percentageText;
	}

	public void setPercentageText(String percentageText) {
		this.percentageText = percentageText;
	}

}