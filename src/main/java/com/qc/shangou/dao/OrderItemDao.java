package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.OrderItem;

public interface OrderItemDao {
    int insert(OrderItem record);

    int insertSelective(OrderItem record);
}