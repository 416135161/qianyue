/**  
 * @author ningsj@shishike.com
 * @Title: CityVO.java 
 * @Package com.internet.yousheng.http.data.vo 
 * @Description: TODO
 * @date 2015-4-26 上午8:59:25 
 * @version V1.0  
*/
package com.internet.http.data.vo;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-26 上午8:59:25
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class CityVO {
	String cityid;
	String cityname;
	String provinceid;
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public CityVO() {
		// TODO Auto-generated constructor stub
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	
	
}
