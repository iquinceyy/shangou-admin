package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.Goods;
import com.qc.shangou.pojo.query.GoodsQuery;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.vo.GoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDao {
    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsVO> ajaxGoodsList(GoodsQuery query);

    Integer ajaxGoodsCount(GoodsQuery query);
}