package com.example.xproduct.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hp.box.xproduct.R;

public class CvsFg extends Fragment {

	private Context context;
	private View view;

	public static CvsFg newInstance() {
		CvsFg fg = new CvsFg();
		return fg;
	}

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
		view = View.inflate(context, R.layout.cvs_view, null);
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
	}

}
