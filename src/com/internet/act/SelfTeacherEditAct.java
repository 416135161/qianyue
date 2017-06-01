/**  
 * @author ningsj@shishike.com
 * @Title: SelfTeacherEditAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-11-27 上午9:42:50 
 * @version V1.0  
 */
package com.internet.act;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.internet.action.ActionRefresh;
import com.internet.action.ActionSelectPhoto;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.dialog.SelectPhotoDialog_;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.AuthInfoPost;
import com.internet.http.data.post.SelectSysTypePost;
import com.internet.http.data.post.SubmitAuthPost;
import com.internet.http.data.response.AuthInfoResponse;
import com.internet.http.data.response.SelectSysTypeResponse;
import com.internet.http.data.vo.AuthInfoVO;
import com.internet.http.data.vo.SelectSysTypeVO;
import com.internet.turnright.b.R;
import com.internet.util.FileUtils;
import com.internet.util.PicUtil;
import com.internet.view.HeaderView;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-11-27 上午9:42:50
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */

@EActivity(R.layout.act_self_teacher_edit)
public class SelfTeacherEditAct extends BasicActivity {

	@ViewById
	HeaderView view_header;

	@ViewById
	Spinner mCarType;

	@ViewById
	EditText text_name, text_no, text_carNo;

	@ViewById
	com.internet.view.PaperItemView paper1, paper2, paper3, paper4, paper5,
			paper6;

	AuthInfoVO mAuthInfo;

	private ArrayAdapter<String> adapterCarType;

	private ArrayList<String> mListSelectSysType = new ArrayList<String>();

	private ArrayList<SelectSysTypeVO> mListSelectSysTypeVO = new ArrayList<SelectSysTypeVO>();

	int mPosition = 0;

	Map<String, String> mapFile = new HashMap<String, String>();

	SubmitAuthPost mSubmitAuthPost;

	ArrayList<String> keyList;

	@AfterViews
	void init() {
		registerEventBus();
		view_header.setTitle("注册-教练信息");
		view_header.setRight("提交审核");

		adapterCarType = new ArrayAdapter<String>(this,
				R.layout.deep_search_spinner_item, mListSelectSysType);
		adapterCarType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCarType.setAdapter(adapterCarType);
		paper1.setText("身份证");
		paper2.setText("教练证");
		paper3.setText("行驶证");
		paper4.setText("驾驶证");
		paper5.setText("道路运输证");
		paper6.setText("车身照");
		getCarType();
		// getAuthInfo();
	}

	int positionCarType = 0;

	@ItemSelect(R.id.mCarType)
	void itemSelectCity(boolean arg1, int arg2) {
		positionCarType = arg2;
	}

	@Click({ R.id.paper1, R.id.paper2, R.id.paper3, R.id.paper4, R.id.paper5,
			R.id.paper6 })
	void clickPaper(View view) {
		switch (view.getId()) {
		case R.id.paper1:
			mPosition = 1;
			break;
		case R.id.paper2:
			mPosition = 2;
			break;
		case R.id.paper3:
			mPosition = 3;
			break;
		case R.id.paper4:
			mPosition = 4;
			break;
		case R.id.paper5:
			mPosition = 5;
			break;
		case R.id.paper6:
			mPosition = 6;
			break;
		}
		// Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//
		// 调用android自带的照相机
		// startActivityForResult(intent, 1);
		SelectPhotoDialog_.builder().build().show(getFragmentManager(), null);
	}

