package com.qc.shangou.service.impl;

import com.qc.shangou.dao.GoodsDao;
import com.qc.shangou.dao.MerchantDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Goods;
import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.pojo.query.GoodsQuery;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.vo.GoodsVO;
import com.qc.shangou.pojo.vo.MerchantVO;
import com.qc.shangou.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/6 11:54
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsDao goodsDao;
    @Resource
    MerchantDao merchantDao;


    @Override
    public PageDTO ajaxList(GoodsQuery query) {
        List<GoodsVO> goodsVOS = goodsDao.ajaxGoodsList(query);
        Integer count = goodsDao.ajaxGoodsCount(query);
        return PageDTO.setPageData(count,goodsVOS);
    }

    @Override
    public ResponseDTO addGoods(Goods goods) {

        Merchant merchant = merchantDao.selectByPrimaryKey(goods.getMerchantId());
        goods.setBusinessTypeId(merchant.getBusinessType());
        if (goods.getIsCoupon()==null){
            goods.setIsCoupon(false);
        }
        if (goods.getTakeaway()==null){
            goods.setTakeaway(false);
        }
        if (goods.getOnSale()==null){
            goods.setOnSale(false);
        }
        //删除图片
        deleteImgCache(goods);
        return ResponseDTO.get(goodsDao.insertSelective(goods)==1);
    }

    @Override
    public Goods getById(Long goodsId) {
        return goodsDao.selectByPrimaryKey(goodsId);
    }

    @Override
    public ResponseDTO deleteGoods(Goods goods) {
        return ResponseDTO.get(goodsDao.deleteByPrimaryKey(goods.getGoodsId())==1);
    }

    @Override
    public ResponseDTO editGoods(Goods goods) {

        return ResponseDTO.get(goodsDao.updateByPrimaryKeySelective(goods)==1);
    }

}
