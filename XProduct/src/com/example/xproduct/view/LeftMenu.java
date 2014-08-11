package com.example.xproduct.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.InjectView;

import com.example.xproduct.aplication.XProductData;
import com.example.xproduct.tool.qq.Utils;
import com.hp.box.xproduct.R;

public class LeftMenu {

	private Context context;
	View view;
	@InjectView(R.id.login_register_tv)
	TextView login_register_tv;
	@InjectView(R.id.add_dv_tv)
	TextView add_dv_tv;
	@InjectView(R.id.about_us_tv)
	TextView about_us_tv;
	@InjectView(R.id.user_buy_tv)
	TextView user_buy_tv;
	@InjectView(R.id.xbox_lv)
	LinearLayout xbox_lv;
	public static final int ADDDVINFOVIEW = 1, UPDATADVLISTSTATE = 2;

	public LeftMenu(Context context) {
		this.context = context;
		initView();
	}

	public View getView() {
		return view;
	}

	Handler handler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case ADDDVINFOVIEW:
				if (xbox_lv != null) {
					DvInfoView view = new DvInfoView(context, handler,
							"My Home");
					xbox_lv.addView(view);
				}
				break;
			case UPDATADVLISTSTATE:
				String DvNm = (String) msg.obj;
				if (DvNm != null) {
					seleXboxItem(DvNm);
				}
				break;
			default:
				break;
			}
		}
	};

	private void seleXboxItem(String DvNm) {
		int num = xbox_lv.getChildCount();
		for (int i = 0; i < num; i++) {
			DvInfoView dvView = (DvInfoView) xbox_lv.getChildAt(i);
			String Nm = dvView.getDvName();
			if (Nm.equals(DvNm)) {
				dvView.setSeleted();
			} else {
				dvView.cancelSeleted();
			}
		}
	}

	private OnClickListener lis = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.login_register_tv:
				new Utils().initQQLoginCondition(context);
				new Utils().qqlogin();
				break;
			case R.id.about_us_tv:

				break;
			case R.id.user_buy_tv:

				break;
			case R.id.add_dv_tv:
				handler.sendEmptyMessage(ADDDVINFOVIEW);
				break;
			default:
				break;
			}
		}
	};

	private void initView() {
		view = View.inflate(context, R.layout.left_menu, null);
		view.setLayoutParams(new RelativeLayout.LayoutParams(
				XProductData.LEFTVIEWWIDTH, LayoutParams.FILL_PARENT));
		findViewById();
		setOnclickListener();
	}

	private void setOnclickListener() {
		login_register_tv.setOnClickListener(lis);
		add_dv_tv.setOnClickListener(lis);
		about_us_tv.setOnClickListener(lis);
		user_buy_tv.setOnClickListener(lis);
	}

	private void findViewById() {
		login_register_tv = (TextView) view
				.findViewById(R.id.login_register_tv);
		add_dv_tv = (TextView) view.findViewById(R.id.add_dv_tv);
		about_us_tv = (TextView) view.findViewById(R.id.about_us_tv);
		user_buy_tv = (TextView) view.findViewById(R.id.user_buy_tv);
		xbox_lv = (LinearLayout) view.findViewById(R.id.xbox_lv);
	}
}
