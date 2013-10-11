package com.lihu.shakeforpick;

import android.content.Context;
import android.util.TypedValue;

public class DensityUtil
{
	/**
	 * 根据手机的分辨率�?dp 的单�?转成�?px(像素)
	 */
	public static int dip2px(Context context, float dpValue)
	{
//		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
	}

	/**
	 * 根据手机的分辨率�?px(像素) 的单�?转成�?dp
	 */
	public static int px2dip(Context context, float pxValue)
	{
//		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue, context.getResources().getDisplayMetrics());
	}
	
}