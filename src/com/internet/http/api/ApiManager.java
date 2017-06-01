package com.internet.http.api;

import java.util.HashMap;
import java.util.Map;

import com.internet.db.SpHelper;
import com.internet.http.HttpHelper;
import com.internet.http.data.post.AddSchedulePost;
import com.internet.http.data.post.AddStudentPost;
import com.internet.http.data.post.AnswerQuestionListPost;
import com.internet.http.data.post.AnswerRecordPost;
import com.internet.http.data.post.AppointDatePost;
import com.internet.http.data.post.AreaSearchPost;
import com.internet.http.data.post.AuthInfoPost;
import com.internet.http.data.post.CitySearchPost;
import com.internet.http.data.post.CloseOneKeyPost;
import com.internet.http.data.post.CopySchedulePost;
import com.internet.http.data.post.DeepSearchPost;
import com.internet.http.data.post.DelSchedulePost;
import com.internet.http.data.post.DeleteCalendarPost;
import com.internet.http.data.post.DeleteMessagePost;
import com.internet.http.data.post.DeleteStudentPost;
import com.internet.http.data.post.EditSelfInfoPost;
import com.internet.http.data.post.EditStudentPost;
import com.internet.http.data.post.FeedBackPost;
import com.internet.http.data.post.FindPwdPost;
import com.internet.http.data.post.FindUserPost;
import com.internet.http.data.post.GetBannerPost;
import com.internet.http.data.post.GetCalenderListPost;
import com.internet.http.data.post.GetMessageQuntityPost;
import com.internet.http.data.post.GetOrderCalendarPost;
import com.internet.http.data.post.GetSiteListPost;
import com.internet.http.data.post.GetStudentDetailPost;
import com.internet.http.data.post.GetStudentListPost;
import com.internet.http.data.post.ListMessagePost;
import com.internet.http.data.post.LoginPost;
import com.internet.http.data.post.OrderDetailPost;
import com.internet.http.data.post.OrderListPost;
import com.internet.http.data.post.QuestionSubjectPost;
import com.internet.http.data.post.RegisterPost;
import com.internet.http.data.post.ReleaseCalenderPost;
import com.internet.http.data.post.ResetPwdPost;
import com.internet.http.data.post.SchedulePost;
import com.internet.http.data.post.SelectSysTypePost;
import com.internet.http.data.post.SendCodePost;
import com.internet.http.data.post.SubmitAuthPost;
import com.internet.http.data.post.TakeInOrderPost;
import com.internet.http.data.post.TextSearchPost;
import com.internet.http.data.post.UpdatePost;
import com.internet.http.data.post.UpdateSchedulePost;
import com.internet.http.data.post.VerifyCodePost;
import com.internet.http.data.response.AnswerQuestionListResponse;
import com.internet.http.data.response.AppointDateResponse;
import com.internet.http.data.response.AreaResponse;
import com.internet.http.data.response.AuthInfoResponse;
import com.internet.http.data.response.CityResponse;
import com.internet.http.data.response.CommonResponse;
import com.internet.http.data.response.EditSelfInfoResponse;
import com.internet.http.data.response.FindUserResponse;
import com.internet.http.data.response.GetBannerResponse;
import com.internet.http.data.response.GetCalenderListResponse;
import com.internet.http.data.response.GetMessageQuntityResponse;
import com.internet.http.data.response.GetOrderCalendarResponse;
import com.internet.http.data.response.GetSiteListResponse;
import com.internet.http.data.response.GetStudentDetailResponse;
import com.internet.http.data.response.GetStudentListResponse;
import com.internet.http.data.response.GetYzwMessageResponse;
import com.internet.http.data.response.ListMessageResponse;
import com.internet.http.data.response.LoginResponse;
import com.internet.http.data.response.OrderDetailResponse;
import com.internet.http.data.response.OrderListResponse;
import com.internet.http.data.response.ProvinceResponse;
import com.internet.http.data.response.QuestionSubjectResponse;
import com.internet.http.data.response.RegistResponse;
import com.internet.http.data.response.ScheduleResponse;
import com.internet.http.data.response.SelectSysTypeResponse;
import com.internet.http.data.response.TextSearchResponse;
import com.internet.http.data.response.UpdateResponse;
import com.internet.http.data.vo.UserInfoVO;
import com.internet.util.JsonUtil;
import com.internet.util.Utils;

