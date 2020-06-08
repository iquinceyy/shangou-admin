package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Goods;
import com.qc.shangou.pojo.query.GoodsQuery;


import java.util.List;

/**
 * Author quincey
 * Date 2020/6/6 11:53
 */
public interface GoodsService extends BaseService {

    //商品 分页
    PageDTO ajaxList(GoodsQuery query);

    //添加商品
    ResponseDTO addGoods(Goods goods);

}
