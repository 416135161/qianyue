package com.internet.basic;

import android.app.Instrumentation;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import com.internet.action.ActionTitleChange;

import de.greenrobot.event.EventBus;


/**
 * 所有Fragment的父类
 * @date 2014-8-18
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class BasicFragment extends Fragment {

    private boolean isRegisterEventBus = false;
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterEventBus();
    }
    
    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        EventBus.getDefault().register(this);
        isRegisterEventBus = true;
    }
    
    public void unregisterEventBus() {
        if (isRegisterEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }
    
    /**
     * 设置页面标题
     * @param title
     */
    public void setTitle (String title) {
        EventBus.getDefault().post(new ActionTitleChange(title));
    }
    
    /**
     * 用EventBus发送一个事件
     * @param obj
     */
    public void post(Object obj) {
        EventBus.getDefault().post(obj);
    }
    
    /**
     * 用EventBus发送一个Sticky事件
     * @param obj
     */
    public void postSticky(Object obj) {
        EventBus.getDefault().postSticky(obj);
    }
    
    
    /**
     * 显示一组View
     * @param views
     */
    public void show(View... views) {
        for (View view : views) {
            if (view != null) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }
    
    /**
     * 隐藏一组View
     * @param views
     */
    public void hide(View... views) {
        for (View view : views) {
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        }
    }
    
    protected void doBack(){
    	Instrumentation inst = new Instrumentation();
		inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
    }
    
}