/**
 * 处理所有服务端接口调用, 所有动作不会新开线程
 * 
 * @date 2014-8-19
 * @author declan.z(declan.zhang@gmail.com)
 * 
 */
public class ApiManager implements Api {

	 public static String mUriRoot = "http://112.74.198.172:9527/yzw_app"; //测试环境

//	public static String mUriRoot = "http://api.youzhuanwan.cn"; // 正式环境

	public static final String URI_SEND_CODE = "%1$s/app/sms/sendCode.json";

	public static final String URI_VERIFY_CODE = "%1$s/ys_mob/api/user/verifyCode";

	public static final String URI_REGISTER = "%1$s/app/driver/register.json";

	public static final String URI_LOGIN = "%1$s/app/driver/login.json";

	public static final String URI_FINDUSER = "%1$s/app/driver/info.json";

	public static final String URI_TEXT_SEARCH = "%1$s/ys_mob/api/questions/txtsearch";

	public static final String URI_PHOTO_SRARCH = "%1$s/ys_mob/api/questions/photosearch";

	public static final String URI_QUESTION_SUBJECT = "%1$s/ys_mob/api/questions/questionSubject";

	public static final String URI_UPDATE = "%1$s/ys_mob/api/user/update";

	public static final String URI_ANSWER_QUESTIPN_LIST = "%1$s/ys_mob/api/questions/answerQuestionList";

	public static final String URI_DEEP_SEARCH = "%1$s/ys_mob/api/questions/submitAnswerQuestion";

	public static final String URI_PROVINCR_SEARCH = "%1$s/ys_mob/api/user/province";

	public static final String URI_CITY_SEARCH = "%1$s/ys_mob/api/user/city";

	public static final String URI_AREA_SEARCH = "%1$s/app/region/findRegionByParentId.json";

	public static final String URI_ANSWER_RECORD = "%1$s/ys_mob/api/questions/answerQuestionRecord";

	public static final String URI_UPDATE_PWD = "%1$s/ys_mob/api/user/updatepwd";

	public static final String URI_ORDER_LIST = "%1$s/app/order/list.json";

	public static final String URI_EDIT_SELF_INFFO = "%1$s/app/driver/setting.json";

	public static final String URI_SELECT_SYS_TYPE = "%1$s/app/dict/selectSysTypeDictByParentId.json";

	public static final String URI_GET_AUTH_INFO = "%1$s/app/driver/authInfo.json";

	public static final String URI_SUBMIT_AUTH = "%1$s/app/driver/submitauth.json";

	public static final String URI_GET_MESSAGE_QUNTITY = "%1$s/app/msg/count.json";

	public static final String URI_LIST_MESSAGE = "%1$s/app/msg/list.json";

	public static final String URI_APPOINT_DATE = "%1$s/app/driver/appointDate.json";

	public static final String URI_GET_SITE_LIST = "%1$s/app/site/list.json";

	public static final String URI_GET_ORDER_DETAIL = "%1$s/app/order/detail.json";

	public static final String URI_TAKE_IN_ORDER = "%1$s/app/order/optionOrder.json";

	public static final String URI_ADD_STUDENT = "%1$s/app/driver/addStudent.json";

	public static final String URI_GET_STUDENT_LIST = "%1$s/app/driver/studentList.json";

	public static final String URI_GET_STUDENT_DETAIL = "%1$s/app/driver/getUserDetail.json";

	public static final String URI_GET_CALENDER = "%1$s/app/driver/calenderList.json";

	public static final String URI_FIND_PWD = "%1$s/app/driver/retrievepwd.json";

	public static final String URI_ONE_KEY_CLOSE = "%1$s/app/driver/closeOneKey.json";

	public static final String URI_COPY_SCHEDULE = "%1$s/app/driver/copySchedule.json";

	public static final String URI_RELEASE_CALENDER = "%1$s/app/driver/diverCalender.json";

