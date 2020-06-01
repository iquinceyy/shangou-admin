package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.RoleQuery;

/**
 * Author quincey
 * Date 2020/5/29 15:56
 */
public interface RoleService extends BaseService{

    PageDTO ajaxList(RoleQuery query);

    ResponseDTO editRole(Role role);


}
