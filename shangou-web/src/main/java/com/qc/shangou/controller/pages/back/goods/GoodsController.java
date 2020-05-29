package com.qc.shangou.controller.pages.back.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author quincey
 * Date 2020/5/29 11:29
 */
@Controller
@RequestMapping("/pages/back/goods")
public class GoodsController {

    @RequestMapping("list")
    String list(){
        return "pages/back/goods/goods-list";
    }
}
