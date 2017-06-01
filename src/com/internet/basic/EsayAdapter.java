package com.internet.basic;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 简单自定义布局Adapter , <br>
 * 使用时直接new一个子类传入对应的{@link AdapterData}, {@link AdapterView} 即可
 * 
 * @date 2014-7-23
 * @author declan.z(declan.zhang@gmail.com)
 * 
 * @param <T>
 *            List, Grid中的列表数据类型
 * @param <K>
 *            列表中的自定义View
 */
public abstract class EsayAdapter<T extends AdapterData, K extends AdapterView<T>>
		extends BaseAdapter {

	private List<T> mListData;
	private Context mContext;

	public EsayAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return mListData == null ? 0 : mListData.size();
	}

	@Override
	public T getItem(int position) {
		return mListData == null ? null : mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setList(List<T> list) {
		mListData = list;
		notifyDataSetInvalidated();
	}

	public List<T> getList() {
		return mListData;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public View getView(int position, View convertView, ViewGroup parent) {
		K adapterView = null;
		if (convertView == null) {
			try {
				adapterView = (K) ((Class) ((ParameterizedType) this.getClass()
						.getGenericSuperclass()).getActualTypeArguments()[1])
						.getDeclaredMethod("build", Context.class).invoke(null,
								mContext);
				convertView = adapterView.getView();
				convertView.setTag(adapterView);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			adapterView = (K) convertView.getTag();
		}

		T data = getItem(position);
		adapterView.bindData(position, data);

		return convertView;
	}

}
