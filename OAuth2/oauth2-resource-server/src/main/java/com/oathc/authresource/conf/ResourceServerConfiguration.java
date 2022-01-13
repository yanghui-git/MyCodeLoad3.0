package com.oathc.authresource.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import java.util.UUID;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      //  resources.tokenServices(remoteTokenServices());
        super.configure(resources);
    }

    /**
     * 配置资源服务器的 token service
     *
     * @return
     */
    public RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:7777/oauth/check_token");
        services.setClientId("cms");
        services.setClientSecret(UUID.randomUUID().toString());
        return services;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 因为是资源服务器，假设所有的请求都需要登录
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 匿名用户访问无权限资源时的异常
                .accessDeniedHandler(new CustomAccessDeineHandler())    // 认证过的用户访问无权限资源时的异常
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable().httpBasic();
    }
}
