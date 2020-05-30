package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.query.RoleQuery;

/**
 * Author quincey
 * Date 2020/5/30 15:52
 */
public interface PermissionService {

    PageDTO ajaxList(RoleQuery query);
}
