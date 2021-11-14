package com.hundsun.ts2dingding.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hundsun.ts2dingding.model.TsLoginResult;
import com.hundsun.ts2dingding.model.TsProducts;
import com.hundsun.ts2dingding.model.TsProductsResult;
import com.hundsun.ts2dingding.utils.HtmlViewUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litg
 * @date 2018/10/26.
 */
public class TsProductService {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("SYS");
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public Map<String, String> loadProductsMap(TsLoginResult tsLogin) {
        Map<String, String> products = new HashMap<>();
        try {
            log.info("开始载入产品信息列表");
            String tsHtml = getProducts(tsLogin);
            TsProductsResult productsResult = gson.fromJson(tsHtml, TsProductsResult.class);
            if (productsResult != null && productsResult.getResultMap() != null && productsResult.getResultMap().size() > 0) {
                for (TsProducts oneProduct : productsResult.getResultMap().get("PRODUCT")) {
                    products.put(oneProduct.getA1(), oneProduct.getA3());
                }
            }
            log.info("产品信息列表载入成功，共有" + products.size() + "项\r\n" + products.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return products;
    }

    /**
     * 查询修改单
     */
    private String getProducts(TsLoginResult login) throws IOException {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient;
        // 发送请求
        CloseableHttpResponse response = null;
        httpclient = HttpClients.createDefault();

        // 创建httppost
        String url = "https://ts.hundsun.com/se/reqmSupport/TranslationByGroupNewAfterLoadAction.htm";
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
        formparams.add(new BasicNameValuePair("userId", login.getUserId()));
        formparams.add(new BasicNameValuePair("sourceId", "PRODUCT"));
        formparams.add(new BasicNameValuePair("currentGroup", login.getCurrentGroup()));
        formparams.add(new BasicNameValuePair("token", login.getToken()));
        formparams.add(new BasicNameValuePair("groupId", login.getCurrentGroup()));

        //参数转码
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);

        String tsHtml = HtmlViewUtil.getResponseHtml(httpclient, response, httppost);
        return tsHtml;
    }
}
