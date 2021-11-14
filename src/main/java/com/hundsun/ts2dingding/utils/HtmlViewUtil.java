package com.hundsun.ts2dingding.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangzx19274 on 2018/1/26.
 */
public class HtmlViewUtil {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("SYS");

    public static String queryField(String html, String node, String keyField, String keyValue, String needField) {
        Document doc;
        try {
        	String htmlStr = html;
        	//定义script的正则表达式
        	String regExScript="<script[^>]*?>[\\s\\S]*?<\\/script>";  
        	Pattern pScript=Pattern.compile(regExScript,Pattern.CASE_INSENSITIVE); 
            Matcher mScript=pScript.matcher(htmlStr); 
            htmlStr=mScript.replaceAll(""); 

            doc = DocumentHelper.parseText(htmlStr);
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("xml不能为空");
        }
        Element element = doc.getRootElement();

        List<Element> scopeList = new ArrayList<Element>();
        scopeList.add(element);

        while (!scopeList.isEmpty()) {
            element = scopeList.remove(0);
            if (element.getName().equals(node)) {
                Attribute attribute = element.attribute(keyField);
                if (attribute != null && keyValue.equals(attribute.getValue())) {
                    attribute = element.attribute(needField);
                    if (attribute != null) {
                        return attribute.getValue();
                    }
                }
            }
            Iterator<?> iterator = element.elementIterator();
            while (iterator.hasNext()) {
                scopeList.add(0, (Element) iterator.next());
            }
        }
        throw new RuntimeException("无节点");
    }


    public static String getResponseHtml(CloseableHttpClient httpclient, CloseableHttpResponse response, HttpPost httppost) {
        String tsHtml = null;
        try {
            //获取结果
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            tsHtml = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                //释放连接
                httpclient.close();
                response.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return tsHtml;
    }

    public static void getAtList(List<String> resvPhone, String atuser, Object oneSearch, Map<String, String> userinfo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (atuser == null || atuser.length() == 0 || "[ALL]".equals(atuser)) {
            return;
        }
        if (atuser.equals("[ALL_IN_LIST]")){
            for (Map.Entry<String, String> entity : userinfo.entrySet()) {
               resvPhone.add(entity.getValue());
            }
            return;
        }
        Object tdAtUser = oneSearch.getClass().getMethod("get" + StringUtils.captureFirst(atuser), new Class[]{}).invoke(oneSearch, new Object[]{});
        if (tdAtUser != null) {
            for (Map.Entry<String, String> entity : userinfo.entrySet()) {
                if (((String) tdAtUser).contains(entity.getKey())) {
                    resvPhone.add(entity.getValue());
                }
            }
        }
    }

    public static void getSendList(List<String> toSend, String want2show, Object oneSearch, Map<String, Map<String, String>> sysCodeMap, String atuser, Map<String, String> sysUserMap, Map<String, String> productsMap) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String[] want2shows = want2show.split(",");
        String oneLine = "";
        for (String oneTD : want2shows) {
            Object tdValue = null;
            try {
                tdValue = oneSearch.getClass().getMethod("get" + StringUtils.captureFirst(oneTD), new Class[]{}).invoke(oneSearch, new Object[]{});
            }catch (NoSuchMethodException ex){
                log.error("TS新增了字段" + oneTD +",该字段尚不支持，不会显示");
                continue;
            }
            if (tdValue == null) {
                oneLine = oneLine + " ";
                continue;
            }
            //如果取到的内容是一个字典值的话，需要用系统字典进行转译
            Map<String, String> codeMap = sysCodeMap.get(oneTD);
            if (codeMap != null) {
                tdValue = codeMap.get(tdValue);
            }
            //如果是产品ID的话，需要翻译为产品名称
            else if (oneTD.equalsIgnoreCase("productId")) {
                tdValue = productsMap.get(tdValue);
            }
            //对于是推送目标的员工号，需要翻译为姓名来显示
            else if (oneTD.equals(atuser)) {
                if (tdValue.toString().contains(",")) {
                    String[] users = tdValue.toString().split(",");
                    String[] usernames= new String[users.length];
                    int i=0;
                    for (String userid : users) {
                        String username = sysUserMap.get(userid);
                        usernames[i] = username != null ? username : userid;
                        i++;
                    }
                    tdValue = Arrays.toString(usernames);
                }else {
                    String username = sysUserMap.get(tdValue);
                    tdValue = username != null ? username : tdValue;
                }
            }
            oneLine = oneLine + tdValue + " ";
        }
        toSend.add(oneLine);
    }
}
