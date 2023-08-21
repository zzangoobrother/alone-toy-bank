package com.example.bankapi.global.config;

import com.example.bankapi.global.handler.AuthMemberArgument;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthMemberArgument authMemberArgument;

    public WebConfig(AuthMemberArgument authMemberArgument) {
        this.authMemberArgument = authMemberArgument;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authMemberArgument);
    }
}
