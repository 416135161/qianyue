/**  
 * @author ningsj@shishike.com
 * @Title: MyMessageAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-12-1 上午10:44:27 
 * @version V1.0  
 */
package com.internet.act;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.internet.basic.BasicActivity;
import com.internet.basic.EsayAdapter;
import com.internet.db.SpHelper;
import com.internet.entity.MessageEntity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.DeleteMessagePost;
import com.internet.http.data.post.ListMessagePost;
import com.internet.http.data.response.ListMessageResponse;
import com.internet.turnright.b.R;
import com.internet.util.DensityUtil;
import com.internet.view.HeaderView;
import com.internet.view.ListItemMessageView_;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-12-1 上午10:44:27
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */

@EActivity(R.layout.act_my_message)
public class MyMessageAct extends BasicActivity {
	@ViewById
	HeaderView view_header;

	@ViewById
	TextView text_no_data;

	@ViewById(R.id.listView)
	com.baoyz.swipemenulistview.SwipeMenuListView mListView;

	private EsayAdapter<MessageEntity, ListItemMessageView_> mListAdapter;

	private ArrayList<MessageEntity> mListData;

	@AfterViews
	void init() {
		view_header.setTitle("我的消息");
		mListAdapter = new EsayAdapter<MessageEntity, ListItemMessageView_>(
				this) {
		};
		mListData = new ArrayList<MessageEntity>();
		mListAdapter.setList(mListData);
		mListView.setAdapter(mListAdapter);
		mListView.setMenuCreator(creator);
		// step 2. listener item click event
		mListView.setOnMenuItemClickListener(menuItemClickListener);
		getListMessage();
	}

	@Background
	void getListMessage() {
		showLoading(null);
		try {
			ListMessagePost data = new ListMessagePost();
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			data.setStatus("1");
			ListMessageResponse response = ApiManager.getDefault().listMessage(
					data);
			if (response != null && response.getResult() != null) {
				if (response.getResult().getData() != null
						&& !response.getResult().getData().isEmpty())
					freshListView(response.getResult().getData());
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());

		}
		closeLoading();

	}

	@UiThread
	void freshListView(ArrayList<MessageEntity> listData) {
		mListData.clear();
		mListData.addAll(listData);
		mListAdapter.notifyDataSetChanged();
		hideEmpoty();
	}

	@UiThread
	void hideEmpoty() {
		text_no_data.setVisibility(View.INVISIBLE);
	}

	@Background
	void deleteMessage(int position) {
		showLoading();
		boolean isDelete = false;
		try {
			DeleteMessagePost data = new DeleteMessagePost();
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			data.setId(mListData.get(position).getId() + "");
			isDelete = ApiManager.getDefault().deleteMessage(data);

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());

		}
		closeLoading();
		if (isDelete) {
			mListData.remove(position);
			refreshList();
		}
	}

	@UiThread
	void refreshList() {
		mListAdapter.notifyDataSetChanged();
	}

	OnMenuItemClickListener menuItemClickListener = new OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
			switch (index) {
			case 0:
				deleteMessage(position);
				break;
			}
			return false;
		}

	};

	SwipeMenuCreator creator = new SwipeMenuCreator() {
		@Override
		public void create(SwipeMenu menu) {

			// create "delete" item
			SwipeMenuItem deleteItem = new SwipeMenuItem(
					getApplicationContext());
			// set item background
			deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F,
					0x25)));
			// set item width
			deleteItem.setWidth(DensityUtil
					.dip2px(getApplicationContext(), 100));
			// // set a icon
			// deleteItem.setIcon(R.drawable.ic_delete);
			deleteItem.setTitle("删除");
			deleteItem.setTitleColor(getResources()
					.getColor(R.color.text_white));
			deleteItem.setTitleSize(DensityUtil.sp2px(getApplicationContext(),
					8));
			// add to menu
			menu.addMenuItem(deleteItem);
		}

	};

}
