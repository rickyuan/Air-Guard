package com.example.xproduct.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.hp.box.xproduct.R;

public class HCHODialView extends SurfaceView implements Callback {
	private SurfaceHolder holder;
	private Paint paint;
	private Canvas canvas;
	private int screenW, screenH, imgeW, imgeH;
	private Bitmap leftDialBmp;
	private int leftDialX, leftDialY;
	private Rect bgRect;
	public double dialDegrees = 0.99;
	private Bitmap textBg;
	private String percentageText = "";

	public HCHODialView(Context context, AttributeSet attrs) {
		super(context, attrs);
		holder = getHolder();
		holder.addCallback(this);
		paint = new Paint();
		paint.setAntiAlias(true);
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
		paint.setStrokeWidth(11);
		setProgress();
		canvas.restore();
	}

	private void setProgress() {
		if (dialDegrees != 0) {
			boolean is33125 = false;
			boolean is6625 = false;
			if (dialDegrees >= 0.33125) {
				is33125 = true;
				if (dialDegrees >= 0.6625) {
					is6625 = true;
				}
			}
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(11);
			// 定义一个矩形
			RectF rf1 = new RectF(leftDialX + 7, leftDialY + 7, leftDialX
					+ imgeW - 7, leftDialY + imgeW - 7);
			if (!is33125) {
				paint.setColor(Color.argb(255, 191, 46, 46));
				canvas.drawArc(rf1, 110, (int) (106 * dialDegrees), false,
						paint);
			} else {
				if (!is6625) {
					paint.setColor(Color.argb(200, 191, 46, 46));
					canvas.drawArc(rf1, 110, 106, false, paint);
					paint.setColor(Color.argb(200, 249, 137, 49));
					canvas.drawArc(rf1, 110,
							(int) (106 * (dialDegrees - 0.33125)), false, paint);
				} else {
					paint.setColor(Color.argb(200, 191, 46, 46));
					canvas.drawArc(rf1, 110, 106, false, paint);
					paint.setColor(Color.argb(200, 249, 137, 49));
					canvas.drawArc(rf1, 216, 106, false, paint);
					paint.setColor(Color.argb(200, 255, 200, 41));
					canvas.drawArc(rf1, 322,
							(int) (106 * (dialDegrees - 0.6625)), false, paint);
				}
			}
		}
	}

	private boolean flag;

	public void surfaceCreated(SurfaceHolder holder) {
		textBg = BitmapFactory.decodeResource(getResources(),
				R.drawable.black_box);
		leftDialBmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.signsec_dashboard);
		imgeW = leftDialBmp.getWidth();
		imgeH = leftDialBmp.getHeight();
		screenH = getHeight();
		screenW = getWidth();
		bgRect = new Rect(0, 0, screenW / 2, (int) (screenH * 0.25));
		leftDialX = (screenW / 2 - imgeW) / 2;
		leftDialX = leftDialX > 0 ? leftDialX : 30;
		leftDialY = 50;
		myDraw();
		flag = true;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		flag = false;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public double getdDialDegrees() {
		return dialDegrees;
	}

	public void setDialDegrees(double dialDegrees) {
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
