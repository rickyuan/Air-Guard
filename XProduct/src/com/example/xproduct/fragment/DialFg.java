package com.example.xproduct.fragment;

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

public class DialFg extends Fragment {

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

	}

	private void findViewById() {
		view = View.inflate(context, R.layout.dil_view, null);
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
