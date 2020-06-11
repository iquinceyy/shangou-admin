package com.qc.shangou.dada.utils;

import com.lh.shangou.dada.client.DadaApiResponse;
import com.lh.shangou.dada.config.AppConstant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * creator：杜夫人
 * date: 2020/6/11
 */
@Component
public class DadaClient {


    public void request(String orderAddUrl, Map<String, String> pMap) {


        String requestUrl = AppConstant.ONLINE_HOST.concat(orderAddUrl);// 真正请求路径
        String requestParams = getRequestParams();

        try {
            String resp = HttpClientUtil.postRequest(requestUrl, requestParams);
            return JSONUtil.fromJson(resp, DadaApiResponse.class);
        } catch (Exception e) {
            return DadaApiResponse.except();
        }


    }

    private String getRequestParams(Map<String, String> pMap) {
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("source_id", AppConstant.SOURCE_ID);
        requestParams.put("app_key", AppConstant.APP_KEY);
        requestParams.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestParams.put("format", AppConstant.FORMAT);
        requestParams.put("v", AppConstant.V);
        requestParams.put("body", this.apiService.getParams());
        requestParams.put("signature", this.getSign(requestParams));
        return JSONUtil.toJson(requestParams);
    }
}
