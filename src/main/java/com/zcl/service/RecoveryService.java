package com.zcl.service;

import com.zcl.entity.TrainInfo;
import com.zcl.entity.TrainLog;

import java.util.List;

/**
 * @ProjectName: health-care
 * @Package: com.zcl.service
 * @ClassName: RecoveryService
 * @Author: zheng
 * @Description: 训练记录
 * @Date: 2020/8/16 17:10
 * @Version: 1.0
 */

public interface RecoveryService {
    List<TrainLog> selectTrainLog();


    List<TrainInfo> selectLatestTrainInfo(String user_account);

    List<TrainLog> selectTrainLogByTrainIdAndUser(String user_account, Integer train_id);
}
