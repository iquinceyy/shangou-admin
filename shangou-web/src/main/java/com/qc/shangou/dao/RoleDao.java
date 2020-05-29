package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.pojo.vo.RoleVO;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/29 16:05
 */
public interface RoleDao {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<RoleVO> selectHisRolesByRoles(String phone);

    List<RoleVO> ajaxList(RoleQuery query);

    Integer ajaxListCount(RoleQuery query);
}
