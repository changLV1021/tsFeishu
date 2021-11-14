package com.hundsun.ts2dingding.service;

import com.hundsun.ts2dingding.model.TsDemand;
import com.hundsun.ts2dingding.model.TsDemandQueryResult;
import com.hundsun.ts2dingding.model.TsLoginResult;
import com.hundsun.ts2dingding.utils.HtmlViewUtil;
import com.hundsun.ts2dingding.utils.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 需求查询和推送的类
 *
 * @author litg
 * @date 2018/4/9.
 */
public class TsDemandService extends AbstractTsService {
    private static Logger log = org.apache.log4j.Logger.getLogger("SYS");

    public TsDemandService() throws IOException {
        super("demand");
    }

    /**
     * 通过调用TS的需求查询接口，获取需求数据列表
     *
     * @param login        登录用户名
     * @param start        开始行号
     * @param page         页码
     * @param param        查询参数（json格式）
     * @param currentGroup 查询时用的产品组
     * @return 返回为Json格式的数据
     * @throws IOException
     */
    private String queryDemand(TsLoginResult login, int start, int page, String param, String currentGroup) throws IOException {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient;
        // 发送请求
        CloseableHttpResponse response = null;
        httpclient = HttpClients.createDefault();

        // 创建httppost
        String url = "https://ts.hundsun.com/se/services/sys/fetchReqByUserdefinedModluePaginated.htm?_dc=" + TimeUtil.getTimeStamp();
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
        formparams.add(new BasicNameValuePair("param", param));
        formparams.add(new BasicNameValuePair("start", String.valueOf(start)));
        // 固定每次查100条
        formparams.add(new BasicNameValuePair("limit", "100"));
        formparams.add(new BasicNameValuePair("page", String.valueOf(page)));
        formparams.add(new BasicNameValuePair("currentGroup", StringUtils.isEmpty(currentGroup) ? login.getCurrentGroup() : currentGroup));
        formparams.add(new BasicNameValuePair("token", login.getToken()));
        formparams.add(new BasicNameValuePair("testingag", login.getTestAg()));

        // 参数转码
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);
        String tsHtml = HtmlViewUtil.getResponseHtml(httpclient, response, httppost);
        return tsHtml;
    }

    /**
     * 获取需求列表
     *
     * @param tsLogin      登录结果集
     * @param login        登录调用实例
     * @param param        参数列表
     * @param currentGroup 查询用的产品组
     * @return 返回List每一项是一条需求
     */
    @Override
    public List<TsDemand> getTsList(TsLoginResult tsLogin, TsLoginService login, String param, String currentGroup) {
        int sum = 1008600;
        int start = 0;
        int page = 1;
        List<TsDemand> tsDemand = new ArrayList<>();
        String tsHtml = null;
        while (sum > start) {
            try {
                tsHtml = queryDemand(tsLogin, start, page++, param, currentGroup);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            if (tsHtml != null) {
                TsDemandQueryResult firstQuery = gson.fromJson(tsHtml, TsDemandQueryResult.class);
                if (firstQuery == null) {
                    tsLogin = login.login();
                    try {
                        tsHtml = queryDemand(tsLogin, start, page++, param, currentGroup);
                        firstQuery = gson.fromJson(tsHtml, TsDemandQueryResult.class);
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }
                sum = firstQuery.getRecordsCount();
                if (sum != 0) {
                    tsDemand.addAll(firstQuery.getResultBOList());
                    start += tsDemand.size();
                }
            } else {
                break;
            }
        }
        // 需求内容里有太多的html语言标签，要去除
        for(TsDemand demand : tsDemand){
            demand.setDescription(com.hundsun.ts2dingding.utils.StringUtils.getTextFromHtml(demand.getDescription()));
        }

        return tsDemand;
    }
}
