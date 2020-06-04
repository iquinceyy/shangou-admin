package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.BusinessType;

import java.util.List;

public interface BusinessTypeDao {
    int deleteByPrimaryKey(Integer typeId);

    int insert(BusinessType record);

    int insertSelective(BusinessType record);

    BusinessType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(BusinessType record);

    int updateByPrimaryKey(BusinessType record);

    List<BusinessType> selectBusinessTypeAll(Integer parentId);

}