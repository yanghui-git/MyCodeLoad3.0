package com.oathc.authclient.controller;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.apache.http.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试controller
 */
@RestController
@RequestMapping("/test")
public class TestController {

    // 远程服务调用地址
    String resourceUrl = "http://localhost:7778/resource_server";

    // 资源认证服务器 授权码地址
    String authorizeUrl = "http://localhost:7777/oauth/authorize?client_id=cms&response_type=code";

    String tokenUrl = "http://localhost:7777/oauth/token?grant_type=authorization_code&code=378kcG&client_id=cms&client_secret=secret";

    String checkToken = "http://localhost:7777/oauth/check_token?token=13cdc1e5-df6f-4dcd-80ff-b0600c6f6408";

    /**
     * 模拟消费者 调用 资源服务器的接口  返回: 您还未进行登录，没有访问权限!
     */
    @GetMapping("/one")
    public void testOne() throws Exception {
        // 直接访问此接口
        String result = HttpUtil.get(resourceUrl);
        System.out.println(result);
        // 您还未进行登录，没有访问权限!
    }

    /**
     * 正常接口请求流程
     * 1 浏览器访问 认证服务中心授权地址  拿到授权码 code
     * http://localhost:7777/oauth/authorize?client_id=cms&response_type=code 然后授权
     * 通过拿到的code 调用此接口进行下一步
     */
    @GetMapping("/two")
    public void testTwo(@RequestParam(required = true) String code) throws Exception {
        // 2 通过此授权码 继续 访问资源认证服务器 拿到 访问 token
        String tokenResult = HttpUtil.post("http://localhost:7777/oauth/token?grant_type=authorization_code&code=" +
                        code +
                        "&client_id=cms&client_secret=secret",
                new ArrayList<>());
        /**
         * {"access_token":"977cf512-fb00-464b-8e9e-169b142197e1","token_type":"bearer","refresh_token":"bb3c0fed-02f4-4918-8541-989544e57a6f","expires_in":43199,"scope":"app file"}
         */
        System.out.println("获取到的 token信息: " + tokenResult);

        // 3 校验拿到的token信息 是否有问题
        String checkResult = "http://localhost:7777/oauth/check_token?token=" + JSON.parseObject(tokenResult).get("access_token");
        System.out.println(" 校验token 信息 ：" + HttpUtil.get(checkResult));

        // 4 携带token 再次访问 目标资源地址
        String resourceUrl = "http://localhost:7778/resource_server" + "?access_token=" + JSON.parseObject(tokenResult).get("access_token");
        System.out.println(HttpUtil.get(resourceUrl));
    }

    @Autowired
    private RestOperations restTemplate;

    /**
     * 底层 底层调用校验
     */
    @GetMapping("/three")
    public void testThree() throws Exception {
        LinkedMultiValueMap formData = new LinkedMultiValueMap<String, String>();
        formData.add("token", "ea06134b-f79a-4c84-ac62-0888bf43bd89");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic bnVsbDo3Y2M1YmY4ZC0yZTNlLTRmODMtYmVjMi0zOTIxNzVlMGZmOGI=");
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        try {
            ResponseEntity responseEntity = (ResponseEntity) restTemplate.exchange("http://localhost:7777/oauth/check_token", HttpMethod.POST,
                    new HttpEntity<MultiValueMap<String, String>>(formData, headers), Map.class).getBody();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(HttpUtil.get("http://localhost:7777/oauth/check_token?token=ea06134b-f79a-4c84-ac62-0888bf43bd89"));
    }

}
