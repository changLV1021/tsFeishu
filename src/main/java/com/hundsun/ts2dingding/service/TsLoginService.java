package com.hundsun.ts2dingding.service;

import com.hundsun.ts2dingding.model.LoginConfigBean;
import com.hundsun.ts2dingding.model.PostResponse;
import com.hundsun.ts2dingding.model.TsLoginResult;
import com.hundsun.ts2dingding.utils.AesUtils;
import com.hundsun.ts2dingding.utils.HtmlViewUtil;
import com.hundsun.ts2dingding.utils.PropertiesUtils;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * 登录实现类
 * @date 2018/1/26.
 * @author huangzx19274
 */
public class TsLoginService {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("SYS");
    private static String ENCODING = "UTF-8";
    private final LoginConfigBean loginBean = new LoginConfigBean();

    public TsLoginService() throws IOException {
        loadTsUser();
    }

    private static TsLoginResult finalLogin2(TsLoginResult login) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient;
        // 发送请求
        CloseableHttpResponse response;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("https://ts.hundsun.com/se/userGroup/UserGroupAction.htm");
            httppost.addHeader("Accept", "*/*");
            httppost.addHeader("Cache-Control", "no-cache");
            httppost.addHeader("Connection", "Keep-Alive");
            httppost.addHeader("Cookie", login.getCookie());
            httppost.addHeader("Host", "ts.hundsun.com");
            httppost.addHeader("Pragma", "no-cache");
            httppost.addHeader("Origin", "https://ts.hundsun.com");
            httppost.addHeader("Referer", "https://ts.hundsun.com/se/portal/SupportPortal.htm");
            httppost.addHeader("Upgrade-Insecure-Requests", "1");
            httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

            // 创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("groupId", login.getCurrentGroup()));
            formparams.add(new BasicNameValuePair("currentGroup", login.getCurrentGroup()));
            formparams.add(new BasicNameValuePair("token", login.getToken()));

