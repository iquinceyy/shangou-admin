package com.qc.shangou.service.impl;

import com.qc.shangou.dao.AddressDao;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Address;
import com.qc.shangou.pojo.query.AddressQuery;
import com.qc.shangou.pojo.vo.AddressVO;
import com.qc.shangou.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/11 19:51
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    AddressDao addressDao;

    @Override
    public ResponseDTO getUserAddress(AddressQuery query) {

        List<AddressVO> userAddress = addressDao.getUserAddress(query);
        return ResponseDTO.ok("查询成功",userAddress);
    }

    @Override
    public ResponseDTO addAddress(Address address) {
        return null;
    }
}
