package com.cutety.service;

import com.cutety.domain.Reply;

import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/12/2,09:02.
 **/
public interface ReplyService {
    List<Reply> selectRepliesByTopicId(Integer topicId);
    void clickToLike(Integer replyId);
    void addReply(Reply reply);
}
