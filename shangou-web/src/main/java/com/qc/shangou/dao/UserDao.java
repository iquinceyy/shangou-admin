package com.qc.shangou.dao;

import com.qc.shangou.pojo.entity.User;
import com.qc.shangou.pojo.query.UserQuery;
import com.qc.shangou.pojo.vo.UserVO;

/**
 * Author quincey
 * Date 2020/5/29 16:06
 */
public interface UserDao {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    UserVO selectUserByPhoneAndPassword(UserQuery query);

    UserVO selectUserByPhone(String phone);
}
