package com.zcl.entity;

/**
 * @author changliang
 * 用户康复阶段历史记录
 */
public class StageHistory {
    private Integer stageHis_id;
    private String user_account;
    private String stage_name;
    private String update_time;

    public void setStageHis_id(Integer stageHis_id) {
        this.stageHis_id = stageHis_id;
    }

    public Integer getStageHis_id() {
        return stageHis_id;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }


    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUser_account() {
        return user_account;
    }


    public String getStage_name() {
        return stage_name;
    }

    public String getUpdate_time() {
        return update_time;
    }
}
