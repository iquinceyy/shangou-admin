package com.qc.shangou.service;

import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.User;
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

    //查询用户列表
    PageDTO ajaxUserList(UserQuery query);

    ResponseDTO addAjxaUser(User user);

    ResponseDTO editUser(User user);

    ResponseDTO deleteUser(User user);
}
