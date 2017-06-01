package com.internet.action;

import com.internet.http.data.vo.AreaVO;

public class ActionSelectArea {
	AreaVO province;
	AreaVO city;
	AreaVO area;
	public ActionSelectArea(AreaVO province, AreaVO city, AreaVO area) {
		super();
		this.province = province;
		this.city = city;
		this.area = area;
	}
	public AreaVO getProvince() {
		return province;
	}
	public void setProvince(AreaVO province) {
		this.province = province;
	}
	public AreaVO getCity() {
		return city;
	}
	public void setCity(AreaVO city) {
		this.city = city;
	}
	public AreaVO getArea() {
		return area;
	}
	public void setArea(AreaVO area) {
		this.area = area;
	}
	
}
