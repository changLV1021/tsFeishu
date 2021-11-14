/**
 *
 */
package com.hundsun.ts2dingding;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hundsun.ts2dingding.spider.ExtJsonApiSpider;
import com.hundsun.ts2dingding.utils.CronUtils;
import com.hundsun.ts2dingding.utils.DateUtils;
import com.hundsun.ts2dingding.utils.PropertiesUtils;

/**
 * 启动下载器
 * @author litg 
 * @date 2017/06/05
 */
public class StartRunner {
    public static final String CONF_CONFIG_PROPERTIES = "\\conf\\timetask.properties";
    private static Logger LOG = Logger.getLogger("SYS");

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 从配置文件读取运行时间的设置
        PropertiesUtils configs = null;


        try {
            configs = new PropertiesUtils(CONF_CONFIG_PROPERTIES);
        } catch (IOException e) {
            LOG.error("读取运行时间配置失败，结束运行 ...", e);
            return;
        }
        String configedStartHour = configs.getPropertiesValue("ts2dingding.runarea.start");
        String configedEndHour = configs.getPropertiesValue("ts2dingding.runarea.end");
        String configedsleep = configs.getPropertiesValue("ts2dingding.runarea.sleep");
        String configedcron = configs.getPropertiesValue("ts2dingding.runarea.cron");

		final int liveStartHour = StringUtils.isEmpty(configedStartHour) ? 140000 : Integer.valueOf(configedStartHour);
		final int liveEndHour = StringUtils.isEmpty(configedEndHour) ? 235959 : Integer.valueOf(configedEndHour);
		final int sleepTime = StringUtils.isEmpty(configedsleep) && StringUtils.isEmpty(configedcron) ? 3600000
				: (StringUtils.isNotEmpty(configedcron) ? 60000 : Integer.valueOf(configedsleep));

        int i = 1;
        LOG.debug("主线程启动，开始等待配置的时间……");
        boolean isRunTime = false;
        while (true) {
            if (StringUtils.isNotEmpty(configedcron)) {
                try {
                    isRunTime = CronUtils.filterWithCronTime(configedcron, "yyyyMMdd HHmm");
                } catch (ParseException e) {
                    LOG.error("Cron表达式配置错误 ...", e);
                }
            } else {
                isRunTime = DateUtils.isTimeInArea(new Date(), liveStartHour, liveEndHour);
            }
            if (isRunTime) {
                LOG.debug("已到达配置时间点，开始抓取 ...第" + i++ + "次");
                ExtJsonApiSpider spider = new ExtJsonApiSpider();
                spider.start();
                LOG.debug("抓取线程已启动 ...主线程休眠，等待下一个时间点");
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                LOG.error("休眠失败 ...", e);
            }
        }
    }
}
