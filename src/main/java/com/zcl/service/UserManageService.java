package com.zcl.service;

import com.zcl.entity.Doctors;
import com.zcl.entity.Stage;
import com.zcl.entity.StageHistory;
import com.zcl.entity.Users;

import java.util.List;



public interface UserManageService {
    Integer insertUser(Users users);
    Integer updateUser(Users users);
    String selectStageByAccount(String user_account);
    Integer insertStageChange(StageHistory stageHistory);
    List<Doctors> selectDoctor();
    Users queryUserByAccount(String user_account);
    List<Stage> selectAllStage();
}
