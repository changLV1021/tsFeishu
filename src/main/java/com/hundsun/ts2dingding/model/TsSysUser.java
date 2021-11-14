package com.hundsun.ts2dingding.model;

/**
 * @author  litg
 * @date 2018/05/06.
 */
public class TsSysUser {
    /** 部门 */
    private String department;
    /** 邮箱 */
    private String email;
    /** 拼音 */
    private String objno;
    /** 员工号 */
    private String userId;
    /** 姓名 */
    private String userName;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjno() {
        return objno;
    }

    public void setObjno(String objno) {
        this.objno = objno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
