package com.hundsun.ts2dingding;

import java.text.ParseException;

import org.junit.Assert;

import com.hundsun.ts2dingding.utils.CronUtils;

import junit.framework.TestCase;

public class CronUtilsTest extends TestCase  {

	public void testFilterWithCronTime() throws ParseException {
        String cron = "0 0 10,11,15 * * ? ";
        Assert.assertTrue(CronUtils.filterWithCronTime(cron, "HH"));//true，我当前时间为15:36，
        Assert.assertFalse(CronUtils.filterWithCronTime(cron, "HHmm"));//false，我当前时间为15:36，
	}
}
