package com.yh.mybatisplus.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yh.mybatisplus.interceptor.MyInterceptor;
import com.yh.mybatisplus.interceptor.MyInterceptorThree;
import com.yh.mybatisplus.interceptor.MyInterceptorTwo;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.yh.mybatisplus.*.mapper", sqlSessionFactoryRef = "sqlSessionFactoryOfMaster")
public class MybatisConfig {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.druid.max-active:20}")
    private int maxActive;

    /**
     * 不使用mybatis plus 配置如下
     * SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
     * bean.setDataSource(dataSource);
     * bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
     * return bean.getObject();
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("sqlSessionFactoryOfMaster")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlConfig") DataSource dataSource,
                                               @Qualifier("mybatisPlusInterceptor") MybatisPlusInterceptor mybatisPlusInterceptor
    ) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //添加自定义拦截器 与mybatis-plus 多租户拦截器
        bean.setPlugins(new MyInterceptor(), new MyInterceptorTwo(),
                new MyInterceptorThree(), mybatisPlusInterceptor);

        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    /**
     * 新多租户插件配置,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存万一出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
              //  return new LongValue(1);
                return new LongValue(0);
            }

            // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
            @Override
            public boolean ignoreTable(String tableName) {
                return !"user".equalsIgnoreCase(tableName);
            }
        }));
        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false
        //  interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

  /*      @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }*/

    @Bean("mysqlConfig")
    @Primary
    public DataSource dataSourceConfig() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setMaxActive(maxActive);
        return druidDataSource;
    }


}

