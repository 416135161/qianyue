package com.internet.http.data.response;

import java.io.Serializable;
import java.util.ArrayList;

import com.internet.basic.AdapterData;

public class GetCalenderListResponse extends CommonResponse {

	ArrayList<DriversCalender> result;

	public ArrayList<DriversCalender> getResult() {
		return result;
	}

	public void setResult(ArrayList<DriversCalender> result) {
		this.result = result;
	}

	public static class DriversCalender implements AdapterData, Serializable {
		public String scheduleId;// FieldType=class java.lang.Long、日程id
		public String driverId;// FieldType=class java.lang.Long、教练id
		public String driverName;// FieldType=class java.lang.String、教练姓名
		public String siteId;// FieldType=class java.lang.Long、场地id
		public String siteName;// FieldType=class java.lang.String、场地名称
		long calenderDate;// FieldType=class java.util.Date、日期
		public String scheduleStartTime;// FieldType=class java.util.Date、开始时间
		public String scheduleEndTime;// FieldType=class java.util.Date、结束时间
		public String schedulePrice;// FieldType=class java.lang.Integer、价格
		public String scheduleTypeCode;// FieldType=class
										// java.lang.String、日程类型Code
		public String scheduleTypeName;// FieldType=class
										// java.lang.String、日程类型名称
		public String isRepeat;// FieldType=class java.lang.String、是否重复
		public String isFull;// FieldType=class java.lang.String、是否已约满
		public String subjectCode;// FieldType=class java.lang.String、科目Code
		public String subjectName;// FieldType=class java.lang.String、科目名称
		public String subjectTypeCode;// FieldType=class
										// java.lang.String、科目类型Code
		public String subjectTypeName;// FieldType=class java.lang.String、科目类型名称
		public int scheduleCurrentNum;// FieldType=class
										// java.lang.Integer、当前预约人数
		public int scheduleMinNum;// FieldType=class java.lang.Integer、最大预约人数
		public int scheduleMaxNum;// FieldType=class java.lang.Integer、最小预约人数
		public String isVouch;// FieldType=class java.lang.Integer、是否担保
		public long createTime;// FieldType=class java.util.Date、创建时间
		public int updatedCount;// FieldType=class java.lang.Integer、更细次数
		public String status;// FieldType=class java.lang.String、状态

	}

}
