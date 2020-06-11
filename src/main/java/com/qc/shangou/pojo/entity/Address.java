package com.qc.shangou.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * address
 * @author 
 */
@Data
public class Address implements Serializable {
    /**
     * 收获地址id
     */
    private Long addressId;

    /**
     * 外键用户
     */
    private Long userId;

    /**
     * 省市区
     */
    private String pcd;

    /**
     * 楼层门牌号
     */
    private String floorDoorNumber;

    /**
     * 收货电话
     */
    private String phone;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 是否默认：YES或NO
     */
    private Boolean moren;

    /**
     * 地址标签：家、学校、公司
     */
    private String tag;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 小区大厦学校名称
     */
    private String name;

    /**
     * 城市编码
     */
    private String cityCode;

    private static final long serialVersionUID = 1L;
}