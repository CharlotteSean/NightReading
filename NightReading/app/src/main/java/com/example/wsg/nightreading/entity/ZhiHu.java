package com.example.wsg.nightreading.entity;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.entity
 * 文件名：ZhiHu
 * 创建者：wsg
 * 创建时间：2017/9/8  20:22
 * 描述：知乎日报实体
 */

public class ZhiHu {

    private String title;
    private String images;
    private String  id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ZhiHu{" +
                "title='" + title + '\'' +
                ", images='" + images + '\'' +
                ", id=" + id +
                '}';
    }
}
