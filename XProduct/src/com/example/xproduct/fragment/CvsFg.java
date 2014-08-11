package com.example.xproduct.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.InjectView;

import com.example.xproduct.FullscreenActivity;
import com.example.xproduct.tool.image.SmartImageView;
import com.example.xproduct.view.SimpleGraph;
import com.hp.box.xproduct.R;

public class CvsFg extends Fragment {

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
	private RelativeLayout cvs_rl;

	public static CvsFg newInstance() {
		CvsFg fg = new CvsFg();
		return fg;
	}

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
		view = View.inflate(context, R.layout.cvs_view, null);
		cvs_rl = (RelativeLayout) view.findViewById(R.id.cvs_rl);
		leftBt_iv = (SmartImageView) view.findViewById(R.id.left_bt_iv);
		settingBt_iv = (SmartImageView) view.findViewById(R.id.setting_bt_iv);
		shareBt_iv = (SmartImageView) view.findViewById(R.id.share_bt_iv);
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
		cvs_rl.addView(new SimpleGraph(context).getView());
	}

}
