package com.qc.shangou.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * order_item
 * @author 
 */
@Data
public class OrderItem implements Serializable {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 下单时候的商品图片地址
     */
    private String goodsImg;

    /**
     * 下单时候的商品数量
     */
    private String goodsCount;

    /**
     * 原来的商品id
     */
    private Long originGoodsId;

    /**
     * 下单时候的商品名称
     */
    private String goodsTitle;

    /**
     * 下单时候的价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商户id:冗余字段：是专门拿来给程序查询用的
     */
    private Long merchantId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}