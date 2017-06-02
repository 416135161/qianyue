package com.internet.act;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.drm.DrmStore.RightsStatus;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.internet.action.ActionAddStudentOK;
import com.internet.action.ActionLoginOK;
import com.internet.basic.BasicActivity;
import com.internet.basic.EsayAdapter;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.DeleteStudentPost;
import com.internet.http.data.post.GetStudentListPost;
import com.internet.http.data.response.GetStudentListResponse;
import com.internet.http.data.vo.StudentVO;
import com.internet.qianyue.R;
import com.internet.util.DensityUtil;
import com.internet.view.ListItemStudentView_;
import com.internet.view.SearchStudentView;

@EActivity(R.layout.act_my_student)
public class MyStudentAct extends BasicActivity implements OnScrollListener {
	@ViewById
	SearchStudentView layout_search;

	@ViewById
	TextView text_no_data;

	@ViewById
	EditText edit_search;

	@ViewById
	com.baoyz.swipemenulistview.SwipeMenuListView mListView;

	private EsayAdapter<StudentVO, ListItemStudentView_> mListAdapter;

	private ArrayList<StudentVO> mListData;

	private int pageNo = 1;

	private int pageSize = 50;
	private String keyword;

	@AfterViews
	void init() {
		registerEventBus();
		mListData = new ArrayList<StudentVO>();
		mListAdapter = new EsayAdapter<StudentVO, ListItemStudentView_>(this) {
		};
		mListAdapter.setList(mListData);
		mListView.setAdapter(mListAdapter);
		mListView.setMenuCreator(creator);
		// step 2. listener item click event
		mListView.setOnMenuItemClickListener(menuItemClickListener);

		mListView.setOnScrollListener(this);

		keyword = null;
		initList();
		getStuentList();
	}

	public void onEventMainThread(ActionAddStudentOK action) {
		keyword = null;
		initList();
		getStuentList(null);
	}

	private void initList() {
		hasMore = true;
		pageNo = 1;
		mListData.clear();
		mListAdapter.notifyDataSetChanged();
		text_no_data.setVisibility(View.VISIBLE);
	}

	@Click(R.id.image_back)
	void clickBack() {
		doBack();
	}

	@Click(R.id.view_title_left)
	void clickTitleLeft() {

	}

	@Click(R.id.view_title_right)
	void clickTitleRight() {

	}

	@Click(R.id.image_add)
	void clickAdd() {
		AddStudentAct_.intent(this).start();
	}

	@Click(R.id.view_search)
	void clickSearch() {
		layout_search.setVisibility(View.VISIBLE);
	}

	@Click(R.id.text_cancel)
	void clickSearchCancel() {
		layout_search.setVisibility(View.GONE);
	}

	@Click(R.id.text_search)
	void clickDoSearch() {
		layout_search.setVisibility(View.GONE);
		keyword = edit_search.getEditableText().toString();
		initList();
		getStuentList();
	}

	@ItemClick(R.id.mListView)
	void itemClick(int position) {
		StudentDspAct_.IntentBuilder_ build = StudentDspAct_.intent(this);
		build.get()
				.putExtra(StudentDspAct.INTENT_FLAG, mListData.get(position));
		build.start();
	}

	@Background
	void getStuentList(Object noLoading) {
		GetStudentListResponse response = null;
		try {
			GetStudentListPost data = new GetStudentListPost();
			data.sign = SpHelper.getDefault().getString(SpHelper.SIGN);
			data.setDriverId(SpHelper.getDefault()
					.getString(SpHelper.DRIVER_ID));
			data.setKeyword(keyword);
			data.setType("1");
			data.setPageNo(pageNo + "");
			data.setPageSize(pageSize + "");
			response = ApiManager.getDefault().getStudentList(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		if (response != null && response.getResult() != null
				&& response.getResult().getResult() != null) {
			refreshListView(response.getResult().getResult());
		} else {
			showToast("未获取到学员！");
		}
	}

	@Background
	void getStuentList() {
		showLoading(null);
		GetStudentListResponse response = null;
		try {
			GetStudentListPost data = new GetStudentListPost();
			data.sign = SpHelper.getDefault().getString(SpHelper.SIGN);
			data.setDriverId(SpHelper.getDefault()
					.getString(SpHelper.DRIVER_ID));
			data.setKeyword(keyword);
			data.setType("1");
			data.setPageNo(pageNo + "");
			data.setPageSize(pageSize + "");
			response = ApiManager.getDefault().getStudentList(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response != null && response.getResult() != null
				&& response.getResult().getResult() != null) {
			refreshListView(response.getResult().getResult());
		} else {
			showToast("未获取到学员！");
		}
	}

	boolean hasMore = true;

	@UiThread
	void refreshListView(ArrayList<StudentVO> list) {
		if (list != null && !list.isEmpty()) {
			if (list.size() >= pageSize) {
				++pageNo;
				hasMore = true;
			} else {
				hasMore = false;
			}
			mListData.addAll(list);
			mListAdapter.notifyDataSetChanged();

		} else {
			hasMore = false;
		}

		if (mListAdapter.getCount() == 0) {
			text_no_data.setVisibility(View.VISIBLE);
		} else {
			text_no_data.setVisibility(View.INVISIBLE);
		}
	}

	@Background
	void deleteStudent(int position) {
		showLoading();
		boolean isDelete = false;
		try {
			DeleteStudentPost data = new DeleteStudentPost();
			data.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
			data.setId(mListData.get(position).getDriverStudentId() + "");
			isDelete = ApiManager.getDefault().deleteStudent(data);

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
	void hideEmpoty() {
		text_no_data.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (layout_search.getVisibility() == View.VISIBLE) {
			layout_search.setVisibility(View.GONE);
			return;
		}
		super.onBackPressed();
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
				deleteStudent(position);
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

	private int visibleLastIndex = 0; // 最后的可视项索引

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		int itemsLastIndex = mListAdapter.getCount() - 1; // 数据集最后一项的索引
		int lastIndex = itemsLastIndex; // 加上底部的loadMoreView项
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& visibleLastIndex == lastIndex) {
			// 如果是自动加载,可以在这里放置异步加载数据的代码
			if (hasMore) {
				getStuentList();
				// showToast("KKKKKKKKKK");
			}

		}
	}
}
