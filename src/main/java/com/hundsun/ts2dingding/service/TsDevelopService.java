package com.hundsun.ts2dingding.service;

import com.hundsun.ts2dingding.model.TsDevelop;
import com.hundsun.ts2dingding.model.TsDevelopQueryResult;
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
 * 查询修改单的类
 *
 * @author litg
 * @date 2018/1/31.
 */
public class TsDevelopService extends AbstractTsService {
    private static Logger log = org.apache.log4j.Logger.getLogger("SYS");

    public TsDevelopService() throws IOException {
        super("develop");
    }

    /**
     * 根据条件查询修改单
     *
     * @param login 登录结果集
     * @param start 开始条数
     * @param page  开始页码
     * @param param 查询条件
     * @return 返回Response的HTML源文件
     * @throws IOException
     */
    private String queryDevelop(TsLoginResult login, int start, int page, String param, String currentGroup) throws IOException {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient;
        // 发送请求
        CloseableHttpResponse response = null;
        httpclient = HttpClients.createDefault();

        // 创建httppost
        String url = "https://ts.hundsun.com/se/services/modify/fetchModifyByUserdefinedModluePaginatedLucene.htm?_dc" + TimeUtil.getTimeStamp();
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
        formparams.add(new BasicNameValuePair("limit", "100"));
        formparams.add(new BasicNameValuePair("page", String.valueOf(page)));
        formparams.add(new BasicNameValuePair("currentGroup", StringUtils.isEmpty(currentGroup) ? login.getCurrentGroup() : currentGroup));
        formparams.add(new BasicNameValuePair("token", login.getToken()));
        formparams.add(new BasicNameValuePair("testingag", login.getTestAg()));

        //参数转码
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);
        String tsHtml = HtmlViewUtil.getResponseHtml(httpclient, response, httppost);
        return tsHtml;
    }

    /**
     * 循环执行一组条件查询，因为有可能超过一页，要考虑分页
     *
     * @param tsLogin      登录结果集
     * @param login        登录执行类
     * @param param        查询条件
     * @param currentGroup 当前查询的产品组
     * @return 结果集列表
     */
    @Override
    public List<TsDevelop> getTsList(TsLoginResult tsLogin, TsLoginService login, String param, String currentGroup) {
        int sum = 1008600;
        int start = 0;
        int page = 1;
        List<TsDevelop> tsDevelop = new ArrayList<>();
        while (sum > start) {
            String tsHtml = null;
            try {
                tsHtml = queryDevelop(tsLogin, start, page++, param, currentGroup);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            if (tsHtml != null) {
                TsDevelopQueryResult firstQuery = gson.fromJson(tsHtml, TsDevelopQueryResult.class);
                if (firstQuery == null) {
                    tsLogin = login.login();
                    try {
                        tsHtml = queryDevelop(tsLogin, start, page++, param, currentGroup);
                        firstQuery = gson.fromJson(tsHtml, TsDevelopQueryResult.class);
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }
                sum = firstQuery.getRecordsCount();
                if (sum != 0) {
                    tsDevelop.addAll(firstQuery.getResultBOList());
                    start += tsDevelop.size();
                }
            } else {
                break;
            }
        }

        return tsDevelop;
    }

}
