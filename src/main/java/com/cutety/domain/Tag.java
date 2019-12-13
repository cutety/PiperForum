package com.cutety.domain;

/**
 * Description:
 * Created by cutety on 2019/12/1,16:14.
 **/
public class Tag extends BaseDomain {
    private Integer id;
    private String tagName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
