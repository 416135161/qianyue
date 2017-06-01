package com.internet.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	private static Toast sToastView;

	public static void showLongToast(Context context, String msg) {
		showToast(context, msg, Toast.LENGTH_LONG);
	}

	public static void showLongToast(Context context, int msgRes) {
		showToast(context, msgRes, Toast.LENGTH_LONG);
	}

	public static void showShortToast(Context context, String msg) {
	    showToast(context, msg, Toast.LENGTH_SHORT);
	}

	public static void showShortToast(Context context, int msgRes) {
	    showToast(context, msgRes, Toast.LENGTH_SHORT);
	}

	public static void showToast(Context context, String msg, int duration) {
		if (sToastView != null) {
			sToastView.cancel();
		}
		sToastView = Toast.makeText(context, msg, duration);
		sToastView.show();
	}

	public static void showToast(Context context, int msgRes, int duration) {
		if (sToastView != null) {
			sToastView.cancel();
		}
		sToastView = Toast.makeText(context, msgRes, duration);
		sToastView.show();
	}

	public static void clearToast(){
		if (sToastView != null) {
			sToastView.cancel();
		}
	}
}
