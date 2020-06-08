package com.qc.shangou.service.impl;

import com.qc.shangou.dao.GoodsDao;
import com.qc.shangou.dao.GoodsTypeDao;
import com.qc.shangou.dao.MerchantDao;
import com.qc.shangou.pojo.vo.GoodsTypeVO;
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
}
