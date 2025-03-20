package com.teamwork.kejizhai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    // 确保默认Servlet处理器正确配置
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    // 明确指定静态资源位置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(
                    "classpath:/resources/",
                    "classpath:/static/",
                    "classpath:/public/"
                );
                
        // 特定资源映射
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/resources/img/");
        
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/resources/css/");
                
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/resources/js/");
    }
}