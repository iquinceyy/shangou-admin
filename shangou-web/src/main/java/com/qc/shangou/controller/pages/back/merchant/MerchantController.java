package com.qc.shangou.controller.pages.back.merchant;

import com.qc.shangou.dao.BusinessTypeDao;
import com.qc.shangou.pojo.entity.BusinessType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/6/3 19:51
 */
@Controller
@RequestMapping("/pages/back/merchant")
public class MerchantController {


    @Resource
    BusinessTypeDao businessTypeDao;
    @RequestMapping("addPre")
    String addMerchant(String name) {
        return  "/pages/back/merchant/merchant-addPre";
    }


    @RequestMapping("business")
    @ResponseBody
    BusinessType selectBusiness(Integer BusinessTypeId){
        BusinessType businessType = businessTypeDao.selectByPrimaryKey(1);
        System.out.println(businessType);
        return businessTypeDao.selectByPrimaryKey(1);
    }

}
