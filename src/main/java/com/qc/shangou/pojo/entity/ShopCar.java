package com.qc.shangou.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * shop_car
 * @author 
 */
@Data
public class ShopCar implements Serializable {
    /**
     * 购物车子的id
     */
    private Integer shopCarId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 购物车的数量
     */
    private Integer count;

    /**
     * 商品id
     */
    private Long goodsId;

    private static final long serialVersionUID = 1L;
}