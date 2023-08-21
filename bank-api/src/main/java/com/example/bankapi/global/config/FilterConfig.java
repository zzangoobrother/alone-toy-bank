package com.example.bankapi.global.config;

import com.example.bankapi.global.auth.MemberDetailsService;
import com.example.bankapi.global.auth.jwt.JwtProvider;
import com.example.bankapi.global.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    private final MemberDetailsService memberDetailsService;
    private final JwtProvider jwtProvider;

    public FilterConfig(MemberDetailsService memberDetailsService, JwtProvider jwtProvider) {
        this.memberDetailsService = memberDetailsService;
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public FilterRegistrationBean<Filter> authFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new AuthFilter(memberDetailsService, jwtProvider));
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new OrderedCharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");

        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(characterEncodingFilter);
        filterFilterRegistrationBean.setOrder(0);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return characterEncodingFilter;
    }
}
