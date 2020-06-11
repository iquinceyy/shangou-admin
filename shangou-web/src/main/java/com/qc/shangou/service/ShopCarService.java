package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.ShopCar;

/**
 * Author quincey
 * Date 2020/6/11 10:07
 */
public interface ShopCarService {


    //编辑购物车（一种商品就是一个购物车）
    ResponseDTO editShopCar(ShopCar shopCar);

}
