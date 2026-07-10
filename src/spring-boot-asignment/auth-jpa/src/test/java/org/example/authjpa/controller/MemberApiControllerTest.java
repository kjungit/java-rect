package org.example.authjpa.controller;

import org.example.authjpa.exception.DuplicateUserIdException;
import org.example.authjpa.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberApiController.class)
class MemberApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 성공 - 200과 이동할 url 반환")
    void join_성공() throws Exception {
        String requestJson = objectMapper.writeValueAsString(
                Map.of(
                        "userId", "test",
                        "password", "1234",
                        "name", "test"
                      )

                                                            );
        mockMvc.perform(
                        post("/api/members/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                       )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url")
                                   .value("/members/login"));
    }


    @Test
    @DisplayName("회원가입 실패 - 아이디가 중복이면 409와 에러 메시지를 반환한다.")
    void join_중복이면_409() throws Exception {
        willThrow( new DuplicateUserIdException("[회원가입] 이미 존재하는 아이디입니다."))
                .given( memberService ).join( any() );

        String requestJson = objectMapper.writeValueAsString(
                Map.of(
                        "userId", "test",
                        "password", "1234",
                        "name", "test"
                      )
                                                            );
        // when & then
        mockMvc.perform(
                        post("/api/members/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                       )
                .andExpect( status().isConflict() )
                .andExpect( jsonPath("$.message").value("[회원가입] 이미 존재하는 아이디입니다.") );
    }

}