package com.hundsun.ts2dingding.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * 对称加密的工具类
 *
 * @author tiegangli
 * @date
 */
public class AesUtils {

    private static Logger LOG = Logger.getLogger("SYS");

    /**
     * 使用AES加密
     *
     * @param content 需要加密的内容，字符串形式
     * @param seed    生成密钥的种子
     * @return String 加密后的密文
     */
    public static String encrypt(String content, String seed) {
        try {
            //加密方式：AES
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            //产生密钥
            secureRandom.setSeed(seed.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // 创建密码器
            byte[] byteContent = content.getBytes("UTF-8");
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //转换为String返回
            return parseByte2HexStr(result);
        } catch (Exception e) {
            LOG.error("加密失败", e);
        }
        return null;
    }

    /**
     * 使用AES解密
     *
     * @param content 要解密的内容，字符串形式
     * @param seed    生成密钥的种子
     * @return String 解密后的明文
     */
    public static String decrypt(String content, String seed) {
        try {
            //加密方式：AES
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            //产生密钥
            secureRandom.setSeed(seed.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 解密
            byte[] result = cipher.doFinal(parseHexStr2Byte(content));
            //转换为String返回
            return new String(result, "UTF-8");
        } catch (Exception e) {
            LOG.error("解密失败", e);
        }
        return null;
    }

    /**
     * 将二进制转换成字符串
     *
     * @param buf 待转换的二进制数组
     * @return String 转换后的字符串
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将字符串转换为二进制
     *
     * @param hexStr 待转换的字符串
     * @return byte[] 转换后的二进制数组
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 加密配置文件里的密码
     *
     * @param pu   配置文件对象
     * @param path 配置文件地址
     * @return String 明文的密码
     */
    public static String getTruePassword(PropertiesUtils pu, String path) {
        String password = pu.getPropertiesValue("ts2dingding.login.password");
        //判断密码是否已经加密，如果没有，要加密后回存，如果已经加密，则解密后使用
        if (password.length() != 32) {
            String encryptedPwd = AesUtils.encrypt(password, "HUNDSUN@1");
            pu.setProprtiesValue("ts2dingding.login.password", encryptedPwd);
            pu.write2PropertiesFile(path);
            return password;
        } else {
            String decryptedPwd = AesUtils.decrypt(password, "HUNDSUN@1");
            pu.setProprtiesValue("ts2dingding.login.password", password);
            return decryptedPwd;
        }
    }
}
