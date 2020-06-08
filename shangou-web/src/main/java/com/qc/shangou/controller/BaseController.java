package com.qc.shangou.controller;

import com.qc.shangou.config.webmvc.WebMvcConfig;
import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.service.MerchantService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Author quincey
 * Date 2020/5/29 11:30
 */
public class BaseController {

    @Resource
    MerchantService merchantService;
    protected String saveFile(MultipartFile f, String uploadPath) {// 很多控制器里边都可能会有保存文件的操作
        if (f != null && !f.isEmpty()) {
            if (f.getSize() > 0) {
                String uid = UUID.randomUUID().toString();// 文件名称不会重复
                String originalFilename = f.getOriginalFilename();// 获取原始文件的名称
                String s = originalFilename.substring(originalFilename.lastIndexOf("."));// 获取文件后缀名称
                String fileName = uid + s;// 文件的名称（包含文件后缀）
                String realPath = getRequest().getServletContext().getRealPath("/");// 这个才是项目发布的磁盘目录

                String s1 = WebMvcConfig.getUploadPath();// 磁盘任意路径

                File file = new File(s1 + uploadPath + fileName);// 需要指定上文的保存目录路径
                if (!file.getParentFile().exists()) {// 如果父目录不存在
                    file.getParentFile().mkdirs();// 就多级创建父目录
                }
                try {
                    f.transferTo(file);
                    return uploadPath + fileName;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    protected String getPhone(){
        return (String) getSession().getAttribute("phone");
    }

    protected Long getUserId() {// 获取当前用户id
        return (Long) getSession().getAttribute("userId");
    }

    //获取当前商户id
    protected Long getMerchantId() {// 获取当前用户的商户id
        Long merchantId = (Long) getSession().getAttribute("merchantId");

        if (merchantId == null) {
            merchantId = merchantService.selectMerchantIdByUserId(getUserId());
            if (merchantId != null) {
                getSession().setAttribute("merchantId", merchantId);// 保存到session里边去
            }
        }
        return merchantId;
    }
}
