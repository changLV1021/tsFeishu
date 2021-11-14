package com.hundsun.ts2dingding.spider;

import com.hundsun.ts2dingding.model.TsLoginResult;
import com.hundsun.ts2dingding.service.*;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.File;
import java.io.IOException;
import java.util.Map;


/**
 * 模拟Ext-JS的客户端去请求TS的json接口，直接获取数据
 * @author  litg
 * @date 2018/03/19
 */
public class ExtJsonApiSpider extends Thread {
    public static final String USER_DIR = "user.dir";
    public static final String DEMAND_CONFIG_PROPERTIES = "demand-config.properties";
    public static final String TASK_CONFIG_PROPERTIES = "task-config.properties";
    public static final String DEVELOP_CONFIG_PROPERTIES = "develop-config.properties";
    private static Logger log = Logger.getLogger("SYS");


    /**
     * 实现下载器逻辑
     */
    @Override
    public void run() {
        try {
            TsLoginService login = new TsLoginService();
            TsLoginResult loginresult = login.login();

            //取系统字典
            Map<String, Map<String, String>> sysCodeMap = new TsSysCodeService().loadSysCodeMap(loginresult);
            //取用户列表
            Map<String, String> sysUserMap = new TsSysUserService().loadSysUserMap(loginresult);
            //获取产品列表
            Map<String, String> productsMap = new TsProductService().loadProductsMap(loginresult);

            try {
                if (new File(System.getProperty(USER_DIR) + TsDemandService.CONFIG_PROPERTIES + DEMAND_CONFIG_PROPERTIES).exists()) {
                    TsDemandService demand = new TsDemandService();
                    demand.sendAllTsList(loginresult, login, sysCodeMap, sysUserMap, productsMap);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }

            try {
                if (new File(System.getProperty(USER_DIR) + TsTaskService.CONFIG_PROPERTIES + TASK_CONFIG_PROPERTIES).exists()) {
                    TsTaskService task = new TsTaskService();
                    task.sendAllTsList(loginresult, login, sysCodeMap, sysUserMap, productsMap);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }

            try {
                if (new File(System.getProperty(USER_DIR) + TsDevelopService.CONFIG_PROPERTIES + DEVELOP_CONFIG_PROPERTIES).exists()) {
                    TsDevelopService develop = new TsDevelopService();
                    develop.sendAllTsList(loginresult, login, sysCodeMap, sysUserMap, productsMap);
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

}
