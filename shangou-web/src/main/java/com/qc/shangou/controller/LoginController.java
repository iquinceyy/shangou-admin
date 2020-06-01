package com.qc.shangou.controller;

import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.User;
import com.qc.shangou.pojo.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    ResponseDTO login(UserVO u) {// 这个方法是执行登录操作的

        // 获取subject
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("18875150682", "123456");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(u.getPhone(), u.getPassword());
        System.out.println(u.getPhone());
        System.out.println(u.getPassword());
        System.out.println(123);
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
    String loginPage() {
        return "loginPage";
    }

    @RequestMapping("/pages/back/loginSuccess")
    String loginSuccess() {
        return "pages/back/home";
    }
}
