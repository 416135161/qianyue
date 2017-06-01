package com.internet.http;

import java.io.File;
import java.util.Map;

import android.text.TextUtils;

import com.internet.action.ActionToLogin;
import com.internet.http.api.ApiException;
import com.internet.util.JsonUtil;

import de.greenrobot.event.EventBus;

/**
 * http辅助类, 实现一个统一的认证及http基本配置
 * 
 * @date 2014-8-5
 * @author declan.z(declan.zhang@gmail.com)
 * 
 */
public class HttpHelper {

	public static final int TIMEOUT_READ = 15000;

	public static final int TIMEOUT_CONNECT = 5000;

	/**
	 * 各种授权认证
	 * 
	 * @param request
	 * @return
	 */
	public static HttpRequest auth(HttpRequest request) throws ApiException {
		// 如果有的话加在这里处理
		request.readTimeout(TIMEOUT_READ);
		request.connectTimeout(TIMEOUT_CONNECT);
		return request;
	}

	/**
	 * 发起调用处理结果
	 * 
	 * @param request
	 * @return
	 */
	public static String result(HttpRequest request) throws ApiException {

		int code = -1;
		try {

			String body = request.body();
			code = request.code();
			System.out.println("code :" + code);
			if (request.ok()) {

				return body;
			}

			// 继续处理其他状态码
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("网络连接错误", e);
		}
		throw new ApiException("未知的服务端错误:" + code);
	}

	public static String get(CharSequence url) throws ApiException {
		return result(auth(HttpRequest.get(url)));
	}

	public static String get(CharSequence url, boolean isEncode,
			Object... params) throws ApiException {
		return result(auth(HttpRequest.get(url, isEncode, params)));
	}

	public static String post(CharSequence url) throws ApiException {
		return result(auth(HttpRequest.post(url, true)));
	}

	public static String post(CharSequence url, Object... params)
			throws ApiException {
		HttpRequest request;
		try {
			request = HttpRequest.post(url);
			request = auth(request);
			if (params != null) {
				for (int i = 0; i < params.length / 2; i++) {
					request.form(params[i * 2], params[i * 2 + 1]);
				}
			}
			return result(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("网络连接错误", e);
		}
	}

	public static String post(CharSequence url, Map<String, String> map)
			throws ApiException {
		String temp = null;
		for (String key : map.keySet()) {
			temp += (key + ":");
			temp += (map.get(key) + " ");
		}
		System.out.println(url + ":" + temp);
		HttpRequest request;
		try {
			request = HttpRequest.post(url);
			request = auth(request);
			if (map != null) {
				for (String key : map.keySet()) {
					request.form(key, map.get(key));
				}
			}
			String response = result(request);
			String state = JsonUtil.getString(response, "state");
			if (!TextUtils.isEmpty(state) && TextUtils.equals(state, "888888")) {
				EventBus.getDefault().post(new ActionToLogin());
			}
			System.out.println("reponse:" + response);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("网络连接错误", e);
		}
	}

	public static String post(CharSequence url, Map<String, String> map,
			String photoPath, String photoKey) throws ApiException {
		String temp = null;
		for (String key : map.keySet()) {
			temp += (key + ":");
			temp += (map.get(key) + " ");
		}
		System.out.println(url + ":" + temp);
		HttpRequest request;
		try {
			request = HttpRequest.post(url);
			request = auth(request);
			if (map != null) {
				for (String key : map.keySet()) {
					request.part(key, map.get(key));
				}
			}
			if (photoPath != null)
				request.part(photoKey, getFileName(photoPath),
						"CommonsMultipartFile", new File(photoPath));
			String response = result(request);
			System.out.println("reponse:" + response);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("网络连接错误", e);
		}
	}

	public static String getFileName(String url) {
		return url.substring(url.lastIndexOf("/") + 1);
	}

	public static String post(CharSequence url, Map<String, String> map,
			Map<String, String> mapFile) throws ApiException {
		String temp = "";
		for (String key : map.keySet()) {
			temp += (key + ":");
			temp += (map.get(key) + " ");
		}
		System.out.println(url + ":" + temp);
		HttpRequest request;
		try {
			request = HttpRequest.post(url);
			request = auth(request);
			if (map != null) {
				for (String key : map.keySet()) {
					request.part(key, map.get(key));
					System.out.println("key:" + key + "value:" + map.get(key));
				}
			}
			if (mapFile != null) {
				for (String key : mapFile.keySet()) {
					String photoKey = key;
					String photoPath = mapFile.get(key);
					System.out.println("key:" + key + "value:"
							+ mapFile.get(key));
					request.part(photoKey, getFileName(photoPath),
							"CommonsMultipartFile", new File(photoPath));
				}
			}

			String response = result(request);
			System.out.println("reponse:" + response);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException("网络连接错误", e);
		}
	}

}
