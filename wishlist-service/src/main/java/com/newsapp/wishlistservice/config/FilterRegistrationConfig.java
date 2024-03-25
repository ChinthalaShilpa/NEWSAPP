package com.newsapp.wishlistservice.config;

import com.newsapp.wishlistservice.filter.JwtFilter;
import com.newsapp.wishlistservice.service.AuthenticationServerFeignClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationConfig {
    @Bean
    public FilterRegistrationBean jwtFilter()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v1.0/wishlist/*");
        return filterRegistrationBean;
    }

    @Bean
    public JwtFilter jwtFilterBean(){
        return new JwtFilter();
    }

}