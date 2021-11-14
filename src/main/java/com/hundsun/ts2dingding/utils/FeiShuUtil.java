package com.hundsun.ts2dingding.utils;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 * @author lvc
 * @date 2021/10/31 12:47
 * @description FeiShuUtil
 */
public class FeiShuUtil {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("SYS");


    private static int connectionTimeout = 1000;// 连接超时时间,毫秒
    private static int soTimeout = 30000;// 读取数据超时时间，毫秒
    /** HttpClient对象 */
    private static CloseableHttpClient httpclient = HttpClients.
            custom().disableAutomaticRetries().build();
    /*** 超时设置 ****/
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(soTimeout)
            .setConnectTimeout(connectionTimeout)
            .build();//设置请求和传输超时时间


    /**
     * 根据给定的URL地址和参数字符串，以Get方法调用，如果成功返回true，如果失败返回false
     *
     * @param url
     *            String url地址，不含参数
     * @param param
     *            String 参数字符串，例如：a=1&b=2&c=3
     */
    public String executeGetMethod(String url, String param) {
        String strResult = "";
        StringBuffer serverURL = new StringBuffer(url);
        if (param != null && param.length() > 0) {
            serverURL.append("?");
            serverURL.append(param);
        }
        HttpGet httpget = new HttpGet(serverURL.toString());
        httpget.setConfig(requestConfig);
        CloseableHttpResponse response=null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            int iGetResultCode = response.getStatusLine().getStatusCode();
            if (iGetResultCode >= 200 && iGetResultCode < 303) {
                strResult = EntityUtils.toString(entity);
            } else if (iGetResultCode >= 400 && iGetResultCode < 500) {
                strResult = "请求的目标地址不存在：" + iGetResultCode;
            } else {
                strResult = "请求错误：" + iGetResultCode;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(response !=null){
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }


    /**
     * 根据给定的URL地址和参数字符串，以Get方法调用，如果成功返回true，如果失败返回false
     *
     * @param strURL
     *            String url地址，不含参数
     * @param param
     *            Map<String, Object> 参数字表单
     * @return boolean true－成功，false失败，如果返回成功可以getStrGetResponseBody()
     *         获取返回内容字符串，如果失败，则可访问getErrorInfo()获取错误提示。
     */
    public static String executePostMethod(String strURL, Map<String, String> param) {
        String strResult = "";
        HttpPost post = new HttpPost(strURL);
        post.setConfig(requestConfig);
        List<BasicNameValuePair> paraList = new ArrayList<BasicNameValuePair>(param.size());

        for (Map.Entry<String, String> pEntry : param.entrySet()) {
            if(null != pEntry.getValue()){
                BasicNameValuePair nv = new BasicNameValuePair(pEntry.getKey(), pEntry.getValue());
                paraList.add(nv);
            }
        }
        //使用UTF-8
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paraList, Charset.forName("utf-8"));
        post.setEntity(entity);
        CloseableHttpResponse response=null;
        try {
            response = httpclient.execute(post);
            int iGetResultCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == iGetResultCode) {
                HttpEntity responseEntity = response.getEntity();
                strResult = EntityUtils.toString(responseEntity);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
            }
        }
        return strResult;
    }


    /**
     * 以文本格式发送消息
     * @param toSend 待发送内容
     * @param resvPhone 需要@的人
     * @param title 消息的标题
     * @param webhook 接收消息的接口地址
     * @param dingTalkRobotSec 机器人验签码
     * @return 发送成功true，失败false
     */
    public static boolean sendTextMessage2FeiBot(List<String> toSend, List<String> resvPhone, String title, String webhook, String dingTalkRobotSec, boolean isAtAll) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        if (toSend.size() != 0) {
            // 生成验签码
            int timestamp = getSecondTimestamp(new Date());
            String sign = stringToSign(dingTalkRobotSec, timestamp);
            String content = Joiner.on("\n").join(toSend);

            HashMap<String, String> paramsMap = Maps.newHashMap();
            paramsMap.put("text",content);

            String paramJson = JSON.toJSONString(paramsMap);

            Map<String,String> sendMap = new HashMap<>();
            sendMap.put("msg_type","text");
            sendMap.put("content",paramJson);
            sendMap.put("sign",sign);
            sendMap.put("timestamp",String.valueOf(timestamp));
            try {
                executePostMethod(webhook,sendMap);
                return true;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
    }

    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.parseInt(timestamp);
    }


    /**
     * 加签
     * @param secret
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static String stringToSign(String secret, int timestamp) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        //把timestamp+"\n"+密钥当做签名字符串
        String stringToSign = timestamp + "\n" + secret;

        //使用HmacSHA256算法计算签名
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(stringToSign.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(new byte[]{});
        return new String(Base64.encodeBase64(signData));
    }



    /**
     * 清除List里的重复项
     * @param list 源List
     * @return 清理重复项后的List
     */
    private static List<String> removeDuplicate(List<String> list) {
        HashSet<String> h = new HashSet<>(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
