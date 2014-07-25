package com.example.xproduct.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xproduct.aplication.XProductData;
import com.hp.box.xproduct.R;

public class DvInfoView extends RelativeLayout {

	private Handler handler;
	private Context context;
	private TextView dv_name_tv;
	private CheckBox dv_iv_bl;

	private String DvNm = "";

	public DvInfoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public DvInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DvInfoView(Context context, Handler handler, String DvNm) {
		super(context);
		this.handler = handler;
		this.context = context;
		this.DvNm = DvNm;
		initView();
	}

	public void setSeleted() {
		if (dv_iv_bl != null) {
			dv_iv_bl.setChecked(true);
		}
	}

	public void cancelSeleted() {
		if (dv_iv_bl != null) {
			dv_iv_bl.setChecked(false);
		}
	}

	public String getDvName() {
		return DvNm;
	}

	private void initView() {
		findViewById();
		setOnclickListener();
	}

	private void setOnclickListener() {
		dv_iv_bl.setOnCheckedChangeListener(chList);
	}

	private OnCheckedChangeListener chList = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				Message ms = new Message();
				ms.what = LeftMenu.UPDATADVLISTSTATE;
				ms.obj = buttonView.getTag();
				handler.sendMessage(ms);
			}
		}
	};

	private void findViewById() {
		View view = inflate(context, R.layout.dv_info_view, null);
		this.addView(view);
		view.setLayoutParams(new RelativeLayout.LayoutParams(
				XProductData.LEFTVIEWWIDTH, LayoutParams.WRAP_CONTENT));
		dv_name_tv = (TextView) view.findViewById(R.id.dv_name_tv);
		dv_iv_bl = (CheckBox) view.findViewById(R.id.dv_iv_bl);
		dv_iv_bl.setTag(DvNm);
		dv_name_tv.setText(DvNm);
	}
}
