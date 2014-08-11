package com.example.xproduct.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xproduct.aplication.XProductData;
import com.example.xproduct.tool.image.SmartImageView;
import com.hp.box.xproduct.R;

public class DvInfoView extends RelativeLayout {

	private Handler handler;
	private Context context;
	private TextView dv_name_tv;
	private SmartImageView dv_iv_bl;
	View dv_info_v;

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
			dv_iv_bl.setSelected(true);
		}
	}

	public void cancelSeleted() {
		if (dv_iv_bl != null) {
			dv_iv_bl.setSelected(false);
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
		dv_info_v.setOnClickListener(lis);
	}

	private OnClickListener lis = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.dv_info_v:
				Message ms = new Message();
				ms.what = LeftMenu.UPDATADVLISTSTATE;
				ms.obj = arg0.getTag();
				handler.sendMessage(ms);
				break;

			default:
				break;
			}
		}
	};

	private void findViewById() {
		dv_info_v = inflate(context, R.layout.dv_info_view, null);
		this.addView(dv_info_v);
		dv_info_v.setLayoutParams(new RelativeLayout.LayoutParams(
				XProductData.LEFTVIEWWIDTH, LayoutParams.WRAP_CONTENT));
		dv_name_tv = (TextView) dv_info_v.findViewById(R.id.dv_name_tv);
		dv_iv_bl = (SmartImageView) dv_info_v.findViewById(R.id.dv_iv_bl);
		dv_iv_bl.setTag(DvNm);
		dv_name_tv.setText(DvNm);
	}
}
