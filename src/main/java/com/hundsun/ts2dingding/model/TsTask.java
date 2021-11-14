package com.hundsun.ts2dingding.model;

import java.util.List;

/**
 * @author huangzx19274
 * @date 2018/1/25.
 */
public class TsTask {
    private String productId;
    private String assignDate;
    private String caseStatus;
    private String completedStatus;
    private String customerName;
    private String deleteStatus;
    private String designAccount;
    private String desinger;
    private String emailNoticeUsers;
    private String extDate;
    private String extTxt1;
    private String firstAssignDate;
    private String firstModifyDate;
    private String hasTaskFeedBack;
    private String isAssign;
    private String isRequiredAuditing;
    private String lastUpdateBy;
    private String lastUpdateDttm;
    private String light;
    private String modifyDate;
    private String modifyReview;
    private String modifyVer;
    private String modifyVerNames;
    private String moduleIds;
    private String moduleNames;
    private String productVersionType;
    private String programer;
    private String projectId;
    private String promiseDate;
    private String[] relationList;
    private String relationType;
    private String reqConfirm;
    private String reqCount;
    private String[] reqIdArr;
    private String reqIds;
    private String reqNums;
    private String requireSendMessage;
    private String resultReview;
    private String sendType;
    private String sugesstion;
    private String taskAssigner;
    private String taskDifficulty;
    private String taskId;
    private String taskNum;
    private String taskOwner;
    private String taskReqDesc;
    private String taskStatus;
    private String taskType;
    private String testAssignStatus;
    private String testStatus;
    private String testamount;
    private long timestamp;
    private String workAccount;
    private long workflowId;

    /** 是否现场测试*/
    private int isUserTest;
    /**是否需要提交脚本 2 否*/
    private int isScriptCommit;
    /**是否需要脚本 2 否*/
    private int isScript;
    /**是否提交 3*/
    private int isCommit;
    /**实际开发完成日期*/
    private String realDevDate;
    /**任务审核人*/
    private String modifyReviewer;
    /**完成状态2*/
    private int isAllCompleted;
    /**审核人*/
    private String resultReviewer;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getModifyReview() {
        return modifyReview;
    }

    public String[] getRelationList() {
        return relationList;
    }

    public String getReqCount() {
        return reqCount;
    }

    public String getResultReview() {
        return resultReview;
    }

    public String getIsRequiredAuditing() {
        return isRequiredAuditing;
    }

    public void setIsRequiredAuditing(String isRequiredAuditing) {
        this.isRequiredAuditing = isRequiredAuditing;
    }

    public void setModifyReview(String modifyReview) {
        this.modifyReview = modifyReview;
    }

    public String getProductVersionType() {
        return productVersionType;
    }

    public void setProductVersionType(String productVersionType) {
        this.productVersionType = productVersionType;
    }

    public void setRelationList(String[] relationList) {
        this.relationList = relationList;
    }

    public void setReqCount(String reqCount) {
        this.reqCount = reqCount;
    }

    public String[] getReqIdArr() {
        return reqIdArr;
    }

    public void setReqIdArr(String[] reqIdArr) {
        this.reqIdArr = reqIdArr;
    }

    public String getRequireSendMessage() {
        return requireSendMessage;
    }

    public void setRequireSendMessage(String requireSendMessage) {
        this.requireSendMessage = requireSendMessage;
    }

    public void setResultReview(String resultReview) {
        this.resultReview = resultReview;
    }

    public String getTestamount() {
        return testamount;
    }

    public void setTestamount(String testamount) {
        this.testamount = testamount;
    }

    public String getRealDevDate() {
        return realDevDate;
    }

    public void setRealDevDate(String realDevDate) {
        this.realDevDate = realDevDate;
    }

    public String getModifyReviewer() {
        return modifyReviewer;
    }

