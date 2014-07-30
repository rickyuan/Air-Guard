package com.example.xproduct.adapter;

import java.util.List;

import com.example.xproduct.fragment.FragmentUtil.ArrayFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ContentAdapter extends FragmentStatePagerAdapter {
	// List<Object> mList;

	public ContentAdapter(FragmentManager fm, List<Object> mList) {
		super(fm);
		// this.mList = mList;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		System.out.print("getItem arg0 = " + arg0);
		// return (Fragment) mList.get(arg0);
		return ArrayFragment.newInstance(arg0);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}

	@Override
	public Object instantiateItem(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		System.out.print("instantiateItem arg1 = " + arg1);
		// return mList.get(arg1);
		return ArrayFragment.newInstance(arg1);
	}

}
