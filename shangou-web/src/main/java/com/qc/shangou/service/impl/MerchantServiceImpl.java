package com.qc.shangou.service.impl;

import com.qc.shangou.dao.GoodsDao;
import com.qc.shangou.dao.GoodsTypeDao;
import com.qc.shangou.dao.MerchantDao;
import com.qc.shangou.pojo.consts.enums.ApprovalEnum;
import com.qc.shangou.pojo.dto.PageDTO;
import com.qc.shangou.pojo.dto.ResponseDTO;
import com.qc.shangou.pojo.entity.Goods;
import com.qc.shangou.pojo.entity.GoodsType;
import com.qc.shangou.pojo.entity.Merchant;
import com.qc.shangou.pojo.query.MerchantQuery;
import com.qc.shangou.pojo.vo.*;
import com.qc.shangou.service.ImgCacheService;
import com.qc.shangou.service.MerchantService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    GoodsTypeDao goodsTypeDao;
    @Resource
    GoodsDao goodsDao;

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

    @Override
    public Merchant seleleMerchantIdIdByPhone(String phone) {
        return merchantDao.seleleMerchantIdIdByPhone(phone);
    }

    @Override
    public Long selectMerchantIdByUserId(Long userId) {
        MerchantVO m = merchantDao.selectByUserId(userId);
        if (m != null) {
            return m.getMerchantId();
        }
        return null;
    }

    @Override
    public PageDTO getNearByMerchantGoods(MerchantQuery merchantQuery) {
        //1.查询附近的商铺  剔除空的  用iterator
        List<MerchantVO> merchantVOS = merchantDao.ajaxList(merchantQuery);
        //2.查询商铺卖的最好的商品
        if (!CollectionUtils.isEmpty(merchantVOS)){
            //卖的最好的商品类型集合
            List<GoodsVO> merchantBestGoods = goodsDao.getMerchantBestGoods(merchantVOS);
            //流 根据MerchantId分组
            Map<Long, List<GoodsVO>> collect = merchantBestGoods.stream().collect(Collectors.groupingBy(GoodsVO::getMerchantId));

            //迭代除去空的商铺 没有卖的
            Iterator<MerchantVO> iterator = merchantVOS.iterator();
            while (iterator.hasNext()){//是否有下一个
                MerchantVO next = iterator.next();//得到商户
                List<GoodsVO> goodsVOS = collect.get(next.getMerchantId());

                //判断集合是否为空
                if (CollectionUtils.isEmpty(merchantBestGoods)){
                    //为空 移除
                    iterator.remove();
                }else{
                    next.setBestGoods(goodsVOS.get(0));// 不管有多少个卖得相同数量最好的，都只取一个
                }
            }
        }

        Integer count = merchantDao.ajaxListCount(merchantQuery);
        return PageDTO.setPageData(count,merchantVOS);

    }

    @Override
    public MerchantVO selectMerchantById(Long merchantId) {
        //1.查询商铺的基本字段（信息）
        MerchantVO merchantVO = merchantDao.selectBaseKey(merchantId);
        //2.根据商户merchantId查询全部商品 类型
        List<GoodsTypeVO> goodsTypeVOS = goodsTypeDao.selectByMerchantId(merchantId);

        if (!CollectionUtils.isEmpty(goodsTypeVOS)){
            //3.根据商品类型集合  批量查询
            List<GoodsVO> goodsVOS = goodsDao.selectGoodsByGoodsType(goodsTypeVOS);
            //4.按商品类型分组
            Map<Long, List<GoodsVO>> collect = goodsVOS.stream().collect(Collectors.groupingBy(GoodsVO::getGoodsTypeId));
            System.out.println(collect);
            //设置这种商品类型的商品
            for (GoodsTypeVO g : goodsTypeVOS) {
                g.setGoodsVOS(collect.get(g.getGoodsTypeId()));;
            }
        }
        merchantVO.setGoodsTypeList(goodsTypeVOS);

        return merchantVO;
    }

}
