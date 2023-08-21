package com.example.bankapi.global.auth;

public class AuthThreadLocalContext {
    private final static ThreadLocal<MemberDetails> threadLocal = new ThreadLocal<>();

    public static void setMemberDetails(MemberDetails memberDetails) {
        threadLocal.set(memberDetails);
    }

    public static MemberDetails getMemberDetails() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
