package com.qc.shangou.service.impl;

import com.qc.shangou.dao.ApprovalLogDao;
import com.qc.shangou.dao.MerchantDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.ApprovalLog;
import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.pojo.query.ApprovalLogQuery;
import com.qc.shangou.pojo.vo.ApprovalLogVO;
import com.qc.shangou.service.ApprovalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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


    @Override
    public ResponseDTO addApprovalLog(ApprovalLog approvalLog) {
        //设置日志创建时间
        approvalLog.setCreateTime(new Date());
        //如果日志插入成功，根据merchantId修改审核结果
        if (approvalLogDao.insertSelective(approvalLog)==1){
            //传建一个实例
            Merchant merchant = new Merchant();
            //获取前端修改的状态
            merchant.setApprovalStatus(approvalLog.getRes());
            merchant.setMerchantId(approvalLog.getMerchantId());
            return ResponseDTO.get(merchantDao.updateByPrimaryKeySelective(merchant)==1);
        }
        return ResponseDTO.fail("审核失败");
    }


    //商户的申请信息 分页
    @Override
    public PageDTO getMerchantLogsByQuery(ApprovalLogQuery query) {
        List<ApprovalLogVO> list = approvalLogDao.getMerchantLogsByQuery(query);
        Integer count = approvalLogDao.getMerchantLogsByQueryCount(query);
        return PageDTO.setPageData(count,list);
    }


}
