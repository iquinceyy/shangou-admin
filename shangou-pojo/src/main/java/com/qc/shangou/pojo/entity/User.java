package com.qc.shangou.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author quincey
 * Date 2020/5/29 15:30
 */
@Data
public class User implements Serializable {
    /**
     * 用户主键id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 真名字
     */
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱可以为null
     */
    private String email;

    /**
     * 上次登录ip:检测用户异地登录
     */
    private String lastLoginIp;

    /**
     * 上次登录日期
     */
    private Date lastLoginTime;

    /**
     * 角色组
     */
    private String roles;

    private static final long serialVersionUID = 1L;
}
