package com.lihu.shakeforpick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

public class TradeMainActivity extends FragmentActivity
{
	FrameLayout pager;
	// private TradePagerAdapter adapter;
	public static TradeMainActivity instance;

	public TradeMainActivity()
	{
		instance = this;
	}

	public void show(Fragment fragment)
	{
		getSupportFragmentManager().beginTransaction().replace(R.id.trade_main_pager, fragment).commit();
		// adapter = new
		// TradePagerAdapter(getSupportFragmentManager(),fragment);
		// pager = (ViewPager) findViewById(R.id.trade_main_pager);
		// pager.setAdapter(adapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trade_main);
		pager = (FrameLayout) findViewById(R.id.trade_main_pager);
		FragmentTradeList list = FragmentTradeList.newInstance(this, "");
		show(list);
	}

	@Override
	public void onBackPressed()
	{
		FragmentTradeList list = FragmentTradeList.newInstance(this, "");
		show(list);
	}

	public void onHomeBtnClicker(View v)
	{
		finish();
	}

	public void onMyItemBtnClicker(View v)
	{
		FragmentTradeMyItem frag = FragmentTradeMyItem.newInstance(instance, "");
		show(frag);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trade_main, menu);
		return true;
	}
	// class TradePagerAdapter extends FragmentPagerAdapter
	// {
	// private Fragment curFragment;
	// public TradePagerAdapter(FragmentManager fm ,Fragment fragment) {
	// super(fm);
	// curFragment = fragment;
	// }
	//
	// @Override
	// public Fragment getItem(int position) {
	// return curFragment;
	// }
	// @Override
	// public int getCount() {
	// return 2;
	// }
	//
	// public Fragment getCurFragment() {
	// return curFragment;
	// }
	//
	// public void setCurFragment(Fragment curFragment) {
	// this.curFragment = curFragment;
	// }
	//
	// }
}
