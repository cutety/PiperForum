package com.cutety.domain;

import java.util.List;

/**
 * Description:板块实体类
 * Created by cutety on 2019/11/21,1:08 下午.
 **/
public class Tab extends BaseDomain {
    private Integer id;
    private String tabName;
    private String tabNameEn;
    private List<Topic> topics;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabNameEn() {
        return tabNameEn;
    }

    public void setTabNameEn(String tabNameEn) {
        this.tabNameEn = tabNameEn;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
