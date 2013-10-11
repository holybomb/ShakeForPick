package com.lihu.shakeforpick;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener
{
	class ViewHolder
	{
		ImageView img;
		TextView text;
	}

	ViewPager pager;
	ArrayList<View> pagerViews;
	ImageView shake, forward;
	private String[] imgs;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgs = getResources().getStringArray(R.array.index_imgs);
		pagerViews = new ArrayList<View>();
		for (int i = 0; i < imgs.length; i++)
		{
			ViewHolder holder = new ViewHolder();
			View child = LayoutInflater.from(this).inflate(R.layout.index_pager_item, null);
			holder = new ViewHolder();
			holder.img = (ImageView) child.findViewById(R.id.index_pager_item_bg);
			holder.text = (TextView) child.findViewById(R.id.index_pager_like_num);
			FinalBitmap.create(this).display(holder.img, imgs[i]);
			holder.text.setText("" + (1232 * i));
			child.setTag(holder);
			pagerViews.add(child);
		}
		ImgPagerAdapter adapter = new ImgPagerAdapter(this, pagerViews);
		shake = (ImageView) findViewById(R.id.main_shake_btn);
		shake.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				Intent i = new Intent();
				int selectId = pager.getCurrentItem();
				i.putExtra("url", imgs[selectId]);
				ViewHolder holder = (ViewHolder) pagerViews.get(selectId).getTag();
				i.putExtra("like", holder.text.getText());
				i.setClass(MainActivity.this, ShakeResultActivity.class);
				startActivity(i);
			}
		});
		pager = (ViewPager) findViewById(R.id.main_img_pager);
		pager.setAdapter(adapter);
		// 初始化传感器
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
	}

	public void onForwardClicker(View v)
	{
		startActivity(new Intent(this, TradeMainActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class ImgPagerAdapter extends PagerAdapter
	{
		Context context;
		ArrayList<View> pagerViews;

		public ImgPagerAdapter(Context context, ArrayList<View> pagerViews)
		{
			this.context = context;
			this.pagerViews = pagerViews;
		}

		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return pagerViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object)
		{
			((ViewPager) container).removeView(pagerViews.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position)
		{
			((ViewPager) container).addView(pagerViews.get(position));
			return pagerViews.get(position);
		}
	}

	// Sensor管理器
	private SensorManager mSensorManager = null;
	// 震动
	private Vibrator mVibrator = null;

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		int sensorType = event.sensor.getType();
		float[] values = event.values;
		if (sensorType == Sensor.TYPE_ACCELEROMETER)
		{
			if (Math.abs(values[0]) > 14 || Math.abs(values[1]) > 14 || Math.abs(values[2]) > 14)
			{
				mVibrator.vibrate(100);
				shake.performClick();
			}
		}
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop()
	{
		// TODO Auto-generated method stub
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		mSensorManager.unregisterListener(this);
		super.onPause();
	}
}
