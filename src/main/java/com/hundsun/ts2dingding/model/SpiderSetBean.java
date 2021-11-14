package com.hundsun.ts2dingding.model;

public class SpiderSetBean {


    public SpiderSetBean() {
    }

    /**
     * 登录页标志
     */
    private String main_login;

    /**
     * 模板名
     */
    private String temp_name;
    /**
     * 列表页标志
     */
    private String list_load_flag;
    /**
     * 模板显示名称
     */
    private String show_temp_name;

    /**
     * 返回主页的链接
     */
    private String main_page_link;
    /**
     * 登录用户
     */
    private String login_name;
    /**
     * 登录密码
     */
    private String login_password;
    /**
     * 钉钉机器人webhook地址
     */
    private String dingdingUrl;
    /**
     * 单行标志
     */
    private String tr_line_flag;
    /**
     * 要获取的单元格序号
     */
    private String td2get;
    /**
     * 用于检查的单元格序号
     */
    private String td2check;

    /**
     * TS首页地址
     */
    private String t2Url;
    /**
     * 检查标识是否也返回
     */
    private String check_show;
    /**
     * 登录成功标志
     */
    private String login_success;
    /**
     * 在单元格输出内容前的修饰语
     */
    private String before_td;

    /**
     * 在单元格输出内容后的修饰语
     */
    private String after_td;

    /**
     * 在单元格输出内容后的修饰语
     */
    private String td_length;
    
    /**
     * 模板所属的产品组
     */
    private String temp_pg;

    /**
     * 配置用于直接@某人的电话号码列表
     */
    private String mobile_list;

    /**
     * 配置是抓取接收人的单元格
     */
    private String td_user;

    public String getTemp_pg() {
		return temp_pg;
	}

	public void setTemp_pg(String temp_pg) {
		this.temp_pg = temp_pg;
	}

	public String getTd_length() {
        return td_length;
    }

    public void setTd_length(String td_length) {
        this.td_length = td_length;
    }


    public String getMain_login() {
        return main_login;
    }

    public void setMain_login(String main_login) {
        this.main_login = main_login;
    }


    public String getTemp_name() {
        return temp_name;
    }

    public void setTemp_name(String temp_name) {
        this.temp_name = temp_name;
    }



    public String getList_load_flag() {
        return list_load_flag;
    }

    public void setList_load_flag(String list_load_flag) {
        this.list_load_flag = list_load_flag;
    }



    public String getShow_temp_name() {
        return show_temp_name;
    }

    public void setShow_temp_name(String show_temp_name) {
        this.show_temp_name = show_temp_name;
    }


    public String getMain_page_link() {
        return main_page_link;
    }

    public void setMain_page_link(String main_page_link) {
        this.main_page_link = main_page_link;
    }



    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }


    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }


    public String getDingdingUrl() {
        return dingdingUrl;
    }

    public void setDingdingUrl(String dingdingUrl) {
        this.dingdingUrl = dingdingUrl;
    }



    public String getT2Url() {
        return t2Url;
    }

    public void setT2Url(String t2Url) {
        this.t2Url = t2Url;
    }


    public String getTr_line_flag() {
        return tr_line_flag;
    }

    public void setTr_line_flag(String tr_line_flag) {
        this.tr_line_flag = tr_line_flag;
    }


    public String getTd2get() {
        return td2get;
    }

    public void setTd2get(String td2get) {
        this.td2get = td2get;
    }


    public String getTd2check() {
        return td2check;
    }

    public void setTd2check(String td2check) {
        this.td2check = td2check;
    }


    public String getCheck_show() {
        return check_show;
    }

    public void setCheck_show(String check_show) {
        this.check_show = check_show;
    }


    public String getLogin_success() {
        return login_success;
    }

    public void setLogin_success(String login_success) {
        this.login_success = login_success;
    }


    public String getBefore_td() {
        return before_td;
    }

    public void setBefore_td(String before_td) {
        this.before_td = before_td;
    }

    public String getAfter_td() {
        return after_td;
    }

    public void setAfter_td(String after_td) {
        this.after_td = after_td;
    }


    public String getMobile_list() {
        return mobile_list;
    }

    public void setMobile_list(String mobile_list) {
        this.mobile_list = mobile_list;
    }

    public String getTd_user() {
        return td_user;
    }

    public void setTd_user(String td_user) {
        this.td_user = td_user;
    }
}