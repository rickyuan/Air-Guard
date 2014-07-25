package com.example.xproduct.view;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.example.xproduct.FullscreenActivity;
import com.example.xproduct.aplication.XProductData;
import com.hp.box.xproduct.R;

public class LeftMenu {

	private Context context;
	private View view;
	private ImageView user_img;
	private LinearLayout xbox_lv, add_dv_ll, user_st_ll, user_pay_ll;
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
			case R.id.user_st_ll:

				break;
			case R.id.user_pay_ll:

				break;
			case R.id.add_dv_ll:
				handler.sendEmptyMessage(ADDDVINFOVIEW);
				break;
			default:
				break;
			}
		}
	};

	private void initView() {
		findViewById();
		view.setLayoutParams(new RelativeLayout.LayoutParams(
				XProductData.LEFTVIEWWIDTH, LayoutParams.FILL_PARENT));
		setOnclickListener();
	}

	private void setOnclickListener() {
		add_dv_ll.setOnClickListener(lis);
		user_st_ll.setOnClickListener(lis);
		user_pay_ll.setOnClickListener(lis);
	}

	private void findViewById() {
		view = View.inflate(context, R.layout.left_menu, null);
		user_img = (ImageView) view.findViewById(R.id.user_img);
		xbox_lv = (LinearLayout) view.findViewById(R.id.xbox_lv);
		add_dv_ll = (LinearLayout) view.findViewById(R.id.add_dv_ll);
		user_st_ll = (LinearLayout) view.findViewById(R.id.user_st_ll);
		user_pay_ll = (LinearLayout) view.findViewById(R.id.user_pay_ll);
	}

}
