package com.example.bankcommon.global.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Name {

    @Column(name = "name", nullable = false)
    private String name;

    private Name(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("이름은 필수값 입니다.");
        }

        this.name = name;
    }

    public static Name newInstance(String name) {
        return new Name(name);
    }

    public boolean isEquals(String target) {
        return this.name.equals(target);
    }
}
