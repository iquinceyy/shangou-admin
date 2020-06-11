package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Address;
import com.qc.shangou.pojo.query.AddressQuery;

/**
 * Author quincey
 * Date 2020/6/11 19:50
 */
public interface AddressService {


    ResponseDTO getUserAddress(AddressQuery query);

    ResponseDTO addAddress(Address address);
}
