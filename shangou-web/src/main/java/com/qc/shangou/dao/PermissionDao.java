package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.vo.PermissionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Author quincey
 * Date 2020/5/29 16:04
 */
public interface PermissionDao {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<PermissionVO> selectPermissionsByIds(String permissions);

    List<PermissionVO> selectPermissionsBySet(@Param("ids") Set<String> paramSet);

    List<PermissionVO> ajaxPermissionList(PermissionQuery query);

    Integer ajaxCountPermission(PermissionQuery query);
}
