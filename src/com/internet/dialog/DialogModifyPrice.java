package com.internet.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.internet.http.data.response.ScheduleResponse.ResultBean.TimeOfDaysBean;
import com.internet.util.ToastUtil;
import com.internet.qianyue.R;

public class DialogModifyPrice extends Dialog implements
		android.view.View.OnClickListener {

	private Window window = null;
	private DialogClickListener mListener;

	EditText  et;

	public DialogModifyPrice(Context context) {
		super(context, R.style.DialogTheme);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_modify_price);
		this.window = getWindow();

		WindowManager.LayoutParams localLayoutParams = getWindow()
				.getAttributes();

		localLayoutParams.height = LayoutParams.WRAP_CONTENT;
		localLayoutParams.width = LayoutParams.MATCH_PARENT;
		
		this.window.setAttributes(localLayoutParams);

		
		setCanceledOnTouchOutside(true);
		setCancelable(true);
		initViews();
	}

	private void initViews() {
		View tvOk =  findViewById(R.id.tv_ok);
		View iv_cancel = findViewById(R.id.iv_cancel);
		  et = (EditText) findViewById(R.id.et);
		
		

		if (mListener != null) {
			tvOk.setOnClickListener(this);
			
		}
		
		iv_cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				cancel();
				
			}
		});

	}

	public void setDialogListener(DialogClickListener paramListener) {
		mListener = paramListener;
	}
	
	public void setTimeList(List<TimeOfDaysBean> list){
		GridView gridView = (GridView) findViewById(R.id.gridView);
		
		GridAdapter adapter = 	new GridAdapter(getContext());
		
		gridView.setAdapter(adapter);
		
		adapter.setList(list);
		if (list!=null&&!list.isEmpty()) {
			
			et.setText(list.get(0).price+"");;
			
		}
	}

	@Override
	public void onClick(View v) {
		
		int price = 0;
		if (mListener != null) {
			
			String str = et.getText().toString();
			
			try {
				price = 	Integer.parseInt(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		if (price>0) {
			mListener.onChlidViewClick(v,price);
			this.cancel();
		}else{
			
			ToastUtil.showShortToast(getContext(), "单价必须大于0");
		}
		
	}



	public interface DialogClickListener {

		void onChlidViewClick(View v,int price);

	}
	
	
	
	
	class GridAdapter extends BaseAdapter {

		private List<TimeOfDaysBean> list;
		private Context context;

		public GridAdapter(Context context) {
			super();
			this.list = new ArrayList<TimeOfDaysBean>();
			this.context = context;
		}

		@Override
		public int getCount() {
			if (list == null || list.isEmpty()) {
				return 0;
			}
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public void setList(List<TimeOfDaysBean> list) {

			this.list = list;
			notifyDataSetChanged();
		

		}

		public void addItem(TimeOfDaysBean bean) {

			if (list == null) {
				list = new ArrayList<TimeOfDaysBean>();
			}

			list.add(bean);
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			TextView  tv;
			if (view == null) {
				tv = new TextView(context);
				view = tv;
				
				tv.setTextColor(context.getResources().getColor(R.color.text_gray));
				tv.setTextSize(13);
				
			}else{
				tv = (TextView) view;
			}
			
			TimeOfDaysBean  bean =	list.get(position);

			tv.setText(bean.strCurrTime+"~"+bean.strNextTime);

			return view;
		}

	}

}
