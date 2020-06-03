package com.qc.shangou.controller;

import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.User;
import com.qc.shangou.pojo.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

/**
 * Author quincey
 * Date 2020/5/29 11:32
 */
@Controller
public class LoginController extends BaseController{

    @RequestMapping("/login")
    @ResponseBody
    ResponseDTO login(UserVO u,boolean isBack) {// 这个方法是执行登录操作的
        System.out.println("login");
        if (!isBack){
            if (!StringUtils.isEmpty(u.getCode())) {//验证码登录或者注册
                //不是后台登录
                SecurityUtils.getSubject().getSession().setAttribute("isBack",false);
            }
        }
        // 获取subject
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("18875150682", "123456");
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(u.getPhone(), u.getPassword());
        getSession().setAttribute("code",u.getCode());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);// 只要 执行login方法，那么它就会跑到userRealm里边的认证方法（doGetAuthenticationInfo）
        } catch (AuthenticationException a) {
            a.printStackTrace();
//            model.addAttribute("errorMsg", a.getMessage());
//            return "loginPage";
            return ResponseDTO.fail(a.getMessage());
        }
//        return "pages/back/home";
        return ResponseDTO.ok("登陆成功");
    }

    // 这个方法是跳转到登录页面用的
    @RequestMapping("/loginPage")
    String loginPage(HttpServletRequest request,boolean isBack) {
        System.out.println("isBack:"+isBack);
        //判断来自哪一个
        if (isBack){
            System.out.println(isBack);
            return "loginPage";
        }
        //不是后台就是前台
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if (savedRequest!=null){
            String queryString = savedRequest.getQueryString();
            if ("/pages/back/merchant/addPre".equals(savedRequest.getRequestURI())) {
                return "client-loginPage";
            }
        }
        return "client-loginPage";
//        return "loginPage";
    }

    @RequestMapping("/pages/back/loginSuccess")
    String loginSuccess(HttpServletRequest request,boolean isBack) {

        //
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);// shiro保存拦截之前的请求对象
        if (savedRequest!=null){
            String queryString = savedRequest.getQueryString();// 获取参数字符串,有被拦截的路径，就跳转回拦截之前的那个路径return "/pages/back/home";//后台管理页面
            return "redirect:"+savedRequest.getRequestURI()+"?"+queryString;
        }

        if(isBack){
            return "/pages/back/home";//后台管理页面
        }else{
            return "pages/back/client/my-info";//客户端我的界面
        }

    }

    // 这个方法是跳转到登录页面用的,退出App
    @RequestMapping("logoutApp")
    String logout(){
        SecurityUtils.getSubject().logout();
        return "loginPage";
    }

}
