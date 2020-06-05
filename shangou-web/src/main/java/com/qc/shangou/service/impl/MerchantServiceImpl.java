package com.qc.shangou.service.impl;

import com.qc.shangou.dao.MerchantDao;
import com.qc.shangou.pojo.consts.enums.ApprovalEnum;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.pojo.query.MerchantQuery;
import com.qc.shangou.pojo.vo.MerchantVO;
import com.qc.shangou.pojo.vo.PermissionVO;
import com.qc.shangou.pojo.vo.RoleVO;
import com.qc.shangou.service.ImgCacheService;
import com.qc.shangou.service.MerchantService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Author quincey
 * Date 2020/6/4 11:20
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Resource
    MerchantDao merchantDao;
    @Resource
    ImgCacheService imgCacheService;

    @Override
    public PageDTO ajaxList(MerchantQuery query) {
        List<MerchantVO> merchantVOS = merchantDao.ajaxList(query);
        Integer count = merchantDao.ajaxListCount(query);
        return PageDTO.setPageData(count,merchantVOS);
    }

    @Override

    public ResponseDTO addMerchant(Merchant merchant) {

        Assert.notNull(merchant.getPcd(), "省份不能为空!");//判断省份
        String province = merchant.getPcd().split("-")[0];//取出省份
        merchant.setProvince(province);//设置省份

        Date date = new Date();//设置。。。。
        merchant.setCreateTime(date);
        merchant.setUpdateTime(date);
        merchant.setMaxDeliveryArea(3.0);//最大配送距离3千米
        merchant.setMerchantId(System.currentTimeMillis());//设置当前商户的id

        merchant.setApprovalStatus(ApprovalEnum.审核中.toString());

        if (merchantDao.insertSelective(merchant) == 1) {
            imgCacheService.deleteImgCache(merchant);//清除缓存图片
            return ResponseDTO.ok("申请成功");
        }

        return ResponseDTO.fail("申请失败");
    }


    @Override
    public boolean selectMerchantIsOrNot(Long userId) {

        if (merchantDao.selectMerchantIsOrNot(userId)){
            return false;
        }
        return true;
    }

    @Override
    public ResponseDTO deleteMerchantByMerchantId(List<Merchant> merchant) {
        return ResponseDTO.get(merchantDao.deleteMerchant(merchant)==merchant.size());
    }

}
