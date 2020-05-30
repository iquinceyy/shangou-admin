package com.qc.shangou.service.impl;

import com.qc.shangou.dao.PermissionDao;
import com.qc.shangou.dao.RoleDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.pojo.vo.PermissionVO;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Author quincey
 * Date 2020/5/30 15:53
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionDao permissionDao;


    @Override
    public PageDTO ajaxList(PermissionQuery query) {
        List<PermissionVO> permissionVOS = permissionDao.ajaxPermissionList(query);
        Integer count = permissionDao.ajaxCountPermission(query);

        return PageDTO.setPageData(count,permissionVOS);
    }
}
