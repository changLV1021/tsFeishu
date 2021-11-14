package com.hundsun.ts2dingding.model;

/**
 * @author  litg
 * @date 2018/05/06.
 */
public class TsSysCode {
    /** 字典值 */
    private String code;
    /** 字典值说明 */
    private String codeDesc;
    /** 字典编号 */
    private String codeSeq;
    /** 字典名称 */
    private String codetypeId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getCodeSeq() {
        return codeSeq;
    }

    public void setCodeSeq(String codeSeq) {
        this.codeSeq = codeSeq;
    }

    public String getCodetypeId() {
        return codetypeId;
    }

    public void setCodetypeId(String codetypeId) {
        this.codetypeId = codetypeId;
    }
}
