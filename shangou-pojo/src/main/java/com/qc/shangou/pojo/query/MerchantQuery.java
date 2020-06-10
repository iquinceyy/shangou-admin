package com.qc.shangou.pojo.query;


import com.qc.shangou.util.PageQuery;
import lombok.Data;

import java.util.Date;

/**
 * Author quincey
 * Date 2020/6/5 9:48
 */
@Data
public class MerchantQuery extends PageQuery {

    private Long userId;// 用户id或者是商户号
    private Long merchantId;
    private String shopName;//  店铺名称模糊查询
    private String phone;   //  手机号模糊
    private String contactName;   //  联系人模糊查询
    private String address;   //  地址模糊
    private String approvalStatus;   //  审核状态：等值查询
    private Boolean isOpen;   //  营业状态：等值查询
    private Boolean isPlatSend;   //  平台配送状态：等值查询


    // 最小最大经纬度
    private Double maxLng;
    private Double maxLat;
    private Double minLng;
    private Double minLat;
}
