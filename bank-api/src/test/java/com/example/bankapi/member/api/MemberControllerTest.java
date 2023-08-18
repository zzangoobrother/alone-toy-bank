package com.example.bankapi.member.api;

import com.example.bankapi.member.api.dto.request.SignupRequest;
import com.example.bankapi.member.applications.MemberCommandService;
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
}