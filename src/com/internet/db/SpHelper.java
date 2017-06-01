package com.internet.db;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 不影响以前的数据单独写了一个, 主要用于存放当前登录商户的一些简单配置.
 * 
 * @date 2014-8-29
 * @author declan.z(declan.zhang@gmail.com)
 * 
 */
public class SpHelper {

	private static SpHelper mInstance;

	private Context mContext;

	public static final String SP_NAME = "yzw-sp";

	public static final String MOBILE = "mobile";

	public static final String HEAD_PATH1 = "head_path";

	public static final String SHAKE_FLAG = "shake_flag";

	public static final String VOICE_FLAG = "voice_flag";

	public static final String MSHAKE_FLAG = "mshake";

	public static final String MVOICEO_FLAG = "mVoiceo";

	public static final String SIGN = "sign";

	public static final String DRIVER_ID = "driver_id";
	
	public static final String USER_TYPE = "user_type";
	
	public static final String USER_INFO = "user_info";

	private SpHelper() {
	}

	public synchronized static SpHelper getDefault() {
		if (mInstance == null) {
			mInstance = new SpHelper();
		}
		return mInstance;
	}

	public void init(Application context) {
		mContext = context;
	}

	public SpHelper removeKey(String key) {
		getSharedPreferencesEditor().remove(key).commit();
		return this;
	}

	public SpHelper putString(String key, String value) {
		getSharedPreferencesEditor().putString(key, value).commit();
		return this;
	}

	public SpHelper putInt(String key, int value) {
		getSharedPreferencesEditor().putInt(key, value).commit();
		return this;
	}

	public SpHelper putBoolean(String key, boolean value) {
		getSharedPreferencesEditor().putBoolean(key, value).commit();
		return this;
	}

	public SpHelper putLong(String key, long value) {
		getSharedPreferencesEditor().putLong(key, value).commit();
		return this;
	}

	public String getString(String key, String defValue) {
		return getSharedPreferences().getString(key, defValue);
	}

	public String getString(String key) {
		return getString(key, null);
	}

	public int getInt(String key, int defValue) {
		return getSharedPreferences().getInt(key, defValue);
	}

	public int getInt(String key) {
		return getInt(key, 0);
	}

	public long getLong(String key, long defValue) {
		return getSharedPreferences().getLong(key, defValue);
	}

	public long getLong(String key) {
		return getLong(key, 0);
	}

	public boolean getBoolean(String key, boolean defValue) {
		return getSharedPreferences().getBoolean(key, defValue);
	}

	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	private SharedPreferences getSharedPreferences() {
		return mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	}

	private Editor getSharedPreferencesEditor() {
		return mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
				.edit();
	}

}
