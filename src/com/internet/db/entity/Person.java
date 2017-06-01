package com.internet.db.entity;

import java.util.Collection;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 测试用类 ,  最简单的实体类配置可以参考此类</br>
 * 一般这里不用显示指定表名, 但不排除以后需要手动写SQL的情况, 所以最好还是有个常量供引用
 * @date 2014-8-18
 * @author declan.z(declan.zhang@gmail.com)
 */
@DatabaseTable
public class Person {
    
    public static final String TABLE_NAME = "Person";
    
    public static final String COL_NAME = "name";
    
    /**
     * 自增长ID 
     */
    @DatabaseField(generatedId=true)
    private int id;
    
    /**
     * 列名其实可以不用写,  但为了方便以后的查询最好还是专门注明下. 
     */
    @DatabaseField(columnName=COL_NAME)
    private String name;
    
    /**
     * 注解还有很多属性可选, 详细参见官网
     */
    @DatabaseField
    private int age;
    
    /**
     * 一对多关联的使用使用这个注解,  而且类型只能是 {@link Collection} 或者 {@link ForeignCollection}
     */
    @ForeignCollectionField
    private Collection<Cat> cats;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Collection<Cat> getCats() {
        return cats;
    }

    public void setCats(Collection<Cat> cats) {
        this.cats = cats;
    }
    
}
