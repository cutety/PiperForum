package com.cutety.controller;

import com.cutety.domain.Reply;
import com.cutety.domain.Tab;
import com.cutety.domain.Tag;
import com.cutety.domain.Topic;
import com.cutety.service.ReplyService;
import com.cutety.service.TopicService;
import com.cutety.service.impl.ReplyServiceImpl;
import com.cutety.service.impl.TagServiceImpl;
import com.cutety.service.impl.TopicServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/11/25,11:39.
 **/
@Controller
public class TopicController {
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private ReplyServiceImpl replyService;
    @Autowired
    private TagServiceImpl tagService;
    @RequestMapping("/topic/detail")
    @ResponseBody
    public Object getTopicContent(Integer topicID){
        Topic topic = topicService.selectById(topicID);
        List<Reply> replies = replyService.selectRepliesByTopicId(topicID);
        HashMap<String, Object> topicDetail = new HashMap<>();
        List<String> tags = tagService.getAllTagsByTopicId(topic.getId());
        topicDetail.put("topic",topic);
        topicDetail.put("replies",replies);
        topicDetail.put("tags",tags);
        topicService.addClickNumber(topicID);
        return topicDetail;
    }


    @RequestMapping("/topic/post")
    @ResponseBody
    public Object postATopic(HttpServletRequest request){
        String content = request.getParameter("content");
        String userId = request.getParameter("userId");
        String title = request.getParameter("title");
        String tags=request.getParameter("tags");
        byte tabId = Byte.parseByte(request.getParameter("tabId"));
        Topic topic = new Topic();
        topic.setTabId(tabId);
        topic.setTitle(title);
        topic.setUserId(Integer.parseInt(userId));
        topic.setContent(content);
        topic.setUpdateTime(new Date());
        topic.setCreateTime(new Date());
        topicService.postATopic(topic);
        Integer topicId = topic.getId();
        String[] _tags = tags.split(",");
        for (int i = 0; i < _tags.length; i++) {
            Integer tag_id = tagService.existTag(_tags[i]);
            if(tag_id!=null){
                //该标签已存在，直接将该id与topicId建立联系
                boolean b = tagService.addTags2Topic(topicId, tag_id);
                System.out.println(b);
            }else{
                //该标签不存在，将其先插入tag表，返回id值插入tag和topic关系表
                Tag tag = new Tag();
                tag.setTagName(_tags[i]);
                tagService.addTags(tag);
                System.out.println("The new tagId is:"+ tag.getId());
                tagService.addTags2Topic(topicId,tag.getId());
            }
        }
        return topicId;
    }
    @RequestMapping("post/test")
    @ResponseBody
    public Object testPost(String tagName){
        Tag tag = new Tag();
        tag.setTagName(tagName);
        Integer tagId = tagService.addTags(tag);
        System.out.println("==============tagID==============="+tag.getId());
        return tag.getId();
    }
    @RequestMapping("/user/topics")
    @ResponseBody
    public Object showTopics(Integer userId){
        List<Topic> topics = topicService.selectTopicsByUserId(userId);
        return topics;
    }
    @RequestMapping("/topic/searchByTag")
    @ResponseBody
    public Object   topicsOfTag(String tagName){
        List<Topic> topics = topicService.getAllTopicsByTagName(tagName);
        for (Topic topic : topics) {
            if(topic.getTags()!=null&&!topic.getTags().equals(""))
            {
                System.out.println("======topicTags::======"+topic.getTags());
                String[] res = topic.getTags().split(",");
                List<String> stringlist = new ArrayList<>();
                if(res.length>1){
                    stringlist.add(res[0]);
                    stringlist.add(res[1]);
                }else{
                    stringlist.add(res[0]);
                }
                topic.setTagAry(stringlist);
            }
        }
        return topics;
    }

    @RequestMapping("/topic/updateTime")
    @ResponseBody
    public Object alterUpdateTime(Integer topicId){
        topicService.alterUpdateTime(topicId,new Date());
        return "succ";
    }

    @RequestMapping("/topic/blurSearch")
    @ResponseBody
    public Object blurSearchTopicByTitle(String description){
        List<Topic> topics = topicService.selectTopicsLike(description);
        for (Topic topic : topics) {
            if(topic.getTags()!=null&&!topic.getTags().equals(""))
            {
                String[] res = topic.getTags().split(",");
                List<String> stringlist = new ArrayList<>();
                if(res.length>1){
                    stringlist.add(res[0]);
                    stringlist.add(res[1]);
                }else{
                    stringlist.add(res[0]);
                }
                topic.setTagAry(stringlist);
            }
        }
        return topics;
    }

}
