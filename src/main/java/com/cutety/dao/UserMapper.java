package com.cutety.dao;


import com.cutety.domain.Person;
import com.cutety.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Description:
 * Created by cutety on 2019/11/21,10:49 下午.
 **/
public interface UserMapper {
    /**
     * 添加新用户
     * @param user
     */
    int addUser(User user);

    int existUsername(String username);

    User selectUserByUsername(String username);

    boolean addCredits(@Param("points") Integer points, @Param("id") Integer id);

    boolean updateCheckinTime(@Param("userid") Integer userid, @Param("checkinTime") Date checkinTime);

    void activeUserByCode(String code);

    String ifUserIsActived(Integer userId);

    Integer changePasswordByUsername(@Param("username") String username,@Param("password") String password);


}
