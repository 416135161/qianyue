package com.internet.basic;

import android.view.View;

/**
 * 用于{@link EsayAdapter}的View, 其实不是真正的View, 类似ViewHolder<br>
 * 用interface是方便子类使用不同的layout, 尽可能减少不必要的布局嵌套<br>
 * @date 2014-7-29
 * @author declan.z(declan.zhang@gmail.com)
 *
 * @param <T> 与View绑定的数据类型
 */
public interface AdapterView<T extends AdapterData> {
	
	/**
	 * 返回真正的View对象
	 * @return
	 */
	public abstract View getView();
	
	/**
	 * 将具体数据绑定到View
	 * @param position
	 * @param data
	 */
	public abstract void bindData(int position, T data);
	
}
