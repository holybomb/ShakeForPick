package com.lihu.shakeforpick;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ShakeResultActivity extends Activity {

	TextView likeTxt;
	ImageButton homeBtn;
	ImageView infoImg,home;
	Button get,give,abort;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake_result);
		Intent i = getIntent();
		String url = i.getStringExtra("url");
		String like = i.getStringExtra("like");
		infoImg = (ImageView) findViewById(R.id.shake_result_info_img);
		FinalBitmap.create(this).display(infoImg, url);
		
		likeTxt = (TextView) findViewById(R.id.shake_result_like_num);
		likeTxt.setText(like);
		
	}
	public void onHomeBtnClicker(View v)
	{
		finish();
	}
	public void onGetBtnClicker(View v)
	{
		finish();
		startActivity(new Intent(this, GetRewardActivity.class));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shake_result, menu);
		return true;
	}

}
