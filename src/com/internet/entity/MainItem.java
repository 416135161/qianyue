package com.internet.entity;

import com.internet.basic.AdapterData;

public class MainItem implements AdapterData {

	private int indexDrawableId;
	private int indexColorId;
	private int imageId;
	private String typeName;
	private String description;

	public MainItem(int indexDrawableId, int indexColorId, int imageId, String typeName, String description) {
		super();
		this.indexDrawableId = indexDrawableId;
		this.indexColorId = indexColorId;
		this.imageId = imageId;
		this.typeName = typeName;
		this.description = description;
	}

	public int getIndexDrawableId() {
		
		return indexDrawableId;
	}

	public void setIndexDrawableId(int indexDrawableId) {
		this.indexDrawableId = indexDrawableId;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIndexColorId() {
		return indexColorId;
	}

	public void setIndexColorId(int indexColorId) {
		this.indexColorId = indexColorId;
	}

}
