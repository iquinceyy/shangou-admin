package com.qc.shangou.service;

import com.qc.shangou.pojo.query.UserQuery;
import com.qc.shangou.pojo.vo.PermissionVO;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.pojo.vo.UserVO;

import java.util.List;

/**
 * Author quincey
 * Date 2020/5/29 16:12
 */
public interface UserService extends BaseService{
    UserVO addUser(UserVO user);

    /**
     * 用户进行登录的方法
     *
     * @param query
     * @return
     */
    UserVO login(UserQuery query);

    List<RoleVO> selectHisRolesByPhone(String phone);

    List<PermissionVO> selectHisPermissionByRoles(List<RoleVO> roles);

    UserVO selectDbUserByPhone(UserQuery query);

    // 检测用户是否存在
    boolean checkPhoneExist(String phone);
}
