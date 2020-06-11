package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.Address;
import com.qc.shangou.pojo.query.AddressQuery;
import com.qc.shangou.pojo.vo.AddressVO;

import java.util.List;

public interface AddressDao {
    int deleteByPrimaryKey(Long addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Long addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    //多个地址
    List<AddressVO> getUserAddress(AddressQuery query);
}