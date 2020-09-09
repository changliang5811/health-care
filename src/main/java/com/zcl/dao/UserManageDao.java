package com.zcl.dao;

import com.zcl.entity.Doctors;
import com.zcl.entity.Stage;
import com.zcl.entity.StageHistory;
import com.zcl.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author changliang
 * 用户管理持久层
 */
@Repository
public interface UserManageDao {
    Integer insertUser(Users users);
    Integer updateUser(Users users);
    String selectStageByAccount(@Param("user_account") String user_account);
    Integer insertStageChange(StageHistory stageHistory);
    List<Doctors> selectDoctor();
    Users queryUserByAccount(String user_account);
    List<Stage> selectAllStage();
}
