/**  
 * @author ningsj@shishike.com
 * @Title: SelectSysTypePost.java 
 * @Package com.internet.http.data.post 
 * @Description: TODO
 * @date 2016-1-11 上午10:50:03 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-11 上午10:50:03
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class SelectSysTypePost {
	public String typeDictParentId; // 类型父id
	
	public SelectSysTypePost(String typeDictParentId) {
		super();
		this.typeDictParentId = typeDictParentId;
	}
	
	public String getTypeDictParentId() {
		return typeDictParentId;
	}
	
	public void setTypeDictParentId(String typeDictParentId) {
		this.typeDictParentId = typeDictParentId;
	}
	
}
