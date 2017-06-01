/**  
 * @author ningsj@shishike.com
 * @Title: PaperItemView.java 
 * @Package com.internet.view 
 * @Description: TODO
 * @date 2016-1-11 下午5:17:35 
 * @version V1.0  
*/
package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.turnright.b.R;

/**
 * @Author: ningsj@shishike.com
 * @Date：2016-1-11 下午5:17:35
 * @Description: TODO
 * @Version: 1.0
 * @CopyRight：Copyright ＠2050 keruyun Incorporated. All rights reserved.
 */
@EViewGroup(R.layout.view_paper_item)
public class PaperItemView extends LinearLayout {
	@ViewById
	ImageView image;
	
	@ViewById
	TextView text;
	
	public PaperItemView(Context context) {
		super(context);
		
	}
	
	public PaperItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	@AfterViews
	void init() {
		setOrientation(VERTICAL);
		setGravity(Gravity.CENTER_HORIZONTAL);
	}
	
	public void setText(String title) {
		text.setText(title);
	}
	
	public void setImage(Bitmap bitmap){
		if(bitmap != null)
			image.setImageBitmap(bitmap);
	}
}