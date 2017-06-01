package com.internet.http.data.response;

import java.util.ArrayList;

public class GetBannerResponse extends CommonResponse {

	ArrayList<Banner> result;

	public ArrayList<Banner> getResult() {
		return result;
	}

	public void setResult(ArrayList<Banner> result) {
		this.result = result;
	}

	public class Banner {
		String bannerId;// FieldType=class java.lang.Long、主键id
		String title;// FieldType=class java.lang.String、标题
		String img;// FieldType=class java.lang.String、封面
		String url;// FieldType=class java.lang.String、地址
		String type;// FieldType=class java.lang.String、类型
		String status;// FieldType=class java.lang.String、状态
		String createTime;// FieldType=class java.util.Date、创建时间

		public String getBannerId() {
			return bannerId;
		}

		public void setBannerId(String bannerId) {
			this.bannerId = bannerId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

	}
}
