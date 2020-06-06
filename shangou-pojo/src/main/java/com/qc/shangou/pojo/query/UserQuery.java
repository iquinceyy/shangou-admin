package com.qc.shangou.pojo.query;

import com.qc.shangou.util.PageQuery;
import lombok.Data;

/**
 * Author quincey
 * Date 2020/5/29 15:30
 */
@Data
public class UserQuery extends PageQuery {
    private String password;
    private String phone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 真名字
     */
    private String realName;

}
