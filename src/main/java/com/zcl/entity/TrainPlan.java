package com.zcl.entity;

import java.util.List;

/**
 * @author changliang
 * 训练计划表
 */
public class TrainPlan {
    private Integer plan_id;
    private String user_account;
    private String item_id;
    private String item_name;
    private String update_time;
    private Integer day_num;
    private Integer group_num;
    private Integer action_num;
    private Integer break_time;
    private String coefficient;
    private String threshold;
    private String advice;
    private String train_type;
    private List<TrainInfo> trainInfo;


    public void setTrainInfo(List<TrainInfo> trainInfo) {
        this.trainInfo = trainInfo;
    }

    public List<TrainInfo> getTrainInfo() {
        return trainInfo;
    }

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
    }

    public String getTrain_type() {
        return train_type;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setPlan_id(Integer plan_id) {
        this.plan_id = plan_id;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public void setDay_num(Integer day_num) {
        this.day_num = day_num;
    }

    public void setGroup_num(Integer group_num) {
        this.group_num = group_num;
    }

    public void setAction_num(Integer action_num) {
        this.action_num = action_num;
    }

    public void setBreak_time(Integer break_time) {
        this.break_time = break_time;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Integer getPlan_id() {
        return plan_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public Integer getDay_num() {
        return day_num;
    }

    public Integer getGroup_num() {
        return group_num;
    }

    public Integer getAction_num() {
        return action_num;
    }

    public Integer getBreak_time() {
        return break_time;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public String getThreshold() {
        return threshold;
    }

    public String getAdvice() {
        return advice;
    }
}
