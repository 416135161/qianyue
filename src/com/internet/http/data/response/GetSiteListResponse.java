package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.http.data.vo.SiteVO;

public class GetSiteListResponse extends CommonResponse {
	SiteData result;

	public SiteData getResult() {
		return result;
	}

	public void setResult(SiteData result) {
		this.result = result;
	}

	public static class SiteData {
		ArrayList<SiteVO> result;

		public ArrayList<SiteVO> getResult() {
			return result;
		}

		public void setResult(ArrayList<SiteVO> result) {
			this.result = result;
		}
	}

}
