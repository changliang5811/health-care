package com.zcl.service.impl;

import com.zcl.dao.ItemManageDao;
import com.zcl.entity.TrainInfo;
import com.zcl.entity.TrainItem;
import com.zcl.entity.TrainLog;
import com.zcl.entity.TrainPlan;
import com.zcl.service.ItemManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author changliang
 * 动作项目管理service层
 */
@Service
public class ItemManageServiceImpl implements ItemManageService {
    @Autowired
    private ItemManageDao itemManageDao;

    @Override
    public List<TrainItem> selectAllItem() {
        return itemManageDao.selectAllItem();
    }

    @Override
    public TrainPlan selectTopItemPlanByAccountAndItemId(String user_account, String item_id,String train_type) {
        return itemManageDao.selectTopItemPlanByAccountAndItemId(user_account,item_id,train_type);
    }

    @Override
    public Integer insertItemPlan(TrainPlan trainPlan) {
        return itemManageDao.insertItemPlan(trainPlan);
    }

    @Override
    public List<TrainPlan> queryTopItemPlanByAccount(String user_account) {
        return itemManageDao.queryTopItemPlanByAccount(user_account);
    }

    @Override
    public Integer updateItemCoefByAccountAndItem(TrainPlan trainPlan) {
        return itemManageDao.updateItemCoefByAccountAndItem(trainPlan);
    }

    @Override
    public Integer insertTrainInfo(TrainInfo trainInfo) {
        return itemManageDao.insertTrainInfo(trainInfo);
    }

    @Override
    public Integer insertTrainLog(TrainLog trainLog) {
        return itemManageDao.insertTrainLog(trainLog);
    }

    @Override
    public TrainInfo queryTopHistoryByAccount(String user_account, String plan_id,String group_info) {
        return itemManageDao.queryTopHistoryByAccount(user_account,plan_id,group_info);
    }

    @Override
    public Integer updateEndTimeInTrainInfoByTrainId(String end_time, Integer train_id) {
        return itemManageDao.updateEndTimeInTrainInfoByTrainId(end_time,train_id);
    }

    @Override
    public List<TrainPlan> selectHistoryRecentWeekByAccount(String user_account, String start_time, String end_time) {
        return itemManageDao.selectHistoryRecentWeekByAccount(user_account,start_time,end_time);
    }

    @Override
    public String selectItemNameByItemId(String item_id) {
        return itemManageDao.selectItemNameByItemId(item_id);
    }

    @Override
    public Integer selectTrain_id(String user_account, Integer plan_id, String group_info) {
        return itemManageDao.selectTrain_id(user_account,plan_id,group_info);
    }

    @Override
    public Map<String, String> queryThreshold(String user_account, String item_id) {
        return itemManageDao.queryThreshold(user_account,item_id);
    }

    @Override
    public Integer updateGroupNum(Integer group_num,String update_time, Integer plan_id) {
        return itemManageDao.updateGroupNum(group_num,update_time,plan_id);
    }

    @Override
    public List<TrainPlan> queryTopItemPlanOfFastTrainByAccount(String user_account) {
        return itemManageDao.queryTopItemPlanOfFastTrainByAccount(user_account);
    }

    @Override
    public List<TrainInfo> queryTrainInfoByTrainPlan(Integer plan_id, String user_account,String start_time, String end_time) {
        return itemManageDao.queryTrainInfoByTrainPlan(String.valueOf(plan_id),user_account, start_time, end_time);
    }

    @Override
    public Integer insertDefaultFastTrain(TrainPlan trainPlan) {
        Integer flag = 0;
        Integer i = itemManageDao.queryDefaultFastTrainPlan(trainPlan.getUser_account(),"fastTrain");//查询是否已存在快速训练，如果存在则不自动插入
        if (i == 0){
            flag = itemManageDao.insertItemPlan(trainPlan);
        }
        return flag;
    }
}
