package com.internet.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.conn.util.InetAddressUtils;

/**
 * 网络相关工具类
 * 
 * @date 2014-8-13
 * @author declan.z(declan.zhang@gmail.com)
 * 
 */
public class NetWorkUtil {

	/**
	 * 获取本地IPv4地址
	 * 
	 * @return
	 */
	public static String getLocalIpv4Address() {
		try {
			String ipv4;
			ArrayList<NetworkInterface> mylist = Collections
					.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface ni : mylist) {
				ArrayList<InetAddress> ialist = Collections.list(ni
						.getInetAddresses());
				for (InetAddress address : ialist) {
					if (!address.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(ipv4 = address
									.getHostAddress())) {
						return ipv4;
					}
				}
			}
		} catch (SocketException ex) {
		}
		return null;
	}
}
