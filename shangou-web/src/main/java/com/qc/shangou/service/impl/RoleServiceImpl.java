package com.qc.shangou.service.impl;

import com.qc.shangou.dao.RoleDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.pojo.vo.PermissionVO;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.service.RoleService;
import com.qc.shangou.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author quincey
 * Date 2020/5/29 16:02
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;
    @Resource
    UserService userService;
    @Override
    public PageDTO ajaxList(RoleQuery query) {
        List<RoleVO> roleVOS = roleDao.ajaxList(query);
        Integer count = roleDao.ajaxListCount(query);

        if (!CollectionUtils.isEmpty(roleVOS)) {
            List<PermissionVO> permissionVOS = userService.selectHisPermissionByRoles(roleVOS);
            roleVOS = getRoleVOList(roleVOS, permissionVOS);
        }

        return PageDTO.setPageData(count,roleVOS);
    }

    @Override
    public ResponseDTO editRole(Role role) {
        return ResponseDTO.get(roleDao.updateByPrimaryKeySelective(role) == 1);
    }
}
