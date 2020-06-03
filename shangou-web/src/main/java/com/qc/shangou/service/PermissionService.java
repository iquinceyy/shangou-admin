package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.vo.RoleVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/30 15:52
 */
public interface PermissionService extends BaseService{

    PageDTO ajaxList(PermissionQuery query);

    ResponseDTO editPermission(Permission permission);

    ResponseDTO deletePermissions(List<Permission> permission);

    ResponseDTO addPermissionToRole(RoleVO rolevo);

    ResponseDTO removePermissionFromRole(RoleVO rolevo);

    ResponseDTO add(Permission permission);
}
