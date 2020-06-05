package com.qc.shangou.service.impl;

import com.qc.shangou.dao.ApprovalLogDao;
import com.qc.shangou.dao.MerchantDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.query.ApprovalLogQuery;
import com.qc.shangou.pojo.vo.ApprovalLogVO;
import com.qc.shangou.service.ApprovalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/5 19:08
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Resource
    ApprovalLogDao approvalLogDao;
    @Resource
    MerchantDao merchantDao;

    @Override
    public PageDTO getMerchantLogsById(Long merchantId) {
        List<ApprovalLogVO> merchantLogsById = approvalLogDao.getMerchantLogsById(merchantId);
        return PageDTO.setPageData(merchantLogsById.size(),merchantLogsById);
    }
}
