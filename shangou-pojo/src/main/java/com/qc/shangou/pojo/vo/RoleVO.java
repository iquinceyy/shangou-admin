package com.qc.shangou.pojo.vo;

import com.qc.shangou.pojo.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/29 15:34
 */
@Data
public class RoleVO extends Role {
    List<PermissionVO> permissionVOS;
}
