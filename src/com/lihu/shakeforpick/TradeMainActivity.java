package com.lihu.shakeforpick;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class TradeMainActivity extends Activity
{
	ListView tradeList;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trade_main);
		String[] urls = getResources().getStringArray(R.array.index_imgs);
		ArrayList<ArrayList<String>> tradeListData = new ArrayList<ArrayList<String>>();
		for(int i =0;i<5;i++)
		{
			ArrayList<String> perLine = new ArrayList<String>();
			for(int j =0;j<4;j++)
			{
				if(i %2==0)
				{
					if(j==3)
						perLine.add(null);
					else
						perLine.add(urls[j]);
				}
				else
				{
					if(j==1)
						perLine.add(null);
					else
						perLine.add(urls[j]);
				}
			}
			tradeListData.add(perLine);
		}
		TradeMainListAdapter adapter = new TradeMainListAdapter(tradeListData, this);
		tradeList = (ListView) findViewById(R.id.trade_main_list);
		tradeList.setAdapter(adapter);
	}
	public void onHomeBtnClicker(View v)
	{
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trade_main, menu);
		return true;
	}
	
	class TradeMainListAdapter extends BaseAdapter
	{
		ArrayList<ArrayList<String>> mList;
		Context mContext;
		FinalBitmap fb;
		class ViewHolder
		{
			ImageView left1,left2,right1,right2;
			LinearLayout left,right;
		}
		public TradeMainListAdapter(ArrayList<ArrayList<String>> mList, Context mContext)
        {
	        super();
	        this.mList = mList;
	        this.mContext = mContext;
	        fb = FinalBitmap.create(mContext);
        }

		@Override
        public int getCount()
        {
	        // TODO Auto-generated method stub
	        return mList.size();
        }

		@Override
        public Object getItem(int position)
        {
	        // TODO Auto-generated method stub
	        return null;
        }

		@Override
        public long getItemId(int position)
        {
	        // TODO Auto-generated method stub
	        return 0;
        }

		@Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
			ArrayList<String> urls = mList.get(position);
			ViewHolder holder = new ViewHolder();
	        //if(convertView == null)
	        {
	        	if(position%2 != 0)
	        		convertView = LayoutInflater.from(mContext).inflate(R.layout.trade_main_list_item2, null);
	        	else
	        		convertView = LayoutInflater.from(mContext).inflate(R.layout.trade_main_list_item, null);
	        	holder.left = (LinearLayout) convertView.findViewById(R.id.trade_main_list_item_left);
	        	holder.right = (LinearLayout) convertView.findViewById(R.id.trade_main_list_item_right);
	        	holder.left1 = (ImageView) convertView.findViewById(R.id.trade_main_list_item_left1);
	        	holder.left2 = (ImageView) convertView.findViewById(R.id.trade_main_list_item_left2);
	        	holder.right1 = (ImageView) convertView.findViewById(R.id.trade_main_list_item_right1);
	        	holder.right2 = (ImageView) convertView.findViewById(R.id.trade_main_list_item_right2);
	        	convertView.setTag(holder);
	        }
//	        else
//	        {
//	        	holder = (ViewHolder) convertView.getTag();
//	        }
	        if(!TextUtils.isEmpty(urls.get(0)))
	        {
	        	fb.display(holder.left1, urls.get(0));
	        }
	        
	        if(!TextUtils.isEmpty(urls.get(1)))
	        {
	        	fb.display(holder.left2, urls.get(1));
	        }
	        
	        if(!TextUtils.isEmpty(urls.get(2)))
	        {
	        	fb.display(holder.right1, urls.get(2));
	        }

	        if(!TextUtils.isEmpty(urls.get(3)))
	        {
	        	fb.display(holder.right2, urls.get(3));
	        }
	        return convertView;
        }
		
	}
}
