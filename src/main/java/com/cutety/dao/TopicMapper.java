package com.cutety.dao;

import com.cutety.domain.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/11/25,11:57.
 **/
public interface TopicMapper {
    /**
     * 获取所有帖子
     * @return
     */
    List<Topic> getAllTopics();

    /**
     * 获取所有帖子和用户信息，用于渲染首页
     * @return
     */
    List<Topic> listTopicsAndUsers();

    /**
     * 通过帖子id查看帖子内容，用于渲染帖子详情页
     * @param topicID
     * @return
     */
    List<Topic> getTopicContent(Integer topicID);

    /**
     * 通过帖子id查找帖子
     * @param topicId
     * @return
     */
    Topic selectById(Integer topicId);

    /**
     * 发布新帖
     * @param topic
     * @return
     */
    Integer postATopic(Topic topic);

    /**
     * 通过用户id查找该用户的所有帖子，渲染用户详情页。
     * @param userId
     * @return
     */
    List<Topic> selectTopicsByUserId(Integer userId);

    List<Topic> getAllTopicsByTagName(String tagName);

    /**
     * 更改帖子更新时间
     * @param date
     */
    void alterUpdateTime(@Param("topicId") Integer topicId, @Param("date") Date date);

    /**
     *  增加点击数
     * @param topicId
     */
    void addClickNumber(Integer topicId);

    /**
     * 模糊查找帖子
     * @param description
     * @return
     */
    List<Topic> selectTopicsLike(String description);
}
