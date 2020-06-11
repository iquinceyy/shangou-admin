package com.qc.shangou.dada.service;

/**
 * DATE: 18/9/3
 *
 * @author: wan
 */
public class BaseService {

    // 请求uri
    private String url;

    // 业务参数（业务参数：JSON字符串"{merchan_id:111,sss:[]}"）
    private String params;

    public BaseService(String url, String params){
        this.url = url;
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
