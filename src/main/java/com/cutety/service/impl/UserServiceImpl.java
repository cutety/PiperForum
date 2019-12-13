package com.cutety.service.impl;

import com.cutety.dao.UserMapper;
import com.cutety.domain.User;
import com.cutety.service.UserService;
import com.cutety.util.MailUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * Description:
 * Created by cutety on 2019/11/21,10:53 下午.
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;


    public boolean addUser(User user) {
        boolean res = userDao.addUser(user) > 0;
        String content="<a href='http://localhost:8080/user/active?code="+user.getActiveCode()+"'>点击激活</a>";
        try {
            MailUtil.sendMail("smtp.qq.com","846601604@qq.com","PiperForm",
                    user.getEmail(),user.getUsername(),"激活邮件",content,null,"846601604@qq.com","pbhbnkrprkozbfba");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;

    }

    /**
     * 1.验证数据库中是否有该账号
     * 2.验证密码是否正确
     * 3.返回结果 0没有该用户 1密码错误 2存在用户且密码正确
     * @param username
     * @param password
     * @return
     */
    public int login(String username, String password) {
        if(existUsername(username)){
            User userRes = userDao.selectUserByUsername(username);
            if(userRes.getPassword().equals(password)){
                return 2;
            }else{
                return 1;
            }
        }else{
            //没有该用户
            return 0;
        }

    }
    @Override
    public User getUserByUsername(String username) {
        return userDao.selectUserByUsername(username);

    }

    public boolean existUsername(String username){
        return userDao.existUsername(username)==1;
    }

    @Override
    public boolean addCredit(Integer points, Integer userId) {
        return userDao.addCredits(points,userId);
    }

    @Override
    public boolean updateCheckinTime(Integer userid, Date checkinTime) {
        return userDao.updateCheckinTime(userid,checkinTime);
    }

    @Override
    public void activeUserByCode(String code) {
        userDao.activeUserByCode(code);
    }

    @Override
    public String ifUserIsActived(Integer userId) {
        return userDao.ifUserIsActived(userId);
    }

    @Override
    public Integer changePasswordByUsername(@Param("username") String username,@Param("password") String password) {
        return userDao.changePasswordByUsername(username,password);
    }
}
