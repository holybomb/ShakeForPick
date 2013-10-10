package com.lihu.shakeforpick;

import net.tsz.afinal.FinalBitmap;

import com.lihu.shakeforpick.MainActivity.ViewHolder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ShakeResultActivity extends Activity {

	TextView likeTxt;
	ImageView infoImg;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shake_result, menu);
		return true;
	}

}
