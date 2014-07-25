package com.example.xproduct.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hp.box.xproduct.R;

public class DialFg {

	private Context context;
	private View view;
	private PMDialView pm_dialv;
	private HCHODialView hcho_dialv;
	private ImageView add;
	private LinearLayout dial_ll;
	private boolean flag = true;

	public static DialFg newInstance() {
		DialFg fg = new DialFg();
		return fg;
	}

	public View getView(Context context) {
		this.context = context;
		initView();
		return view;
	}

	private void setOnclickListener() {
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pm_dialv.setDialDegrees(num += 30);
				pm_dialv.setPercentageText(num + "%");
				hcho_dialv.setDialDegrees(num1 -= 30);
			}
		});
	}

	private void findViewById() {
		view = View.inflate(context, R.layout.dil_view, null);
		pm_dialv = (PMDialView) view.findViewById(R.id.pm_dialv);
		hcho_dialv = (HCHODialView) view.findViewById(R.id.hcho_dialv);
		add = (ImageView) view.findViewById(R.id.add);
		// dial_ll = (LinearLayout) view.findViewById(R.id.dial_ll);
	}

	private int num = 0;
	private int num1 = 0;
	Handler handler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case 0:

				break;
			default:
				break;
			}
		}
	};

	private void initView() {
		findViewById();
		setOnclickListener();
	}
}
