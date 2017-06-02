/**  
 * @author ningsj@shishike.com
 * @Title: StudentDspAct.java 
 * @Package com.internet.act 
 * @Description: TODO
 * @date 2015-12-1 上午10:52:44 
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.internet.basic.BasicActivity;
import com.internet.basic.EsayAdapter;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.GetStudentDetailPost;
import com.internet.http.data.response.GetStudentDetailResponse;
import com.internet.http.data.vo.StudentDetailVO;
import com.internet.http.data.vo.StudentDetailVO.UserAppoint;
import com.internet.http.data.vo.StudentVO;
import com.internet.qianyue.R;
import com.internet.util.FileUtils;
import com.internet.util.PicUtil;
import com.internet.view.HeaderView;
import com.internet.view.ListItemUserAppointView_;

/**
 * @Author: ningsj@shishike.com
 * @Date：2015-12-1 上午10:52:44
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */

@EActivity(R.layout.act_student_dsp)
public class StudentDspAct extends BasicActivity {

	public static final String INTENT_FLAG = "intent_flag";

	@ViewById
	HeaderView view_header;
	@ViewById
	com.internet.view.CircleImageView image_photo;
	@ViewById
	View view_jihuo;
	@ViewById
	TextView text_name, text_phone, text_shenfenzheng, text_drive_type,
			text_remark, text_weijihuo;
	@ViewById
	ImageView image_phone;

	@ViewById
	ListView mListView;

	private EsayAdapter<UserAppoint, ListItemUserAppointView_> mListAdapter;

	ArrayList<UserAppoint> mListData;

	StudentVO studentData;

	@AfterViews
	void init() {
		view_header.setTitle("学员信息");
		view_header.setRightImage(R.drawable.student_edit);
		mListData = new ArrayList<UserAppoint>();
		mListAdapter = new EsayAdapter<StudentDetailVO.UserAppoint, ListItemUserAppointView_>(
				this) {
		};
		mListAdapter.setList(mListData);
		mListView.setAdapter(mListAdapter);

		studentData = (StudentVO) getIntent().getSerializableExtra(INTENT_FLAG);
		if (studentData != null) {
			
			if (studentData.getStudentId() > 0) {
				text_weijihuo.setVisibility(View.GONE);
				text_name.setText(studentData.getStudentName() + "       " + "已激活");
				text_phone.setText(studentData.getStudentMobile());
				getStuentDetail(studentData.getDriverStudentId() + "");
			} else {
				view_jihuo.setVisibility(View.GONE);
				text_name.setText(studentData.getStudentName() + "         " + "未激活");
				text_phone.setText(studentData.getStudentMobile());
				
			}
		}

	}
	
	@Click(R.id.image_header_right)
	void clickRightIamge(){
		EditStudentAct_.IntentBuilder_ build = EditStudentAct_.intent(this);
		build.get()
				.putExtra(StudentDspAct.INTENT_FLAG, studentData);
		build.start();
		
	}

	@Click(R.id.image_phone)
	void clickCallPhone() {
		if(!TextUtils.isEmpty(text_phone.getText())){
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ text_phone.getText()));
			startActivity(intent);
		}
	}

	@Background
	void getStuentDetail(String userId) {
		showLoading(null);
		GetStudentDetailResponse response = null;
		try {
			GetStudentDetailPost data = new GetStudentDetailPost();
			data.userId = userId;
			response = ApiManager.getDefault().getStudentDetail(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response != null) {
			refreshViewByData(response.getResult());
		} else {
			showToast("未获取到学员详情！");
		}
	}

	@UiThread
	void refreshViewByData(StudentDetailVO result) {
		showHead(result.getUserAvatar());
		if (studentData.getStudentId() > 0) {
			
			text_shenfenzheng.setText(result.getLicenseTypeName());
			text_remark.setText(studentData.getRemarks());
			if (result.getUserAppoints() != null
					&& !result.getUserAppoints().isEmpty()) {
				mListData.clear();
				mListData.addAll(result.getUserAppoints());
				mListAdapter.notifyDataSetChanged();
			}

			text_weijihuo.setVisibility(View.GONE);
		}

	}

	@UiThread
	void showHead(Bitmap bitmap) {
		image_photo.setImageBitmap(bitmap);
	}

	@Background
	void showHead(String url) {
		if (!TextUtils.isEmpty(url)) {
			String namePath = getFileName(FileUtils.getLocalFileName(url));
			System.out.println(this.getClass().getName() + namePath);
			if (!FileUtils.checkFileExist(namePath)) {
				System.out.println(this.getClass().getName() + "hhhh");
				Bitmap bitmap = PicUtil.getbitmap(url);
				if (bitmap != null) {
					showHead(bitmap);
					saveMyBitmap(namePath, bitmap, 100);
				}
			} else {
				Bitmap bitmap = BitmapFactory.decodeFile(namePath);
				System.out.println(this.getClass().getName() + "vvvvv");
				if (bitmap != null) {
					showHead(bitmap);
					System.out.println(this.getClass().getName() + "jjjjjj");
				}
			}
		}

	}

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
		return fileName;
	}

}
