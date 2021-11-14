package com.hundsun.ts2dingding.model;

/**
 * Created by litg on 2018/03/31.
 */
public class LoginConfigBean {
    /**
     * 登录用户
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPassword;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
