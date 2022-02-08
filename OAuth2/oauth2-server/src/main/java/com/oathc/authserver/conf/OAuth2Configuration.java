package com.oathc.authserver.conf;

import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * OAuth2 配置
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    private static final String CLIENT_ID = "cms";
    private static final String SECRET_CHAR_SEQUENCE = "secret";
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final String TRUST = "trust";
    private static final String USER = "user";
    private static final String ALL = "all";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 2 * 60;
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 2 * 60;
    // 密码模式授权模式
    private static final String GRANT_TYPE_PASSWORD = "password";
    // 授权码模式
    private static final String AUTHORIZATION_CODE = "authorization_code";
    // refresh token模式
    private static final String REFRESH_TOKEN = "refresh_token";
    // 简化授权模式
    private static final String IMPLICIT = "implicit";
    // 指定哪些资源是需要授权验证的
    private static final String RESOURCE_ID = "resource_id";

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()")    // 开启/oauth/token_key验证端口认证权限访问
                .checkTokenAccess("permitAll()")  // 开启/oauth/check_token验证端口认证权限访问
                .allowFormAuthenticationForClients();   // 允许表单认证 在授权码模式下会导致无法根据code获取token　
    }

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients
     * @throws Exception
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用内存存储
        clients.inMemory()
                .withClient(CLIENT_ID) //标记客户端id
                .secret(passwordEncoder.encode(SECRET_CHAR_SEQUENCE)) //客户端安全码
                .autoApprove(false) //为true 直接自动授权成功返回code
                .redirectUris("http://localhost:7778/resource_server") //重定向uri，uri中携带的必须为这里面填写的之一
                .scopes("app", "file", "zone") //允许授权范围，ALL就不会出现授权页
//                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS) //token 时间秒
//                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS) //刷新token 时间 秒
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)

                // 配置密码授权模式
                .and()
                .withClient("cms1")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                // user --->  BCryptPasswordEncoder 加密算法生成
                .secret("$2a$10$vrRjc8vlDwv6gBHlv/me8eeRsHO7P3I7ge0F03sr4C3hh/SmS/lMC")

               // 配置客户端授权模式
                .and()
                .withClient("client_1")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret("$2a$10$vrRjc8vlDwv6gBHlv/me8eeRsHO7P3I7ge0F03sr4C3hh/SmS/lMC");
    }

    public static void main(String[] args) {
        String pass = "user";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String passHash = encoder.encode(pass);
        System.out.println(passHash);
        final boolean matches = encoder.matches(pass, passHash);
        System.out.println(matches);
    }


    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 使用内存保存生成的token
        endpoints.authenticationManager(authenticationManager).tokenStore(memoryTokenStore());
        endpoints.userDetailsService(securityUserDetailsService);
    }

    public TokenStore memoryTokenStore() {
        // 最基本的InMemoryTokenStore生成token
        return new InMemoryTokenStore();
    }
}
