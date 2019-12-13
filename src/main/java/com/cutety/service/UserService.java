package com.cutety.service;

import com.cutety.domain.Person;
import com.cutety.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Description:
 * Created by cutety on 2019/11/21,10:52 下午.
 **/
public interface UserService {
    boolean addUser(User user) throws Exception;

    int login(String username,String password);

    User getUserByUsername(String username);

    boolean existUsername(String username);

    boolean addCredit(Integer points,Integer userId);

    boolean updateCheckinTime(Integer userid, Date checkinTime);

    void activeUserByCode(String code);

    String ifUserIsActived(Integer userId);

    Integer changePasswordByUsername(@Param("username") String username, @Param("password") String password);

}
