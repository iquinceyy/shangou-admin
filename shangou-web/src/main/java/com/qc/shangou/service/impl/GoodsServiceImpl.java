package com.qc.shangou.service.impl;

import com.qc.shangou.dao.GoodsDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Goods;
import com.qc.shangou.pojo.query.GoodsQuery;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.vo.GoodsVO;
import com.qc.shangou.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/6 11:54
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsDao goodsDao;


    @Override
    public PageDTO ajaxList(GoodsQuery query) {
        List<GoodsVO> goodsVOS = goodsDao.ajaxGoodsList(query);
        Integer count = goodsDao.ajaxGoodsCount(query);
        return PageDTO.setPageData(count,goodsVOS);
    }

    @Override
    public ResponseDTO addGoods(Goods goods) {

        return ResponseDTO.get(goodsDao.insertSelective(goods)==1);
    }
}
