package com.hundsun.ts2dingding.model;

import com.hundsun.ts2dingding.utils.StringUtils;

/**
 *
 * @author litg
 * @date 2018/03/31
 */
public class TsDemand {

    private String assignStatus;
    private String auditDate;
    private String auditer;
    private String batch;
    private String commerceIs;
    private String commitDate;
    private String commiter;
    private String commiterType;
    private String connectType;
    private String connectWith;
    private String contactorEmail;
    private String contactorId;
    private String contactorId3;
    private String contactorName;
    private String customerId;
    private String[] customerIds;
    private String customerName;
    private String dealDate;
    private String dealDesc;
    private String dealResult;
    private String[] deleteStates;
    private String deleteStatus;
    private String description;
    private String division;
    private String extTxt3;
    private String firstAuditDate;
    private String firstPromiseDate;
    private String hasAudit;
    private String industry;
    private String inputDate;
    private String isExistComment;
    private String isHasAccessory;
    private String isHasReply;
    private String isHasTask;
    private String lastUpdateBy;
    private String lastUpdateDttm;
    private String opinionAuthor;
    private String phase;
    private String productId;
    private String[] productIds;
    private String productVer;
    private String productVerName;
    private String programIssuedIs;
    private String promiseDate;
    private String promiseDateStr;
    private String promiseVerName;
    private String promiseVersion;
    private String publishStatus;
    private String publishedNoUpdated;
    private String[] publishedNoUpdateds;
    private String reReplyDate;
    private String receiver;
    private String releationReqs;
    private String replyDate;
    private String replyStatus;
    private String replyer;
    private String reqId;
    private String[] reqIds;
    private String reqNum;
    private String reqReplyDate;
    private String reqSource;
    private String reqStatus;
    private String reqType;
    private String reqWorth;
    private String reqViewed;
    private String synProductStatus;
    private String requireSendMessage;
    private String taskStatus;
    private String timestamp;
    private String workAmount;
    private String workAmount2;
    private String workAmount3;
    private String workflowId;
    /** 新增ts字段：测试遗漏分析人 */
    private String testAnalyzer;

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getContactorEmail() {
        return contactorEmail;
    }

    public void setContactorEmail(String contactorEmail) {
        this.contactorEmail = contactorEmail;
    }

