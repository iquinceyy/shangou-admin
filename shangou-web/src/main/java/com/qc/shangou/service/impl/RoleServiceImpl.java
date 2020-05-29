package com.qc.shangou.service.impl;

import com.qc.shangou.dao.RoleDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.service.RoleService;
import org.springframework.stereotype.Service;

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
    @Override
    public PageDTO ajaxList(RoleQuery query) {
        List<RoleVO> roleVOS = roleDao.ajaxList(query);
        Integer count = roleDao.ajaxListCount(query);

        return PageDTO.setPageData(count,roleVOS);
    }
}
