package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.Merchant;

public interface MerchantDao {
    int deleteByPrimaryKey(Long merchantId);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Long merchantId);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);

    boolean selectMerchantIsOrNot(Long userId);
}