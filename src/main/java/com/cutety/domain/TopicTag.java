package com.cutety.domain;

/**
 * Description:
 * Created by cutety on 2019/12/6,10:44.
 **/
public class TopicTag {
    private Integer id;
    private Integer topicId;
    private Integer tagId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
