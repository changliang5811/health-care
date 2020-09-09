package com.zcl.entity;

/**
 * @author changliang
 * 用户康复阶段时期类
 */
public class Stage {
    private String stage_id;
    private String stage_name;
    private String stage_info;

    public void setStage_id(String stage_id) {
        this.stage_id = stage_id;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }

    public void setStage_info(String stage_info) {
        this.stage_info = stage_info;
    }

    public String getStage_id() {
        return stage_id;
    }

    public String getStage_name() {
        return stage_name;
    }

    public String getStage_info() {
        return stage_info;
    }
}
