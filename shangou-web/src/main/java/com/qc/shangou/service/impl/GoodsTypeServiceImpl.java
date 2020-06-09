package com.qc.shangou.service.impl;

import com.qc.shangou.dao.GoodsDao;
import com.qc.shangou.dao.GoodsTypeDao;
import com.qc.shangou.dao.MerchantDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.GoodsType;
import com.qc.shangou.pojo.query.GoodsQuery;
import com.qc.shangou.pojo.query.GoodsTypeQuery;
import com.qc.shangou.pojo.vo.GoodsTypeVO;
import com.qc.shangou.pojo.vo.GoodsVO;
import com.qc.shangou.service.GoodsTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/8 19:05
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Resource
    GoodsTypeDao goodsTypeDao;
    @Override
    public List<GoodsTypeVO> getMerchantGoodsTypes(Long merchantId) {
        return goodsTypeDao.selectByMerchantId(merchantId);
    }

    @Override
    public PageDTO ajaxList(GoodsTypeQuery query) {
        List<GoodsTypeVO> goodsTypeVOS = goodsTypeDao.ajaxGoodsTypeList(query);
        int count = goodsTypeDao.ajaxGoodsTypeCount(query);
        return PageDTO.setPageData(count,goodsTypeVOS);
    }

    @Override
    public ResponseDTO addGoodsType(GoodsType goodsType) {
        return ResponseDTO.get(goodsTypeDao.insertSelective(goodsType)==1);
    }

    @Override
    public ResponseDTO editGoodsType(GoodsType goodsType) {
        return ResponseDTO.get(goodsTypeDao.updateByPrimaryKeySelective(goodsType)==1);
    }

    @Override
    public ResponseDTO deleteGoodsType(GoodsType goodsType) {
        return ResponseDTO.get(goodsTypeDao.deleteByPrimaryKey(goodsType.getGoodsTypeId())==1);
    }
}
