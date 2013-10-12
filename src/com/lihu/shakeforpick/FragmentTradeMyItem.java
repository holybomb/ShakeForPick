package com.lihu.shakeforpick;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link FragmentTradeList.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link FragmentTradeMyItem#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class FragmentTradeMyItem extends Fragment
{
	public static final String TAG = FragmentTradeMyItem.class.getCanonicalName();
	ListView tradeList;
	Context mContext;

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
	public static FragmentTradeMyItem newInstance(Context context, Object... params)
	{
		FragmentTradeMyItem fragment = new FragmentTradeMyItem();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		fragment.mContext = context;
		return fragment;
	}

	public FragmentTradeMyItem()
	{
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_trade_list, container, false);
		String[] urls = getResources().getStringArray(R.array.my_item_imgs);
		String[] descs = getResources().getStringArray(R.array.my_item_descs);
		ArrayList<ArrayList<String>> tradeListData = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 5; i++)
		{
			ArrayList<String> perLine = new ArrayList<String>();
			for (int j = 0; j < 4; j++)
			{
				String title = "店铺" + j;
				String floor = j + "F";
				String url = urls[(i + j) % urls.length];
				String desc = descs[(i + j) % descs.length];
				perLine.add(title);
				perLine.add(floor);
				perLine.add(url);
				perLine.add(desc);
			}
			tradeListData.add(perLine);
		}
		TradeMainListAdapter adapter = new TradeMainListAdapter(tradeListData, mContext);
		tradeList = (ListView) v.findViewById(R.id.trade_main_list);
		tradeList.setAdapter(adapter);
		tradeList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
	            FragmentTradeDetail frag = FragmentTradeDetail.newInstance(mContext, new Boolean(false));
	            TradeMainActivity.instance.show(frag);
            }

		
		});
		return v;
	}

	class TradeMainListAdapter extends BaseAdapter
	{
		ArrayList<ArrayList<String>> mList;
		Context mContext;
		FinalBitmap fb;

		class ViewHolder
		{
			ImageView icon;
			TextView title, floor, desc;
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
			ArrayList<String> perLine = mList.get(position);
			ViewHolder holder = new ViewHolder();
			if (convertView == null)
			{
				convertView = LayoutInflater.from(mContext).inflate(R.layout.trade_my_item_item, null);
				convertView.setTag(holder);
				convertView.setBackgroundResource(android.R.drawable.list_selector_background);
				holder.title = (TextView) convertView.findViewById(R.id.my_item_title);
				holder.desc = (TextView) convertView.findViewById(R.id.my_item_desc);
				holder.icon = (ImageView) convertView.findViewById(R.id.my_item_icon);
				holder.floor = (TextView) convertView.findViewById(R.id.my_item_floor);
			} else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			String title = perLine.get(0);
			String floor = perLine.get(1);
			String url = perLine.get(2);
			String desc = perLine.get(3);
			holder.title.setText(title);
			holder.floor.setText(floor);
			holder.desc.setText(desc);
			FinalBitmap.create(mContext).display(holder.icon, url);
			return convertView;
		}
	}
}
