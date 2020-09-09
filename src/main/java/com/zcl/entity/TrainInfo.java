package com.zcl.entity;

/**
 * @author changliang
 * 训练具体信息类
 */
public class TrainInfo {
    private Integer train_id;
    private String user_account;
    private Integer plan_id;
    private String group_info;
    private String start_time;
    private String end_time;
    private String train_type;

    public void setTrain_id(Integer train_id) {
        this.train_id = train_id;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public void setPlan_id(Integer plan_id) {
        this.plan_id = plan_id;
    }

    public void setGroup_info(String group_info) {
        this.group_info = group_info;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
    }

    public Integer getTrain_id() {
        return train_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public Integer getPlan_id() {
        return plan_id;
    }

    public String getGroup_info() {
        return group_info;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getTrain_type() {
        return train_type;
    }
}
