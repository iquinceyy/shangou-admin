package com.qc.shangou.controller.pages.back.shopCar;

import com.qc.shangou.controller.BaseController;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.ShopCar;
import com.qc.shangou.service.ShopCarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/6/11 10:04
 */
@Controller
@RequestMapping("/pages/front/shopCar")
public class shopCarController extends BaseController {

    @Resource
    ShopCarService shopCarService;

    @RequestMapping("editShopCar")
    @ResponseBody
    ResponseDTO editShopCar(ShopCar shopCar){
        //设置当前人
        shopCar.setUserId(getUserId());
        return shopCarService.editShopCar(shopCar);
    }

    @RequestMapping("list")
    String list(){
        return "pages/front/merchant/merchantShop";
    }


}
