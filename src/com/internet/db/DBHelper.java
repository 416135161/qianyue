package com.internet.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.internet.db.entity.Cat;
import com.internet.db.entity.Person;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * 负责所有表的初始化及升级更改维护, </br> 不需要直接访问此类, 所有DB操作请使用{@link DBManager}
 * 
 * @date 2014-8-18
 * @author declan.z(declan.zhang@gmail.com)
 * 
 */
public final class DBHelper extends OrmLiteSqliteOpenHelper {

	public static final String DB_NAME = "print.db";
	public static final int DB_VERSION = 3;

	protected DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			// 这两个表仅供学习ORMLITE 测试用, 可以无视, 以后看情况注释掉就好...
			TableUtils.createTable(connectionSource, Person.class);
			TableUtils.createTable(connectionSource, Cat.class);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {

		// 这两个表仅供学习ORMLITE 测试用, 可以无视, 以后看情况注释掉就好...
		// try {
		// TableUtils.dropTable(connectionSource, Cat.class, true);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// 一般在这里判断2个version, 然后, 做你该做的...............

		// onCreate(db, connectionSource);

	}

}
