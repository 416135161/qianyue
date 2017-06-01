package com.internet.entity;

import com.internet.basic.AdapterData;

public class MakeCardItem implements AdapterData {

	private String bankType;
	private String name;
	private String cardType;
	private String applyDate;
	private int status;

	public MakeCardItem(String bankType, String name, String cardType,
			String applyDate, int status) {
		this.bankType = bankType;
		this.name = name;
		this.cardType = cardType;
		this.applyDate = applyDate;
		this.status = status;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
