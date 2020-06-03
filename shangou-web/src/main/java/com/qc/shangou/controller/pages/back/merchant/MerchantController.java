package com.qc.shangou.controller.pages.back.merchant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author quincey
 * Date 2020/6/3 19:51
 */
@Controller
@RequestMapping("/pages/back/merchant")
public class MerchantController {

    @RequestMapping("addPre")
    String addMerchant(String name) {
        return  "/pages/back/merchant/merchant-addPre";
    }

}
