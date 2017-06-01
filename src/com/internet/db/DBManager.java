package com.internet.db;

import android.app.Application;

import com.internet.db.service.CatService;

/**
 * 所有DB相关操作的入口都应该在这里</br>
 * 使用前可以简单了解ormlite的相关api (http://ormlite.com/)</br>
 * 
 * 
 * 需要查看详细日志请执行以下操作 </br>
 * adb shell  </br>
 * setprop log.tag.ORMLite DEBUG </br>
 * 
 * 
 * 建议在Application中完成初始化{@link #init(Application)}
 * @date 2014-8-18
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public final class DBManager {

    private static DBManager mInstance;
    private DBHelper mDBHelper;
    
    private CatService mCatService;
  
    
    private DBManager() { 
        
    }

    /**
     * 不在这里传入Context, 而是提供单独的init方法, 主要为了避免每次传入Context
     * @return
     */
    public static DBManager getDefault() {
        if (mInstance == null) {
            mInstance = new DBManager();
        }
        return mInstance;
    }
    
    /**
     * 初始化DB
     * @param context
     */
    public void init(Application context) {
        mDBHelper = new DBHelper(context);
    }
    
    public synchronized CatService getCatService(){
    	if(mCatService == null){
    		mCatService = new CatService(mDBHelper);
    	}
    	return mCatService;
    }
   
}
