package com.tgs.mitra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment3 extends Fragment/*Fragment1*/ {
	private FragmentTabHost mTabHost;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("TEST Fragment3 onCreate");
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*mTabHost = new FragmentTabHost(getActivity());
		mTabHost.setup(getActivity(), getChildFragmentManager(),
				R.id.menu_settings);
		Bundle b = new Bundle();
		b.putString("key", "Simple");
		mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"),
				Fragment1.class, b);
		//
		b = new Bundle();
		b.putString("key", "Contacts");
		mTabHost.addTab(mTabHost.newTabSpec("contacts")
				.setIndicator("Contacts"), Fragment2.class, b);
		System.out.println("TEST Fragment3 onCreateView");*/
		
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout,
				null);
		/*return mTabHost;*/
		
		return v;
	}
}
