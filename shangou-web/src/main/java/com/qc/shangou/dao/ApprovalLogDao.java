package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.ApprovalLog;
import com.qc.shangou.pojo.query.ApprovalLogQuery;
import com.qc.shangou.pojo.vo.ApprovalLogVO;

import java.util.List;

public interface ApprovalLogDao {
    int deleteByPrimaryKey(Integer approvalLogId);

    int insert(ApprovalLog record);

    int insertSelective(ApprovalLog record);

    ApprovalLog selectByPrimaryKey(Integer approvalLogId);

    int updateByPrimaryKeySelective(ApprovalLog record);

    int updateByPrimaryKey(ApprovalLog record);

    List<ApprovalLogVO> getMerchantLogsById(Long merchantId);
}