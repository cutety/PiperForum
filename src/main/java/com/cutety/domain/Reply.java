package com.cutety.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:回复实体类
 * Created by cutety on 2019/11/21,1:18 下午.
 **/
public class Reply extends BaseDomain{
    private User user;
    private Long id;
    private Integer topicId;
    private Integer replyUserId;
    private Date createTime;
    private Date updateTime;
    private String device;
    private String content;
    private Integer likes;
    private Integer dislike;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocalCreateTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-M dd HH:mm:ss");//设置日期格式
        String date=df.format(updateTime);
        return date;
    }
    public String getLocalUpdateTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-M dd HH:mm:ss");//设置日期格式
        String date=df.format(updateTime);
        return date;
    }
}
