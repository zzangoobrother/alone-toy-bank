package com.example.bankapi.global.aop;

import com.example.bankapi.global.annotation.Authorization;
import com.example.bankapi.global.auth.AuthThreadLocalContext;
import com.example.bankapi.global.auth.MemberDetails;
import com.example.bankapi.global.exception.ApiErrorCode;
import com.example.bankapi.global.exception.NotAuthorizationException;
import com.example.bankmember.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Order(1)
@Component
@Aspect
public class AuthorizationAop {

    @Around("@annotation(com.example.bankapi.global.annotation.Authorization)")
    public Object authorization(final ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Authorization authorization = method.getAnnotation(Authorization.class);

        try {
            MemberDetails memberDetails = AuthThreadLocalContext.getMemberDetails();
            Role role = authorization.role();
            if (memberDetails.getRole() != role) {
                log.error("접근 권한이 없습니다. role : {}", memberDetails.getRole());
                throw new NotAuthorizationException(ApiErrorCode.NOT_AUTHORIZATION);
            }

            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
