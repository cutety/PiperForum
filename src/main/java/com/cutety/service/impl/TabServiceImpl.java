package com.cutety.service.impl;

import com.cutety.dao.TabMapper;
import com.cutety.domain.Tab;
import com.cutety.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created by cutety on 2019/11/29,09:35.
 **/
@Service
public class TabServiceImpl implements TabService {
    @Autowired
    private TabMapper tabDao;
    @Override
    public List<Tab> getAllTabs() {
        return tabDao.getAllTabs();
    }
}
