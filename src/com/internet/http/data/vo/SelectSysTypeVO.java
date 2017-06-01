/**  
 * @author ningsj@shishike.com
 * @Title: SelectSysTypeVO.java 
 * @Package com.internet.http.data.vo 
 * @Description: TODO
 * @date 2016-1-11 上午10:52:44 
 * @version V1.0  
 */
package com.internet.http.data.vo;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-11 上午10:52:44
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class SelectSysTypeVO {
	String typeDictId; // 字典表主键id
	
	String typeDictCode;// 字典code
	
	String typeDictName;// 字典名称
	
	String parentTypeDictId;// 父类id
	
	String typeDictStatus;// 状态
	
	String typeDictSeqNo;// 序号
	
	String createTime;// 创建时间
	
	public String getTypeDictId() {
		return typeDictId;
	}
	
	public void setTypeDictId(String typeDictId) {
		this.typeDictId = typeDictId;
	}
	
	public String getTypeDictCode() {
		return typeDictCode;
	}
	
	public void setTypeDictCode(String typeDictCode) {
		this.typeDictCode = typeDictCode;
	}
	
	public String getTypeDictName() {
		return typeDictName;
	}
	
	public void setTypeDictName(String typeDictName) {
		this.typeDictName = typeDictName;
	}
	
	public String getParentTypeDictId() {
		return parentTypeDictId;
	}
	
	public void setParentTypeDictId(String parentTypeDictId) {
		this.parentTypeDictId = parentTypeDictId;
	}
	
	public String getTypeDictStatus() {
		return typeDictStatus;
	}
	
	public void setTypeDictStatus(String typeDictStatus) {
		this.typeDictStatus = typeDictStatus;
	}
	
	public String getTypeDictSeqNo() {
		return typeDictSeqNo;
	}
	
	public void setTypeDictSeqNo(String typeDictSeqNo) {
		this.typeDictSeqNo = typeDictSeqNo;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
