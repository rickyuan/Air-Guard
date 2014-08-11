package com.example.xproduct.view;

import android.content.Context;
import android.graphics.Shader.TileMode;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.InjectView;

import com.example.xproduct.FullscreenActivity;
import com.example.xproduct.tool.image.SmartImageView;
import com.hp.box.xproduct.R;

public class ViewPagerTop extends RelativeLayout implements OnClickListener {

	@InjectView(R.id.left_bt_iv)
	SmartImageView leftBt_iv;
	@InjectView(R.id.setting_bt_iv)
	SmartImageView settingBt_iv;
	@InjectView(R.id.share_bt_iv)
	SmartImageView shareBt_iv;
	@InjectView(R.id.top_title_tv)
	TextView top_title_tv;
	private Context context;
	private Handler handler;
	private View view;

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.left_bt_iv:
			handler.sendEmptyMessage(FullscreenActivity.SHOWLEFTV);
			break;
		case R.id.setting_bt_iv:

			break;
		case R.id.share_bt_iv:

			break;
		default:
			break;
		}
	}

	public ViewPagerTop(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ViewPagerTop(Context context, Handler handler) {
		super(context);
		this.context = context;
		this.handler = handler;
		initView();
	}

	private void initView() {
		findViewById();
		setOnclickListener();
	}

	private void findViewById() {
		view = inflate(context, R.layout.viewpager_top, null);
		this.addView(view);
		top_title_tv = (TextView) view.findViewById(R.id.top_title_tv);
		leftBt_iv = (SmartImageView) view.findViewById(R.id.left_bt_iv);
		settingBt_iv = (SmartImageView) view.findViewById(R.id.setting_bt_iv);
		shareBt_iv = (SmartImageView) view.findViewById(R.id.share_bt_iv);
	}

	public void setTitle(String titleTv) {
		if (!titleTv.equals("") && titleTv != null) {
			top_title_tv.setText(titleTv);
		}
	}

	private void setOnclickListener() {
		leftBt_iv.setOnClickListener(this);
	}

}
