package com.zcl.entity;

/**
 * @author changliang
 * 医生实体类
 */
public class Doctors {
    private Integer doc_id;
    private String doctor_account;
    private String password;
    private String name;
    private String email;
    private String phone;

    public void setDoc_id(Integer doc_id) {
        this.doc_id = doc_id;
    }

    public Integer getDoc_id() {
        return doc_id;
    }

    public void setDoctor_account(String doctor_account) {
        this.doctor_account = doctor_account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoctor_account() {
        return doctor_account;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
