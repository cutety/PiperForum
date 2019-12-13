package com.cutety.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Description:帖子实体类
 * Created by cutety on 2019/11/21,1:12 下午.
 **/
public class Topic extends BaseDomain {
    private User user;
    private Tab tab;
    private String tags;
    private Tag tag;
    private TopicTag topicTag;
    private  Integer countReplies;
    private Integer id;
    private Integer userId;
    private Date createTime;
    private Date updateTime;
    private String title;
    private Integer click;
    private Byte tabId;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private List<String> tagAry;

    public List<String> getTagAry() {
        return tagAry;
    }

    public void setTagAry(List<String> tagAry) {
        this.tagAry = tagAry;
    }

    public TopicTag getTopicTag() {
        return topicTag;
    }

    public void setTopicTag(TopicTag topicTag) {
        this.topicTag = topicTag;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public Integer getCountReplies() {
        return countReplies;
    }

    public void setCountReplies(Integer countReplies) {
        this.countReplies = countReplies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Byte getTabId() {
        return tabId;
    }

    public void setTabId(Byte tabId) {
        this.tabId = tabId;
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
