package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.ShopCar;

public interface ShopCarDao {
    int deleteByPrimaryKey(Integer shopCarId);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(Integer shopCarId);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);

    //查询购物车的数量
    int selectShopCarCountByCar(ShopCar shopCar);

    //跟新购物车数量
    int updateShopCar(ShopCar shopCar);
}