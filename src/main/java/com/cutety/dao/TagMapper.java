package com.cutety.dao;

import com.cutety.domain.Tag;
import com.cutety.domain.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/12/6,09:42.
 **/
public interface TagMapper {
    Integer addTags(Tag tag);
    boolean addTags2Topic(@Param("topicId") Integer topicId, @Param("tagId") Integer tagId);
    Integer existTag(String tagName);
    List<String> getAllTagsByTopicId(Integer topicId);

}
