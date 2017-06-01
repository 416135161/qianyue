package com.internet.dialog;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import com.internet.action.ActionSelectPhoto;
import com.internet.turnright.b.R;

import de.greenrobot.event.EventBus;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

@EFragment(R.layout.dialog_select_photo)
public class SelectPhotoDialog extends DialogFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);

	}

	@Click(R.id.view)
	void clickView(){
		this.dismiss();
	}
	
	@Click(R.id.text_pz)
	void clickPZ() {
		EventBus.getDefault().post(new ActionSelectPhoto(ActionSelectPhoto.PZ));
		this.dismiss();
	}
	
	@Click(R.id.text_xc)
	void clickXC() {
		EventBus.getDefault().post(new ActionSelectPhoto(ActionSelectPhoto.XC));
		this.dismiss();
	}
	
	@Click(R.id.text_qx)
	void clickQX() {
		this.dismiss();
	}
}
