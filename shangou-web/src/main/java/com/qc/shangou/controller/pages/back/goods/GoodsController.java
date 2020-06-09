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
import org.springframework.web.bind.annotation.RequestBody;
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

    //商品列表url 平台对商品进行列表查看   4
    @RequestMapping("list")
    String list(){
        return "pages/back/goods/goods-list";
    }

    // 商户对自己的商品进行列表查看
    @RequestMapping("merchantList")
    String merchantlist(Model model){
        List<GoodsTypeVO> merchantGoodsTypes = goodsTypeService.getMerchantGoodsTypes(getMerchantId());
        model.addAttribute("goodsTypes",merchantGoodsTypes);
        return "pages/back/goods/merchant-goods-list";
    }

    //增加商品列表url      2
    @RequestMapping("addPre")
    String addPre(Model model){
        //查询商品分类和类型 传入前端 渲染选择
        List<GoodsTypeVO> goodsTypeVOS = goodsTypeService.getMerchantGoodsTypes(getMerchantId());
        model.addAttribute("goodsTypes", goodsTypeVOS);
        return "pages/back/goods/goods-addPre";
    }

    //平台获取商品列表
    @RequestMapping("ajaxList")
    @ResponseBody
    PageDTO ajaxList(GoodsQuery query) {
        return goodsService.ajaxList(query);
    }

    //商户获取商品列表
    @RequestMapping("ajaxListMerchantGoods")
    @ResponseBody
    PageDTO ajaxListByMerchantId(GoodsQuery query) {
        //根据商户id查询商品
        query.setMerchantId(getMerchantId());
        return goodsService.ajaxList(query);
    }

    //修改   1
    @RequestMapping("editPre/{goodsId}")
    String editPre(@RequestBody Long goodsId,Model model){
        List<GoodsTypeVO> merchantGoodsTypes = goodsTypeService.getMerchantGoodsTypes(getMerchantId());
        model.addAttribute("goodsTypes",merchantGoodsTypes);
        Goods goods = goodsService.getById(goodsId);
        model.addAttribute("goods",goods);
        return "pages/back/goods/goods-editPre";
    }


    @RequestMapping("edit}")
    @ResponseBody
    ResponseDTO editGoods(Goods goods) {
        //设置最后一次的修改人
        goods.setUpdateUser(getUserId());
        return goodsService.editGoods(goods);
    }


    //添加  3
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

    //根据goodsId删除
    @RequestMapping("delete/{goodsId}")
    @ResponseBody
    ResponseDTO deleteGoods(Goods goods) {
        return goodsService.deleteGoods(goods);
    }
}
