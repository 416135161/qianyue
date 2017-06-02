/**  
 * @author ningsj@shishike.com
 * @Title: SelectPhotoView.java 
 * @Package com.internet.view 
 * @Description: TODO
 * @date 2016-1-7 下午3:37:00 
 * @version V1.0  
 */
package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.internet.action.ActionSelectPhoto;
import com.internet.qianyue.R;

import de.greenrobot.event.EventBus;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-7 下午3:37:00
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All
 *                      rights reserved.
 */
@EViewGroup(R.layout.view_select_photo)
public class SelectPhotoView extends LinearLayout {
	
	public SelectPhotoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public SelectPhotoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@AfterViews
	void init() {
		
	}
	
}
