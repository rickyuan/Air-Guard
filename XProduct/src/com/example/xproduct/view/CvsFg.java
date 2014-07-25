package com.example.xproduct.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hp.box.xproduct.R;

public class CvsFg {

	private Context context;
	private View view;
	private RelativeLayout cvs_rl;

	public static CvsFg newInstance() {
		CvsFg fg = new CvsFg();
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
		view = View.inflate(context, R.layout.cvs_view, null);
		cvs_rl = (RelativeLayout) view.findViewById(R.id.cvs_rl);
	}

	private void initView() {
		findViewById();
		setOnclickListener();
		cvs_rl.addView(new SimpleGraph(context).getView());
	}

}
