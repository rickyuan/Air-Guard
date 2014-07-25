package com.example.xproduct;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.example.xproduct.adapter.ContentAdapter1;
import com.example.xproduct.aplication.XProductData;
import com.example.xproduct.util.BidirSlidingLayout;
import com.example.xproduct.util.Utils;
import com.example.xproduct.view.CldFg;
import com.example.xproduct.view.CvsFg;
import com.example.xproduct.view.DialFg;
import com.example.xproduct.view.LeftMenu;
import com.example.xproduct.view.SimpleGraph;
import com.hp.box.xproduct.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FullscreenActivity extends FragmentActivity implements
		OnTouchListener {

	private String TAG = "FullscreenActivity";
	private Context context;
	/**
	 * 双向滑动菜单布局
	 */
	private BidirSlidingLayout xproduct_content;
	public ViewPager contentList;
	private RelativeLayout left_menu;
	private ContentAdapter1 vpAdapter;
	private ArrayList<View> mList;
	public static int width;
	public static int CURRENTPAGEITEM = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);
		context = this;
		findViewById();
		initView();
		setOnclickListener();
	}

	private void findViewById() {
		xproduct_content = (BidirSlidingLayout) findViewById(R.id.xproduct_content);
		left_menu = (RelativeLayout) findViewById(R.id.left_menu);
		contentList = (ViewPager) findViewById(R.id.contentList);
	}

	private void initView() {
		if (width == 0) {
			width = Utils.getWidth(((Activity) context));
		}
		XProductData.LEFTVIEWWIDTH = (int) (width * 0.8);
		left_menu.addView(new LeftMenu(this).getView());
		left_menu.setLayoutParams(new RelativeLayout.LayoutParams(
				XProductData.LEFTVIEWWIDTH, LayoutParams.FILL_PARENT));
		mList = new ArrayList<View>();
		mList.add(new DialFg().getView(context));
		mList.add(new CvsFg().getView(context));
		mList.add(new CldFg().getView(context));
		vpAdapter = new ContentAdapter1(getSupportFragmentManager(), mList);
		contentList.setOffscreenPageLimit(0);
		contentList.setAdapter(vpAdapter);
		xproduct_content.setScrollEvent(contentList);
		contentList.setOnPageChangeListener(pgLis);
	}

	private GestureDetector mGestureDetector;

	private void setOnclickListener() {
		xproduct_content.setOnTouchListener(this);
		xproduct_content.setLongClickable(true);
		mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
	}

	private OnClickListener lis = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			default:
				break;
			}
		}
	};

	private OnPageChangeListener pgLis = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			CURRENTPAGEITEM = arg0;
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};

	private class MyOnGestureListener implements OnGestureListener {
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			boolean sdMask = false;
			if (contentList.getCurrentItem() == 0) {
				if (e2.getX() - e1.getX() > 0) {
					if (xproduct_content.isLeftLayoutVisible()) {
						xproduct_content.scrollToContentFromLeftMenu();
					} else {
						xproduct_content.initShowLeftState();
						xproduct_content.scrollToLeftMenu();
					}
					sdMask = false;
				}
			}
			return sdMask;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return mGestureDetector.onTouchEvent(event);
	}

}
