package com.cutety.controller;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.cutety.domain.LoginLog;
import com.cutety.domain.User;
import com.cutety.service.impl.LoginLogServiceImpl;
import com.cutety.service.impl.UserServiceImpl;
import com.cutety.util.*;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description:
 * Created by cutety on 2019/11/21,11:27 下午.
 **/
@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LoginLogServiceImpl loginLogService;
    @RequestMapping("user/check/exist")
    @ResponseBody
    public Object checkUserExist(String username){
        boolean res = userService.existUsername(username);
        if(res){
            return '0';
        }else{
            return '1';
        }
    }

    @RequestMapping("/user/add/do")
    @ResponseBody
    public Object addUser(HttpServletRequest request) {
        HashMap<String, String> res = new HashMap<>();
        String username = request.getParameter("username");
        String password = ProduceMD5.getMD5(request.getParameter("password"));
        String phone = request.getParameter("tel");
        User user = new User();
        Byte type = new Byte("0");
        Random rand = new Random();
        int randomNum = rand.nextInt(10) + 1;
        String avatarUrl = "/images/avatar/avatar-default-" + randomNum + ".png";
        String activeCode = UuidUtil.getUuid();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(request.getParameter("email"));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setPhoneNum(phone);
        user.setType(type);
        user.setCredit(0);
        user.setAvatar(avatarUrl);
        user.setActiveCode(activeCode);
        user.setStatus("N");
        System.out.println(user.toString());
        boolean ifSucc = userService.addUser(user);
        if (ifSucc) {
            res.put("msg", "1");
        } else {
            res.put("msg", "0");
        }

        return res;
    }

    @RequestMapping("/api/loginCheck")
    @ResponseBody
    public Object signin(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String _password = request.getParameter("password");
        String password = ProduceMD5.getMD5(_password);
        int loginVerify = userService.login(username, password);
        Map<String, String> res = new HashMap<String, String>();
        if (loginVerify == 2) {
            User user = userService.getUserByUsername(username);
            System.out.println(user.toString());
            Integer id = user.getId();
            //判断是否激活账号了
            String status = userService.ifUserIsActived(id);
            if(status.equals("N")){
                //发送激活邮件返回状态,已注册未激活
                String content="点击下面的激活链接进行激活\n"+"http://localhost:8080/user/active?code="+user.getActiveCode();
              /*  try {
                    MailUtil.sendMail("smtp.qq.com","846601604@qq.com","PiperForm",
                            user.getEmail(),user.getUsername(),"激活邮件",content,null,"846601604@qq.com","pbhbnkrprkozbfba");
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                MailUtils.sendMail(user.getEmail(),content,"PiperForm激活邮件");
                res.put("stateCode","3");
                return res;
            }
            Date checkinTime = user.getCheckinTime();
            //  System.out.println(checkinTime.toString());
            Date updateTime = new Date();
            System.out.println("=================updateTime:" + updateTime);
            if (checkinTime != null) {
                long checkinGap = updateTime.getTime() - checkinTime.getTime();
                //每日登陆增加1积分
                if (checkinGap >= 1000 * 24 * 3600) {
                    //签到间隔超过24小时
                    boolean isDailyCreditAvalible = userService.addCredit(1, id);
                    //更新最近签到时间
                    userService.updateCheckinTime(id, updateTime);
                }

            } else if (checkinTime == null) {
                boolean isDailyCreditAvalible = userService.addCredit(2, id);
                //更新最近签到时间
                userService.updateCheckinTime(id, updateTime);
            }


            //讲用户信息写入session中
            session.setAttribute("userId", id);
            session.setAttribute("username", username);
            //获取登陆信息
            String ip = getRemortIP(request);
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            //浏览器信息
            Browser browser = userAgent.getBrowser();
            //操作系统信息
            OperatingSystem os = userAgent.getOperatingSystem();
            LoginLog log = new LoginLog();
            log.setDevice(browser.toString());
            log.setLoginTime(updateTime);
            log.setUserId(id);
            log.setIp(ip);
            log.setOs(os.toString());
            boolean logAddResult = loginLogService.addLog(log);
            res.put("stateCode", "2");
            res.put("userInfo", username);
            res.put("userId",user.getId()+"");
            res.put("credits", user.getCredit() + "");

        } else if (loginVerify == 1) {
            res.put("stateCode", "1");
        } else if (loginVerify == 0) {
            res.put("stateCode", "0");
        }
        return res;
    }

    //注销
    @RequestMapping("/logout")
    @ResponseBody
    public String userLogout(HttpSession session) {
        session.invalidate();

        return "successfully logout";
    }

    @RequestMapping("/checkUserExist")
    @ResponseBody
    public boolean checkUserExist(HttpServletRequest request) {
        String username = request.getParameter("username");
        boolean flag = userService.existUsername(username);
        System.out.println("是否存在" + flag);
        return flag;
    }
    @RequestMapping("/user/active")
    @ResponseBody
    public Object activeUser(HttpServletRequest request){
        String code = request.getParameter("code");
        userService.activeUserByCode(code);
        return "successfully actived";

    }
    @RequestMapping("user/retrievePsw")
    @ResponseBody
    public Object retrievePassword(String username){
        String mailCode=dateFormat(new Date())+"@"+username;
        System.out.println("mailCode==========================="+mailCode);
        User user = userService.getUserByUsername(username);
        String code = null;
        try {
            code = Encrypt.encode(mailCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String content="请在五分钟之内，点击下面的链接进行修改密码\n\n"+"http://localhost:8080/user/expireTest?code="+code;
        MailUtils.sendMail(user.getEmail(),content,"PiperForm的找回密码邮件");
        return "We've sent you a email to help you retrieve your account,please click the link in 5 minutes...";
    }
    @RequestMapping("/user/expireTest")
    public Object checkEffectiveness(String code){
        System.out.println("code has recieved:"+code);
        String res = null;
        try {
           res=Encrypt.decode(code);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("res:"+res);
        String[] codes = res.split("@");
        System.out.println(codes[0]);
            boolean notExpire = judgeOfTime(codes[0]);
            if(notExpire){
                //可以修改密码
                return "redirect:/page-changePsw.html?code="+code;
            }else{
                //过期
                return "redirect:/error404.html";
            }

    }
    @RequestMapping("user/changePsw")
    @ResponseBody
    public Object changePasswordByUsername(String password,String url){

        String code="";
        try {
            code=Encrypt.decode(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] codes = code.split("@");
        System.out.println(codes+"====="+codes[1]);
        String username=codes[1];
        return userService.changePasswordByUsername(username,password);
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

    public boolean judgeOfTime(String time){
        System.out.println(time);
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date past = null;
        try {
            past = fmt.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        System.out.println("======================"+past);
        System.out.println("======================"+now);
        if((now.getTime()-past.getTime())<1000*5*60){
            //可以
            return true;
        }else{
            return false;
        }
      
    }
    public String dateFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;

    }
}
