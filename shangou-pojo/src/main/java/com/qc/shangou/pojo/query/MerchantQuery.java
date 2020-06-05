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

    private Date createTime;
    private String takeawayPhone;
    private String contactName;
    private String shopName;
    private String phone;
}
