package com.qc.shangou.controller.pages.front.merchant;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.query.MerchantQuery;
import com.qc.shangou.pojo.vo.MerchantVO;
import com.qc.shangou.service.BusinessTypeSerrvice;
import com.qc.shangou.service.MerchantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/6/10 19:15
 */
@RequestMapping("/pages/front/merchant")
@Controller
public class MerchantControllerFront {

    @Resource
    MerchantService merchantService;
    @Resource
    BusinessTypeSerrvice businessTypeSerrvice;

    //首页获取附近的商品
    @RequestMapping("getNearByMerchantGoods")
    PageDTO getNearByMerchantGoods(MerchantQuery merchantQuery){
        //存在有一个数据为空就不查
        if (merchantQuery.getMaxLat()==null
        || merchantQuery.getMaxLng()==null
                || merchantQuery.getMinLat()==null
                || merchantQuery.getMinLng()==null){

            return PageDTO.setPageData(0,null);
        }
        return merchantService.getNearByMerchantGoods(merchantQuery);
    }

    @RequestMapping("merchantShop/{merchantId}")
    String MerchantShopCar(@PathVariable Long merchantId,Model model){
        MerchantVO merchantVO = merchantService.selectMerchantById(merchantId);
        model.addAttribute("m",merchantVO);
        return "/pages/front/merchant/merchantShop";
    }

    }
