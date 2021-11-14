package com.hundsun.ts2dingding.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;

/**
 * @author  litg
 * @date 2018/03/31.
 */
public class DingDingUtil {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("SYS");

    /**
     * 加签
     * @param secret
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static String stringToSign(String secret, Long timestamp) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        // 加签
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
        System.out.println(sign);
        return sign;
    }

    /**
     * 设置字体
     * @param s 内容
     * @return 加了字体的内容
     */
    private static String font(String s){
        return "<font color=\"#3296FA\">"+s+"</font>";
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

    /**
     * 默认的发送消息方法
     * @param toSend 待发送内容
     * @param resvPhone 需要@的人
     * @param title 消息的标题
     * @param webhook 接收消息的接口地址
     * @param dingTalkRobotSec 机器人验签码
     * @return 发送成功true，失败false
     */
    public static boolean sendMessage2DingBot(List<String> toSend, List<String> resvPhone, String title, String webhook, String dingTalkRobotSec, boolean isAtAll) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException{
        //@人员名单会有重复，所以要去重
        resvPhone = removeDuplicate(resvPhone);

        if (toSend.size() != 0) {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("markdown");
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            markdown.setTitle(title);
            StringBuilder text = new StringBuilder();
            text.append("### **" + title + ":**\n");
            for (int i = 1; i <= toSend.size(); i++) {
                text.append(i + ". " + font(toSend.get(i - 1).replaceFirst("-\\W+","")) + "\n");
            }
            if (isAtAll){
                text.append("### ");
                text.append("@所有人");
            }else if (resvPhone.size() > 0) {
                text.append("### ");
                for (int i = 0; i < resvPhone.size(); i++) {
                    text.append("@").append(resvPhone.get(i));
                }
            }
            log.info("发送内容："+text.toString());
            Long timestamp = System.currentTimeMillis();
            String sign = stringToSign(dingTalkRobotSec, timestamp);
            String webhookWithSigned = webhook + "&timestamp=" + timestamp + "&sign=" + sign;
            markdown.setText(text.toString());
            request.setMarkdown(markdown);
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setAtMobiles(resvPhone);
            request.setAt(at);
            try {
                DingTalkClient client = new DefaultDingTalkClient(webhookWithSigned);
                OapiRobotSendResponse response = client.execute(request);
                log.info("发送成功：" + response.getErrorCode()+":"+response.getErrmsg());
                return true;
            } catch (ApiException e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
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
    public static boolean sendTextMessage2DingBot(List<String> toSend, List<String> resvPhone, String title, String webhook, String dingTalkRobotSec, boolean isAtAll) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {


        if (toSend.size() != 0) {
            // 生成验签码
            Long timestamp = System.currentTimeMillis();
            String sign = stringToSign(dingTalkRobotSec, timestamp);
            String webhookWithSigned = webhook + "&timestamp=" + timestamp + "&sign=" + sign;

            DingTalkClient client = new DefaultDingTalkClient(webhookWithSigned);
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            StringBuilder textContent = new StringBuilder();
            textContent.append(title + "：\n");
            for (int i = 0; i < toSend.size(); i++) {
                textContent.append("-" + toSend.get(i) + "\n");
            }
            text.setContent(textContent.toString());
            request.setText(text);
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();

            if (isAtAll) {
                at.setIsAtAll(true);
            }else{
                //@人员名单会有重复，所以要去重
                resvPhone = removeDuplicate(resvPhone);
                at.setAtMobiles(resvPhone);
            }

            request.setAt(at);
            try {
                log.info("发送内容：" + request);
                OapiRobotSendResponse response = client.execute(request);
                log.info("发送成功：" + response);
                return true;
            } catch (ApiException e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
    }

    /**
     * 以事件形式发送消息
     * @param toSend 待发送内容
     * @param title 消息的标题
     * @param webhook 接收消息的接口地址
     * @return 发送成功true，失败false
     */
    public static boolean sendActionCardMessage2DingBot(List<String> toSend, String title, String webhook) {
        if (toSend.size() != 0) {
            DingTalkClient client = new DefaultDingTalkClient(webhook);
            OapiRobotSendRequest request = new OapiRobotSendRequest();


            request.setMsgtype("actionCard");
            OapiRobotSendRequest.Actioncard actioncard = new OapiRobotSendRequest.Actioncard();
            actioncard.setTitle(title);
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < toSend.size(); i++) {
                text.append("#### " + toSend.get(i) + "\n");
            }
            actioncard.setText(text.toString());
            request.setActionCard(actioncard);
            actioncard.setSingleURL("https://ts.hundsun.com/se");
            request.setActionCard(actioncard);
            try {
                log.info("发送内容：" + request);
                OapiRobotSendResponse response = client.execute(request);
                log.info("发送成功：" + response);
                return true;
            } catch (ApiException e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
    }
}