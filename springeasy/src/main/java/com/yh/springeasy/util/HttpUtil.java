package com.yh.springeasy.util;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * HttpUtil 工具类
 */
@CommonsLog
public class HttpUtil {

    /**
     * 模拟Get 访问请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        String result = "";
        try {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            result = StringUtils.isEmpty(httpEntity) ? result : EntityUtils.toString(httpEntity, "UTF-8");
            response.close();
        } catch (Exception e) {
            log.error("HttpUtil get 接口调用失败,调用地址 " + url + " 失败原因" + e);
            throw new Exception(e);
        }
        return result;
    }


    /**
     * 模拟PUT 访问请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String put(String url) throws Exception {
        String result = "";
        try {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(url);
            CloseableHttpResponse response = httpClients.execute(httpPut);
            HttpEntity httpEntity = response.getEntity();
            result = StringUtils.isEmpty(httpEntity) ? result : EntityUtils.toString(httpEntity, "UTF-8");
            response.close();
        } catch (Exception e) {
            log.error("HttpUtil put 接口调用失败,调用地址 " + url + " 失败原因" + e);
            throw new Exception(e);
        }
        return result;
    }

    /**
     * 模拟Post 访问请求
     *
     * @param url
     * @param formParams 请求body参数
     * @return
     * @throws Exception
     */
    public static String post(String url, List<NameValuePair> formParams) throws Exception {
        String result = "";
        try {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            result = StringUtils.isEmpty(httpEntity) ? result : EntityUtils.toString(httpEntity, "UTF-8");
            response.close();
        } catch (Exception e) {
            log.error("HttpUtil post 接口调用失败,调用地址 " + url + " 失败原因" + e);
            throw new Exception(e);
        }
        return result;
    }

    /**
     * 模拟Delete 访问请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String delete(String url) throws Exception {
        String result = "";
        try {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDelete httpDelete = new HttpDelete(url);
            CloseableHttpResponse response = httpClients.execute(httpDelete);
            HttpEntity httpEntity = response.getEntity();
            result = StringUtils.isEmpty(httpEntity) ? result : EntityUtils.toString(httpEntity, "UTF-8");
            response.close();
        } catch (Exception e) {
            log.error("HttpUtil delete 接口调用失败,调用地址 " + url + " 失败原因" + e);
            throw new Exception(e);
        }
        return result;
    }
}
