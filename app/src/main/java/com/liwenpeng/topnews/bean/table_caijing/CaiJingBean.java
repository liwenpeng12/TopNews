package com.liwenpeng.topnews.bean.table_caijing;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * liwenpeng
 * 2018/4/6 12:34
 */
@Entity
public class CaiJingBean {
    @Id
    private Long id;
    @Property(nameInDb = "TITLE")
    private String title;

    @Property(nameInDb = "DATE")
    private String date;

    @Property(nameInDb = "CATEGORY")
    private String category;

    @Property(nameInDb = "CATEGORY_NAME")
    private String author_name;

    @Property(nameInDb = "URL")
    private String url;

    @Property(nameInDb = "THUMBNAIL_PIC_S")
    private String thumbnail_pic_s;

    @Generated(hash = 541957033)
    public CaiJingBean(Long id, String title, String date, String category,
            String author_name, String url, String thumbnail_pic_s) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.category = category;
        this.author_name = author_name;
        this.url = url;
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    @Generated(hash = 1851054428)
    public CaiJingBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return this.author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return this.thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
}
