package com.qc.shangou.pojo.vo;

import com.qc.shangou.pojo.entity.Merchant;
import lombok.Data;

import java.util.List;

/**
 * Author quincey
 * Date 2020/6/5 9:53
 */
@Data
public class MerchantVO extends Merchant {

    //卖的最好的商品
    private GoodsVO bestGoods;

    //商铺的商品类型集合
    private List<GoodsTypeVO> goodsTypeList;
}
