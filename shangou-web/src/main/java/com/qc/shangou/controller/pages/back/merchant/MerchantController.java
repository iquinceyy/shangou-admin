package com.qc.shangou.controller.pages.back.merchant;

import com.qc.shangou.controller.BaseController;
import com.qc.shangou.dao.BusinessTypeDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.BusinessType;
import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.MerchantQuery;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.service.BusinessTypeSerrvice;
import com.qc.shangou.service.MerchantService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/3 19:51
 */
@Controller
@RequestMapping("/pages/back/merchant")
public class MerchantController extends BaseController {

    @Resource
    MerchantService merchantService;
    @Resource
    BusinessTypeSerrvice businessTypeSerrvice;
    @Resource
    BusinessTypeDao businessTypeDao;
    @RequestMapping("addPre")
    String addMerchant() {
        //判断商户是否已经入住
        //我的方式是查询merchant 中user_id是否存在
        return  "/pages/back/merchant/merchant-addPre";
    }


    @RequestMapping("business")
    @ResponseBody
    List<BusinessType> selectBusinessType(Integer  parentId){
        //所有类型
        return businessTypeSerrvice.selectBusinessTypeAll(parentId);
    }

    @RequestMapping("add")
    @ResponseBody
    ResponseDTO addMerchant(Merchant merchant){
        System.out.println("merchantadd");
        merchant.setUserId((Long) getSession().getAttribute("userId"));
        merchant.setPhone((String) getSession().getAttribute("phone"));

        return merchantService.addMerchant(merchant);
    }

    @RequestMapping("approvalStatus")
    String approvalStatus(Model model) {// 在这里就应该判断以下当前用户是不是已经入驻了申请了
        System.out.println("approvalStatus");
        return "pages/back/merchant/merchant-approvalStatus";
    }



    @RequestMapping("list")
    String list(){
        return "pages/back/merchant/merchant-list";
    }

    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(MerchantQuery query) {

        return merchantService.ajaxList(query);
    }

    @RequestMapping("delete")
    @ResponseBody
    ResponseDTO deteledeleteMerchantByMerchantId(@RequestBody List<Merchant> permissions){
        System.out.println("delete");
        return merchantService.deleteMerchantByMerchantId(permissions);
    }

}
