package com.teamwork.kejizhai.config;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class CustomBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {
    
    public CustomBeanPropertyRowMapper(Class<T> mappedClass) {
        super(mappedClass);
        // 设置为不区分大小写
        this.setMappedClass(mappedClass);
        this.setPrimitivesDefaultedForNullValue(true);
    }
    
    public static <T> RowMapper<T> newInstance(Class<T> mappedClass) {
        CustomBeanPropertyRowMapper<T> newInstance = new CustomBeanPropertyRowMapper<>(mappedClass);
        return newInstance;
    }
}