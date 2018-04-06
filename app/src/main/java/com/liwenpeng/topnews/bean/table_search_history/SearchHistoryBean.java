package com.liwenpeng.topnews.bean.table_search_history;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * liwenpeng
 * 2018/4/6 15:30
 */
@Entity
public class SearchHistoryBean {

    @Id
    private Long id;

    @Property(nameInDb = "world")
    private String world;

    @Property(nameInDb = "targeturl")
    private String targiturl;

    @Generated(hash = 1038526010)
    public SearchHistoryBean(Long id, String world, String targiturl) {
        this.id = id;
        this.world = world;
        this.targiturl = targiturl;
    }

    @Generated(hash = 1570282321)
    public SearchHistoryBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorld() {
        return this.world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getTargiturl() {
        return this.targiturl;
    }

    public void setTargiturl(String targiturl) {
        this.targiturl = targiturl;
    }
}
