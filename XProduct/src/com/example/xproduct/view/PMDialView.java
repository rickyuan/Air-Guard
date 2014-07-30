package com.example.xproduct.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.hp.box.xproduct.R;

@SuppressLint("ResourceAsColor")
public class PMDialView extends SurfaceView implements Callback {
	private SurfaceHolder holder;
	private Paint paint;
	private Paint paint2;
	private Canvas canvas;
	private int screenW, screenH, imgeW, imgeH, pointImgW, pointImgH;
	private float scaleX = 1;
	private Bitmap leftDialBmp;// , leftPointerBmp;
	private int leftDialX, leftDialY, leftPointerX, leftPointerY;// , textBgX,
	// textBgY;
	private Rect bgRect;
	private Bitmap textBg;
	public int dialDegrees;
	private String percentageText = "";
	private int percentageX, percentageY;

	public PMDialView(Context context, AttributeSet attrs) {
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
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.RED);// , R.color.pm_cl_2,
		// R.color.pm_cl_3
		paint.setStrokeWidth(11);
		// 定义一个矩形
		RectF rf1 = new RectF(leftDialX + 5, leftDialY + 5, leftDialX + imgeW,
				leftDialY + imgeW);
		canvas.drawArc(rf1, -105, 360, false, paint);
		// canvas.drawCircle(leftPointerX, leftPointerY, imgeW / 2 - 7, paint);
		// canvas.rotate(dialDegrees,
		// leftPointerX + leftPointerBmp.getWidth() / 2, leftPointerY
		// + leftPointerBmp.getHeight() / 2);
		// canvas.drawBitmap(leftDialBmp, leftPointerX, leftPointerY, paint);
		canvas.restore();
	}

	private boolean flag;

	public void surfaceCreated(SurfaceHolder holder) {
		textBg = BitmapFactory.decodeResource(getResources(),
				R.drawable.black_box);
		leftDialBmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.signsec_dashboard_1);
		// leftPointerBmp = BitmapFactory.decodeResource(getResources(),
		// R.drawable.signsec_dashboard_1);
		imgeW = leftDialBmp.getWidth();
		imgeH = leftDialBmp.getHeight();
		// pointImgW = leftPointerBmp.getWidth();
		// pointImgH = leftPointerBmp.getHeight();
		screenW = getWidth();
		screenH = getHeight();
		bgRect = new Rect(0, 0, screenW / 2, (int) (screenH * 0.25));
		leftDialX = (screenW / 2 - imgeW) / 2;
		leftDialX = leftDialX > 0 ? leftDialX : 30;
		leftDialY = 50;
		// leftPointerX = leftDialBmp.getWidth() / 2 - leftPointerBmp.getWidth()
		// / 2 + leftDialX;
		// leftPointerY = 50;
		// leftPointerX = imgeW / 2 + leftDialX;
		// leftPointerY = 50 + imgeH / 2 + 5;
		// textBgX = leftDialX + leftDialBmp.getWidth() / 2 - textBg.getWidth()
		// / 2;
		// textBgY = leftDialY + leftDialBmp.getHeight() / 2 -
		// textBg.getHeight()
		// / 2 - 50;
		myDraw();
		flag = true;
		// Thread thread = new Thread(this);
		// thread.start();
	}

	// public void run() {
	// while (flag) {
	// long start = System.currentTimeMillis();
	// myDraw();
	// dialDegrees++;
	// long end = System.currentTimeMillis();
	// try {
	// if (end - start < 50)
	// Thread.sleep(50 - (end - start));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }

	private void scaleBmp() {
		int screenhfw = (screenW / 2);
		scaleX = (float) ((screenhfw * 0.85) / imgeW);
		Matrix matrix = new Matrix();
		matrix.postScale(scaleX, scaleX);
		leftDialBmp = Bitmap.createBitmap(leftDialBmp, 0, 0, imgeW, imgeH - 1,
				matrix, true);
		// leftPointerBmp = Bitmap.createBitmap(leftPointerBmp, 0, 0, pointImgW,
		// pointImgH, matrix, true);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		flag = false;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public int getDialDegrees() {
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
