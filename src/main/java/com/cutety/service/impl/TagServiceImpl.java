package com.cutety.service.impl;

import com.cutety.dao.TagMapper;
import com.cutety.domain.Tag;
import com.cutety.domain.Topic;
import com.cutety.service.TagService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/12/6,09:54.
 **/
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagDao;
    @Override
    public Integer addTags(Tag tag) {
        return tagDao.addTags(tag);
    }

    @Override
    public Integer existTag(String tagName) {
        //1表示存在tag，返回true值
        return tagDao.existTag(tagName);
    }

    @Override
    public boolean addTags2Topic(@Param("topicId") Integer topicID, @Param("tagId") Integer tagId) {
        return tagDao.addTags2Topic(topicID,tagId);
    }

    @Override
    public List<String> getAllTagsByTopicId(Integer topicID) {
        return tagDao.getAllTagsByTopicId(topicID);
    }


}
