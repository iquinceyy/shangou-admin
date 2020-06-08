package com.qc.shangou.service;

import com.qc.shangou.pojo.vo.GoodsTypeVO;

import java.util.List;

/**
 * Author quincey
 * Date 2020/6/8 19:03
 */
public interface GoodsTypeService {


     List<GoodsTypeVO> getMerchantGoodsTypes(Long merchantId);
}
