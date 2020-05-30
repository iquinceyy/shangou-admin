package com.qc.shangou.pojo.query;

import com.qc.shangou.pojo.entity.Permission;
import com.qc.shangou.util.PageQuery;
import lombok.Data;

/**
 * Author quincey
 * Date 2020/5/30 16:22
 */
@Data
public class PermissionQuery extends PageQuery {

    private String name;
    private String note;
    private String flag;

}
