package com.qc.shangou.controller.pages.back.address;

import com.qc.shangou.controller.BaseController;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Address;
import com.qc.shangou.pojo.query.AddressQuery;
import com.qc.shangou.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Author quincey
 * Date 2020/6/11 19:48
 */
@Controller
@RequestMapping("/pages/back/address")
public class AddressController extends BaseController {

    @Resource
    AddressService addressService;

    @ResponseBody
    @RequestMapping("getUserAddress")
    //查询用户地址
    ResponseDTO selectAddress(AddressQuery query){
        query.setUserId(getUserId());
        return addressService.getUserAddress(query);
    }

    //新增地址
    @ResponseBody
    @RequestMapping("addAddress")
    //查询用户地址
    ResponseDTO addAddress(Address address){
        address.setUserId(getUserId());
        return addressService.addAddress(address);
    }
}
