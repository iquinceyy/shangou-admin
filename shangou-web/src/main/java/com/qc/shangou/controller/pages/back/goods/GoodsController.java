package com.qc.shangou.controller.pages.back.goods;

import com.qc.shangou.controller.BaseController;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Goods;
import com.qc.shangou.pojo.entity.GoodsType;
import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.query.GoodsQuery;
import com.qc.shangou.pojo.query.MerchantQuery;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.vo.GoodsTypeVO;
import com.qc.shangou.pojo.vo.MerchantVO;
import com.qc.shangou.service.GoodsService;
import com.qc.shangou.service.GoodsTypeService;
import com.qc.shangou.service.MerchantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Author quincey
 * Date 2020/5/29 11:29
 */
@Controller
@RequestMapping("/pages/back/goods")
public class GoodsController extends BaseController {

    @Resource
    GoodsService goodsService;
    @Resource
    MerchantService merchantService;
    @Resource
    GoodsTypeService goodsTypeService;
    //商品列表url
    @RequestMapping("list")
    String list(){
        return "pages/back/goods/goods-list";
    }

    //增加商品列表url
    @RequestMapping("addPre")
    String addPre(Model model){
        //查询商品分类和类型
        List<GoodsTypeVO> goodsTypeVOS = goodsTypeService.getMerchantGoodsTypes(getMerchantId());
        model.addAttribute("goodsTypes", goodsTypeVOS);
        return "pages/back/goods/goods-addPre";
    }

    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(GoodsQuery query) {
        return goodsService.ajaxList(query);
    }

    @RequestMapping("ajaxList/{merchantId}")
    @ResponseBody
    PageDTO ajaxListByMerchantId(@PathVariable GoodsQuery query) {
        return goodsService.ajaxList(query);
    }


    @RequestMapping("add")
    @ResponseBody
    ResponseDTO addGoods(Goods goods) {

        String phone = getPhone();
        //从merchant 拿goods merchantid
        Merchant merchant = merchantService.seleleMerchantIdIdByPhone(phone);
        if (merchant!=null){
            goods.setMerchantId(merchant.getMerchantId());
        }
        goods.setUpdateUser(merchant.getUserId());
        Date date = new Date();
        goods.setCreateTime(date);
        goods.setUpdateTime(date);
        goods.setProvince(merchant.getProvince());

        return goodsService.addGoods(goods);
    }

}
