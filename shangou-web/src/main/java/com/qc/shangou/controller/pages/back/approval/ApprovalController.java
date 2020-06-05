package com.qc.shangou.controller.pages.back.approval;

import com.qc.shangou.controller.BaseController;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.ApprovalLog;
import com.qc.shangou.pojo.query.ApprovalLogQuery;
import com.qc.shangou.service.ApprovalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/6/5 19:04
 */
@Controller
@RequestMapping("/pages/back/approval")
public class ApprovalController extends BaseController {

    @Resource
    ApprovalService approvalService;



    //商户的申请信息 分页
    @RequestMapping("getMerchantLogsById/{merchantId}")
    @ResponseBody
    PageDTO getMerchantLogsByQuery(ApprovalLogQuery query) {

        return approvalService.getMerchantLogsByQuery(query);
    }


    //审批商户的申请信息
    @RequestMapping("addApprovalLog")
    @ResponseBody
    ResponseDTO addApprovalLog(ApprovalLog approvalLog) {
        // 设置审核人
        approvalLog.setApprovalUserId(getUserId());
        approvalLog.setApprovalUserName(getSession().getAttribute("realName").toString());
        return approvalService.addApprovalLog(approvalLog);
    }

}



















