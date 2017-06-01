package com.internet.db.service;

import java.sql.SQLException;

import com.internet.db.DBHelper;
import com.internet.db.DBManager;
import com.internet.db.entity.Cat;

/**
 * 测试用DAO, 封装各种Person相关的操作, 如果需要事务应该在这里搞定<br/>
 * 正常情况应该在{@link DBManager}中提供实例化的方法, 由于是测试用就不加了
 * @date 2014-8-18
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
public class CatService {
    
    private DBHelper mDBHelper;
    
    public CatService (DBHelper helper) {
        mDBHelper = helper;
    }
    
    public void create(Cat cat) {
        
        // DBHelper提供2种封装好了的DAO, 根据情况选择
        //case 1
        mDBHelper.getRuntimeExceptionDao(Cat.class).create(cat);
        
        // case 2
        try {
            mDBHelper.getDao(Cat.class).create(cat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //当然你也可以直接使用原始的方法
        // case 3
//        mDBHelper.getWritableDatabase().insert(table, nullColumnHack, values);
        
    }
    
}
