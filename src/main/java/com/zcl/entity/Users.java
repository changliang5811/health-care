package com.zcl.entity;

/**
 * @author changliang
 * 用户实体类
 */
public class Users {
    private Integer user_id;
    private String user_account;
    private String user_name;
    private String phone;
    private String gender;
    private String birth;
    private Float height;
    private Float weight;
    private String stage;
    private String image;
    private String doctor_account;

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDoctor_account(String doctor_account) {
        this.doctor_account = doctor_account;
    }

    public String getUser_account() {
        return user_account;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public Float getHeight() {
        return height;
    }

    public Float getWeight() {
        return weight;
    }

    public String getStage() {
        return stage;
    }

    public String getImage() {
        return image;
    }

    public String getDoctor_account() {
        return doctor_account;
    }
}
