package com.cutety.service.impl;

import com.cutety.dao.ReplyMapper;
import com.cutety.domain.Reply;
import com.cutety.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/12/2,09:05.
 **/
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyDao;
    public List<Reply> selectRepliesByTopicId(Integer topicId) {
        return replyDao.selectRepliesByTopicId(topicId);
    }

    @Override
    public void clickToLike(Integer replyId) {
        replyDao.clickToLike(replyId);
    }

    @Override
    public void addReply(Reply reply) {
        replyDao.addReply(reply);
    }
}
