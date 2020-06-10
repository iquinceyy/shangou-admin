package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.SgOrder;

public interface SgOrderDao {
    int deleteByPrimaryKey(Long orderId);

    int insert(SgOrder record);

    int insertSelective(SgOrder record);

    SgOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(SgOrder record);

    int updateByPrimaryKey(SgOrder record);
}