    public void setModifyReviewer(String modifyReviewer) {
        this.modifyReviewer = modifyReviewer;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getDesignAccount() {
        return designAccount;
    }

    public void setDesignAccount(String designAccount) {
        this.designAccount = designAccount;
    }

    public String getDesinger() {
        return desinger;
    }

    public void setDesinger(String desinger) {
        this.desinger = desinger;
    }

    public String getEmailNoticeUsers() {
        return emailNoticeUsers;
    }

    public void setEmailNoticeUsers(String emailNoticeUsers) {
        this.emailNoticeUsers = emailNoticeUsers;
    }

    public String getExtDate() {
        return extDate;
    }

    public void setExtDate(String extDate) {
        this.extDate = extDate;
    }

    public String getExtTxt1() {
        return extTxt1;
    }

    public void setExtTxt1(String extTxt1) {
        this.extTxt1 = extTxt1;
    }

    public String getFirstAssignDate() {
        return firstAssignDate;
    }

    public void setFirstAssignDate(String firstAssignDate) {
        this.firstAssignDate = firstAssignDate;
    }

    public String getFirstModifyDate() {
        return firstModifyDate;
    }

    public void setFirstModifyDate(String firstModifyDate) {
        this.firstModifyDate = firstModifyDate;
    }

    public String getHasTaskFeedBack() {
        return hasTaskFeedBack;
    }

    public void setHasTaskFeedBack(String hasTaskFeedBack) {
        this.hasTaskFeedBack = hasTaskFeedBack;
    }

    public int getIsAllCompleted() {
        return isAllCompleted;
    }

    public void setIsAllCompleted(int isAllCompleted) {
        this.isAllCompleted = isAllCompleted;
    }

    public String getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(String isAssign) {
        this.isAssign = isAssign;
    }

    public int getIsCommit() {
        return isCommit;
    }

    public void setIsCommit(int isCommit) {
        this.isCommit = isCommit;
    }

    public int getIsScript() {
        return isScript;
    }

    public void setIsScript(int isScript) {
        this.isScript = isScript;
    }

    public int getIsScriptCommit() {
        return isScriptCommit;
    }

    public void setIsScriptCommit(int isScriptCommit) {
        this.isScriptCommit = isScriptCommit;
    }

    public int getIsUserTest() {
        return isUserTest;
    }

    public void setIsUserTest(int isUserTest) {
        this.isUserTest = isUserTest;
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

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }


    public String getModifyVer() {
        return modifyVer;
    }

    public void setModifyVer(String modifyVer) {
        this.modifyVer = modifyVer;
    }

    public String getModifyVerNames() {
        return modifyVerNames;
    }

    public void setModifyVerNames(String modifyVerNames) {
        this.modifyVerNames = modifyVerNames;
    }

    public String getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(String moduleIds) {
        this.moduleIds = moduleIds;
    }

    public String getModuleNames() {
        return moduleNames;
    }

    public void setModuleNames(String moduleNames) {
        this.moduleNames = moduleNames;
    }

    public String getProgramer() {
        return programer;
    }

    public void setProgramer(String programer) {
        this.programer = programer;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(String promiseDate) {
        this.promiseDate = promiseDate;
    }


    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getReqConfirm() {
        return reqConfirm;
    }

    public void setReqConfirm(String reqConfirm) {
        this.reqConfirm = reqConfirm;
    }



    public String getReqIds() {
        return reqIds;
    }

    public void setReqIds(String reqIds) {
        this.reqIds = reqIds;
    }

    public String getReqNums() {
        return reqNums;
    }

    public void setReqNums(String reqNums) {
        this.reqNums = reqNums;
    }


    public String getResultReviewer() {
        return resultReviewer;
    }

    public void setResultReviewer(String resultReviewer) {
        this.resultReviewer = resultReviewer;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSugesstion() {
        return sugesstion;
    }

    public void setSugesstion(String sugesstion) {
        this.sugesstion = sugesstion;
    }

    public String getTaskAssigner() {
        return taskAssigner;
    }

    public void setTaskAssigner(String taskAssigner) {
        this.taskAssigner = taskAssigner;
    }

    public String getTaskDifficulty() {
        return taskDifficulty;
    }

    public void setTaskDifficulty(String taskDifficulty) {
        this.taskDifficulty = taskDifficulty;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(String taskOwner) {
        this.taskOwner = taskOwner;
    }

    public String getTaskReqDesc() {
        return taskReqDesc;
    }

    public void setTaskReqDesc(String taskReqDesc) {
        this.taskReqDesc = taskReqDesc;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTestAssignStatus() {
        return testAssignStatus;
    }

    public void setTestAssignStatus(String testAssignStatus) {
        this.testAssignStatus = testAssignStatus;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getWorkAccount() {
        return workAccount;
    }

    public void setWorkAccount(String workAccount) {
        this.workAccount = workAccount;
    }

    public long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(long workflowId) {
        this.workflowId = workflowId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TsTask{");
        sb.append("assignDate='").append(assignDate).append('\'');
        sb.append(", caseStatus='").append(caseStatus).append('\'');
        sb.append(", completedStatus='").append(completedStatus).append('\'');
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", deleteStatus='").append(deleteStatus).append('\'');
        sb.append(", designAccount='").append(designAccount).append('\'');
        sb.append(", desinger='").append(desinger).append('\'');
        sb.append(", emailNoticeUsers='").append(emailNoticeUsers).append('\'');
        sb.append(", extDate='").append(extDate).append('\'');
        sb.append(", extTxt1='").append(extTxt1).append('\'');
        sb.append(", firstAssignDate='").append(firstAssignDate).append('\'');
        sb.append(", firstModifyDate='").append(firstModifyDate).append('\'');
        sb.append(", hasTaskFeedBack='").append(hasTaskFeedBack).append('\'');
        sb.append(", isAllCompleted=").append(isAllCompleted);
        sb.append(", isAssign='").append(isAssign).append('\'');
        sb.append(", isCommit=").append(isCommit);
        sb.append(", isScript=").append(isScript);
        sb.append(", isScriptCommit=").append(isScriptCommit);
        sb.append(", isUserTest=").append(isUserTest);
        sb.append(", lastUpdateBy='").append(lastUpdateBy).append('\'');
        sb.append(", lastUpdateDttm='").append(lastUpdateDttm).append('\'');
        sb.append(", light='").append(light).append('\'');
        sb.append(", modifyDate='").append(modifyDate).append('\'');
        sb.append(", modifyReview=").append(modifyReview);
        sb.append(", modifyReviewer='").append(modifyReviewer).append('\'');
        sb.append(", modifyVer='").append(modifyVer).append('\'');
        sb.append(", modifyVerNames='").append(modifyVerNames).append('\'');
        sb.append(", moduleIds='").append(moduleIds).append('\'');
        sb.append(", moduleNames='").append(moduleNames).append('\'');
        sb.append(", programer='").append(programer).append('\'');
        sb.append(", projectId='").append(projectId).append('\'');
        sb.append(", promiseDate='").append(promiseDate).append('\'');
        sb.append(", relationList=").append(relationList);
        sb.append(", relationType='").append(relationType).append('\'');
        sb.append(", reqConfirm='").append(reqConfirm).append('\'');
        sb.append(", reqCount=").append(reqCount);
        sb.append(", reqIds='").append(reqIds).append('\'');
        sb.append(", reqNums='").append(reqNums).append('\'');
        sb.append(", requireSendMessage=").append(requireSendMessage);
        sb.append(", resultReview=").append(resultReview);
        sb.append(", resultReviewer='").append(resultReviewer).append('\'');
        sb.append(", sendType='").append(sendType).append('\'');
        sb.append(", sugesstion='").append(sugesstion).append('\'');
        sb.append(", taskAssigner='").append(taskAssigner).append('\'');
        sb.append(", taskDifficulty='").append(taskDifficulty).append('\'');
        sb.append(", taskId='").append(taskId).append('\'');
        sb.append(", taskNum='").append(taskNum).append('\'');
        sb.append(", taskOwner='").append(taskOwner).append('\'');
        sb.append(", taskReqDesc='").append(taskReqDesc).append('\'');
        sb.append(", taskStatus='").append(taskStatus).append('\'');
        sb.append(", taskType='").append(taskType).append('\'');
        sb.append(", testAssignStatus='").append(testAssignStatus).append('\'');
        sb.append(", testStatus='").append(testStatus).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", workAccount='").append(workAccount).append('\'');
        sb.append(", workflowId=").append(workflowId);
        sb.append(", realDevDate='").append(realDevDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
