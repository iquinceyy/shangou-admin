package com.qc.shangou.pojo.query;

import com.qc.shangou.util.PageQuery;
import lombok.Data;

/**
 * Author quincey
 * Date 2020/5/29 15:31
 */
@Data
public class RoleQuery extends PageQuery {

    private String name;
    private String flag;
    private String note;
    private Boolean system;
}
