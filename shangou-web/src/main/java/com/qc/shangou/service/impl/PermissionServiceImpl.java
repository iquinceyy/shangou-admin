package com.qc.shangou.service.impl;

import com.qc.shangou.dao.PermissionDao;
import com.qc.shangou.dao.RoleDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.query.PermissionQuery;
import com.qc.shangou.pojo.query.RoleQuery;
import com.qc.shangou.pojo.vo.PermissionVO;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.service.PermissionService;
import com.qc.shangou.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 * Author quincey
 * Date 2020/5/30 15:53
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionDao permissionDao;
    @Resource
    RoleDao roleDao;
    @Resource
    UserService userService;


    @Override
    public PageDTO ajaxList(PermissionQuery query) {
        List<PermissionVO> permissionVOS = permissionDao.ajaxPermissionList(query);
        Integer count = permissionDao.ajaxCountPermission(query);
        return PageDTO.setPageData(count,permissionVOS);
    }

    @Override
    public ResponseDTO editPermission(Permission permission) {
        return ResponseDTO.get(permissionDao.updateByPrimaryKeySelective(permission) == 1);
    }

    @Override
    public ResponseDTO deletePermissions(List<Permission> permissions) {

        return ResponseDTO.get(permissionDao.deletePermissions(permissions)==permissions.size());
    }


    @Override
    public ResponseDTO addPermissionToRole(RoleVO r) {
        //1.通过这个参数 r的 roleId 查询获取这个角色
        Role role = roleDao.selectByPrimaryKey(r.getRoleId());
        //2.通过这个角色获取 有哪些permission, 然后给r
//        r.setPermissions(role.getPermissions());
        //3.再把原先的权限取出来 Collections.singletonList将单个元素变成 单个元素的集合
//        List<PermissionVO> permissionVOS = userService.selectHisPermissionByRoles(Collections.singletonList(r));
        TreeSet<Integer> collect1 = strToTreeSet(role.getPermissions());
        TreeSet<Integer> collect2 = r.getPermissionVOS().stream().map(Permission::getPermissionId).collect(Collectors.toCollection(TreeSet::new));
        collect1.addAll(collect2);
        //4.合并所有的权限
//        permissionVOS.addAll(r.getPermissionVOS());
//        //排序
//        TreeSet<Integer> treeSet = new TreeSet<>();
//        for (PermissionVO p: permissionVOS){
//            treeSet.add(p.getPermissionId());
//        }

        // 走到这一步，那么treeSet集合里边就拥有了本身的权限了和传过来的权限都有了
        String Str = collectionsToStr(collect1);
        //把新的权限赋值给对应角色
        Role updateRole = new Role();
        updateRole.setPermissions(Str);
        updateRole.setRoleId(r.getRoleId());

        return ResponseDTO.get(roleDao.updateByPrimaryKeySelective(updateRole)==1);
    }

    @Override
    public ResponseDTO removePermissionFromRole(RoleVO r) {
        //1.通过这个参数 r的roleId 查询 获取这个角色
        Role role = roleDao.selectByPrimaryKey(r.getRoleId());
        //2.通过这个角色获取 有哪些permission, 然后给r
//        r.setPermissions(role.getPermissions());
        //3.查询，再把原先的权限取出来 Collections.singletonList将单个元素变成 单个元素的集合
//        List<PermissionVO> permissionVOS = userService.selectHisPermissionByRoles(Collections.singletonList(r));
        // 这里的移除，不能简单的使用 permissionVOS.removeAll(r.getPermissionVOS())，这样是移除不了的。这个时候我们可以用stream处理
        // 这波操作能看懂就行，把集合中的id 通过 map收集起来，并且收集成为一个TreeSet集合
//        TreeSet<Integer> collect = permissionVOS.stream().map(Permission::getPermissionId).collect(Collectors.toCollection(TreeSet::new));

        //把这个角色的权限拆分
        TreeSet<Integer> collect1 = strToTreeSet(role.getPermissions());
        TreeSet<Integer> collect2 = r.getPermissionVOS().stream().map(Permission::getPermissionId).collect(Collectors.toCollection(TreeSet::new));
        //把传过来的权限从老权限里面删除
        collect1.removeAll(collect2);

        String Str = collectionsToStr(collect1);
        Role updateRole = new Role();
        updateRole.setPermissions(Str);
        updateRole.setRoleId(r.getRoleId());
        //8.把新的权限赋值给对应角色

        return ResponseDTO.get(roleDao.updateByPrimaryKeySelective(updateRole)==1);
    }

    @Override
    public ResponseDTO add(Permission permission) {
        return ResponseDTO.get(permissionDao.insertSelective(permission) == 1);
    }


}
