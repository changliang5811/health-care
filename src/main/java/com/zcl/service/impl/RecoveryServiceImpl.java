package com.zcl.service.impl;

import com.zcl.dao.RecoveryDao;
import com.zcl.entity.TrainInfo;
import com.zcl.entity.TrainLog;
import com.zcl.service.RecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: health-care
 * @Package: com.zcl.service.impl
 * @ClassName: RecoveryServiceImpl
 * @Author: zheng
 * @Description: 训练记录
 * @Date: 2020/8/16 17:11
 * @Version: 1.0
 */
@Service
public class RecoveryServiceImpl implements RecoveryService {
    @Autowired
    private RecoveryDao recoveryDao;

    @Override
    public List<TrainLog> selectTrainLog() {
        return recoveryDao.selectTrainLog();
    }

    @Override
    public List<TrainInfo> selectLatestTrainInfo(String user_account) {
        return recoveryDao.selectLatestTrainInfo(user_account);
    }

    @Override
    public List<TrainLog> selectTrainLogByTrainIdAndUser(String user_account,Integer train_id){

        return recoveryDao.selectTrainLogByTrainIdAndUser(user_account,train_id);
    }
}