	@Click(R.id.text_header_right)
	void clickSubmit() {
		if (TextUtils.isEmpty(text_name.getText().toString())) {
			showToast("请输入姓名");
			return;
		}
		if (TextUtils.isEmpty(text_no.getText().toString().toString())
				|| text_no.getText().toString().length() < 18) {
			showToast("请输入正確身份证信息");
			return;
		}
		if (mListSelectSysTypeVO == null) {
			showToast("请选择车型");
			return;
		}

		if (TextUtils.isEmpty(text_carNo.getText().toString())
				|| text_carNo.getText().toString().length() < 3) {
			showToast("请填写正确车牌号");
			return;
		}

		if (!mapFile.containsKey("driverSfzFace")) {
			showToast("请拍摄身份证");
			return;
		}
		if (!mapFile.containsKey("driverJlzFace")) {
			showToast("请拍摄教练证");
			return;
		}
		if (!mapFile.containsKey("driverXszFace")) {
			showToast("请拍摄行使证");
			return;
		}
		if (!mapFile.containsKey("driverJszFace")) {
			showToast("请拍摄驾驶证");
			return;
		}
		if (!mapFile.containsKey("driverDlysFace")) {
			showToast("请拍摄道路运输证");
			return;
		}

		if (!mapFile.containsKey("driverCszFace")) {
			showToast("请拍摄车身照");
			return;
		}

		mSubmitAuthPost = new SubmitAuthPost();
		mSubmitAuthPost.setSign(SpHelper.getDefault().getString(SpHelper.SIGN));
		mSubmitAuthPost.setName(text_name.getText().toString());
		mSubmitAuthPost.setNumber(text_no.getText().toString());
		mSubmitAuthPost.setCarId(mListSelectSysTypeVO.get(positionCarType)
				.getTypeDictId());
		mSubmitAuthPost
				.setCarPlate("A" + text_carNo.getText().toString() + "学");
		mIndex = 1;
		keyList = new ArrayList<String>();
		for (String key : mapFile.keySet()) {
			keyList.add(key);
		}
		showLoading();
		submit();

	}

	@UiThread
	void initView() {
		if (mAuthInfo != null) {
			text_name.setText(mAuthInfo.getDriverIdentityName());
			text_no.setText(mAuthInfo.getDriverIdentityNo());
			text_carNo.setText(mAuthInfo.getDriverCarPlate());

			showHead(mAuthInfo.getDriverSfzFace(), 1);
			showHead(mAuthInfo.getDriverJlzFace(), 2);
			showHead(mAuthInfo.getDriverXszFace(), 3);
			showHead(mAuthInfo.getDriverJszFace(), 4);
			showHead(mAuthInfo.getDirverDlyszFace(), 5);
		}
		getCarType();
	}

