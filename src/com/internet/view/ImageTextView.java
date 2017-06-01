package com.internet.view;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.internet.turnright.b.R;

@EViewGroup(R.layout.view_image_text)
public class ImageTextView extends LinearLayout {
	@ViewById
	ImageView image;
	@ViewById
	TextView text;

	public ImageTextView(Context context) {
		super(context);

	}

	public ImageTextView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@AfterViews
	void init() {
		setOrientation(VERTICAL);
	}

	public void setText(String t) {
		text.setText(t);
	}

	public void setImage(Bitmap bm) {
		image.setImageBitmap(bm);
	}
}
