package com.qc.shangou.dada.domain;


import com.lh.shangou.dada.utils.JSONUtil;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public class BaseModel {

    public String toJson() {
        return JSONUtil.toJson(this);
    }
}
