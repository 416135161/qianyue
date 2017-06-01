/**  
 * @author ningsj@shishike.com
 * @Title: AreaVO.java 
 * @Package com.internet.http.data.vo 
 * @Description: TODO
 * @date 2016-1-8 下午5:40:07 
 * @version V1.0  
 */
package com.internet.http.data.vo;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-8 下午5:40:07
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
public class AreaVO {
	String regionId;// 区域id

	String name;// 全称

	String parentId;// 父级id

	String parentName;// 父级名称

	String shortName;// 简称

	int levelType;// 等级类型

	String cityCode;// 区域编码

	String zipCode;// 国际编码

	String mergerName;// 全名字

	Double lng;// 纬度

	Double lat;// 经度

	String pinyin;// 拼音

	String createTime;// 创建数据时间

	public AreaVO() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getLevelType() {
		return levelType;
	}

	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setLevelType(int levelType) {
		this.levelType = levelType;
	}

}
