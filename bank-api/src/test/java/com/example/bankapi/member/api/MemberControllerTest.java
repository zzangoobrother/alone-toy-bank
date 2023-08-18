package com.example.bankapi.member.api;

import com.example.bankapi.global.auth.JwtProvider;
import com.example.bankapi.member.api.dto.request.LoginRequest;
import com.example.bankapi.member.api.dto.request.SignupRequest;
import com.example.bankapi.member.applications.MemberCommandService;
import com.example.bankapi.member.applications.MemberQueryService;
import com.example.bankapi.member.applications.dto.response.LoginServiceResponse;
import com.example.bankapi.member.applications.dto.response.SignupServiceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberCommandService memberCommandService;

    @MockBean
    private MemberQueryService memberQueryService;

    @MockBean
    private JwtProvider jwtProvider;

    private static final String NAME = "홍길동";

    @DisplayName("회원가입 성공")
    @Test
    void signup() throws Exception {
        // given
        SignupRequest request = new SignupRequest(NAME);

        when(memberCommandService.signup(any())).thenReturn(SignupServiceResponse.builder().build());

        mockMvc.perform(
                post("/api/signup")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("회원명이 null 이면 에러")
    @Test
    void nullName() throws Exception {
        SignupRequest request = new SignupRequest(null);

        mockMvc.perform(
                        post("/api/signup")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원명이 빈값이면 에러")
    @Test
    void notInputName() throws Exception {
        SignupRequest request = new SignupRequest("");

        mockMvc.perform(
                        post("/api/signup")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("로그인 성공")
    @Test
    void login() throws Exception {
        // given
        LoginRequest request = new LoginRequest(NAME);

        when(memberQueryService.login(any())).thenReturn(LoginServiceResponse.builder().build());

        mockMvc.perform(
                        post("/api/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("회원명이 null 이면 에러")
    @Test
    void nullNameLogin() throws Exception {
        LoginRequest request = new LoginRequest(null);

        mockMvc.perform(
                        post("/api/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원명이 빈값이면 에러")
    @Test
    void notInputNameLogin() throws Exception {
        LoginRequest request = new LoginRequest("");

        mockMvc.perform(
                        post("/api/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}