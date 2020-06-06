package com.qc.shangou.controller.pages.back.goods;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Goods;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.query.GoodsQuery;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/5/29 11:29
 */
@Controller
@RequestMapping("/pages/back/goods")
public class GoodsController {

    @Resource
    GoodsService goodsService;
    //商品列表url
    @RequestMapping("list")
    String list(){
        return "pages/back/goods/goods-list";
    }
    //增加商品列表url
    @RequestMapping("addPre")
    String addPre(){
        return "pages/back/goods/goods-addPre";
    }

    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(GoodsQuery query) {
        return goodsService.ajaxList(query);
    }

    @RequestMapping("addPre")
    @ResponseBody
    ResponseDTO addGoods(Goods goods) {
        return goodsService.addGoods(goods);
    }




}
