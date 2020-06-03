package com.qc.shangou.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * merchant
 * @author 
 */
@Data
public class Merchant implements Serializable {
    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 外键userId，是哪个用户的商户
     */
    private Long userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺logo
     */
    private String shopLogo;

    /**
     * 店铺详情
     */
    private String shopDetail;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 省市区（门店区域）：重庆市-重庆市-渝中区
     */
    private String pcd;

    /**
     * 详细的街道地址
     */
    private String address;

    /**
     * 用户输入的坐标：百脑汇大夕阳国际
     */
    private String location;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 营业状态：营业中1，关门了0
     */
    private Boolean isOpen;

    /**
     * 审批状态：待提交、审核中，审核通过，需重新提交
     */
    private String approvalStatus;

    /**
     * 店铺所在的街道（营业执照上面的街道）
     */
    private String street;

    /**
     * 标语1
     */
    private String title1;

    /**
     * 标语2
     */
    private String title2;

    /**
     * 标语3
     */
    private String title3;

    /**
     * 最少起送价格
     */
    private BigDecimal minPrice;

    /**
     * 营业开始时间1
     */
    private Date businessStartTime1;

    /**
     * 营业结束时间1
     */
    private Date businessEndTime1;

    /**
     * 营业开始时间2
     */
    private Date businessStartTime2;

    /**
     * 营业结束时间2
     */
    private Date businessEndTime2;

    /**
     * 根据这个省字段分商品表
     */
    private String province;

    /**
     * 门脸图
     */
    private String doorImg;

    /**
     * 室内图
     */
    private String interiorImg;

    /**
     * 主要经营类目id
     */
    private Integer businessType;

    /**
     * 外卖电话
     */
    private String takeawayPhone;

    /**
     * 是否开启了平台配送：1或0
     */
    private Boolean isPlatSend;

    private static final long serialVersionUID = 1L;
}