package com.internet.act;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.internet.action.ActionSelectArea;
import com.internet.app.MainApplication;
import com.internet.basic.BasicActivity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.AreaSearchPost;
import com.internet.http.data.response.AreaResponse;
import com.internet.http.data.vo.AreaVO;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.turnright.b.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_self_info_select_city)
public class SelfInfoSelectCityAct extends BasicActivity {

	public static final int PROVINCE = 1;
	public static final int CITY = 2;
	public static final int AREA = 3;

	@ViewById
	HeaderView view_header;

	@ViewById
	Spinner mProvince, mCity, mArea;

	UserInfoVO userInfo;

	int positionProvince = -1, positionCity = -1, positionArea = -1;

	private ArrayAdapter<String> adapterProvince, adapterCity, adapterArea;

	private ArrayList<String> mListProvince = new ArrayList<String>();

	private ArrayList<String> mListCity = new ArrayList<String>();

	private ArrayList<String> mListArea = new ArrayList<String>();
	private ArrayList<AreaVO> mListProviceVO = new ArrayList<AreaVO>();

	private ArrayList<AreaVO> mListCityVO = new ArrayList<AreaVO>();

	private ArrayList<AreaVO> mListAreaVO = new ArrayList<AreaVO>();

	@AfterViews
	void init() {

		userInfo = MainApplication.getInstance().getUserInfo();

		view_header.setTitle("地区");

		adapterProvince = new ArrayAdapter<String>(this,
				R.layout.deep_search_spinner_item, mListProvince);
		adapterProvince
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mProvince.setAdapter(adapterProvince);

		adapterCity = new ArrayAdapter<String>(this,
				R.layout.deep_search_spinner_item, mListCity);
		adapterCity
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCity.setAdapter(adapterCity);

		adapterArea = new ArrayAdapter<String>(this,
				R.layout.deep_search_spinner_item, mListArea);
		adapterArea
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mArea.setAdapter(adapterArea);

		getArea("", PROVINCE);
	}

	// @Click(R.id.view_city)
	// void clickCity() {
	// SelfInfoSelectAreaAct_.intent(this).start();
	// }

	@ItemSelect(R.id.mProvince)
	void itemSelectProvince(boolean arg1, int arg2) {
		positionProvince = arg2;
		getArea(mListProviceVO.get(arg2).getRegionId(), CITY);
	}

	@ItemSelect(R.id.mCity)
	void itemSelectCity(boolean arg1, int arg2) {
		positionCity = arg2;
		getArea(mListCityVO.get(arg2).getRegionId(), AREA);
	}

	@ItemSelect(R.id.mArea)
	void itemSelectArea(boolean arg1, int arg2) {
		positionArea = arg2;

	}

	@Background
	void getArea(String regionId, int type) {
		try {

			AreaResponse response = ApiManager.getDefault().areaSearch(
					new AreaSearchPost(regionId));
			if (response != null && response.getResult() != null
					&& response.getResult().size() > 0) {
				if (type == PROVINCE)
					showProvince(response.getResult());
				else if (type == CITY) {
					showCity(response.getResult());

				} else if (type == AREA) {
					showArea(response.getResult());
				}
			} else {

			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
	}

	@UiThread
	void showProvince(ArrayList<AreaVO> listProviceVO) {
		mListProvince.clear();
		mListProviceVO.clear();
		mListProviceVO.addAll(listProviceVO);
		for (AreaVO item : mListProviceVO) {
			mListProvince.add(item.getName());
		}
		adapterProvince.notifyDataSetChanged();
		boolean isSelected = false;
		for (int i = 0; i < mListProviceVO.size(); i++) {
			if (TextUtils.equals(listProviceVO.get(i).getRegionId(),
					userInfo.getDriverProvinceCode())) {
				mProvince.setSelection(i);
				isSelected = true;
				break;
			}

		}
		if (!isSelected)
			mProvince.setSelection(0);
	}

	@UiThread
	void showCity(ArrayList<AreaVO> listCityVO) {
		mListCity.clear();
		mListCityVO.clear();
		mListCityVO.addAll(listCityVO);
		for (AreaVO item : mListCityVO) {
			mListCity.add(item.getName());
		}
		adapterCity.notifyDataSetChanged();
		boolean isSelected = false;
		for (int i = 0; i < mListCityVO.size(); i++) {
			if (TextUtils.equals(listCityVO.get(i).getRegionId(),
					userInfo.getDriverCityCode())) {
				mCity.setSelection(i);
				isSelected = true;
				break;
			}

		}
		if (!isSelected)
			mCity.setSelection(0);
		getArea(mListCityVO.get(0).getRegionId(), AREA);

	}

	@UiThread
	void showArea(ArrayList<AreaVO> listAreaVO) {
		mListArea.clear();
		mListAreaVO.clear();
		mListAreaVO.addAll(listAreaVO);
		for (AreaVO item : mListAreaVO) {
			mListArea.add(item.getName());
		}
		adapterArea.notifyDataSetChanged();
		boolean isSelected = false;
		for (int i = 0; i < mListAreaVO.size(); i++) {
			if (TextUtils.equals(listAreaVO.get(i).getRegionId(),
					userInfo.getDriverAreaCode())) {
				mArea.setSelection(i);
				isSelected = true;
				break;
			}

		}
		if (!isSelected)
			mArea.setSelection(0);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (positionProvince != -1)
			post(new ActionSelectArea(mListProviceVO.get(positionProvince),
					mListCityVO.get(positionCity),
					mListAreaVO.get(positionArea)));
	}
}
