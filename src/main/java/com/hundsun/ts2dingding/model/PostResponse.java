package com.hundsun.ts2dingding.model;

import java.util.List;
import java.util.Map;

/**
 * Created by huangzx19274 on 2018/1/26.
 *
 */
public class PostResponse {
    private Map<String, List<String>> responseHeaders;
    private String result;

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, List<String>> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
