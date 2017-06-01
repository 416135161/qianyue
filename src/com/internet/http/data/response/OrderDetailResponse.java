package com.internet.http.data.response;

import com.internet.http.data.vo.OrderDetailVO;

public class OrderDetailResponse extends CommonResponse {
	OrderDetailVO result;

	public OrderDetailVO getResult() {
		return result;
	}

	public void setResult(OrderDetailVO result) {
		this.result = result;
	}

}
