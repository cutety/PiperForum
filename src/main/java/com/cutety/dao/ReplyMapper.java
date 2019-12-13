package com.cutety.dao;

import com.cutety.domain.Reply;

import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/12/2,09:01.
 **/
public interface ReplyMapper {
    List<Reply> selectRepliesByTopicId(Integer topicId);
    void addReply(Reply reply);
    void clickToLike(Integer replyId);
}
