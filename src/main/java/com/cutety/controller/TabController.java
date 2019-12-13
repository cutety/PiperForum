package com.cutety.controller;

import com.cutety.domain.Tab;
import com.cutety.service.TabService;
import com.cutety.service.impl.TabServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * Created by cutety on 2019/11/29,09:43.
 **/
@Controller
public class TabController {
    @Autowired
    private TabServiceImpl tabService;
    @RequestMapping("/tab")
    @ResponseBody
    public Object getTabs(){
       /* //先去redis里面找
        Jedis jedis = new Jedis("127.0.0.1",6379);
        List<Tab> allTabs=null;
        Set<Tuple> tabs = jedis.zrangeWithScores("tab", -1, 0);
        if(tabs.size()==0||tabs==null){
            //redis里面没，去数据库里找
            allTabs = tabService.getAllTabs();
            //放入redis
            for (int i = 0; i <allTabs.size() ; i++) {
                System.out.println("====放入reds");
                jedis.zadd("tab",allTabs.get(i).getId(),allTabs.get(i).getTabName());
            }

        }else{
            //有的话，从redis中取出
            allTabs=new ArrayList<Tab>();
            for (Tuple tuple : tabs) {
                Tab tab=new Tab();
                tab.setTabName(tuple.getElement());
                tab.setId((int)tuple.getScore());
                allTabs.add(tab);
            }
        }*/
        return tabService.getAllTabs();

    }

}
