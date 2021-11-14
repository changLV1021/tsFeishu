package com.hundsun.ts2dingding.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hundsun.ts2dingding.model.TsLoginResult;
import com.hundsun.ts2dingding.model.TsSysUser;
import com.hundsun.ts2dingding.utils.HtmlViewUtil;
import com.hundsun.ts2dingding.utils.TimeUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取系统用户名单
 * @date 2018/05/06.
 * @author litg
 */
public class TsSysUserService {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("SYS");
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public Map<String, String> loadSysUserMap(TsLoginResult tsLogin) {
        Map<String, String> sysUser = new HashMap<>();
        try {
            log.info("开始载入TS系统的用户列表");
            String tsHtml = getAllSysUser(tsLogin);
            // TS系统2020年4月初改版，把这里返回系统用户名单的接口，返回数据类型改成了列表，所以对应修改
            Type type = new TypeToken<List<TsSysUser>>() {
            }.getType();
            List<TsSysUser> sysUserResult = gson.fromJson(tsHtml, type);
            if (sysUserResult != null && sysUserResult.size() > 0) {
                for (TsSysUser oneUser : sysUserResult) {
                    sysUser.put(oneUser.getUserId(), oneUser.getUserName());
                }
            }
            log.info("用户列表载入成功，共有" + sysUser.size() + "人\r\n");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return sysUser;
    }

    /**
     * 查询修改单
     */
    private String getAllSysUser(TsLoginResult login) throws IOException {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient;
        // 发送请求
        CloseableHttpResponse response = null;
        httpclient = HttpClients.createDefault();

        // 创建httppost
        String url = "https://ts.hundsun.com/se/support/systemUserAction.htm?_dc=" + TimeUtil.getTimeStamp();
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Accept", "*/*");
        httppost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httppost.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httppost.addHeader("Cache-Control", "no-cache");
        httppost.addHeader("Connection", "Keep-Alive");
        httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httppost.addHeader("Cookie", login.getCookie());
        httppost.addHeader("HOST", "ts.hundsun.com");
        httppost.addHeader("Origin", "https://ts.hundsun.com");
        httppost.addHeader("Pragma", "no-cache");
        httppost.addHeader("Referer", "https://ts.hundsun.com/se/portal/SupportPortal.htm");
        httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        httppost.addHeader("X-Requested-With", "XMLHttpRequest");

        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("currentGroup", login.getCurrentGroup()));
        formparams.add(new BasicNameValuePair("token", login.getToken()));
        formparams.add(new BasicNameValuePair("testingag", login.getTestAg()));

        //参数转码
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);

        String tsHtml = HtmlViewUtil.getResponseHtml(httpclient, response, httppost);
        return tsHtml;
    }

}
