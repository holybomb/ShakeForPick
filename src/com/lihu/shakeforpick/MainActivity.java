package com.lihu.shakeforpick;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	class ViewHolder
	{
		ImageView img;
		TextView text;
	}
	ViewPager pager;
	ArrayList<View> pagerViews;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String[] imgs = getResources().getStringArray(R.array.index_imgs);
		pagerViews = new ArrayList<View>();
		for(int i =0;i<imgs.length;i++)
		{
			ViewHolder holder = new ViewHolder();
			View child = LayoutInflater.from(this).inflate(R.layout.index_pager_item, null);
			holder = new ViewHolder();
			holder.img = (ImageView) child.findViewById(R.id.index_pager_item_bg);
			holder.text = (TextView) child.findViewById(R.id.index_pager_like_num);
			FinalBitmap.create(this).display(holder.img,imgs[i]);
			holder.text.setText(""+(1232*i));
			pagerViews.add(child);
		}
		ImgPagerAdapter adapter = new ImgPagerAdapter(this, pagerViews);
		pager = (ViewPager) findViewById(R.id.main_img_pager);
		pager.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	class ImgPagerAdapter extends PagerAdapter
	{
		
		Context context;
		ArrayList<View> pagerViews;
		public ImgPagerAdapter(Context context,ArrayList<View> pagerViews) {
			this.context = context;  
			this.pagerViews = pagerViews;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pagerViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(pagerViews.get(position));
		}
		
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(pagerViews.get(position));
			return pagerViews.get(position);
		}
	}
}
