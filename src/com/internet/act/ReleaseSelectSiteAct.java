package com.internet.act;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.internet.action.ActionSelectSite;
import com.internet.basic.BasicActivity;
import com.internet.basic.EsayAdapter;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.GetSiteListPost;
import com.internet.http.data.response.GetSiteListResponse;
import com.internet.http.data.vo.SiteVO;
import com.internet.qianyue.R;
import com.internet.view.HeaderView;
import com.internet.view.ListItemSiteView_;

@EActivity(R.layout.act_release_select_site)
public class ReleaseSelectSiteAct extends BasicActivity {
	@ViewById
	HeaderView view_header;
	@ViewById
	TextView text_search;
	@ViewById
	EditText edit_search;
	@ViewById
	ListView mListView;

	private EsayAdapter<SiteVO, ListItemSiteView_> mListAdapter;

	private ArrayList<SiteVO> mListData;

	@AfterViews
	void init() {
		view_header.setTitle("场地训练-发布时间");
		mListAdapter = new EsayAdapter<SiteVO, ListItemSiteView_>(this) {
		};
		mListData = new ArrayList<SiteVO>();
		mListAdapter.setList(mListData);
		mListView.setAdapter(mListAdapter);
		
		getSiteList("");
	}

	@Click(R.id.text_search)
	void clickSearch() {
		String keyword = edit_search.getEditableText().toString();
		if (TextUtils.isEmpty(keyword)) {
			showToast("请输入搜索条件！");
			return;
		}
		getSiteList(keyword);
	}
	
	@ItemClick(R.id.mListView)
	void clickItem(int position){
		post(new ActionSelectSite(mListData.get(position)));
		finish();
	}

	@Background
	void getSiteList(String keyword) {
		showLoading();
		try {
			GetSiteListPost data = new GetSiteListPost();
			data.setKeyword(keyword);
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
			mListData.clear();
			mListData.addAll(response.getResult().getResult());
			mListAdapter.notifyDataSetChanged();
		}
	}

}
