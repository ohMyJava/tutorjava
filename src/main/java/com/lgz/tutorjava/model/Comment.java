package com.lgz.tutorjava.model;

/**
 * @author lgz
 * @date 2020/5/15 16:16
 * 用户留言实体类
 */
public class Comment {
    private Integer commentId;
    private Integer commentType;
    private String commentTypeName;
    private String commentContent;
    private String answerContent;
    private String submitTime;
    private String checkTime;
    private Integer userId;
    /**
     * 管理员是否审批
     */
    private Integer isCheck;
    /**
     * 用户是否已读
     */
    private Integer isRead;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getCommentTypeName() {
        return commentTypeName;
    }

    public void setCommentTypeName(String commentTypeName) {
        this.commentTypeName = commentTypeName;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentType=" + commentType +
                ", commentTypeName='" + commentTypeName + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", answerContent='" + answerContent + '\'' +
                ", submitTime='" + submitTime + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", userId=" + userId +
                ", isCheck=" + isCheck +
                ", isRead=" + isRead +
                '}';
    }
}
