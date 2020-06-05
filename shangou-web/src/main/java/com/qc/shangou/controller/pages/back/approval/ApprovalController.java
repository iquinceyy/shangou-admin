package com.qc.shangou.controller.pages.back.approval;

import com.qc.shangou.controller.BaseController;
import com.qc.shangou.pojo.dto.PageDTO;
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

    @RequestMapping("getMerchantLogsById/{merchantId}")
    @ResponseBody
    PageDTO getMerchantLogsById(Long merchantId){
        return approvalService.getMerchantLogsById(merchantId);
    }
}
