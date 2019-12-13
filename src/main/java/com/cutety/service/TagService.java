package com.cutety.service;

import com.cutety.domain.Tag;
import com.cutety.domain.Topic;

import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/12/6,09:51.
 **/
public interface TagService {
    Integer addTags(Tag tag);
    Integer existTag(String tagName);
    boolean addTags2Topic(Integer topicID,Integer tagId);
    List<String> getAllTagsByTopicId(Integer topicID);

}
