package com.hundsun.ts2dingding.model;

import java.util.Map;

/**
 * Created by litg on 2018/5/6.
 */
public class TsProductsResult {

    private Map<String, TsProducts[]> resultMap;

    public Map<String, TsProducts[]> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, TsProducts[]> resultMap) {
        this.resultMap = resultMap;
    }

}
