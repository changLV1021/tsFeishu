package com.hundsun.ts2dingding.model;

import java.util.List;

/**
 * Created by huangzx19274 on 2018/1/25.
 *
 */
public class TsTaskQueryResult {
    private boolean success;
    private List<TsTask> resultBOList;
    private int recordsCount;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<TsTask> getResultBOList() {
        return resultBOList;
    }

    public void setResultBOList(List<TsTask> resultBOList) {
        this.resultBOList = resultBOList;
    }

    public int getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }
}
