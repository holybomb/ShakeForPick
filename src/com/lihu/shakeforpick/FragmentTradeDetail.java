package com.lihu.shakeforpick;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link FragmentTradeList.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link FragmentTradeDetail#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
@SuppressLint("SetJavaScriptEnabled")
public class FragmentTradeDetail extends Fragment implements
		SensorEventListener {
	public static final String TAG = FragmentTradeDetail.class
			.getCanonicalName();
	Context mContext;
	private WebView webview;
	private ImageView shake;
	boolean isCanShake;
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment FragmentTradeList.
	 */
	// TODO: Rename and change types and number of parameters
	public static FragmentTradeDetail newInstance(Context context,
			Object... params) {
		FragmentTradeDetail fragment = new FragmentTradeDetail();
		Bundle args = new Bundle();
		if(params[0] instanceof Boolean)
			fragment.isCanShake = (Boolean) params[0];
		else
			fragment.isCanShake = true;
		fragment.setArguments(args);
		fragment.mContext = context;
		return fragment;
	}

	public FragmentTradeDetail() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {

		}
		// 初始化传感器
		mSensorManager = (SensorManager)mContext.getSystemService(mContext.SENSOR_SERVICE);
		mVibrator = (Vibrator)mContext.getSystemService(mContext.VIBRATOR_SERVICE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_trade_detail, container,
				false);
		webview = (WebView) v.findViewById(R.id.trade_detail_web);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl("http://a.m.tmall.com/i14005708808.htm");
		shake = (ImageView) v.findViewById(R.id.trade_detail_shake);
		if(isCanShake)
		{
			shake.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent();
					String title ="恭喜获得优惠劵一张";
					String desc = "优惠劵优惠劵优惠劵优惠劵优惠劵优惠劵优惠劵优惠劵";
					String url = "http://t1.baidu.com/it/u=3257871685,4053540849&fm=90&gp=0.jpg";
					i.putExtra("title", title);
					i.putExtra("desc", desc);
					i.putExtra("url", url);
					i.setClass(mContext, GetRewardActivity.class);
					startActivity(i);
				}
			});
		}
		else
		{
			shake.setVisibility(View.GONE);
		}
		return v;
	}
	// Sensor绠＄悊鍣�
	private SensorManager mSensorManager = null;
	// 闇囧姩
	private Vibrator mVibrator = null;

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		int sensorType = event.sensor.getType();
		float[] values = event.values;
		if (sensorType == Sensor.TYPE_ACCELEROMETER) {
			if (Math.abs(values[0]) > 14 || Math.abs(values[1]) > 14
					|| Math.abs(values[2]) > 14) {
				mVibrator.vibrate(100);
				shake.performClick();
			}
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		mSensorManager.unregisterListener(this);
		super.onPause();
	}
}
