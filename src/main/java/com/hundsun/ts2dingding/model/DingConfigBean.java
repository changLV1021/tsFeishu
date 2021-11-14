package com.hundsun.ts2dingding.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置项
 *
 * @author litg
 * @date 2018/03/31.
 */
public class DingConfigBean {

    /**
     * 用于直接@某人的电话号码列表
     */
    private String mobileList;
    /**
     * 接收人的字段名称
     */
    private List<String> atUser;
    /**
     * 查询参数
     */
    private List<String> param;
    /**
     * 接收消息的钉钉机器人地址
     */
    private List<String> webHook;
    /**
     * 要显示的字段名列表（逗号分隔）
     */
    private List<String> want2Show;
    /**
     * 消息标题
     */
    private List<String> title;

    /** 组 */
    private List<String> currentGroup;

    /** 钉钉机器人验签码 */
    private List<String> dingTalkRobotSec;

    /** 消息类型： text/markdown */
    private String msgType;


    public List<String> getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(List<String> currentGroup) {
        this.currentGroup = currentGroup;
    }

    public DingConfigBean(String mobileList, List<String> atuser, List<String> param, List<String> webhook, List<String> want2show, List<String> title, List<String> currentGroup, List<String> dingTalkRobotSec) {
        this.mobileList = mobileList;
        this.atUser = atuser;
        this.param = param;
        this.webHook = webhook;
        this.want2Show = want2show;
        this.title = title;
        this.currentGroup = currentGroup;
        this.dingTalkRobotSec = dingTalkRobotSec;
    }

    public DingConfigBean() {
        this.mobileList = "";
        this.atUser = new ArrayList<>();
        this.param = new ArrayList<>();
        this.webHook = new ArrayList<>();
        this.want2Show = new ArrayList<>();
        this.title = new ArrayList<>();
        this.currentGroup = new ArrayList<>();
        this.dingTalkRobotSec = new ArrayList<>();
    }

    public String getMobileList() {
        return mobileList;
    }

    public void setMobileList(String mobileList) {
        this.mobileList = mobileList;
    }

    public List<String> getAtUser() {
        return atUser;
    }

    public void setAtUser(List<String> atuser) {
        this.atUser = atuser;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(List<String> param) {
        this.param = param;
    }

    public List<String> getWebHook() {
        return webHook;
    }

    public void setWebHook(List<String> webHook) {
        this.webHook = webHook;
    }

    public List<String> getWant2Show() {
        return want2Show;
    }

    public void setWant2Show(List<String> want2Show) {
        this.want2Show = want2Show;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getDingTalkRobotSec() {
        return dingTalkRobotSec;
    }

    public void setDingTalkRobotSec(List<String> dingTalkRobotSec) {
        this.dingTalkRobotSec = dingTalkRobotSec;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
