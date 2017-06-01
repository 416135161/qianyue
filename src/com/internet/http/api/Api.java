package com.internet.http.api;

import java.util.Map;

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
import com.internet.http.data.post.SelectSysTypePost;
import com.internet.http.data.post.SendCodePost;
import com.internet.http.data.post.SubmitAuthPost;
import com.internet.http.data.post.TakeInOrderPost;
import com.internet.http.data.post.TextSearchPost;
import com.internet.http.data.post.UpdatePost;
import com.internet.http.data.post.VerifyCodePost;
import com.internet.http.data.response.AnswerQuestionListResponse;
import com.internet.http.data.response.AppointDateResponse;
import com.internet.http.data.response.AreaResponse;
import com.internet.http.data.response.AuthInfoResponse;
import com.internet.http.data.response.CityResponse;
import com.internet.http.data.response.CommonResponse;
import com.internet.http.data.response.GetBannerResponse;
import com.internet.http.data.response.GetCalenderListResponse;
import com.internet.http.data.response.GetOrderCalendarResponse;
import com.internet.http.data.response.GetSiteListResponse;
import com.internet.http.data.response.GetStudentDetailResponse;
import com.internet.http.data.response.GetStudentListResponse;
import com.internet.http.data.response.GetYzwMessageResponse;
import com.internet.http.data.response.ListMessageResponse;
import com.internet.http.data.response.OrderDetailResponse;
import com.internet.http.data.response.OrderListResponse;
import com.internet.http.data.response.ProvinceResponse;
import com.internet.http.data.response.QuestionSubjectResponse;
import com.internet.http.data.response.SelectSysTypeResponse;
import com.internet.http.data.response.TextSearchResponse;
import com.internet.http.data.vo.UserInfoVO;

/**
 * 所有跟服务端交互的调用
 * 
 * @date 2014-8-19
 * @author declan.z(declan.zhang@gmail.com)
 * 
 */
public interface Api {

	/**
	 * 登录, 不返回任何东西, 登录失败则抛出异常
	 * 
	 * @param shopId
	 *            商户ID
	 * @param password
	 *            商户Admin密码
	 * @throws ApiException
	 */
	public void login(String shopId, String password) throws ApiException;

	/**
	 * 请求服务器向手机发送验证码
	 * 
	 * @Author ningsj@shishike.com
	 * @Title: sendCode
	 * @Description: TODO
	 * @Param @param phoneNo
	 * @Param @return
	 * @Param @throws ApiException TODO
	 * @Return boolean 返回类型
	 */
	public boolean sendCode(SendCodePost data) throws ApiException;

	public boolean verifyCode(VerifyCodePost data) throws ApiException;

	public boolean register(RegisterPost data) throws ApiException;

	public boolean login(LoginPost data) throws ApiException;

	public UserInfoVO findUser(FindUserPost data) throws ApiException;

	public TextSearchResponse textSearch(TextSearchPost data)
			throws ApiException;

	public QuestionSubjectResponse questionSubject(QuestionSubjectPost data)
			throws ApiException;

	public TextSearchResponse photoSearch(TextSearchPost data, String photoPath)
			throws ApiException;

	public void update(UpdatePost data, String photoPath) throws ApiException;

	public AnswerQuestionListResponse answerQuestionList(
			AnswerQuestionListPost data) throws ApiException;

	public void deepSearch(DeepSearchPost data, String photoPath)
			throws ApiException;

	public ProvinceResponse provinceSearch() throws ApiException;

	public CityResponse citySearch(CitySearchPost data) throws ApiException;

	public void answerRecord(AnswerRecordPost data) throws ApiException;

	public boolean reSetPwd(ResetPwdPost data) throws ApiException;

	public OrderListResponse queryOrderList(OrderListPost data)
			throws ApiException;

	public boolean editSelfInfo(EditSelfInfoPost data) throws ApiException;

	public AreaResponse areaSearch(AreaSearchPost data) throws ApiException;

	public boolean editSeltInfo(EditSelfInfoPost data, String photoPath)
			throws ApiException;

	public SelectSysTypeResponse selectSysType(SelectSysTypePost data)
			throws ApiException;

	public AuthInfoResponse getAuthInfo(AuthInfoPost data) throws ApiException;

	public boolean submitAuth(SubmitAuthPost data, Map<String, String> mapFile)
			throws ApiException;

	public int getMessageQuntity(GetMessageQuntityPost data)
			throws ApiException;

	public ListMessageResponse listMessage(ListMessagePost data)
			throws ApiException;

	public AppointDateResponse appointDate(AppointDatePost data)
			throws ApiException;

	public GetSiteListResponse getSiteList(GetSiteListPost data)
			throws ApiException;

	public OrderDetailResponse getOrderDetail(OrderDetailPost data)
			throws ApiException;

	public CommonResponse takeInOrder(TakeInOrderPost data) throws ApiException;

	public CommonResponse addStudent(AddStudentPost data) throws ApiException;

	public GetStudentListResponse getStudentList(GetStudentListPost data)
			throws ApiException;

	public GetStudentDetailResponse getStudentDetail(GetStudentDetailPost data)
			throws ApiException;

	public GetCalenderListResponse getCalenderList(GetCalenderListPost data)
			throws ApiException;

	public boolean findPwd(FindPwdPost data) throws ApiException;

	public boolean closeOneKey(CloseOneKeyPost data) throws ApiException;

	public boolean copySchedule(CopySchedulePost data) throws ApiException;

	public boolean releaseCalender(ReleaseCalenderPost data)
			throws ApiException;

	public boolean deleteCalendarById(DeleteCalendarPost data)
			throws ApiException;

	public GetBannerResponse getBannerList(GetBannerPost data)
			throws ApiException;

	public GetOrderCalendarResponse getOrderCalendar(GetOrderCalendarPost data)
			throws ApiException;

	public boolean deleteMessage(DeleteMessagePost data) throws ApiException;

	public boolean deleteStudent(DeleteStudentPost data) throws ApiException;

	public boolean feedBack(FeedBackPost data) throws ApiException;

	public GetYzwMessageResponse getYzwMessage() throws ApiException;

	public CommonResponse editStudent(EditStudentPost data) throws ApiException;
}
