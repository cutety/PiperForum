package com.cutety.controller;

import com.cutety.domain.Topic;
import com.cutety.service.impl.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/11/25,11:04.
 **/
@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private TopicServiceImpl topicService;
    @RequestMapping("/index")
    @ResponseBody
    public Object toMain(HttpSession session){
        List<Topic> topics = topicService.listTopicsAndUsers();
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