            //参数转码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);

            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String tsHtml = EntityUtils.toString(entity, "UTF-8");
                System.out.println(tsHtml);
            }
            //释放连接
            httpclient.close();
            response.close();
            return login;
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private void loadTsUser() throws IOException {
        PropertiesUtils login = new PropertiesUtils("\\conf\\login.properties");
        // 加载用户名密码
        loginBean.setLoginName(login.getPropertiesValue("ts2dingding.login.name"));
        loginBean.setLoginPassword(AesUtils.getTruePassword(login, "\\conf\\login.properties"));
    }

    public TsLoginResult login() {
        log.info("开始登录...");

        try {
            String url = "https://ts.hundsun.com/se/portal/SupportPortal.htm";
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //不允许缓存
            conn.setUseCaches(false);
            //设置POST方式连接
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charset", ENCODING);
            PostResponse response = new PostResponse();
            PrintWriter out;
            BufferedReader in;
            StringBuilder result = new StringBuilder();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), ENCODING));
            // 发送请求参数
            out.print("");
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENCODING));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            response.setResult(result.toString());
            response.setResponseHeaders(conn.getHeaderFields());
            out.close();
            in.close();
            conn.disconnect();
            TsLoginResult tl = login1(response);
            return tl;
        } catch (ProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private TsLoginResult login1(PostResponse response1) {
        log.info("跳转到恒生统一认证...");
        //获取登录页面信息
        String loginFormAction = HtmlViewUtil.queryField(response1.getResult(), "form", "id", "fm1", "action");
        String loginFieldlt = HtmlViewUtil.queryField(response1.getResult(), "input", "name", "lt", "value");
        String loginFieldExecution = HtmlViewUtil.queryField(response1.getResult(), "input", "name", "execution", "value");
        List<String> cookies = response1.getResponseHeaders().get("Set-Cookie");

        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient;
        // 发送请求
        CloseableHttpResponse response;
        try {
            httpclient = HttpClients.createDefault();

            // 创建httppost
            String url = "https://hs-cas.hundsun.com" + loginFormAction;
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("Connection", "Keep-Alive");
            httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httppost.addHeader("Charset", "UTF-8");
            String cookie = cookies.get(1).split(";")[0] + "; " + cookies.get(0).split(";")[0];
            httppost.addHeader("Cookie", cookie);
            httppost.addHeader("HOST", "hs-cas.hundsun.com");
            httppost.addHeader("Pragma", "no-cache");
            httppost.addHeader("Upgrade-Insecure-Requests", "1");
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

            // 创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("username", loginBean.getLoginName()));
            formparams.add(new BasicNameValuePair("password", loginBean.getLoginPassword()));
            formparams.add(new BasicNameValuePair("lt", loginFieldlt));
            formparams.add(new BasicNameValuePair("execution", loginFieldExecution));
            formparams.add(new BasicNameValuePair("_eventId", "submit"));

            //参数转码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);

            //获取结果
            String nextLocation = "";
            response = httpclient.execute(httppost);
            Thread.sleep(2000);

            //因为不知道什么时候能页面能跳转好，所以循环等待
            int waittime = 0;
            do {
                Header location = response.getFirstHeader("Location");
                if (location != null) {
                    nextLocation = location.getValue();
                    break;
                } else {
                    HeaderIterator it = response.headerIterator();
                    while (it.hasNext()) {
                        Header hd = (Header) it.next();
                        log.debug(hd.getName() + ":" + hd.getValue());
                    }
                    Thread.sleep(waittime + 2000);
                }
            } while (waittime < 20000);

            if ("".equals(nextLocation)) {
                log.error(response.getAllHeaders());
                //释放连接
                httpclient.close();
                response.close();
                throw new IOException("获取返回地址失败...");
            }

            //释放连接
            httpclient.close();
            response.close();
            log.info("返回地址：" + nextLocation);
            return login2(url, nextLocation);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private TsLoginResult login2(String preLocation, String nextLocation) {
        log.info("统一认证成功返回...");
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient;
        // 发送请求
        CloseableHttpResponse response;
        try {
            String cookie = nextLocation.substring(nextLocation.indexOf(";") + 1, nextLocation.indexOf("?"));
            cookie = cookie.toUpperCase();
            httpclient = HttpClients.createDefault();
            String preTemp = preLocation.substring(0, preLocation.indexOf(";")) + preLocation.substring(preLocation.indexOf("?"), preLocation.length());
            HttpPost httppost = new HttpPost(nextLocation);
            httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httppost.addHeader("Cache-Control", "no-cache");
            httppost.addHeader("Connection", "Keep-Alive");
            httppost.addHeader("Cookie", cookie);
            httppost.addHeader("Host", "ts.hundsun.com");
            httppost.addHeader("Pragma", "no-cache");
            httppost.addHeader("Referer", preTemp);
            httppost.addHeader("Upgrade-Insecure-Requests", "1");
            httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            response = httpclient.execute(httppost);

            String finalLocation = "https://ts.hundsun.com/se/portal/SupportPortal.htm";
            Header location = response.getFirstHeader("Location");
            if (location != null) {
                finalLocation = location.getValue();
            }

            //释放连接
            httpclient.close();
            response.close();
            return finalLogin(preTemp, finalLocation, cookie);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private TsLoginResult finalLogin(String preLocation, String finalLocation, String cookie) {
        log.info("登录TS系统，并获取token...");
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient;
        // 发送请求
        CloseableHttpResponse response;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(finalLocation);
            httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httppost.addHeader("Cache-Control", "no-cache");
            httppost.addHeader("Connection", "Keep-Alive");
            httppost.addHeader("Cookie", cookie);
            httppost.addHeader("Host", "ts.hundsun.com");
            httppost.addHeader("Pragma", "no-cache");
            httppost.addHeader("Referer", preLocation);
            httppost.addHeader("Upgrade-Insecure-Requests", "1");
            httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
            TsLoginResult result = new TsLoginResult();
            if (entity != null) {
                String tsHtml = EntityUtils.toString(entity, "UTF-8");
                int tokenStart = tsHtml.indexOf("se.SEConfig.token = \"");

                result.setCookie(cookie);
                result.setToken(tsHtml.substring(tokenStart + 21, tokenStart + 34));

                int testAgStart = tsHtml.indexOf("se.SEConfig.testingag               = \"");
                result.setTestAg(tsHtml.substring(testAgStart + 39, testAgStart + 52));

                int currentGroupStart = tsHtml.indexOf("se.SEConfig.currentGroup\t\t\t= \"");
                result.setCurrentGroup(tsHtml.substring(currentGroupStart + 30, currentGroupStart + 62));

                int userIdStart = tsHtml.indexOf("se.SEConfig.UserId\t\t\t= \"");
                result.setUserId(tsHtml.substring(userIdStart + 24, userIdStart + 29));

            }
            //释放连接
            httpclient.close();
            response.close();
            return finalLogin2(result);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
