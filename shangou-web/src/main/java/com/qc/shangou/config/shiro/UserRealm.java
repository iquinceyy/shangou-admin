package com.qc.shangou.config.shiro;

import com.qc.shangou.pojo.entity.User;
import com.qc.shangou.pojo.query.UserQuery;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.pojo.vo.UserVO;
import com.qc.shangou.service.UserService;
import com.qc.shangou.util.net.NetUtil;
import com.qc.shangou.util.password.PasswordUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Author quincey
 * Date 2020/5/29 11:26
 */
public class UserRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Resource
    UserService userService;

    /**
     * 认证身份用的
     * subject.login(token)调用之后，会进入到这个方法
     *
     * @param authenticationToken 携带了用户名和密码的：认证，判断用户名和密码跟数据库里边是否一致
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object principal = authenticationToken.getPrincipal();// 获取身份（用户名）
        Object credentials = authenticationToken.getCredentials();// 获取密码（密码）：是前端传递来的，不具备真实性
        Session session = SecurityUtils.getSubject().getSession();

        //把code取出来
        Object code = session.getAttribute("code");
        UserQuery query = new UserQuery();
        String password = new String((char[]) credentials);// 前端传递过来的// String.valueOf((char[]) credentials)
        query.setPhone((String) principal);
        UserVO dbUser = userService.selectDbUserByPhone(query);// 拿到了数据库的用户

        //常规密码验证
        if (StringUtils.isEmpty(code)){
            if (dbUser == null) {
                throw new UnknownAccountException("账户或密码错误");
            } else {// 账户虽然存在，就要开始比较密码
                if (!PasswordUtil.encodePassword(password).equals(dbUser.getPassword())) {
                    // 缓存数据库里边存当前用户密码错误的次数
                    throw new CredentialsException("账户或密码错误");
                }
            }
//            session.setAttribute("userId", dbUser.getUserId());
//            session.setAttribute("nickName", dbUser.getNickName());
//            session.setAttribute("phone", dbUser.getPhone());
//            session.setAttribute("realName", dbUser.getRealName());
//            // 设置角色
//            List<RoleVO> roleVOS = userService.selectHisRolesByPhone(dbUser.getPhone());
//            session.setAttribute("hisRoles",roleVOS);
        }else{//短信验证
            //获取发送的短信验证码
            Object loginCode = session.getAttribute("loginCode");
            if (code.equals(loginCode)){//登录成功 验证码相等
                query.setPhone((String) principal);
//                UserVO dbUser = userService.selectDbUserByPhone(query);// 拿到了数据库的用户
                Object isBack = session.getAttribute("isBack");
                if (dbUser==null){
                    if (isBack!=null){
                        boolean isBackFlag = (boolean)isBack;
                        if (!isBackFlag){
                            //注册新用户
                            User user = new User();
                            user.setNickName("请修改名字");
                            user.setPhone(query.getPhone());
                            user.setLastLoginTime(new Date());
//                            user.setLastLoginIp();
                            dbUser=userService.addUser(user);
                        }
                    }
                }
            }else {
                throw  new CredentialsException("验证码错误");
            }
        }

        //第二种方式
//        query.setPassword(PasswordUtil.encodePassword(password));
//        UserVO u = userService.login(query);
//        if (u == null) {
//            // 如果用户是验证码登录的方式，就应该去直接给他注册一个账号，而后登录
//            throw new AuthenticationException("账户名或密码错误");
//        }
        // 应该设置 session
        session.setAttribute("userId",dbUser.getUserId());
        session.setAttribute("nickName",dbUser.getNickName());
        session.setAttribute("phone",dbUser.getPhone());
        session.setAttribute("realName",dbUser.getRealName());
        session.setAttribute("photo", dbUser.getPhoto());

        //设置角色
        List<RoleVO> roleVOS = userService.selectHisRolesByPhone(dbUser.getPhone());
        session.setAttribute("hisRoles",roleVOS);

        User u = new User();
        u.setUserId(dbUser.getUserId());
        u.setLastLoginTime(new Date());
        u.setLastLoginIp(NetUtil.getIpAddr());
        userService.updateUser(u);

        // 设置权限
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), "userRealm");

    }

    /**
     * 对身份进行授权用的
     *
     * @param principalCollection
     * @return
     * @throws AuthenticationException 根据用户名去数据库查询这个 用户的角色和权限设置给 这个SimpleAuthorizationInfo对象即可
     *                                 <p>
     *                                 只有第一次判断的时候，才能进入授权方法，如果当前用户没有这个权限或者角色，那么将会被缓存到内存之中，第二次之后，就不用查询了
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 登录成功之后，给用户授予角色和权限用的，或者说检测用户的角色和权限的
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();


        return auth;

    }
}
