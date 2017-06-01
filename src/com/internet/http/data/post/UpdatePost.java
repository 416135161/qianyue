/**  
 * @author ningsj@shishike.com
 * @Title: UpdatePost.java 
 * @Package com.internet.yousheng.http.data.post 
 * @Description: TODO
 * @date 2015-4-19 下午9:33:04 
 * @version V1.0  
 */
package com.internet.http.data.post;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-4-19 下午9:33:04
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
public class UpdatePost {
	public String mobileNo; // 手机号
	
	public String trueName;// 真实名称(非必填，不更新不传)
	
	public Boolean sex;// 性别(true 男，false 女，非必填，不更新不传)
	
	public String birthday;// 生日时间,格式：2012-01-01(非必填，不更新不传)
	
	public String schoolname;// 学校(非必填，不更新不传)
	
	public String area;// 地区(非必填，不更新不传)
	
	
	public String provinceid;
	
	public String cityid;
	/**
	 * @Constructor
	 * @Description TODO
	 */
	public UpdatePost() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getTrueName() {
		return trueName;
	}
	
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getSchoolname() {
		return schoolname;
	}
	
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	
}
