package com.cutety.service.impl;

import com.cutety.dao.LoginLogMapper;
import com.cutety.domain.LoginLog;
import com.cutety.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by cutety on 2019/11/24,3:56 下午.
 **/
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogDao;

    public boolean addLog(LoginLog log) {
        if(loginLogDao.insertLog(log)>0){
            return true;
        }else{
            return false;
        }
    }
}
