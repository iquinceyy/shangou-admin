package com.qc.shangou.dada.service.order;


import com.lh.shangou.dada.config.UrlConstant;
import com.lh.shangou.dada.service.BaseService;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public class OrderAddService extends BaseService {//订单下单服务

    public OrderAddService(String params){
        super(UrlConstant.ORDER_ADD_URL, params);
    }
}
