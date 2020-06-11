package com.qc.shangou.service.impl;

import com.qc.shangou.dao.ShopCarDao;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.ShopCar;
import com.qc.shangou.service.ShopCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/6/11 10:10
 */
@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Resource
    ShopCarDao shopCarDao;
    @Override
    public ResponseDTO editShopCar(ShopCar shopCar) {
        //查询购物车
        int count = shopCarDao.selectShopCarCountByCar(shopCar);
        if (count>0){//购物车已存在，这是跟新购物车的数量
            return ResponseDTO.get(shopCarDao.updateShopCar(shopCar)==1);
        }else {
            return ResponseDTO.get(shopCarDao.insertSelective(shopCar)==1);
        }
    }
}
