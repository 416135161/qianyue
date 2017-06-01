package com.internet.http.data.response;

import com.internet.http.data.vo.OrderListVO;

public class OrderListResponse extends CommonResponse {

	OrderListVO result;

	public OrderListResponse() {
		// TODO Auto-generated constructor stub
	}

	public OrderListVO getResult() {
		return result;
	}

	public void setResult(OrderListVO result) {
		this.result = result;
	}

}
