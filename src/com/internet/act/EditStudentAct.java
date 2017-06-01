package com.internet.act;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;

import com.internet.action.ActionAddStudentOK;
import com.internet.basic.BasicActivity;
import com.internet.db.SpHelper;
import com.internet.http.api.ApiException;
import com.internet.http.api.ApiManager;
import com.internet.http.data.post.AddStudentPost;
import com.internet.http.data.post.EditStudentPost;
import com.internet.http.data.response.CommonResponse;
import com.internet.http.data.vo.StudentVO;
import com.internet.turnright.b.R;
import com.internet.view.HeaderView;

@EActivity(R.layout.act_edit_student)
public class EditStudentAct extends BasicActivity {

	@ViewById
	HeaderView view_header;
	@ViewById
	EditText edit_name, edit_phone, edit_remark;

	@ViewById
	RadioButton radio_true, radio_false;

	StudentVO studentData;

	@AfterViews
	void init() {
		view_header.setTitle("编辑学员信息");

		studentData = (StudentVO) getIntent().getSerializableExtra(
				StudentDspAct.INTENT_FLAG);
		edit_name.setText(studentData.getStudentName());
		edit_phone.setText(studentData.getStudentMobile());
		edit_remark.setText(studentData.getRemarks());
		if (studentData.getType() == 1) {
			radio_true.setChecked(true);
		} else {
			radio_false.setChecked(true);
		}
	}

	@Click(R.id.btn_save)
	void clickAdd() {
		String name = edit_name.getEditableText().toString();
		if (TextUtils.isEmpty(name)) {
			showToast("请输入学员名称");
			return;
		}
		String phone = edit_phone.getEditableText().toString();
		if (TextUtils.isEmpty(phone)) {
			showToast("请输入学员手机号");
			return;
		}
		String remark = edit_remark.getEditableText().toString();
		addStuent(name, phone, remark);
	}

	@Background
	void addStuent(String name, String phone, String remark) {
		showLoading();
		CommonResponse response = null;
		try {
			EditStudentPost data = new EditStudentPost(SpHelper.getDefault()
					.getString(SpHelper.SIGN), name, phone);
			data.driverStudentId = studentData.getDriverStudentId();
			data.type = radio_true.isChecked() ? "1" : "2";
			data.remark = remark;
			response = ApiManager.getDefault().editStudent(data);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showToast(e.getErrorMessage());
		}
		closeLoading();
		if (response != null
				&& response.getState() == CommonResponse.CODE_SUCCESS) {
			showToast("编辑学员成功！");
			onOk();
		}
	}

	@UiThread
	void onOk() {
		post(new ActionAddStudentOK());
		doBack();
	}

}
