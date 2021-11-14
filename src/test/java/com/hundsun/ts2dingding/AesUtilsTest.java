package com.hundsun.ts2dingding;

import com.hundsun.ts2dingding.utils.AesUtils;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 * @author litg
 * @date 2017/09/15.
 */
public class AesUtilsTest extends TestCase {

    public void testEncrypt(){
        //需要加密的内容
        String content = "加密的內容" ;
        //加密密码
        String password = "HUNDSUN@1" ;
        String encryptContent = AesUtils.encrypt(content, password);
        Assert.assertEquals(encryptContent, "AB97890B081DF0784A2AC05395D03134");
    }

    public void testDecrypt(){
        //需要加密的内容
        String content = "加密的內容" ;
        //加密密码
        String password = "HUNDSUN@1" ;
        String decryptContent = AesUtils.decrypt("AB97890B081DF0784A2AC05395D03134" , password);
        Assert.assertEquals(decryptContent,"加密的內容");
    }

}
