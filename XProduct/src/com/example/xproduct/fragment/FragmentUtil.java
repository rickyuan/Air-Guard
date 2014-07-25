package com.example.xproduct.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xproduct.aplication.XProductData;
import com.example.xproduct.observer.MyObservable;
import com.hp.box.xproduct.R;

public class FragmentUtil {
	public static class ArrayFragment extends Fragment {

		int mNum;

		private List<Fragment> mList;

		public static int getNum() {
			return XProductData.VIEWPAGENUM;
		}

		public List<Fragment> getFgList() {
			mList = new ArrayList<Fragment>();
			mList.add(DialFg.newInstance());
			mList.add(CldFg.newInstance());
			mList.add(CvsFg.newInstance());
			return mList;
		}

		public static ArrayFragment newInstance(int num) {
			ArrayFragment array = new ArrayFragment();
			Bundle args = new Bundle();
			args.putInt("num", num);
			array.setArguments(args);
			return array;
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mNum = getArguments() != null ? getArguments().getInt("num") : 0;
			Log.e("ArrayFragment", "mNum Fragment create =" + mNum);
			System.out.println("mNum Fragment create =" + mNum);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			System.out.println("onCreateView = ");
			// 在这里加载每个 fragment的显示的 View
			View v = inflater.inflate(R.layout.viewpager_fl, container, false);
			Fragment ft = null;
			switch (mNum) {
			case 0:
				ft = DialFg.newInstance();
				break;
			case 1:
				ft = CldFg.newInstance();
				break;
			case 2:
				ft = CvsFg.newInstance();
				break;
			default:
				break;
			}
			FragmentTransaction ft1 = getFragmentManager().beginTransaction();
			ft1.add(R.id.pagers_fragment, ft);
			ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft1.commitAllowingStateLoss();
			return v;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			System.out.println("onActivityCreated = ");
			super.onActivityCreated(savedInstanceState);
		}

		@Override
		public void onDestroyView() {
			System.out.println(mNum + "mNumDestory");
			super.onDestroyView();
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
		}

	}
}
