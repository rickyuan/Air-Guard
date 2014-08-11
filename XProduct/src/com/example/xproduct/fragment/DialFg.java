package com.example.xproduct.fragment;

import butterknife.InjectView;

import com.example.xproduct.FullscreenActivity;
import com.example.xproduct.tool.image.SmartImageView;
import com.example.xproduct.view.HCHODialView;
import com.example.xproduct.view.PMDialView;
import com.hp.box.xproduct.R;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DialFg extends Fragment {

	@InjectView(R.id.left_bt_iv)
	SmartImageView leftBt_iv;
	@InjectView(R.id.setting_bt_iv)
	SmartImageView settingBt_iv;
	@InjectView(R.id.share_bt_iv)
	SmartImageView shareBt_iv;
	@InjectView(R.id.top_title_tv)
	TextView top_title_tv;
	private Context context;
	private View view;
	private PMDialView pm_dialv;
	private HCHODialView hcho_dialv;

	public static DialFg newInstance() {
		DialFg fg = new DialFg();
		return fg;
	}

	Handler handler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case 0:
				// pm_dialv.setDialDegrees(0.7);
				break;
			default:
				break;
			}
		}
	};
	OnClickListener lis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.left_bt_iv:
				if (!FullscreenActivity.xproduct_content.isLeftLayoutVisible()) {
					FullscreenActivity.xproduct_content.initShowLeftState();
					FullscreenActivity.xproduct_content.scrollToLeftMenu();
				}
				break;

			default:
				break;
			}
		}
	};

	public View getView(Context context) {
		this.context = context;
		initView();
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initView();
		return view;
	}

	private void setOnclickListener() {
		leftBt_iv.setOnClickListener(lis);
		settingBt_iv.setOnClickListener(lis);
		shareBt_iv.setOnClickListener(lis);
	}

	private void findViewById() {
		view = View.inflate(context, R.layout.dil_view, null);
		leftBt_iv = (SmartImageView) view.findViewById(R.id.left_bt_iv);
		settingBt_iv = (SmartImageView) view.findViewById(R.id.setting_bt_iv);
		shareBt_iv = (SmartImageView) view.findViewById(R.id.share_bt_iv);
		pm_dialv = (PMDialView) view.findViewById(R.id.pm_dialv);
		hcho_dialv = (HCHODialView) view.findViewById(R.id.hcho_dialv);
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void initView() {
		findViewById();
		setOnclickListener();
		handler.sendEmptyMessage(0);
	}

}
