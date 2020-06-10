package com.qc.shangou.service.impl;

import com.qc.shangou.config.webmvc.WebMvcConfig;
import com.qc.shangou.dao.PermissionDao;
import com.qc.shangou.dao.RoleDao;
import com.qc.shangou.dao.UserDao;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Role;
import com.qc.shangou.pojo.entity.User;
import com.qc.shangou.pojo.query.UserQuery;
import com.qc.shangou.pojo.vo.PermissionVO;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.pojo.vo.UserVO;
import com.qc.shangou.service.UserService;
import com.qc.shangou.util.password.PasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author quincey
 * Date 2020/5/29 16:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Resource
    RoleDao roleDao;

    @Resource
    PermissionDao permissionDao;

    @Override
    public UserVO addUser(User user) {
        int i = userDao.insertSelective(user);
        if (i==1){
            UserVO u = new UserVO();
            u.setPhone(user.getPhone());
            u.setNickName(user.getNickName());
            u.setUserId(user.getUserId());
            return u;
        }
        return null;
    }

    @Override
    public UserVO login(UserQuery query) {
        return userDao.selectUserByPhoneAndPassword(query);
    }

    @Override
    public List<RoleVO> selectHisRolesByPhone(String phone) {
        UserVO u = userDao.selectUserByPhone(phone);
        if (!StringUtils.isEmpty(u.getRoles())) {
            List<RoleVO> roles = roleDao.selectHisRolesByRoles(u.getRoles());
            if (!CollectionUtils.isEmpty(roles)) {
                List<PermissionVO> permissionVOS = this.selectHisPermissionByRoles(roles);// 查出所有的权限
                return getRoleVOList(roles, permissionVOS);
            }
        }
        return null;
    }

    @Override
    public List<PermissionVO> selectHisPermissionByRoles(List<RoleVO> roles) {
        List<PermissionVO> list = new ArrayList<>();
//        // 第一种 ：
//        for (RoleVO role : roles) {
//            List<PermissionVO> permissionVOS = permissionDao.selectPermissionsByIds(role.getPermissions());
//            list.addAll(permissionVOS);
////            Collections.addAll(list, permissionVOS);// 这里有个把两个集合加入到一个集合里边去
//        }
        // 断言工具
//        Assert.notNull(roles, "传递的集合为null");
        if (!CollectionUtils.isEmpty(roles)) {
            Set<String> paramSet = new TreeSet<>();// 查询参数集合
            for (RoleVO role : roles) {// 在内存之中进行的。效率基本最高的
                String psIds = role.getPermissions();// 1,2,34
                if (!StringUtils.isEmpty(psIds)) {
                    Collections.addAll(paramSet, psIds.split(","));
                }
            }
            list = permissionDao.selectPermissionsBySet(paramSet);
        }
        // 第三种
        return list;
    }

    @Override
    public UserVO selectDbUserByPhone(UserQuery query) {
        return userDao.selectUserByPhone(query.getPhone());
    }

    @Override
    public boolean checkPhoneExist(String phone) {

        return userDao.selectUserByPhone(phone) != null;
    }

    @Override
    public PageDTO ajaxUserList(UserQuery query) {

        List<UserVO> userVOS = userDao.ajaxUserList(query);
        Integer count = userDao.ajaxUserCount(query);
        return PageDTO.setPageData(count,userVOS);
    }

    @Override
    public ResponseDTO addAjxaUser(User user) {
        //对密码加密
        String newPwd = PasswordUtil.encodePassword(user.getPassword());
        user.setPassword(newPwd);
        return ResponseDTO.get(userDao.insertSelective(user)==1);
    }

    @Override
    public ResponseDTO editUser(User user) {
        User dbUser = userDao.selectByPrimaryKey(user.getUserId());
        if (StringUtils.isEmpty(user.getPassword())){
            //密码为空
            user.setPassword(null);
        }else {
            //加密
            user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
        }
        if (userDao.updateByPrimaryKeySelective(user)==1){
            //删除缓存图片
            deleteImgCache(user);
            //            replaceOldFile(dbUser, user);
            if (!StringUtils.isEmpty(dbUser.getPhoto())){
                if (dbUser.getPhoto().equals(user.getPhoto())){

                }else {
                    if (!StringUtils.isEmpty(user.getPhoto())){
                        WebMvcConfig.deleteFile(dbUser.getPhoto());
                    }
                }
            }
           return ResponseDTO.ok("更新成功！",user);
        }
        return ResponseDTO.fail("更新失败！");
    }

    @Override
    public ResponseDTO deleteUser(User user) {
        return null;
    }

    @Override
    public void updateUser(User u) {
        userDao.updateByPrimaryKeySelective(u);
    }

    @Override
    public ResponseDTO dispatchUserPermission(Long userId, List<Role> roles) {
        //用流拿到role中的权限 取出来的是字符串，用set排序，去重
        TreeSet treeSet = new TreeSet();// 就是装最后分配好的角色id
        Set<String> collect = roles.stream().map(elem -> {
            String permissions = elem.getPermissions();
            if (StringUtils.isEmpty(permissions)) {
                return "";// 这里返回空字符串才行
            } else {
                // StringUtils 和js中的join方法很强大
                String[] split = permissions.split(",");
                //数组排序  从小到大排序(这里要注意的是，如果是字符串，大小排序不是按照数字来的)
                Arrays.sort(split, Comparator.comparingInt(Integer::valueOf));

                System.out.println(StringUtils.arrayToDelimitedString(split, ","));
                // 就是这个方法，就是把数组直接变成字符串
                return StringUtils.arrayToDelimitedString(split, ",");
            }
        }).collect(Collectors.toSet());
        List<Role> roles1 = roleDao.selectByPermissions(collect);
        Map<String, List<Role>> collect1 = roles1.stream().collect(Collectors.groupingBy(Role::getPermissions));
        System.out.println(collect1);

        for (Role r : roles1) {
            // 从查询结果里边取角色
            List<Role> roles2 = collect1.get(r.getPermissions());
            System.out.println(roles2);

            if (StringUtils.isEmpty(roles2)){
                // 结果集里边没有这个角色，就需要新增角色
                r.setSystem(false);
                r.setNote("---新增角色---");
                //插入数据库
                roleDao.insertSelective(r);
                treeSet.add(r.getRoleId());

            }else {
                treeSet.add(roles2.get(0).getRoleId());
            }
        }
        //设置用户的角色
        User u = new User();
        u.setUserId(userId);
        u.setRoles(StringUtils.collectionToDelimitedString(treeSet,","));
        return ResponseDTO.get(userDao.updateByPrimaryKeySelective(u)==1);
    }

    // 第一个参数，就传父类，如果没有父类对象，就两个参数的 class都一样
    private void replaceOldFile(Object user, Object dbUser) {
        // 1、先把父类的class找到！
        Class cls = user.getClass();
        Field[] declaredFields = dbUser.getClass().getDeclaredFields();// 只能得到一个


        User.class.getDeclaredFields();// 也能得到全部


    }
}
