package com.internet.act;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.entity.OrderEntity;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.OrderDetailPost;
import com.internet.http.data.post.TakeInOrderPost;
import com.internet.http.data.response.CommonResponse;
import com.internet.http.data.response.OrderDetailResponse;
import com.internet.http.data.vo.OrderDetailVO;
import com.internet.http.data.vo.OrderDetailVO.BaseUserVO;
import com.internet.http.data.vo.OrderDetailVO.ScheduleVO;
import com.internet.http.data.vo.StudentDetailVO.UserAppoint;
import com.internet.qianyue.R;
import com.internet.util.DateTimeUtil;
import com.internet.util.FileUtils;
import com.internet.util.PicUtil;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_order_detail)
public class OrderDetailAct extends BasicActivity {

	public static String EXTRA_KEY = "key";

	@ViewById
	HeaderView view_header;

	@ViewById
	com.internet.view.CircleImageView image_photo;

	@ViewById
	ImageView image_phone;

	@ViewById
	TextView text_name, text_phone;
	@ViewById
	TextView text_1_1, text_1_2, text_1_3, text_1_4;
	
	@ViewById
	TextView text_2_1, text_2_2, text_2_3;

	OrderEntity mOrderEntity;

	@AfterViews
	void init() {
		view_header.setTitle("订单详情");
		mOrderEntity = (OrderEntity) getIntent()
				.getSerializableExtra(EXTRA_KEY);

		getOrderDetail();
	}

	@Click(R.id.btn_save)
	void clickSave() {
		takeInOrder();
	}

	@Click(R.id.image_phone)
	void clickCallPhone() {
		if (!TextUtils.isEmpty(text_phone.getText())) {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ text_phone.getText()));
			startActivity(intent);
		}
	}

	@UiThread
	void refreshViews(OrderDetailVO orderDetailVO) {
		ScheduleVO scheduleVO = orderDetailVO.scheduleVO;
		BaseUserVO baseUserVO = orderDetailVO.baseUserVO;
		if (baseUserVO != null) {
			showHead(baseUserVO.userAvatar);
			text_name.setText(baseUserVO.userName);
			if (!TextUtils.isEmpty(baseUserVO.userMobile))
				text_phone.setText(baseUserVO.userMobile);
		}

		if (scheduleVO != null) {

		}
		// text_time.setText(DateTimeUtil.formatDateTime(Long
		// .valueOf(userAppoint.scheduleStartTime)));
		// text_time_stop.setText(DateTimeUtil.formatDateTime(Long
		// .valueOf(userAppoint.scheduleEndTime)));
		// text_type.setText(orderDetail.subjectTypeName + "("
		// + orderDetail.subjectName + ")");
		// text_site.setText(userAppoint.siteName);
		// text_pickup.setText(orderDetail.isPickUp == 0 ? "否" : "是");
		// String status = userAppoint.orderState;
		// if (TextUtils.equals(status, "0")) {
		// status = "未开始";
		// } else if (TextUtils.equals(status, "1")) {
		// status = "进行中";
		// } else if (TextUtils.equals(status, "2")) {
		// status = "已完成";
		// } else if (TextUtils.equals(status, "3")) {
		// status = "已取消";
		// }
		// text_state.setText(status);
		// if (TextUtils.equals(orderDetail.payType, "1")) {
		// text_payType.setText("网上支付");
		// } else if (TextUtils.equals(orderDetail.payType, "2")) {
		// text_payType.setText("线下支付");
		// }
		//
		// text_payMoney.setText(orderDetail.payMoney);
		// text_payDiscountMoney.setText(orderDetail.payDiscountMoney);
		//
		// showHead(userAppoint.userAvatar);
		// text_name.setText(userAppoint.userName);
		// if (!TextUtils.isEmpty(userAppoint.userMobile))
		// text_phone.setText(userAppoint.userMobile);
	}

	@Background
	void getOrderDetail() {

		showLoading(null);
		OrderDetailResponse response = null;
		try {
			OrderDetailPost data = new OrderDetailPost();
			data.sign = SpHelper.getDefault().getString(SpHelper.SIGN);
			data.myAppointmentId = mOrderEntity.myAppointmentId + "";
			response = ApiManager.getDefault().getOrderDetail(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response != null && response.getResult() != null) {
			refreshViews(response.getResult());
		} else {
			showToast("未获取到订单详情！");
		}
	}

	@Background
	void takeInOrder() {
		showLoading();
		CommonResponse response = null;
		try {
			TakeInOrderPost data = new TakeInOrderPost();
//			data.myAppointmentId = 
			
			response = ApiManager.getDefault().takeInOrder(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response != null
				&& response.getState() == CommonResponse.CODE_SUCCESS) {
			
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
