package com.internet.http.data.post;

public class GetSiteListPost {
	public String keyword; // 关键字

	public String areaCode;// 区域编码

	public String areaName;// 区域名称

	public String cityCode;// 城市编码

	public String cityName;// 城市名称

	public String longitude;// 经度

	public String latitude;// 维度

	public String maxDistance;// 最大距离

	public String sortObject = "distance:asc";// 排序字段 可以有多个，如：
	// price:asc,priceSD:desc，其中：asc：表示升序；desc：表示倒序

	public String pageNo;// 起始页码

	public String pageSize;// 每页数据数

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(String maxDistance) {
		this.maxDistance = maxDistance;
	}

	public String getSortObject() {
		return sortObject;
	}

	public void setSortObject(String sortObject) {
		this.sortObject = sortObject;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
