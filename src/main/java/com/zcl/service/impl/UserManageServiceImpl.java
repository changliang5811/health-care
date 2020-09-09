package com.zcl.service.impl;

import com.zcl.dao.UserManageDao;
import com.zcl.entity.Doctors;
import com.zcl.entity.Stage;
import com.zcl.entity.StageHistory;
import com.zcl.entity.Users;
import com.zcl.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 /**
 * @author changliang
 * 用户管理service层
 */
@Service
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    private UserManageDao userManageDao;
    @Override
    public Integer insertUser(Users users) {
        return userManageDao.insertUser(users);
    }

    @Override
    public Integer updateUser(Users users) {
        return userManageDao.updateUser(users);
    }

    @Override
    public String selectStageByAccount(String user_account) {
        return userManageDao.selectStageByAccount(user_account);
    }

    @Override
    public Integer insertStageChange(StageHistory stageHistory) {
        return userManageDao.insertStageChange(stageHistory);
    }

    @Override
    public List<Doctors> selectDoctor() {
        return userManageDao.selectDoctor();
    }

    @Override
    public Users queryUserByAccount(String user_account) {
        return userManageDao.queryUserByAccount(user_account);
    }

    @Override
    public List<Stage> selectAllStage() {
        return userManageDao.selectAllStage();
    }
}
