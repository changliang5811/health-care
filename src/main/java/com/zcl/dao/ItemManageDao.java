package com.zcl.dao;

import com.zcl.entity.TrainInfo;
import com.zcl.entity.TrainItem;
import com.zcl.entity.TrainLog;
import com.zcl.entity.TrainPlan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author changliang
 * 动作项目管理持久层
 */
@Repository
public interface ItemManageDao {
    List<TrainItem> selectAllItem();
    TrainPlan selectTopItemPlanByAccountAndItemId(String user_account, String item_id, String train_type);
    Integer insertItemPlan(TrainPlan trainPlan);
    List<TrainPlan> queryTopItemPlanByAccount(@Param("user_account") String user_account);
    Integer updateItemCoefByAccountAndItem(TrainPlan trainPlan);
    Integer insertTrainInfo(TrainInfo trainInfo);
    Integer insertTrainLog(TrainLog trainLog);
    TrainInfo queryTopHistoryByAccount(String user_account, String plan_id, String group_info);
    Integer updateEndTimeInTrainInfoByTrainId(String end_time, Integer train_id);
    List<TrainPlan> selectHistoryRecentWeekByAccount(String user_account, String start_time, String end_time);
    @Select("select name from trainitem where item_id = #{item_id}")
    String selectItemNameByItemId(String item_id);
    @Select("select MAX(train_id) from trainInfo where user_account= #{user_account} and plan_id = #{plan_id} and group_info = #{group_info}")
    Integer selectTrain_id(String user_account, Integer plan_id, String group_info);
    @Select("select threshold,coefficient,static_threshold from trainplan where user_account=#{user_account} and item_id = #{item_id} ORDER BY update_time DESC LIMIT 1")
    Map<String,String> queryThreshold(String user_account, String item_id);
    Integer updateGroupNum(Integer group_num, String update_time, Integer plan_id);
    List<TrainPlan> queryTopItemPlanOfFastTrainByAccount(@Param("user_account") String user_account);
    List<TrainInfo> queryTrainInfoByTrainPlan(String plan_id, String user_account, String start_time, String end_time);
    @Select("select count(plan_id) from trainplan where user_account = #{user_account} and train_type = #{fastTrain}")
    Integer queryDefaultFastTrainPlan(String user_account, String fastTrain);

}
