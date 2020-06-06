package com.qc.shangou.pojo.query;

import com.qc.shangou.util.PageQuery;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Author quincey
 * Date 2020/6/6 13:06
 */
@Data
public class GoodsQuery extends PageQuery {

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 商家的商品类型id
     */
    private Long goodsTypeId;

    /**
     * 商品的标题
     */
    private String title;

    private BigDecimal minPrice, maxPrice;

    /**
     * 在售：1
     */
    private Boolean onSale;

    /**
     * 是否是团购
     */
    private Boolean isCoupon;
}