    public String[] getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String[] customerIds) {
        this.customerIds = customerIds;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFirstAuditDate() {
        return firstAuditDate;
    }

    public void setFirstAuditDate(String firstAuditDate) {
        this.firstAuditDate = firstAuditDate;
    }

    public String getFirstPromiseDate() {
        return firstPromiseDate;
    }

    public void setFirstPromiseDate(String firstPromiseDate) {
        this.firstPromiseDate = firstPromiseDate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getProductVer() {
        return productVer;
    }

    public void setProductVer(String productVer) {
        this.productVer = productVer;
    }

    public String getProductVerName() {
        return productVerName;
    }

    public void setProductVerName(String productVerName) {
        this.productVerName = productVerName;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getPublishedNoUpdated() {
        return publishedNoUpdated;
    }

    public void setPublishedNoUpdated(String publishedNoUpdated) {
        this.publishedNoUpdated = publishedNoUpdated;
    }

    public String[] getPublishedNoUpdateds() {
        return publishedNoUpdateds;
    }

    public void setPublishedNoUpdateds(String[] publishedNoUpdateds) {
        this.publishedNoUpdateds = publishedNoUpdateds;
    }

    public String getReleationReqs() {
        return releationReqs;
    }

    public void setReleationReqs(String releationReqs) {
        this.releationReqs = releationReqs;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getReplyer() {
        return replyer;
    }

    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }

    public String getReqReplyDate() {
        return reqReplyDate;
    }

    public void setReqReplyDate(String reqReplyDate) {
        this.reqReplyDate = reqReplyDate;
    }

    public String getSynProductStatus() {
        return synProductStatus;
    }

    public void setSynProductStatus(String synProductStatus) {
        this.synProductStatus = synProductStatus;
    }

    public String getAssignStatus() {
        return assignStatus;
    }

    public void setAssignStatus(String assignStatus) {
        this.assignStatus = assignStatus;
    }

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer;
    }

    public String getCommerceIs() {
        return commerceIs;
    }

    public void setCommerceIs(String commerceIs) {
        this.commerceIs = commerceIs;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public String getCommiter() {
        return commiter;
    }

    public void setCommiter(String commiter) {
        this.commiter = commiter;
    }

    public String getCommiterType() {
        return commiterType;
    }

    public void setCommiterType(String commiterType) {
        this.commiterType = commiterType;
    }

    public String getConnectType() {
        return connectType;
    }

    public void setConnectType(String connectType) {
        this.connectType = connectType;
    }

    public String getConnectWith() {
        return connectWith;
    }

    public void setConnectWith(String connectWith) {
        this.connectWith = connectWith;
    }

    public String getContactorId() {
        return contactorId;
    }

    public void setContactorId(String contactorId) {
        this.contactorId = contactorId;
    }

    public String getContactorId3() {
        return contactorId3;
    }

    public void setContactorId3(String contactorId3) {
        this.contactorId3 = contactorId3;
    }

    public String getContactorName() {
        return contactorName;
    }

    public void setContactorName(String contactorName) {
        this.contactorName = contactorName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealDesc() {
        return dealDesc;
    }

    public void setDealDesc(String dealDesc) {
        this.dealDesc = dealDesc;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String[] getDeleteStates() {
        return deleteStates;
    }

    public void setDeleteStates(String[] deleteStates) {
        this.deleteStates = deleteStates;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtTxt3() {
        return extTxt3;
    }

    public void setExtTxt3(String extTxt3) {
        this.extTxt3 = extTxt3;
    }

    public String getHasAudit() {
        return hasAudit;
    }

    public void setHasAudit(String hasAudit) {
        this.hasAudit = hasAudit;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getIsExistComment() {
        return isExistComment;
    }

    public void setIsExistComment(String isExistComment) {
        this.isExistComment = isExistComment;
    }

    public String getIsHasAccessory() {
        return isHasAccessory;
    }

    public void setIsHasAccessory(String isHasAccessory) {
        this.isHasAccessory = isHasAccessory;
    }

    public String getIsHasReply() {
        return isHasReply;
    }

    public void setIsHasReply(String isHasReply) {
        this.isHasReply = isHasReply;
    }

    public String getIsHasTask() {
        return isHasTask;
    }

    public void setIsHasTask(String isHasTask) {
        this.isHasTask = isHasTask;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getLastUpdateDttm() {
        return lastUpdateDttm;
    }

    public void setLastUpdateDttm(String lastUpdateDttm) {
        this.lastUpdateDttm = lastUpdateDttm;
    }

    public String getOpinionAuthor() {
        return opinionAuthor;
    }

    public void setOpinionAuthor(String opinionAuthor) {
        this.opinionAuthor = opinionAuthor;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String[] getProductIds() {
        return productIds;
    }

    public void setProductIds(String[] productIds) {
        this.productIds = productIds;
    }

    public String getProgramIssuedIs() {
        return programIssuedIs;
    }

    public void setProgramIssuedIs(String programIssuedIs) {
        this.programIssuedIs = programIssuedIs;
    }

    public String getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(String promiseDate) {
        this.promiseDate = promiseDate;
    }

    public String getPromiseDateStr() {
        return promiseDateStr;
    }

    public void setPromiseDateStr(String promiseDateStr) {
        this.promiseDateStr = promiseDateStr;
    }

    public String getPromiseVerName() {
        return promiseVerName;
    }

    public void setPromiseVerName(String promiseVerName) {
        this.promiseVerName = promiseVerName;
    }

    public String getPromiseVersion() {
        return promiseVersion;
    }

    public void setPromiseVersion(String promiseVersion) {
        this.promiseVersion = promiseVersion;
    }

    public String getReReplyDate() {
        return reReplyDate;
    }

    public void setReReplyDate(String reReplyDate) {
        this.reReplyDate = reReplyDate;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String[] getReqIds() {
        return reqIds;
    }

    public void setReqIds(String[] reqIds) {
        this.reqIds = reqIds;
    }

    public String getReqNum() {
        return reqNum;
    }

    public void setReqNum(String reqNum) {
        this.reqNum = reqNum;
    }

    public String getReqSource() {
        return reqSource;
    }

    public void setReqSource(String reqSource) {
        this.reqSource = reqSource;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getReqViewed() {
        return reqViewed;
    }

    public void setReqViewed(String reqViewed) {
        this.reqViewed = reqViewed;
    }

    public String getReqWorth() {
        return reqWorth;
    }

    public void setReqWorth(String reqWorth) {
        this.reqWorth = reqWorth;
    }

    public String getRequireSendMessage() {
        return requireSendMessage;
    }

    public void setRequireSendMessage(String requireSendMessage) {
        this.requireSendMessage = requireSendMessage;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getWorkAmount() {
        return workAmount;
    }

    public void setWorkAmount(String workAmount) {
        this.workAmount = workAmount;
    }

    public String getWorkAmount2() {
        return workAmount2;
    }

    public void setWorkAmount2(String workAmount2) {
        this.workAmount2 = workAmount2;
    }

    public String getWorkAmount3() {
        return workAmount3;
    }

    public void setWorkAmount3(String workAmount3) {
        this.workAmount3 = workAmount3;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getTestAnalyzer() {
        return testAnalyzer;
    }

    public void setTestAnalyzer(String testAnalyzer) {
        this.testAnalyzer = testAnalyzer;
    }
}
