package com.batchflow.config;

import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.stream.Stream;

/**
 * packageName    : com.batchflow.config
 * fileName       : MybatisConfig
 * author         : AngryPig123
 * date           : 26. 1. 1.
 * description    :
 * MyBatis 설정
 * - Mapper 스캔(@MapperScan)
 * - snake_case -> camelCase 매핑(mapUnderscoreToCamelCase)
 * - TypeHandler 자동 스캔(typeHandlersPackage)
 * - 배치/대량 처리에 유리한 캐시/타임아웃 기본값
 * <p>
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 1.        AngryPig123       최초 생성
 */
@Configuration
@MapperScan(basePackages = {
        "com.batchflow.domain.**.mapper",
        "com.batchflow.batch.**.mapper"
})
public class MybatisConfig {

    private static final String TYPE_HANDLERS_PACKAGE = "com.batchflow";
    private static final String TYPE_ALIASES_PACKAGE = "com.batchflow";

    private static final String[] MAPPER_LOCATION_PATTERNS = {
            "classpath*:mybatis/mapper/**/*.xml",
            "classpath*:mybatis/mapper/**/**/*.xml",
    };

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {

        return configuration -> {
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }

    @Bean
    public org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeHandlersPackage(TYPE_HANDLERS_PACKAGE);
        factoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        factoryBean.setMapperLocations(resolveMapperLocations());
        org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
        mybatisConfig.setJdbcTypeForNull(JdbcType.NULL);
        factoryBean.setConfiguration(mybatisConfig);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private org.springframework.core.io.Resource[] resolveMapperLocations() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return Stream.of(MAPPER_LOCATION_PATTERNS)
                .flatMap(pattern -> {
                    try {
                        return Stream.of(resolver.getResources(pattern));
                    } catch (Exception e) {
                        return Stream.empty();
                    }
                })
                .toArray(org.springframework.core.io.Resource[]::new);
    }

}