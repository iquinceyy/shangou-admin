package com.qc.shangou.service.impl;

import com.qc.shangou.dao.ImgCacheDao;
import com.qc.shangou.pojo.entity.ImgCache;
import com.qc.shangou.service.ImgCacheService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/4 16:04
 */
@Service
public class ImgCacheServiceImpl implements ImgCacheService {

    @Resource
    ImgCacheDao imgCacheDao;
    @Override
    public boolean addCache(String s) {
        ImgCache imgCache = new ImgCache();
        imgCache.setImgUrl(s);
        imgCache.setCreateTime(new Date());
        return imgCacheDao.insertImgCache(imgCache)==1;
    }

    @Override
    public List<ImgCache> getAllImgCache(Date time) {
        return imgCacheDao.getAllImgCache(time);
    }

    @Override
    public boolean deleteImgCache(Object obj) {
        Class clszz = obj.getClass();//获取反射类对象
        Field[] declaredFields = clszz.getDeclaredFields();//拿到所有属性

        for (Field f : declaredFields) {
            if (f.getType().isAssignableFrom(String.class)){
                f.setAccessible(true);
                String value = null;
                try {
                    value = (String) f.get(obj);// 获取成员的值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                if(!StringUtils.isEmpty(value)){// 不是空，就判断是否是/upload/开头的，如果是，就认为是图片。就去缓存里边删缓存
                    if (value.endsWith("upload")){//图片
                        imgCacheDao.deleteByPrimaryKey(value);
                    }
                }
            }
        }

        return true;
    }
}
