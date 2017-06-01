/**  
 * @author ningsj@shishike.com
 * @Title: AreaSearchPost.java 
 * @Package com.internet.http.data.post 
 * @Description: TODO
 * @date 2016-1-8 下午5:50:56 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-8 下午5:50:56
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class AreaSearchPost {

	public String regionId; // 父区域id，默认中国，返回子一级信息

	public AreaSearchPost() {
		// TODO Auto-generated constructor stub
	}
	
	public AreaSearchPost(String regionId) {
		super();
		this.regionId = regionId;
	}



	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

}
