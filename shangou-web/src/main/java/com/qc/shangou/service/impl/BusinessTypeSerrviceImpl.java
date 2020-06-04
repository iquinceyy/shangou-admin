package com.qc.shangou.service.impl;

import com.qc.shangou.dao.BusinessTypeDao;
import com.qc.shangou.pojo.entity.BusinessType;
import com.qc.shangou.service.BusinessTypeSerrvice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/4 9:05
 */
@Service
public class BusinessTypeSerrviceImpl implements BusinessTypeSerrvice {

    @Resource
    BusinessTypeDao businessTypeDao;

    @Override
    public List<BusinessType> selectBusinessTypeAll(Integer parentId) {
        return businessTypeDao.selectBusinessTypeAll(parentId);
    }


}
