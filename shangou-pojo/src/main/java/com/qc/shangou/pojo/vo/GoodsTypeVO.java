package com.qc.shangou.pojo.vo;

import com.qc.shangou.pojo.entity.GoodsType;
import lombok.Data;

import java.util.List;

/**
 * Author quincey
 * Date 2020/6/8 19:07
 */
@Data
public class GoodsTypeVO extends GoodsType {

    List<GoodsVO> goodsVOS;
}
