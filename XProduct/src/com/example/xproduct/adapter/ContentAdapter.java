package com.example.xproduct.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.xproduct.fragment.FragmentUtil.ArrayFragment;

public class ContentAdapter extends PagerAdapter {
	private FragmentManager mFragmentManager;

	public ContentAdapter(FragmentManager pFragmentManager) {
		this.mFragmentManager = pFragmentManager;
		ArrayFragment.getFgList();
	}

	public ArrayList<Fragment> getData() {
		return ArrayFragment.mList;
	}

	@Override
	public int getCount() {
		return ArrayFragment.mList.size();
	}

	public Fragment getItem(int position) {
		return ArrayFragment.mList.get(position);
	}

	@Override
	public boolean isViewFromObject(View view, Object o) {
		return view == o;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// mFragments.get(position).onPause();
		ArrayFragment.mList.get(position).setUserVisibleHint(false);
		container.removeView(ArrayFragment.mList.get(position).getView());
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = ArrayFragment.mList.get(position);
		if (!fragment.isAdded()) {
			FragmentTransaction ft = mFragmentManager.beginTransaction();
			ft.add(fragment, fragment.getClass().getSimpleName());
			ft.commit();
			mFragmentManager.executePendingTransactions();
		} else {
			// fragment.onResume();
		}
		fragment.setUserVisibleHint(true);

		if (fragment.getView().getParent() == null) {
			container.addView(fragment.getView());
		}

		return fragment.getView();
	}

}
