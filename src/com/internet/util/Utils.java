package com.internet.util;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

import com.internet.http.api.ApiException;

public class Utils {
	
	public static final char PAUSE = ',';
	
	public static final char WAIT = ';';
	
	public static final char WILD = 'N';
	
	/**
	 * True if c is ISO-LATIN characters 0-9, *, # , +,
	 * WILD, WAIT, PAUSE
	 */
	public final static boolean isNonSeparator(char c) {
		return (c >= '0' && c <= '9') || c == '*' || c == '#' || c == '+' || c == WILD || c == WAIT || c == PAUSE;
	}
	
	public static boolean isUserPasswordValid(String userPwd) {
		char[] cs = userPwd.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] >= '0' && cs[i] <= '9') {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 隐藏号码部分内容
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static String formatPhoneNumber(String phoneNumber) {
		char[] cs = phoneNumber.toCharArray();
		char[] res = new char[cs.length];
		for (int i = cs.length - 1, j = 0; i >= 0; i--, j++) {
			if (j >= 4 && j <= 7) {
				res[i] = '*';
			} else {
				res[i] = cs[i];
			}
		}
		return new String(res);
	}
	
	/**
	 * 判断是否为手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		// String pattern =
		// "(13[4-9]|15[7-9]|15[0-2]|18[7-8])[0-9]{8}$";
		// Pattern p = Pattern.compile(pattern);
		// Matcher m = p.matcher(mobile);
		// boolean flag = m.matches();
		// return flag;
		String pattern = "^((\\+86)|(86))?0*(1)\\d{10}$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(mobile);
		boolean flag = m.matches();
		return flag;
	}
	
	/**
	 * 判断电话号码合法性
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isPhoneNumberValid(String number) {
		if (!isMobile(number)) {
			return isTelephoneValid(number);
		}
		return true;
	}
	
	/**
	 * 
	 * @Title:isUserNameValid
	 * @Description: ^[a-zA-Z]{1}[a-zA-Z0-9_]{3,15}$
	 * @param @param name
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isUserNameValid(String name) {
		String pattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]{0,15}$";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(name);
		
		boolean flag = m.matches();
		return flag;
	}
	
	/**
	 * 
	 * @Title:isTelephoneValid
	 * @Description:0\\d{2,3 \\d{7,8}
	 * @param @param name
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isTelephoneValid(String telephone) {
		String pattern = "0\\d{2,3}\\d{7,8}||\\d{7,8}";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(telephone);
		
		boolean flag = m.matches();
		return flag;
	}
	
	/**
	 * 
	 * @Title:isCustomerNameValid
	 * @Description:^([a-zA-Z]{1 
	 *                           [a-zA-Z0-9_]{0,15})|([\u0391
	 *                           - \uFFE5 ] { 2 , 8 } ) $
	 * @param @param name
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isCustomerNameValid(String name) {
		String pattern = "^([a-zA-Z]{1}[a-zA-Z0-9_]{0,15})|([\u0391-\uFFE5]{0,8})$";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(name);
		
		boolean flag = m.matches();
		return flag;
	}
	
	public static String stripSeparators(String phoneNumber) {
		if (phoneNumber == null) {
			return null;
		}
		int len = phoneNumber.length();
		StringBuilder ret = new StringBuilder(len);
		
		for (int i = 0; i < len; i++) {
			char c = phoneNumber.charAt(i);
			if (isNonSeparator(c)) {
				ret.append(c);
			}
		}
		
		return ret.toString();
	}
	
	public static String quoteString(String s) {
		if (s == null) {
			return null;
		}
		if (!s.matches("^\".*\"$")) {
			return "\"" + s + "\"";
		} else {
			return s;
		}
	}
	
	public static String genUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 金额默认为小数点 后两位 例如 10.00元 12.10 转化后变成 12.00
	 */
	@Deprecated
	public static String transfer(String param) {
		double tmp = Math.floor(Double.parseDouble(param));
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(tmp);
	}
	
	/**
	 * <pre>
	 * 类名称:Utils.java 
	 * 方法名称:isNum
	 * 参数说明: 判断字符串是否为数字
	 *     @param str
	 *     @return
	 * 返回类型:
	 *     @return boolean
	 * </pre>
	 */
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	/**
	 * 金额默认两位小数
	 * 
	 */
	public static String transferDot2(String param) {
		if (TextUtils.isEmpty(param))
			param = "0";
		double tmp = Double.parseDouble(param);
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(tmp);
	}
	
	/**
	 * <pre>
	 * 类名称:Utils.java 
	 * 方法名称:getCharNum
	 * 参数说明:
	 *     @param str
	 *     @return
	 * 返回类型:
	 *     @return int  为英文字符和数字  
	 * 方法说明:
	 *   创建历史:
	 *      创建日期:2014-1-16 上午11:25:58
	 *      创建人员:wangshuang
	 *   修改历史:
	 *      修改人员:
	 *      修改日期:
	 *      修改目的:
	 * </pre>
	 */
	public static int getCharNum(String str) {
		int[] count = new int[2];
		// 判断每个字符
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((chars[i] >= 65 && chars[i] <= 90) || (chars[i] >= 97 && chars[i] <= 122)) {
				// 英文字符
				count[0]++;
			} else if (chars[i] >= 48 && chars[i] <= 57) {
				// 数字
				count[0]++;
			} else if (chars[i] == '!' || chars[i] == '[' || chars[i] == ']' || chars[i] == '-' || chars[i] == '|'
				|| chars[i] == '[') {
				count[0]++;
			} else {
				count[1]++;
			}
		}
		return count[0];
	}
	
	/**
	 * <pre>
	 * 类名称:Utils.java 
	 * 方法名称:float2String
	 * 参数说明:
	 *     @param f
	 *     @return
	 * 返回类型:
	 *     @return String
	 * 方法说明:
	 *   创建历史:
	 *      创建日期:Jan 19, 2014 6:38:09 PM
	 *      创建人员:noway
	 *   修改历史:
	 *      修改人员:
	 *      修改日期:
	 *      修改目的:
	 * </pre>
	 */
	public static String float2String(float f) {
		DecimalFormat format = new DecimalFormat("##0.0");
		return format.format(f);
	}
	
	/**
	 * <pre>
	 * 类名称:Utils.java 
	 * 方法名称:isToday
	 * 参数说明:
	 *     @param time 一个表示时间的long值
	 *     @return
	 * 返回类型:
	 *     @return boolean  是否是今天的时间，true是，false 不是
	 * 业务处理描述:
	 * 		可见性原因:需要被其他应用调用
	 * 		目的:方法说明.
	 * 		适用的前提条件:
	 * 		后置条件:
	 * 例外处理:无
	 * 已知问题:
	 *   创建历史:
	 *      创建日期:2014年5月10日 下午1:31:00
	 *      创建人员:z_dlong
	 *   修改历史:
	 *      修改人员:
	 *      修改日期:
	 *      修改目的:
	 * 调用的例子:
	 * 是否建议使用:
	 * </pre>
	 */
	public static boolean isToday(long time) {
		if (time > getActiveDate()[0] && time < getActiveDate()[1]) {
			return true;
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 类名称:Utils.java 
	 * 方法名称:getActiveDate
	 * 参数说明:
	 *     @return
	 * 返回类型:
	 *     @return Long[]  当天时间long的上下限
	 * 业务处理描述:
	 * 		可见性原因:需要被其他应用调用
	 * 		目的:方法说明.
	 * 		适用的前提条件:
	 * 		后置条件:
	 * 例外处理:无
	 * 已知问题:
	 *   创建历史:
	 *      创建日期:2014年5月10日 下午1:30:48
	 *      创建人员:z_dlong
	 *   修改历史:
	 *      修改人员:
	 *      修改日期:
	 *      修改目的:
	 * 调用的例子:
	 * 是否建议使用:
	 * </pre>
	 */
	public static Long[] getActiveDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		Calendar cal = sdf.getCalendar();
		try {
			cal.setTime(sdf.parse(sdf.format(System.currentTimeMillis())));
		} catch (Exception e) {
		}
		Long[] res = new Long[2];
		res[0] = cal.getTimeInMillis();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		res[1] = cal.getTimeInMillis();
		return res;
	}
	
	/**
	 * <pre>
	 * 类名称:Utils.java 
	 * 方法名称:getNumberTowDecimal
	 * 参数说明: 保留一位小数
	 *     @param price
	 *     @return
	 * 返回类型:
	 *     @return String
	 * 方法说明:
	 *   创建历史:
	 *      创建日期:2014-5-19 下午5:30:32
	 *      创建人员:wangshuang
	 *   修改历史:
	 *      修改人员:
	 *      修改日期:
	 *      修改目的:
	 * </pre>
	 */
	public static String getNumberTowDecimal(String price) {
		if ("0.0".equals(price)) {
			return "0";
		}
		return new DecimalFormat("#.0").format(Double.parseDouble(price));
	}
	
	public static Map<String, String> objectToMap(Object model) throws ApiException {
		Map<String, String> map = new HashMap<String, String>();
		Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
				System.out.println("attribute name:" + name);
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				String Value = null;
				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					String value = (String)field[j].get(model);
					if (value != null) {
						Value = value + "";
						System.out.println("attribute value:" + value);
					}
				}
				if (type.equals("class java.lang.Integer")) {
					Integer value = (Integer)field[j].get(model);
					if (value != null) {
						Value = value + "";
						System.out.println("attribute value:" + value);
					}
				}
				if (type.equals("class java.lang.Short")) {
					Short value = (Short)field[j].get(model);
					if (value != null) {
						Value = value + "";
						System.out.println("attribute value:" + value);
					}
				}
				if (type.equals("class java.lang.Double")) {
					Double value = (Double)field[j].get(model);
					if (value != null) {
						Value = value + "";
						System.out.println("attribute value:" + value);
					}
				}
				if (type.equals("class java.lang.Boolean")) {
					Boolean value = (Boolean)field[j].get(model);
					if (value != null) {
						Value = value + "";
						System.out.println("attribute value:" + value);
					}
				}
				if (type.equals("class java.util.Date")) {
					Date value = (Date)field[j].get(model);
					if (value != null) {
						Value = value.toLocaleString() + "";
						System.out.println("attribute value:" + value.toLocaleString());
					}
				}
				if (Value != null)
					map.put(name, Value);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ApiException("对象转map错误", e);
		}
		
		return map;
	}
}