	public static final String URI_DELETE_CALENDAR = "%1$s/app/driver/deleteCalendarById.json";

	public static final String URI_SCHEDULE = "%1$s/app/driver/schedule.json";
	public static final String URI_ADDSCHEDULE = "%1$s/app/driver/addschedule.json";
	public static final String URI_DELSCHEDULE = "%1$s/app/driver/delschedule.json";
	public static final String URI_UPDATESCHEDULE = "%1$s/app/driver/updateSchedule.json";

	public static final String URI_GET_BANNER_LIST = "%1$s/app/banner/list.json";

	public static final String URI_GET_ORDER_CALENDAR = "%1$s/app/order/orderCalendar.json";

	public static final String URI_DELETE_MESSAGE = "%1$s/app/msg/delete.json";

	public static final String URI_DELETE_STUDENT = "%1$s/app/driver/delStudent.json";

	public static final String URI_FEED_BACK = "%1$s/app/user/feedback.json";

	public static final String URI_YZW_MESSAGE = "%1$s/app/banner/getYzwMessage.json";
	
	public static final String URI_EDIT_STUDENT = "%1$s/app/driver/editStudent.json";
	
	private static ApiManager mInstance;

	private ApiManager() {

	}

	public static synchronized ApiManager getDefault() {
		if (mInstance == null) {
			mInstance = new ApiManager();
		}
		return mInstance;
	}

	public void setServerUri(String uri) {
		mUriRoot = uri;

	}

