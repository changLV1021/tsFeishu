package com.hundsun.ts2dingding.utils;

import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取配置文件的类
 * Created by litg on 2017/06/05.
 */
public class PropertiesUtils {
    private static Logger LOG = Logger.getLogger("SYS");

    private Properties p = new Properties();

    public PropertiesUtils(String filepath) throws IOException {
        String truePath = System.getProperty("user.dir") + filepath;
        LOG.debug("尝试查找配置文件：" + truePath);
        InputStream in = new BufferedInputStream(new FileInputStream(truePath));
        // 指定编码，以防止乱码
        p.load(new InputStreamReader(in, "UTF-8"));
    }

    public Map<String, String> getPhoneMap(String mobileList) {
        Map<String, String> userinfo = new HashMap<>();
        // 检查有没有配置电话号码，如果有，发消息要针对接收人分组
        if (!StringUtil.isBlank(mobileList)) {
            String[] users = mobileList.split(",");
            for (String user : users) {
                String[] mobile = user.split("\\|");
                userinfo.put(mobile[0], mobile[1]);
            }
        }

        return userinfo;
    }

    /**
     * 判断配置文件中是否存在某个配置项
     *
     * @param key
     * @return
     */
    public boolean containsKey(String key){
        return p.containsKey(key);
    }

    /**
     * 根据key返回配置项的值
     *
     * @param key 配置文件里的key
     * @return
     */
    public String getPropertiesValue(String key) {
        return p.getProperty(key);
    }

    /**
     * 回写配置项的值
     *
     * @param key   配置文件里的key
     * @param value 回写的value
     */
    public void setProprtiesValue(String key, String value) {
        p.setProperty(key, value);
    }

    /**
     * 输出配置文件内容
     *
     * @param path 文件真实路径
     */
    public void write2PropertiesFile(String path) {
        FileOutputStream oFile = null;
        try {
            String truePath = System.getProperty("user.dir") + path;
            // 用于回写配置文件，以非追加模式打开
            oFile = new FileOutputStream(truePath, false);
            p.store(oFile, "已自动加密");
        } catch (Exception e) {
            LOG.error("回写加密后的密码失败", e);
        } finally {
            if (oFile != null) {
                try {
                    oFile.close();
                } catch (IOException e) {
                    LOG.error("关闭文件失败：" + path);
                }
            }
        }

    }

    /**
     * @param obj 转换的对象值
     * @param clz 类对象
     * @return 转换后的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T transferObject(Object obj, Class<?> clz) {
        T result = null;
        if (obj != null && !obj.equals("")) {
            Method[] methods = obj.getClass().getMethods();
            try {
                result = (T) clz.newInstance();
            } catch (Exception e1) {
                return null;
            }
            Method m;
            for (int i = 0; i < methods.length; i++) {
                m = methods[i];
                try {
                    if (m.getName().startsWith("set")) {
                        String fieldName = m.getName().replaceFirst("set", "");
                        Method method = result.getClass().getMethod(m.getName(), m.getParameterTypes());
                        Method getMethod = obj.getClass().getMethod("get" + fieldName, new Class[]{});
                        method.invoke(result, getMethod.invoke(obj, new Object[]{}));
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return result;
    }
}
