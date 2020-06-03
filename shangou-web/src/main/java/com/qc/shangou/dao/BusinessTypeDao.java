package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.BusinessType;

public interface BusinessTypeDao {
    int deleteByPrimaryKey(Integer typeId);

    int insert(BusinessType record);

    int insertSelective(BusinessType record);

    BusinessType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(BusinessType record);

    int updateByPrimaryKey(BusinessType record);
}