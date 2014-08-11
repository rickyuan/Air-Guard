package com.example.xproduct.base;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import butterknife.ButterKnife;

public abstract class BaseFragmentActivity extends FragmentActivity {
	@Override
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		// TODO Auto-generated method stub
		return super.onCreateView(parent, name, context, attrs);
	}
}
