package com.qc.shangou.service;

import com.qc.shangou.pojo.vo.PermissionVO;
import com.qc.shangou.pojo.vo.RoleVO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Author quincey
 * Date 2020/6/1 20:29
 */
public interface BaseService {
    /**
     * 把角色的集合设置上权限属性
     *
     * @param roles
     * @param permissionVOS
     * @return
     */
    default List<RoleVO> getRoleVOList(List<RoleVO> roles, List<PermissionVO> permissionVOS) {
        Map<Integer, List<PermissionVO>> collect = permissionVOS.stream().collect(Collectors.groupingBy(PermissionVO::getPermissionId));
        for (RoleVO r : roles) {
            String permissions = r.getPermissions();
            if (!StringUtils.isEmpty(permissions)) {
                String[] split = permissions.split(",");
                List<PermissionVO> li = new ArrayList<>();
                for (String s : split) {
                    List<PermissionVO> permissionVOS1 = collect.get(Integer.valueOf(s));
                    if (!CollectionUtils.isEmpty(permissionVOS1)) {
                        PermissionVO p = permissionVOS1.get(0);
                        li.add(p);
                    }

                }
                r.setPermissionVOS(li);
            }
        }
        return roles;
    }

    // 抽取的方法 集合变成字符串，用逗号隔开
    default String collectionsToStr(TreeSet<Integer> collect) {

        if (collect==null){
            return null;
        }
        //6.走到这一步，那么treeSet集合里边就拥有了本身的权限了和传过来的权限都有了
        StringBuffer buffer = new StringBuffer();
        //7.把set集合变成字符串，用逗号分隔
        for (Integer pid : collect) {
            buffer.append(pid).append(",");
        }
        if (buffer.length() > 0) {
            buffer.delete(buffer.length() - 1, buffer.length());
        }
        return buffer.toString();
    }

    //把用逗号分隔的字符串变成TreeSet集合
    default TreeSet<Integer> strToTreeSet(String str){
        TreeSet<Integer> treeSet = new TreeSet<>();
        if (!StringUtils.isEmpty(str)){
            String[] split = str.split(",");
            for (String id :split){
                treeSet.add(Integer.valueOf(id));
            }
        }
        return treeSet;
    }
}
