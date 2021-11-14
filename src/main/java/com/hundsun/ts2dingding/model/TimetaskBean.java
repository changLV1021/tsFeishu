package com.hundsun.ts2dingding.model;

/**
 * 查询的时间配置
 * Created by litg on 2018/03/31.
 */
public class TimetaskBean {
    /**定时任务配置的开始时间*/
    private String configedStartHour ;
    /**定时任务配置的开始时间*/
    private String configedEndHour ;
    /**任务执行期间每隔多少毫秒查询一次*/
    private String configedsleep ;
    /**另一种配置方式，用正则表达式来指定查询的时间*/
    private String configedcron ;

    public String getConfigedStartHour() {
        return configedStartHour;
    }

    public void setConfigedStartHour(String configedStartHour) {
        this.configedStartHour = configedStartHour;
    }

    public String getConfigedEndHour() {
        return configedEndHour;
    }

    public void setConfigedEndHour(String configedEndHour) {
        this.configedEndHour = configedEndHour;
    }

    public String getConfigedsleep() {
        return configedsleep;
    }

    public void setConfigedsleep(String configedsleep) {
        this.configedsleep = configedsleep;
    }

    public String getConfigedcron() {
        return configedcron;
    }

    public void setConfigedcron(String configedcron) {
        this.configedcron = configedcron;
    }
}