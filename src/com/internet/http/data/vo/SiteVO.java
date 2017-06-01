package com.internet.http.data.vo;

import java.io.Serializable;

import com.internet.basic.AdapterData;

public class SiteVO implements AdapterData, Serializable, Comparable<SiteVO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String id; // FieldType=class java.lang.Long、场地id
	public String siteName; // FieldType=class java.lang.String、场地名称
	public String siteProvinceCode; // FieldType=class java.lang.String、省编码
	public String siteCityCode; // FieldType=class java.lang.String、市编码
	public String siteAreaCode; // FieldType=class java.lang.String、区域编码
	public String siteProvinceName; // FieldType=class java.lang.String、省名称
	public String siteCityName; // FieldType=class java.lang.String、市名称
	public String siteAreaName; // FieldType=class java.lang.String、区域名称
	public String siteAddr; // FieldType=class java.lang.String、详细地址
	public String siteLongitude; // FieldType=class java.lang.Float、经度
	public String siteLatitude; // FieldType=class java.lang.Float、纬度
	public String siteStar; // FieldType=class java.lang.Integer、星级
	public String siteAvatar; // FieldType=class java.lang.String、场地图片
	public String createTime; // FieldType=class java.util.Date、创建日期
	public String siteStatus; // FieldType=class java.lang.String、状态
	public double distance;
	public boolean isSelect;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteProvinceCode() {
		return siteProvinceCode;
	}

	public void setSiteProvinceCode(String siteProvinceCode) {
		this.siteProvinceCode = siteProvinceCode;
	}

	public String getSiteCityCode() {
		return siteCityCode;
	}

	public void setSiteCityCode(String siteCityCode) {
		this.siteCityCode = siteCityCode;
	}

	public String getSiteAreaCode() {
		return siteAreaCode;
	}

	public void setSiteAreaCode(String siteAreaCode) {
		this.siteAreaCode = siteAreaCode;
	}

	public String getSiteProvinceName() {
		return siteProvinceName;
	}

	public void setSiteProvinceName(String siteProvinceName) {
		this.siteProvinceName = siteProvinceName;
	}

	public String getSiteCityName() {
		return siteCityName;
	}

	public void setSiteCityName(String siteCityName) {
		this.siteCityName = siteCityName;
	}

	public String getSiteAreaName() {
		return siteAreaName;
	}

	public void setSiteAreaName(String siteAreaName) {
		this.siteAreaName = siteAreaName;
	}

	public String getSiteAddr() {
		return siteAddr;
	}

	public void setSiteAddr(String siteAddr) {
		this.siteAddr = siteAddr;
	}

	public String getSiteLongitude() {
		return siteLongitude;
	}

	public void setSiteLongitude(String siteLongitude) {
		this.siteLongitude = siteLongitude;
	}

	public String getSiteLatitude() {
		return siteLatitude;
	}

	public void setSiteLatitude(String siteLatitude) {
		this.siteLatitude = siteLatitude;
	}

	public String getSiteStar() {
		return siteStar;
	}

	public void setSiteStar(String siteStar) {
		this.siteStar = siteStar;
	}

	public String getSiteAvatar() {
		return siteAvatar;
	}

	public void setSiteAvatar(String siteAvatar) {
		this.siteAvatar = siteAvatar;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSiteStatus() {
		return siteStatus;
	}

	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
	}

	@Override
	public int compareTo(SiteVO another) {
		// TODO Auto-generated method stub
		if(this.distance == another.distance){
			return 0;
		}else if(this.distance > another.distance){
			return 1;
		}else{
			return -1;
		}
	}

}
