package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.query.MerchantQuery;
import com.qc.shangou.pojo.vo.MerchantVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantDao {
    int deleteByPrimaryKey(Long merchantId);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Long merchantId);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);

    boolean selectMerchantIsOrNot(Long userId);

    List<MerchantVO> ajaxList(MerchantQuery query);

    Integer ajaxListCount(MerchantQuery query);

    int deleteMerchant(@Param("ids") List<Merchant> merchant);

    Merchant seleleMerchantIdIdByPhone(String phone);

    MerchantVO selectByUserId(Long userId);

    MerchantVO selectBaseKey(Long merchantId);
}