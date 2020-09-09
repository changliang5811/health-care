package com.zcl.service;

import com.zcl.entity.TrainInfo;
import com.zcl.entity.TrainItem;
import com.zcl.entity.TrainLog;
import com.zcl.entity.TrainPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemManageService {
    List<TrainItem> selectAllItem();
    TrainPlan selectTopItemPlanByAccountAndItemId(String user_account, String item_id, String train_type);
    Integer insertItemPlan(TrainPlan trainPlan);
    List<TrainPlan> queryTopItemPlanByAccount(String user_account);
    Integer updateItemCoefByAccountAndItem(TrainPlan trainPlan);
    Integer insertTrainInfo(TrainInfo trainInfo);
    Integer insertTrainLog(TrainLog trainLog);
    TrainInfo queryTopHistoryByAccount(String user_account, String plan_id, String group_info);
    Integer updateEndTimeInTrainInfoByTrainId(String end_time, Integer train_id);
    List<TrainPlan> selectHistoryRecentWeekByAccount(String user_account, String start_time, String end_time);
    String selectItemNameByItemId(String item_id);
    Integer selectTrain_id(String user_account, Integer plan_id, String group_info);
    Map<String,String> queryThreshold(String user_account, String item_id);
    Integer updateGroupNum(Integer group_num, String update_time, Integer plan_id);
    List<TrainPlan> queryTopItemPlanOfFastTrainByAccount(@Param("user_account") String user_account);
    List<TrainInfo> queryTrainInfoByTrainPlan(Integer plan_id, String user_account, String start_time, String end_time);
    Integer insertDefaultFastTrain(TrainPlan trainPlan);
}
