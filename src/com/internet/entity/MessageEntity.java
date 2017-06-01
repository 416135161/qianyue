package com.internet.entity;

import java.io.Serializable;

import com.internet.basic.AdapterData;

public class MessageEntity implements AdapterData, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long id; // 主键id
	String title; // 标题
	String type; // 消息类型 1:系统消息；2：练路消息；3：评价消息；4：预约消息
	String subType; // 子类型
	String status; // 状态
	long createTime; // 创建时间
	String orderid; // 订单id
	String userid; // 用户id
	String usertype; // 用户类型
	String content; // 消息内容

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
