package com.qc.shangou.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * approval_log
 * @author 
 */
@Data
public class ApprovalLog implements Serializable {
    /**
     * 审批的日志id
     */
    private Integer approvalLogId;

    /**
     * 备注审核的内容
     */
    private String note;

    /**
     * 日志创建时间
     */
    private Date createTime;

    /**
     * 审核的结果:需重新提交、审核通过、暂不合作
     */
    private String res;

    /**
     * 审核人id
     */
    private Integer approvalUserId;

    /**
     * 审核人真实名字
     */
    private String approvalUserName;

    private static final long serialVersionUID = 1L;
}