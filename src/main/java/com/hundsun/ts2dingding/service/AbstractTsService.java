package com.hundsun.ts2dingding.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hundsun.ts2dingding.model.DingConfigBean;
import com.hundsun.ts2dingding.model.TsLoginResult;
import com.hundsun.ts2dingding.utils.DingDingUtil;
import com.hundsun.ts2dingding.utils.FeiShuUtil;
import com.hundsun.ts2dingding.utils.HtmlViewUtil;
import com.hundsun.ts2dingding.utils.PropertiesUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 抓取TS系统的公共父类
 *
 * @author litg
 * @date 2020/05/30 11:26
 */
public abstract class AbstractTsService<T> {
    public static final String CONFIG_PROPERTIES = "\\conf\\";
    public static final String MSG_TYPE_TEXT = "text";
    public static final String MSG_TYPE_MARKDOWN = "markdown";
    public static Gson gson = new GsonBuilder().serializeNulls().create();
    private static Logger log = org.apache.log4j.Logger.getLogger("SYS");
    public final DingConfigBean configBean = new DingConfigBean();
    public final Map<String, String> userinfo;

    public AbstractTsService(String type) throws IOException {
        log.info("开始加载配置文件");
        PropertiesUtils configs = new PropertiesUtils(CONFIG_PROPERTIES + type + "-config.properties");
        configBean.setMobileList(configs.getPropertiesValue("ts2dingding." + type + ".mobile_list"));
        userinfo = configs.getPhoneMap(configBean.getMobileList());
        configBean.setMsgType(configs.getPropertiesValue("ts2dingding." + type + ".msgtype"));

        for (int i = 1; ; i++) {
            if (configs.containsKey("ts2dingding." + type + ".param" + (i == 1 ? "" : i))) {
                configBean.getParam().add(configs.getPropertiesValue("ts2dingding." + type + ".param" + (i == 1 ? "" : i)));
                configBean.getAtUser().add(configs.getPropertiesValue("ts2dingding." + type + ".atuser" + (i == 1 ? "" : i)));
                configBean.getWebHook().add(configs.getPropertiesValue("ts2dingding." + type + ".webhook" + (i == 1 ? "" : i)));
                configBean.getTitle().add(configs.getPropertiesValue("ts2dingding." + type + ".title" + (i == 1 ? "" : i)));
                configBean.getWant2Show().add(configs.getPropertiesValue("ts2dingding." + type + ".want2show" + (i == 1 ? "" : i)));
                configBean.getCurrentGroup().add(configs.getPropertiesValue("ts2dingding." + type + ".currentGroup" + (i == 1 ? "" : i)));
                configBean.getDingTalkRobotSec().add(configs.getPropertiesValue("ts2dingding." + type + ".sec" + (i == 1 ? "" : i)));
            } else {
                break;
            }
        }
        log.info("配置文件加载完成");
    }

    /**
     * 一次查询的具体执行类
     *
     * @param i           第几组条件
     * @param sysCodeMap  TS的系统字典
     * @param sysUserMap  TS的用户清单
     * @param productsMap TS的产品清单
     * @param wait2Send   待发送消息列表
     * @return 返回为boolean型，true成功，false失败
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public boolean sendOnce(int i, Map<String, Map<String, String>> sysCodeMap, Map<String, String> sysUserMap, Map<String, String> productsMap, List<T> wait2Send)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

        List<String> toSend = new ArrayList<>();
        List<String> resvPhone = new ArrayList<>();

        String param = configBean.getParam().get(i);
        String want2show = configBean.getWant2Show().get(i);
        String atuser = configBean.getAtUser().get(i);
        String title = configBean.getTitle().get(i);
        String webhook = configBean.getWebHook().get(i);
        String sec = configBean.getDingTalkRobotSec().get(i);
        String msgType = configBean.getMsgType();
        boolean isAtAll = false;

        if (param != null && param.length() != 0) {
            if (wait2Send != null && wait2Send.size() != 0) {
                for (T oneSearch : wait2Send) {
                    //取要发送的字段
                    HtmlViewUtil.getSendList(toSend, want2show, oneSearch, sysCodeMap, atuser, sysUserMap, productsMap);
                    //取要@的人
                    HtmlViewUtil.getAtList(resvPhone, atuser, oneSearch, userinfo);
                }

                // 要使用钉钉的@所有人功能
                if("[ALL]".equals(atuser)){
                    isAtAll = true;
                }

                //如果没有需要发送的内容，则放弃，否则组织成一条Text消息，调用钉钉的SDK进行发送
                //if (MSG_TYPE_TEXT.equals(msgType) && DingDingUtil.sendTextMessage2DingBot(toSend, resvPhone, title, webhook, sec, isAtAll)) {
                //    return true;
                //}
                if (MSG_TYPE_TEXT.equals(msgType) && FeiShuUtil.sendTextMessage2FeiBot(toSend, resvPhone, title, webhook, sec, isAtAll)) {
                    return true;
                }
                if (MSG_TYPE_MARKDOWN.equals(msgType) && DingDingUtil.sendMessage2DingBot(toSend, resvPhone, title, webhook, sec, isAtAll)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 配置组处理
     *
     * @param tsLogin     登录结果集
     * @param login       登录调用实例
     * @param sysCodeMap 系统代码
     * @param sysUserMap 用户信息
     * @param productsMap 产品信息
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public void sendAllTsList(TsLoginResult tsLogin, TsLoginService login, Map<String, Map<String, String>> sysCodeMap, Map<String, String> sysUserMap, Map<String, String> productsMap) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        log.info("开始查询");
        for (int i = 0; i < configBean.getParam().size(); i++) {
            String param = configBean.getParam().get(i);
            String currentGroup = configBean.getCurrentGroup().get(i);
            if (StringUtils.isNotEmpty(param)) {
                List<T> wait2Send = getTsList(tsLogin, login, param, currentGroup);
                sendOnce(i, sysCodeMap, sysUserMap, productsMap, wait2Send);
            }
        }
        log.info("结束查询");
    }

    /**
     * 获取查询列表
     *
     * @param tsLogin      登录结果集
     * @param login        登录调用实例
     * @param param        参数列表
     * @param currentGroup 查询用的产品组
     * @return 返回List每一项是一条记录
     */
    public abstract List<T> getTsList(TsLoginResult tsLogin, TsLoginService login, String param, String currentGroup);
}
