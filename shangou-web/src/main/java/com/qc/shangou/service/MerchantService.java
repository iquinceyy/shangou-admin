package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.query.MerchantQuery;
import com.qc.shangou.pojo.vo.MerchantVO;

import java.util.List;

/**
 * Author quincey
 * Date 2020/6/4 11:19
 */
public interface MerchantService {

    PageDTO ajaxList(MerchantQuery query);

    ResponseDTO addMerchant(Merchant merchant);

    //在merchant中查询商家是否已经存在 （自己写）
    boolean selectMerchantIsOrNot(Long userId);

    //批量删除
    ResponseDTO deleteMerchantByMerchantId(List<Merchant> merchant);

    //增加商品  需要MerchantIdId
    Merchant seleleMerchantIdIdByPhone(String phone);

    Long selectMerchantIdByUserId(Long userId);

    //查询附近的商家和它卖的最好的商品
    PageDTO getNearByMerchantGoods(MerchantQuery merchantQuery);

    /**
     * 还需要带上商户的所有商品类型
     * 索性把商品类型查出来之后，把商品也查出来，这样一口气查出来传到前端，免费前端再次发ajax来服务器请求数据，一个店铺的全部商品数据也不会太多
     * @param merchantId
     * @return
     */
    MerchantVO selectMerchantById(Long merchantId);
}
