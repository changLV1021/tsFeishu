package com.hundsun.ts2dingding.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;

import java.text.ParseException;

/**
 * Created by  on 2017/07/11.
 */
public class CronUtils {
    private static Logger log = Logger.getLogger("SYS");

    /**
     * 校验在当前时间是否满足cron时间规则表达式
     *
     * @param cron
     * @param format
     * @return
     * @throws ParseException
     */
    public static Boolean filterWithCronTime(String cron, String format) throws ParseException {
        if (StringUtils.isBlank(cron) || StringUtils.isBlank(format)) {
            log.debug("输入不正确");
            return false;
        }
        CronExpression exp = new CronExpression(cron);
        Boolean inCron = exp.isSatisfiedBy(DateUtils.dateStrToDate(DateUtils.formatCurrentDate(format), format));
        return inCron;
    }

    public static void main(String[] args) {
        try {
            System.out.println(CronUtils.filterWithCronTime("0 16 15 ? * WED", "yyyyMMdd HHmm"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(), e);
        }
    }
}
