package com.qc.shangou.pojo.query;

import com.qc.shangou.util.PageQuery;
import lombok.Data;

/**
 * Author quincey
 * Date 2020/6/9 14:55
 */
@Data
public class GoodsTypeQuery extends PageQuery {

    private String typeName;
    private Long merchantId;
}
