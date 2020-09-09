package com.zcl.dao;

import com.zcl.entity.TrainInfo;
import com.zcl.entity.TrainLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: health-care
 * @Package: com.zcl.dao
 * @ClassName: RecoveryDao
 * @Author: zheng
 * @Description: 训练记录
 * @Date: 2020/8/16 17:08
 * @Version: 1.0
 */
@Repository
public interface RecoveryDao {
    List<TrainLog> selectTrainLog();

    List<TrainInfo> selectLatestTrainInfo(String user_account);

    List<TrainLog> selectTrainLogByTrainIdAndUser(String user_account, Integer train_id);
}
