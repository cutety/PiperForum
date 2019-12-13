package com.cutety.service;

import com.cutety.domain.Topic;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/11/25,11:51.
 **/
public interface TopicService {
    List<Topic> getAllTopics();
    List<Topic> listTopicsAndUsers();
    List<Topic> getTopicContent(Integer topicID);
    Topic selectById(Integer topicId);
    Integer postATopic(Topic topic);
    List<Topic> selectTopicsByUserId(Integer userId);

    List<Topic> getAllTopicsByTagName(String tagName);
    void alterUpdateTime(Integer topicId,Date date);
    void addClickNumber(Integer topicId);
    List<Topic> selectTopicsLike(String description);
}
