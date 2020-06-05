package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.query.ApprovalLogQuery;

/**
 * Author quincey
 * Date 2020/6/5 19:07
 */
public interface ApprovalService {

    PageDTO getMerchantLogsById(Long merchantId);
}
