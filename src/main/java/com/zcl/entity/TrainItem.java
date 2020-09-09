package com.zcl.entity;

/**
 * @author changliang
 * 训练动作详情介绍表
 */
public class TrainItem {
    private String item_id;
    private String name;
    private String stage_id;
    private String stage_name;
    private Integer day_num;
    private Integer group_num;
    private Integer action_num;
    private Integer break_time;
    private String setting;
    private Integer status;
    private String notes;

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStage_id(String stage_id) {
        this.stage_id = stage_id;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
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

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public String getStage_id() {
        return stage_id;
    }

    public String getStage_name() {
        return stage_name;
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

    public String getSetting() {
        return setting;
    }

    public Integer getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }
}
