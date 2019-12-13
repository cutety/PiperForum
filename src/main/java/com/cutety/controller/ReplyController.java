package com.cutety.controller;

import com.cutety.domain.Reply;
import com.cutety.domain.User;
import com.cutety.service.ReplyService;
import com.cutety.service.impl.ReplyServiceImpl;
import com.cutety.service.impl.TopicServiceImpl;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/12/2,09:47.
 **/
@Controller
public class ReplyController {
    @Autowired
    private ReplyServiceImpl replyService;
    @Autowired
    private TopicServiceImpl topicService;
    @RequestMapping("/topic/reply")
    @ResponseBody
    public Object getReplies(Integer topicId){
        List<Reply> replies = replyService.selectRepliesByTopicId(topicId);
        return replies;
    }

    @RequestMapping("/topic/reply/like")
    @ResponseBody
    public void clickToLike(Integer replyId){
        replyService.clickToLike(replyId);
    }

    @RequestMapping("topic/reply/add")
    @ResponseBody
    public Object addReply(Reply reply,HttpServletRequest request){
        reply.setCreateTime(new Date());
        reply.setUpdateTime(new Date());
        String ip = getRemortIP(request);
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //浏览器信息
        Browser browser = userAgent.getBrowser();
        reply.setDevice(browser.toString());
        System.out.println("=========reply is =========="+reply.toString());
        topicService.alterUpdateTime(reply.getTopicId(),new Date());
        replyService.addReply(reply);
        return reply;
    }
    /**
     * 获取客户端IP
     */
    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
