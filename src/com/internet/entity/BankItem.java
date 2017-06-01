package com.internet.entity;

import com.internet.basic.AdapterData;

public class BankItem implements AdapterData {

	private int iconId;
	private String name;

	public BankItem(int iconId, String name) {
		this.iconId = iconId;
		this.name = name;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
