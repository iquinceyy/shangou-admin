package com.qc.shangou.service;

import com.qc.shangou.pojo.entity.ImgCache;

import java.util.Date;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/4 16:03
 */
public interface ImgCacheService {

    boolean addCache(String s);

    List<ImgCache> getAllImgCache(Date time);

    boolean deleteImgCache(Object obj);// /upload/
}
