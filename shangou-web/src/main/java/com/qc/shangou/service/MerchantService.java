package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Merchant;

/**
 * Author quincey
 * Date 2020/6/4 11:19
 */
public interface MerchantService {

    ResponseDTO addMerchant(Merchant merchant);

    //在merchant中查询商家是否已经存在 （自己写）
    boolean selectMerchantIsOrNot(Long userId);
}
