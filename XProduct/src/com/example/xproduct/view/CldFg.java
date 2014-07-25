package com.example.xproduct.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xproduct.observer.MyObservable;
import com.hp.box.xproduct.R;

public class CldFg {

	private Context context;
	private View view;

	public static CldFg newInstance() {
		CldFg fg = new CldFg();
		return fg;
	}

	public View getView(Context context) {
		this.context = context;
		initView();
		return view;
	}

	private void setOnclickListener() {

	}

	private void findViewById() {
		view = View.inflate(context, R.layout.cld_view, null);
	}

	private void initView() {
		findViewById();
		setOnclickListener();
	}
}