	@Override
	public void login(String shopId, String password) throws ApiException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean sendCode(SendCodePost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_SEND_CODE, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "发送验证码失败"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public boolean verifyCode(VerifyCodePost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_VERIFY_CODE, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = (CommonResponse) JsonUtil.jsonToObject(
				result, CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "发送验证码失败"
					: response.getMsg());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#register(com.internet
	 * .yousheng.http.data.post.LoginPost)
	 */
	@Override
	public boolean register(RegisterPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_REGISTER, mUriRoot),
				Utils.objectToMap(data));
		RegistResponse response = JsonUtil.jsonToObject(result,
				RegistResponse.class);
		if (response == null) {
			throw new ApiException("注册失败");
		} else if (response.getState() != CommonResponse.CODE_SUCCESS) {
			if (response.getState() == 104) {
				throw new ApiException("验证码输入失败");
			} else if (response.getState() == 105) {
				throw new ApiException("密码太简单");
			} else
				throw new ApiException(response == null ? "注册失败"
						: response.getMsg());

		}
		SpHelper.getDefault().putString(SpHelper.SIGN,
				response.getResult().getSign());
		SpHelper.getDefault().putString(SpHelper.DRIVER_ID,
				response.getResult().getUserId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#login(com.internet
	 * .yousheng.http.data.post.LoginPost)
	 */
	@Override
	public boolean login(LoginPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_LOGIN, mUriRoot),
				Utils.objectToMap(data));
		LoginResponse response = JsonUtil.jsonToObject(result,
				LoginResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "登录失败"
					: response.getMsg());
		}
		SpHelper.getDefault().putString(SpHelper.SIGN,
				response.getResult().getSign());
		SpHelper.getDefault().putString(SpHelper.DRIVER_ID,
				response.getResult().getUserId());
		SpHelper.getDefault().putString(SpHelper.USER_TYPE,
				response.getResult().getUserType() + "");
		return true;
	}

	@Override
	public UserInfoVO findUser(FindUserPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_FINDUSER, mUriRoot),
				Utils.objectToMap(data));
		FindUserResponse response = (FindUserResponse) JsonUtil.jsonToObject(
				result, FindUserResponse.class);
		if (response == null) {
			throw new ApiException("未知异常！");
		} else {
			if (response.getState() == CommonResponse.CODE_SUCCESS
					&& response.getResult() != null) {
				return response.getResult();
			} else {
				throw new ApiException(response == null ? 10000
						: response.getState());
			}

		}
	}

	@Override
	public TextSearchResponse textSearch(TextSearchPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_TEXT_SEARCH, mUriRoot),
				Utils.objectToMap(data));
		TextSearchResponse response = (TextSearchResponse) JsonUtil
				.jsonToObject(result, TextSearchResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#questionSubject (com
	 * .internet.yousheng.http.data.post.QuestionSubjectPost )
	 */
	@Override
	public QuestionSubjectResponse questionSubject(QuestionSubjectPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_QUESTION_SUBJECT, mUriRoot),
				Utils.objectToMap(data));
		QuestionSubjectResponse response = (QuestionSubjectResponse) JsonUtil
				.jsonToObject(result, QuestionSubjectResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#photoSearch(com
	 * .internet.yousheng.http.data.post.TextSearchPost, java.lang.String)
	 */
	@Override
	public TextSearchResponse photoSearch(TextSearchPost data, String photoPath)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_PHOTO_SRARCH, mUriRoot),
				Utils.objectToMap(data), photoPath, "photo");
		TextSearchResponse response = (TextSearchResponse) JsonUtil
				.jsonToObject(result, TextSearchResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#update(com.internet
	 * .yousheng.http.data.post.UpdatePost)
	 */
	@Override
	public void update(UpdatePost data, String photoPath) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_UPDATE, mUriRoot),
				Utils.objectToMap(data), photoPath, "avatar");
		UpdateResponse response = (UpdateResponse) JsonUtil.jsonToObject(
				result, UpdateResponse.class);
		if (response.getData() != null) {

		}
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#questionList(com
	 * .internet.yousheng.http.data.post.QuestionListPost)
	 */
	@Override
	public AnswerQuestionListResponse answerQuestionList(
			AnswerQuestionListPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(
				String.format(URI_ANSWER_QUESTIPN_LIST, mUriRoot),
				Utils.objectToMap(data));
		AnswerQuestionListResponse response = (AnswerQuestionListResponse) JsonUtil
				.jsonToObject(result, AnswerQuestionListResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#deepSearch(com
	 * .internet.yousheng.http.data.post.DeepSearchPost)
	 */
	@Override
	public void deepSearch(DeepSearchPost data, String photoPath)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_DEEP_SEARCH, mUriRoot),
				Utils.objectToMap(data), photoPath, "picContent");
		CommonResponse response = (CommonResponse) JsonUtil.jsonToObject(
				result, CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#provinceSearch()
	 */
	@Override
	public ProvinceResponse provinceSearch() throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_PROVINCR_SEARCH, mUriRoot));
		ProvinceResponse response = (ProvinceResponse) JsonUtil.jsonToObject(
				result, ProvinceResponse.class);
		if (response == null) {
			throw new ApiException("未知异常！");
		} else {

			if (response.getState() == CommonResponse.CODE_SUCCESS) {

			} else
				throw new ApiException(response == null ? "未知异常！"
						: response.getMsg());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#citySearch(com
	 * .internet.yousheng.http.data.post.CitySearchPost)
	 */
	@Override
	public CityResponse citySearch(CitySearchPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_CITY_SEARCH, mUriRoot),
				Utils.objectToMap(data));
		CityResponse response = (CityResponse) JsonUtil.jsonToObject(result,
				CityResponse.class);
		if (response == null) {
			throw new ApiException("未知异常！");
		} else {

			if (response.getState() == CommonResponse.CODE_SUCCESS) {

			} else
				throw new ApiException(response == null ? "未知异常！"
						: response.getMsg());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#answerRecord(com
	 * .internet.yousheng.http.data.post.AnswerRecordPost)
	 */
	@Override
	public void answerRecord(AnswerRecordPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_ANSWER_RECORD, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = (CommonResponse) JsonUtil.jsonToObject(
				result, CommonResponse.class);
		if (response == null) {
			throw new ApiException("未知异常！");
		} else {

			if (response.getState() == CommonResponse.CODE_SUCCESS) {

			} else
				throw new ApiException(response == null ? "未知异常！"
						: response.getMsg());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internet.yousheng.http.api.Api#reSetPwd(com.internet
	 * .yousheng.http.data.post.ResetPwdPost)
	 */
	@Override
	public boolean reSetPwd(ResetPwdPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_UPDATE_PWD, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = (CommonResponse) JsonUtil.jsonToObject(
				result, CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "修改密码失败"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public OrderListResponse queryOrderList(OrderListPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_ORDER_LIST, mUriRoot),
				Utils.objectToMap(data));
		OrderListResponse response = JsonUtil.jsonToObject(result,
				OrderListResponse.class);
		if (response == null) {
			throw new ApiException("未知异常！");
		} else {

			if (response.getState() == CommonResponse.CODE_SUCCESS) {

			} else
				throw new ApiException(response == null ? "未知异常！"
						: response.getMsg());
		}
		return response;
	}

	@Override
	public boolean editSelfInfo(EditSelfInfoPost data) throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	public AreaResponse areaSearch(AreaSearchPost data) throws ApiException {
		String result = null;
		result = HttpHelper.post(String.format(URI_AREA_SEARCH, mUriRoot),
				Utils.objectToMap(data));
		AreaResponse response = JsonUtil.jsonToObject(result,
				AreaResponse.class);
		if (response == null) {
			throw new ApiException("未知异常！");
		} else {
			if (response.getState() == CommonResponse.CODE_SUCCESS) {
				return response;
			} else
				throw new ApiException(response == null ? "未知异常！"
						: response.getMsg());
		}

	}

	@Override
	public boolean editSeltInfo(EditSelfInfoPost data, String photoPath)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_EDIT_SELF_INFFO, mUriRoot),
				Utils.objectToMap(data), photoPath, "file");
		EditSelfInfoResponse response = JsonUtil.jsonToObject(result,
				EditSelfInfoResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}

		return true;
	}

	@Override
	public SelectSysTypeResponse selectSysType(SelectSysTypePost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_SELECT_SYS_TYPE, mUriRoot),
				Utils.objectToMap(data));
		SelectSysTypeResponse response = JsonUtil.jsonToObject(result,
				SelectSysTypeResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}

		return response;
	}

	@Override
	public AuthInfoResponse getAuthInfo(AuthInfoPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_GET_AUTH_INFO, mUriRoot),
				Utils.objectToMap(data));
		AuthInfoResponse response = JsonUtil.jsonToObject(result,
				AuthInfoResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}

		return response;
	}

	@Override
	public boolean submitAuth(SubmitAuthPost data, Map<String, String> mapFile)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_SUBMIT_AUTH, mUriRoot),
				Utils.objectToMap(data), mapFile);
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public int getMessageQuntity(GetMessageQuntityPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(
				String.format(URI_GET_MESSAGE_QUNTITY, mUriRoot),
				Utils.objectToMap(data));
		GetMessageQuntityResponse response = JsonUtil.jsonToObject(result,
				GetMessageQuntityResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response.getResult();
	}

	@Override
	public ListMessageResponse listMessage(ListMessagePost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_LIST_MESSAGE, mUriRoot),
				Utils.objectToMap(data));
		ListMessageResponse response = JsonUtil.jsonToObject(result,
				ListMessageResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public AppointDateResponse appointDate(AppointDatePost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_APPOINT_DATE, mUriRoot),
				Utils.objectToMap(data));
		AppointDateResponse response = JsonUtil.jsonToObject(result,
				AppointDateResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;

	}

	@Override
	public GetSiteListResponse getSiteList(GetSiteListPost data)
			throws ApiException {
		// TODO Auto-generated method stub

		String result = null;
		result = HttpHelper.post(String.format(URI_GET_SITE_LIST, mUriRoot),
				Utils.objectToMap(data));
		GetSiteListResponse response = JsonUtil.jsonToObject(result,
				GetSiteListResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public OrderDetailResponse getOrderDetail(OrderDetailPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_GET_ORDER_DETAIL, mUriRoot),
				Utils.objectToMap(data));
		OrderDetailResponse response = JsonUtil.jsonToObject(result,
				OrderDetailResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public CommonResponse takeInOrder(TakeInOrderPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_TAKE_IN_ORDER, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public CommonResponse addStudent(AddStudentPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_ADD_STUDENT, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public GetStudentListResponse getStudentList(GetStudentListPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_GET_STUDENT_LIST, mUriRoot),
				Utils.objectToMap(data));
		GetStudentListResponse response = JsonUtil.jsonToObject(result,
				GetStudentListResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public GetStudentDetailResponse getStudentDetail(GetStudentDetailPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(
				String.format(URI_GET_STUDENT_DETAIL, mUriRoot),
				Utils.objectToMap(data));
		GetStudentDetailResponse response = JsonUtil.jsonToObject(result,
				GetStudentDetailResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public GetCalenderListResponse getCalenderList(GetCalenderListPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_GET_CALENDER, mUriRoot),
				Utils.objectToMap(data));
		GetCalenderListResponse response = JsonUtil.jsonToObject(result,
				GetCalenderListResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public boolean findPwd(FindPwdPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_FIND_PWD, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			if (response.getState() == 104)
				throw new ApiException(":验证码输入失败");
			else if (response.getState() == 105)
				throw new ApiException("密码太简单");
			else if (response.getState() == 106)
				throw new ApiException("没有该用户");
			else
				throw new ApiException(response == null ? "未知异常"
						: response.getMsg());
		}
		return true;
	}

	@Override
	public boolean closeOneKey(CloseOneKeyPost data) throws ApiException {
		String result = null;
		result = HttpHelper.post(String.format(URI_ONE_KEY_CLOSE, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public boolean copySchedule(CopySchedulePost data) throws ApiException {

		String result = null;
		result = HttpHelper.post(String.format(URI_COPY_SCHEDULE, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public boolean releaseCalender(ReleaseCalenderPost data)
			throws ApiException {
		String result = null;
		result = HttpHelper.post(String.format(URI_RELEASE_CALENDER, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public boolean deleteCalendarById(DeleteCalendarPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_DELETE_CALENDAR, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	public ScheduleResponse schedule(SchedulePost data) throws ApiException {
		String result = null;
		result = HttpHelper.post(String.format(URI_SCHEDULE, mUriRoot),
				Utils.objectToMap(data));
		ScheduleResponse response = JsonUtil.jsonToObject(result,
				ScheduleResponse.class);
		if (response == null || !response.state.equals("1")) {
			throw new ApiException(response == null ? "未知异常" : response.msg);
		}
		return response;

	}

	public boolean addschedule(AddSchedulePost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_ADDSCHEDULE, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	public boolean delschedule(DelSchedulePost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_DELSCHEDULE, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	public boolean updateSchedule(UpdateSchedulePost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_UPDATESCHEDULE, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public GetBannerResponse getBannerList(GetBannerPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_GET_BANNER_LIST, mUriRoot),
				Utils.objectToMap(data));
		GetBannerResponse response = JsonUtil.jsonToObject(result,
				GetBannerResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public GetOrderCalendarResponse getOrderCalendar(GetOrderCalendarPost data)
			throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(
				String.format(URI_GET_ORDER_CALENDAR, mUriRoot),
				Utils.objectToMap(data));
		GetOrderCalendarResponse response = JsonUtil.jsonToObject(result,
				GetOrderCalendarResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

	@Override
	public boolean deleteMessage(DeleteMessagePost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_DELETE_MESSAGE, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public boolean deleteStudent(DeleteStudentPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_DELETE_STUDENT, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public boolean feedBack(FeedBackPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_FEED_BACK, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return true;
	}

	@Override
	public GetYzwMessageResponse getYzwMessage() throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_YZW_MESSAGE, mUriRoot),
				new HashMap<String, String>());
		GetYzwMessageResponse response = JsonUtil.jsonToObject(result,
				GetYzwMessageResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}
	
	@Override
	public CommonResponse editStudent(EditStudentPost data) throws ApiException {
		// TODO Auto-generated method stub
		String result = null;
		result = HttpHelper.post(String.format(URI_EDIT_STUDENT, mUriRoot),
				Utils.objectToMap(data));
		CommonResponse response = JsonUtil.jsonToObject(result,
				CommonResponse.class);
		if (response == null
				|| response.getState() != CommonResponse.CODE_SUCCESS) {
			throw new ApiException(response == null ? "未知异常"
					: response.getMsg());
		}
		return response;
	}

}
