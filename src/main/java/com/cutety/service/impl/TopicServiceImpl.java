package com.cutety.service.impl;

import com.cutety.dao.TagMapper;
import com.cutety.dao.TopicMapper;
import com.cutety.domain.Topic;
import com.cutety.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/11/25,11:56.
 **/
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicDao;
    @Autowired
    private TagMapper tagDao;


    public List<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }

    @Override
    public List<Topic> listTopicsAndUsers() {

        return topicDao.listTopicsAndUsers();
    }

    @Override
    public List<Topic> getTopicContent(Integer topicID) {
        return topicDao.getTopicContent(topicID);
    }

    @Override
    public Topic selectById(Integer topicId) {
        return topicDao.selectById(topicId);
    }

    @Override
    public Integer postATopic(Topic topic) {
        return topicDao.postATopic(topic);
    }

    @Override
    public List<Topic> selectTopicsByUserId(Integer userId) {
        return topicDao.selectTopicsByUserId(userId);
    }
    @Override
    public List<Topic> getAllTopicsByTagName(String tagName) {
        return topicDao.getAllTopicsByTagName(tagName);
    }

    @Override
    public void alterUpdateTime(Integer topicId,Date date) {
         topicDao.alterUpdateTime(topicId,date);
    }

    @Override
    public void addClickNumber(Integer topicId) {
        topicDao.addClickNumber(topicId);
    }

    @Override
    public List<Topic> selectTopicsLike(String description) {
        description="%"+description+"%";
        return topicDao.selectTopicsLike(description);
    }
}