	@Background
	void getAuthInfo() {
		showLoading();
		try {
			AuthInfoResponse response = ApiManager.getDefault().getAuthInfo(
					new AuthInfoPost(SpHelper.getDefault().getString(
							SpHelper.SIGN)));
			if (response.getResult() != null)
				mAuthInfo = response.getResult();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		initView();
	}

	@Background
	void getCarType() {
		try {

			SelectSysTypeResponse response = ApiManager.getDefault()
					.selectSysType(new SelectSysTypePost("10001006"));
			if (response != null && response.getResult() != null
					&& response.getResult().size() > 0) {
				showCarType(response.getResult());
			} else {

			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
	}

	int mIndex;

	@UiThread
	void submit() {
		if (mIndex < 7) {
			submitPhoto(mIndex);
		} else if (mIndex == 7) {
			doSubmit();
		}
	}

	@Background
	void doSubmit() {
		boolean state = false;
		try {
			state = ApiManager.getDefault().submitAuth(mSubmitAuthPost, null);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (state) {
			doOk();
		}
	}

	@Background
	void submitPhoto(int index) {
		boolean state = false;
		try {
			SubmitAuthPost submitAuthPost = new SubmitAuthPost();
			submitAuthPost.setSign(SpHelper.getDefault().getString(
					SpHelper.SIGN));
			Map<String, String> map = new HashMap<String, String>();
			map.put(keyList.get(index - 1), mapFile.get(keyList.get(index - 1)));
			state = ApiManager.getDefault().submitAuth(submitAuthPost, map);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
			closeLoading();
		}
		if (state) {
			submit();
			mIndex++;
		}
	}

	@UiThread
	void doOk() {
		post(new ActionRefresh());
		finish();
	}

	@UiThread
	void showCarType(ArrayList<SelectSysTypeVO> listSelectSysTypeVO) {
		mListSelectSysType.clear();
		mListSelectSysTypeVO.clear();
		mListSelectSysTypeVO.addAll(listSelectSysTypeVO);
		for (SelectSysTypeVO item : mListSelectSysTypeVO) {
			mListSelectSysType.add(item.getTypeDictName());
		}
		adapterCarType.notifyDataSetChanged();
		if (mAuthInfo != null) {
			for (int i = 0; i < mListSelectSysTypeVO.size(); i++) {
				if (TextUtils.equals(
						listSelectSysTypeVO.get(i).getTypeDictId(),
						mAuthInfo.getDriverCarId())) {
					mCarType.setSelection(i);
					break;
				}
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 完成照相后回调用此方法
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 1:
			switch (resultCode) {
			case Activity.RESULT_OK:// 照相完成点击确定
				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
				String fileName = getFileName(null);
				tempFileName(fileName, mPosition);
				saveMyBitmap(fileName, bitmap, 80);
				showHead(mPosition, bitmap);
				break;
			case Activity.RESULT_CANCELED:// 取消
				break;
			}
			break;
		case 2:
			switch (resultCode) {
			case Activity.RESULT_OK:
				Uri uri = data.getData();
				Cursor cursor = this.getContentResolver().query(uri, null,
						null, null, null);
				cursor.moveToFirst();
				String imgPath = cursor.getString(1); // 图片文件路径
				cursor.close();
				Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = false;
				options.inSampleSize = 5;
				Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
				String fileName = getFileName(null);
				tempFileName(fileName, mPosition);
				saveMyBitmap(fileName, bitmap, 80);
				showHead(mPosition, bitmap);
				break;
			case Activity.RESULT_CANCELED:// 取消
				break;
			}
			break;
		}
	}

	void tempFileName(String fileName, int position) {
		System.out.println("HHHHHHHHHHHH" + fileName);
		switch (position) {
		case 1:
			mapFile.put("driverSfzFace", fileName);// 身份证正面照
			break;
		case 2:
			mapFile.put("driverJlzFace", fileName);// 教练证正面照
			break;
		case 3:
			mapFile.put("driverXszFace", fileName);// 行使证正面照
			break;
		case 4:
			mapFile.put("driverJszFace", fileName);// 驾驶证正面照
			break;
		case 5:
			mapFile.put("driverDlysFace", fileName);// 道路运输证
			break;
		case 6:
			mapFile.put("driverCszFace", fileName);// 拍照
			break;
		}
	}

	@UiThread
	void showHead(int position, Bitmap bitmap) {
		switch (position) {
		case 1:
			paper1.setImage(bitmap);
			break;
		case 2:
			paper2.setImage(bitmap);
			break;
		case 3:
			paper3.setImage(bitmap);
			break;
		case 4:
			paper4.setImage(bitmap);
			break;
		case 5:
			paper5.setImage(bitmap);
			break;
		case 6:
			paper6.setImage(bitmap);
		}

	}

	@Background
	void showHead(String url, int position) {

		String namePath = getFileName(FileUtils.getLocalFileName(url));
		System.out.println(this.getClass().getName() + namePath);
		if (!FileUtils.checkFileExist(namePath)) {
			System.out.println(this.getClass().getName() + "hhhh");
			Bitmap bitmap = PicUtil.getbitmap(url);
			if (bitmap != null) {
				showHead(position, bitmap);
				saveMyBitmap(namePath, bitmap, 100);
			}
		} else {
			Bitmap bitmap = BitmapFactory.decodeFile(namePath);
			System.out.println(this.getClass().getName() + "vvvvv");
			if (bitmap != null) {
				showHead(position, bitmap);
				System.out.println(this.getClass().getName() + "jjjjjj");
			}
		}

	}

	@Background
	public void saveMyBitmap(String fileName, Bitmap mBitmap, int quality) {
		File f = new File(fileName);
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String mNamePath = null;

	private String getFileName(String photoName) {
		String saveDir = Environment.getExternalStorageDirectory() + "/myPic";
		File dir = new File(saveDir);
		if (!dir.exists()) {
			dir.mkdir(); // 创建文件夹
		}
		// 用日期作为文件名，确保唯一性
		if (photoName == null) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd_HH-mm-ss");
			photoName = formatter.format(date) + ".PNG";
		}

		String fileName = saveDir + "/" + photoName;
		mNamePath = fileName;
		return fileName;
	}

	public void onEventMainThread(ActionSelectPhoto action) {
		switch (action.type) {
		case ActionSelectPhoto.PZ:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
			startActivityForResult(intent, 1);
			break;
		case ActionSelectPhoto.XC:
			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
			startActivityForResult(i, 2);
			break;
		}
	}
}
