package com.hundsun.ts2dingding.model;

import java.util.List;

/**
 * Created by litg on 2018/03/31.
 */
public class TsDevelopQueryResult {
    private boolean success;
    private List<TsDevelop> resultBOList;
    private int recordsCount;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<TsDevelop> getResultBOList() {
        return resultBOList;
    }

    public void setResultBOList(List<TsDevelop> resultBOList) {
        this.resultBOList = resultBOList;
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }
}
