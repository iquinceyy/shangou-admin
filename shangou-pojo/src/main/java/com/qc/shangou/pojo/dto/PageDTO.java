package com.qc.shangou.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author quincey
 * Date 2020/5/29 10:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO implements Serializable {// 传递给layui使用的DTO对象
    private Integer code = 0;

    private String msg = "";

    private Integer count = 0;

    private Object data;

    public static PageDTO setPageData(Integer count, Object data) {
        return new PageDTO(0, "成功", count, data);

    }
}
