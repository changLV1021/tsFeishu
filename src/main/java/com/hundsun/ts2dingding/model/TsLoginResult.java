package com.hundsun.ts2dingding.model;

/**
 * Created by huangzx19274 on 2018/1/29.
 *
 */
public class TsLoginResult {
    /**令牌（每次登录后更新,在失效前,可用其多次进行查询）*/
    private String token;
    /**TS的cookie*/
    private String cookie;
    /** 改版后加的不知道干什么的参数 */
    private String testAg;
    /** 登陆后获得的组织ID */
    private String currentGroup;
    /** 登录后获取到的员工号 */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTestAg() {
        return testAg;
    }

    public void setTestAg(String testAg) {
        this.testAg = testAg;
    }

    public String getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(String currentGroup) {
        this.currentGroup = currentGroup;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
