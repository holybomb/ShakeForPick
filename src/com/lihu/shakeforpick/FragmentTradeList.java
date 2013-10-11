package com.lihu.shakeforpick;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link FragmentTradeList.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link FragmentTradeList#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class FragmentTradeList extends Fragment {
	public static final String TAG = FragmentTradeList.class.getCanonicalName();
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
	public static FragmentTradeList newInstance(Context context,
			Object... params) {
		FragmentTradeList fragment = new FragmentTradeList();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		fragment.mContext = context;
		return fragment;
	}

	public FragmentTradeList() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_trade_list, container,
				false);
		String[] urls = getResources().getStringArray(R.array.trade_imgs);
		ArrayList<ArrayList<String>> tradeListData = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 5; i++) {
			ArrayList<String> perLine = new ArrayList<String>();
			for (int j = 0; j < 4; j++) {
				if (i % 2 == 0) {
					if (j == 3)
						perLine.add(null);
					else
						perLine.add(urls[(i + j) % urls.length]);
				} else {
					if (j == 1)
						perLine.add(null);
					else
						perLine.add(urls[(i + j) % urls.length]);
				}
			}
			tradeListData.add(perLine);
		}
		TradeMainListAdapter adapter = new TradeMainListAdapter(tradeListData,
				mContext);
		tradeList = (ListView) v.findViewById(R.id.trade_main_list);
		tradeList.setAdapter(adapter);
		return v;
	}

	class TradeMainListAdapter extends BaseAdapter {
		ArrayList<ArrayList<String>> mList;
		Context mContext;
		FinalBitmap fb;

		class ViewHolder {
			ImageView left1, left2, right1, right2;
			LinearLayout left, right;
		}

		public TradeMainListAdapter(ArrayList<ArrayList<String>> mList,
				Context mContext) {
			super();
			this.mList = mList;
			this.mContext = mContext;
			fb = FinalBitmap.create(mContext);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ArrayList<String> urls = mList.get(position);
			ViewHolder holder = new ViewHolder();
			// if(convertView == null)
			{
				if (position % 2 != 0)
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.trade_main_list_item2, null);
				else
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.trade_main_list_item, null);
				holder.left = (LinearLayout) convertView
						.findViewById(R.id.trade_main_list_item_left);
				holder.right = (LinearLayout) convertView
						.findViewById(R.id.trade_main_list_item_right);
				holder.left1 = (ImageView) convertView
						.findViewById(R.id.trade_main_list_item_left1);
				holder.left2 = (ImageView) convertView
						.findViewById(R.id.trade_main_list_item_left2);
				holder.right1 = (ImageView) convertView
						.findViewById(R.id.trade_main_list_item_right1);
				holder.right2 = (ImageView) convertView
						.findViewById(R.id.trade_main_list_item_right2);
				convertView.setTag(holder);
			}
			// else
			// {
			// holder = (ViewHolder) convertView.getTag();
			// }
			if (!TextUtils.isEmpty(urls.get(0))) {
				fb.display(holder.left1, urls.get(0));
				holder.left1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentTradeDetail detail = FragmentTradeDetail
								.newInstance(TradeMainActivity.instance, "");
						TradeMainActivity.instance.show(detail);
					}
				});
			}

			if (!TextUtils.isEmpty(urls.get(1))) {
				fb.display(holder.left2, urls.get(1));
				holder.left2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentTradeDetail detail = FragmentTradeDetail
								.newInstance(TradeMainActivity.instance, "");
						TradeMainActivity.instance.show(detail);
					}
				});
			}

			if (!TextUtils.isEmpty(urls.get(2))) {
				fb.display(holder.right1, urls.get(2));
				holder.right1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentTradeDetail detail = FragmentTradeDetail
								.newInstance(TradeMainActivity.instance, "");
						TradeMainActivity.instance.show(detail);
					}
				});
			}

			if (!TextUtils.isEmpty(urls.get(3))) {
				fb.display(holder.right2, urls.get(3));
				holder.right2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentTradeDetail detail = FragmentTradeDetail
								.newInstance(TradeMainActivity.instance, "");
						TradeMainActivity.instance.show(detail);
					}
				});
			}
			return convertView;
		}

	}
}
