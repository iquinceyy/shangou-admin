package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.GoodsType;
import com.qc.shangou.pojo.query.GoodsTypeQuery;
import com.qc.shangou.pojo.vo.GoodsTypeVO;
import com.qc.shangou.pojo.vo.MerchantVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsTypeDao {
    int deleteByPrimaryKey(Long goodsTypeId);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Long goodsTypeId);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);

    List<GoodsTypeVO> selectByMerchantId(Long merchantId);

    List<GoodsTypeVO> ajaxGoodsTypeList(GoodsTypeQuery query);

    int ajaxGoodsTypeCount(GoodsTypeQuery query);


}