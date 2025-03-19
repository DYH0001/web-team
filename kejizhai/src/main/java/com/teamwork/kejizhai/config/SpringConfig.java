package com.teamwork.kejizhai.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.util.unit.DataSize;

import jakarta.servlet.MultipartConfigElement;

@Configuration
@ComponentScan(basePackages = {"com.teamwork.kejizhai.services", "com.teamwork.kejizhai.dao"})
public class SpringConfig implements WebMvcConfigurer {

    // 数据库配置
    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/kejizhai?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        dataSource.setPassword("dyhDYH@040127"); // 替换为你的实际密码
        return dataSource;
    }

    // JdbcTemplate配置
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // 跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    // 静态资源配置 - 注释掉以避免与application.properties冲突
    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源映射
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/", 
                                     "classpath:/resources/",
                                     "classpath:/static/", 
                                     "classpath:/public/");
                
        // 上传文件映射
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:D:/workplace/web/kejizhai/uploads/");
    }
    */

    // 视图控制器配置
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 将根路径映射到index.html
        registry.addViewController("/").setViewName("forward:/index.html");
    }
    
    // 文件上传配置
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制为10MB
        factory.setMaxFileSize(DataSize.ofMegabytes(10));
        // 设置总上传数据总大小为10MB
        factory.setMaxRequestSize(DataSize.ofMegabytes(10));
        return factory.createMultipartConfig();
    }
}
