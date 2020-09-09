package com.zcl.entity;

/**
 * @author changliang
 * 设定动作项目阈值系数类
 */
public class ItemCoef {
    private Integer id;
    private String user_account;
    private String item_id;
    private String item_name;
    private String coefficient;
    private String threshold;
    private String update_time;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public String getUser_account() {
        return user_account;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public String getThreshold() {
        return threshold;
    }

    public String getUpdate_time() {
        return update_time;
    }
}
