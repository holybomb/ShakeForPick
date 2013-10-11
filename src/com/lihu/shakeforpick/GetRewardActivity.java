package com.lihu.shakeforpick;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GetRewardActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_reward);
		Intent i = getIntent();
		String title = i.getStringExtra("title");
		String url = i.getStringExtra("url");
		String desc = i.getStringExtra("desc");
		TextView titleTxt = (TextView) findViewById(R.id.get_reweard_title);
		titleTxt.setText(title);
		TextView descTxt = (TextView) findViewById(R.id.get_reweard_info);
		descTxt.setText(desc);
		ImageView img = (ImageView) findViewById(R.id.get_reweard_img);
		FinalBitmap.create(this).display(img, url);
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
