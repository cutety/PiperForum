package com.cutety.controller;

import com.cutety.service.impl.LoginLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Description:
 * Created by cutety on 2019/11/24,4:30 下午.
 **/
@Controller
public class LoginLogController {
    @Autowired
    private LoginLogServiceImpl loginLogService;
}
