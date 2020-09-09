package com.zcl.entity;

/**
 * @author changliang
 * 训练日志记录表
 */
public class TrainLog {
    private Integer log_id;
    private String user_account;
    private Integer train_id;
    private String train_time;
    private Integer status;
    private String ax;
    private String ay;
    private String az;
    private String wx;
    private String wy;
    private String wz;
    private String dir_b;
    private String dir_r;
    private String dir_a;
    private Float score;

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public void setTrain_id(Integer train_id) {
        this.train_id = train_id;
    }

    public void setTrain_time(String train_time) {
        this.train_time = train_time;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAx(String ax) {
        this.ax = ax;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public void setAz(String az) {
        this.az = az;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public void setWy(String wy) {
        this.wy = wy;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public void setDir_b(String dir_b) {
        this.dir_b = dir_b;
    }

    public void setDir_r(String dir_r) {
        this.dir_r = dir_r;
    }

    public void setDir_a(String dir_a) {
        this.dir_a = dir_a;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getLog_id() {
        return log_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public Integer getTrain_id() {
        return train_id;
    }

    public String getTrain_time() {
        return train_time;
    }

    public Integer getStatus() {
        return status;
    }

    public String getAx() {
        return ax;
    }

    public String getAy() {
        return ay;
    }

    public String getAz() {
        return az;
    }

    public String getWx() {
        return wx;
    }

    public String getWy() {
        return wy;
    }

    public String getWz() {
        return wz;
    }

    public String getDir_b() {
        return dir_b;
    }

    public String getDir_r() {
        return dir_r;
    }

    public String getDir_a() {
        return dir_a;
    }

    public Float getScore() {
        return score;
    }
}
