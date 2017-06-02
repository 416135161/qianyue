package com.internet.act.qianyue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.internet.act.qianyue.PoorPeopleListAct.SelectSiteAdapter;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.GetSiteListPost;
import com.internet.http.data.response.GetSiteListResponse;
import com.internet.http.data.vo.SiteVO;
import com.internet.qianyue.R;
import com.internet.util.ToastUtil;
import com.internet.util.Utils;
import com.internet.view.HeaderView;
import com.internet.view.SearchSiteView;


@EActivity(R.layout.act_villages_list)
public class VillagesListAct extends BasicActivity implements OnScrollListener {
	@ViewById
	HeaderView view_header;

	@ViewById
	ImageView image_search;

	@ViewById
	EditText edit_search;

	@ViewById
	ListView listView;

	String mKeyword = "";

	SelectSiteAdapter adapter;

	SiteVO selectSite;

	int page = 1;
	int pageSize = 20;
	boolean hasMore = true;

	@AfterViews
	void init() {
		view_header.setTitle("帮扶对象");

		adapter = new SelectSiteAdapter(this);

		listView.setAdapter(adapter);
		listView.setOnScrollListener(this);

		getSiteList();
	}

	@Click(R.id.image_search)
	void clickEnsureSearch() {
		String keyword = edit_search.getEditableText().toString();
		if (TextUtils.isEmpty(keyword)) {
			showToast("搜索条件不能为空！");
			return;
		}
		mKeyword = keyword;
		page = 1;
		hasMore = true;
		adapter.clearList();
		getSiteList();
	}

	@Background
	void getSiteList() {
		showLoading(null);
		try {
			GetSiteListPost data = new GetSiteListPost();
			data.setKeyword(mKeyword);
			data.setPageNo(page + "");
			data.setPageSize(pageSize + "");
			if (MainApplication.getInstance().mLatitude != 0)
				data.setLatitude(MainApplication.getInstance().mLatitude + "");
			if (MainApplication.getInstance().mLongitude != 0) {
				data.setLongitude(MainApplication.getInstance().mLongitude + "");
			}
			GetSiteListResponse response = ApiManager.getDefault().getSiteList(
					data);
			freshList(response);
		} catch (ApiException e) {
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
	}

	@UiThread
	void freshList(GetSiteListResponse response) {
		if (response != null && response.getResult() != null
				&& response.getResult().getResult() != null) {
			if (response.getResult().getResult().size() >= page) {
				++page;
			} else {
				hasMore = false;
			}
			adapter.addList(response.getResult().getResult());

		} else {
			hasMore = false;
		}
	}

	class SelectSiteAdapter extends BaseAdapter {

		private List<SiteVO> list;
		private Context context;

		public SelectSiteAdapter(Context context) {
			super();
			this.list = new ArrayList<SiteVO>();
			this.context = context;
		}

		@Override
		public int getCount() {
			if (list == null || list.isEmpty()) {
				return 0;
			}
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public void clearList() {
			this.list.clear();
			notifyDataSetChanged();
		}

		public void addList(List<SiteVO> list) {

			this.list.addAll(list);
			Collections.sort(this.list);
			notifyDataSetChanged();
		}

		public void addItem(SiteVO bean) {

			if (list == null) {
				list = new ArrayList<SiteVO>();
			}

			list.add(bean);
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {

			if (view == null) {
				view = LayoutInflater.from(context).inflate(
						R.layout.list_item_select_site, null);
			}

			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			TextView tv_address = (TextView) view.findViewById(R.id.tv_address);
			TextView tv_distance = (TextView) view
					.findViewById(R.id.tv_distance);
			ImageView iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
			final CheckBox cb = (CheckBox) view.findViewById(R.id.cb);

			final SiteVO bean = list.get(position);

			tv_address.setText(bean.siteAddr);

			tv_name.setText(bean.siteName);

			tv_distance.setText(Utils.transferDot2(bean.distance + "") + "km");

			Glide.with(context).load(bean.siteAvatar).centerCrop()
					.placeholder(R.drawable.empty).crossFade().into(iv_pic);

			if (bean.isSelect) {

				cb.setChecked(true);
			} else {
				cb.setChecked(false);
			}

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					for (SiteVO site : list) {
						site.isSelect = false;
						selectSite = null;
					}
					if (!cb.isChecked()) {
						bean.isSelect = true;
						selectSite = bean;
					}

					notifyDataSetChanged();

					PoorPeopleDetailAct_.intent(context).start();

				}
			});

			return view;
		}

	}

	private int visibleLastIndex = 0; // 最后的可视项索引

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引
		int lastIndex = itemsLastIndex; // 加上底部的loadMoreView项
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& visibleLastIndex == lastIndex) {
			// 如果是自动加载,可以在这里放置异步加载数据的代码
			if (hasMore) {
				getSiteList();
				// showToast("KKKKKKKKKK");
			}

		}
	}
}
