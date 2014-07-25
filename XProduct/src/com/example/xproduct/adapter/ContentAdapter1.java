package com.example.xproduct.adapter;

import java.util.List;

import com.example.xproduct.fragment.FragmentUtil.ArrayFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ContentAdapter1 extends PagerAdapter {
	List<View> mList;

	public ContentAdapter1(FragmentManager fm, List<View> mList) {
		this.mList = mList;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(mList.get(position));
		return mList.get(position);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(mList.get(position));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
}
