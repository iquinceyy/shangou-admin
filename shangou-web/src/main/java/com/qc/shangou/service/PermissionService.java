package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.PermissionQuery;
import org.springframework.stereotype.Service;

/**
 * Author quincey
 * Date 2020/5/30 15:52
 */
public interface PermissionService {

    PageDTO ajaxList(PermissionQuery query);

    ResponseDTO editPermission(Permission permission);
}
