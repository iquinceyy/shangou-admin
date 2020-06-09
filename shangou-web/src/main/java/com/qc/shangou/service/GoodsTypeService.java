package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.GoodsType;
import com.qc.shangou.pojo.query.GoodsQuery;
import com.qc.shangou.pojo.query.GoodsTypeQuery;
import com.qc.shangou.pojo.vo.GoodsTypeVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author quincey
 * Date 2020/6/8 19:03
 */

public interface GoodsTypeService {


     List<GoodsTypeVO> getMerchantGoodsTypes(Long merchantId);

     //商品类型
     PageDTO ajaxList(GoodsTypeQuery query);

    ResponseDTO addGoodsType(GoodsType goodsType);

    ResponseDTO editGoodsType(GoodsType goodsType);

    ResponseDTO deleteGoodsType(GoodsType goodsType);
}
