package com.internet.db.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 测试用类 ,  最简单的实体类配置可以参考此类
 * @date 2014-8-18
 * @author declan.z(declan.zhang@gmail.com)
 *
 */
@DatabaseTable
public class Cat {

    public static final String TABLE_NAME = "Cat";
    public static final String COL_PERSON_ID = "person_id";
    
    @DatabaseField(generatedId=true)
    private int id;
    
    @DatabaseField
    private String name;
    
    /**
     * 一对多关联的配置, 列名默认为person_id, 为了方便查询最好还是显示指定
     */
    @DatabaseField(foreign=true, foreignAutoRefresh=true, columnName=COL_PERSON_ID)
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}
