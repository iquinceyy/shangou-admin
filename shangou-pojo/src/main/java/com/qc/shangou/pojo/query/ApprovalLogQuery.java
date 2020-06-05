package com.qc.shangou.pojo.query;

import com.qc.shangou.util.PageQuery;
import lombok.Data;

/**
 * Author quincey
 * Date 2020/6/5 19:41
 */
@Data
public class ApprovalLogQuery extends PageQuery {
    private Long merchantId;
}
