package com.internet.http.data.response;

import java.util.ArrayList;

import com.internet.entity.MessageEntity;

public class ListMessageResponse extends CommonResponse {
	Entity result;

	public Entity getResult() {
		return result;
	}

	public void setResult(Entity result) {
		this.result = result;
	}

	public static class Entity {
		ArrayList<MessageEntity> data;
		int recordsTotal;

		public Entity() {
			// TODO Auto-generated constructor stub
		}

		public ArrayList<MessageEntity> getData() {
			return data;
		}

		public void setData(ArrayList<MessageEntity> data) {
			this.data = data;
		}

		public int getRecordsTotal() {
			return recordsTotal;
		}

		public void setRecordsTotal(int recordsTotal) {
			this.recordsTotal = recordsTotal;
		}

	}
}
