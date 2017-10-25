package com.hx.fdb.bean.user;

import java.io.Serializable;

/**
 * Created by yanxin on 17/3/21.
 */

public class UserInfo implements Serializable {

    private Long agentId;
    private String name;
    private String mobile;
    private String encrypterMobile;
    private Long cityId;
    private String mendianName;
    private String ticket;
    private String photoUrl;
    private Long loginTime;
    private String hximUserId;//环信用户名
    private String hximPassword;//环信密码

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEncrypterMobile() {
        return encrypterMobile;
    }

    public void setEncrypterMobile(String encrypterMobile) {
        this.encrypterMobile = encrypterMobile;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getMendianName() {
        return mendianName;
    }

    public void setMendianName(String mendianName) {
        this.mendianName = mendianName;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getHximUserId() {
        return hximUserId;
    }

    public void setHximUserId(String hximUserId) {
        this.hximUserId = hximUserId;
    }

    public String getHximPassword() {
        return hximPassword;
    }

    public void setHximPassword(String hximPassword) {
        this.hximPassword = hximPassword;
    }
}
