package com.qc.shangou.controller.pages.back.goodsTypes;

import com.qc.shangou.controller.BaseController;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.GoodsType;
import com.qc.shangou.pojo.query.GoodsQuery;
import com.qc.shangou.pojo.query.GoodsTypeQuery;
import com.qc.shangou.service.GoodsTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/6/9 14:37
 */
@Controller
@RequestMapping("/pages/back/goodsType")
public class goodsTypeController extends BaseController {
    @Resource
    GoodsTypeService goodsTypeService;

    @RequestMapping("list")
    String list(){
        return "pages/back/goodsType/goods-type-list";
    }

    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(GoodsTypeQuery query) {
        query.setMerchantId(getMerchantId());
        return goodsTypeService.ajaxList(query);
    }

    @RequestMapping("add")
    @ResponseBody
    ResponseDTO add(GoodsType goodsType) {
        goodsType.setMerchantId(getMerchantId());
        return goodsTypeService.addGoodsType(goodsType);
    }

    @RequestMapping("edit")
    @ResponseBody
    ResponseDTO edit(GoodsType goodsType) {
        goodsType.setMerchantId(getMerchantId());
        return goodsTypeService.editGoodsType(goodsType);
    }

    @RequestMapping("delete/{goodsTypeId}")
    @ResponseBody
    ResponseDTO delete(GoodsType goodsType) {
        goodsType.setMerchantId(getMerchantId());
        return goodsTypeService.deleteGoodsType(goodsType);
    }

}
