package com.qc.shangou.service;

import com.qc.shangou.pojo.entity.BusinessType;

import java.util.List;

/**
 * Author quincey
 * Date 2020/6/4 9:05
 */
public interface BusinessTypeSerrvice extends BaseService{


    //所有类型和详情
    List<BusinessType> selectBusinessTypeAll(Integer  typeId);

}
