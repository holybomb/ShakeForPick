package com.lihu.shakeforpick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class GetRewardActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_reward);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_reward, menu);
		return true;
	}
	
	public void onHomeBtnClicker(View v)
	{
		finish();
	}
}
