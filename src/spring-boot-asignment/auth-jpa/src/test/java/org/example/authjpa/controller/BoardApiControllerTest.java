package org.example.authjpa.controller;


import org.example.authjpa.domain.entity.Board;
import org.example.authjpa.domain.repository.BoardRepository;
import org.example.authjpa.service.BoardService;
import org.example.authjpa.service.FileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BoardApiController.class)
class BoardApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BoardService boardService;

    @MockitoBean
    private FileService fileService;

    @MockitoBean
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 목록 조회 성공")
    void getBoardList_성공() throws Exception {
        Board board = Board.builder()
                .id(1L)
                .title("제목")
                .content("내용")
                .userId("jun")
                .build();

        given(boardService.getBoardList(1, 10))
                .willReturn(List.of(board));
        given(boardService.getTotalBoards())
                .willReturn(1);
        mockMvc.perform(
                        get("/api/boards")
                                .param("page", "1")
                                .param("size", "10")
                       )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.last").value(true))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.boards[0].title").value("제목"))
                .andExpect(jsonPath("$.boards[0].content").value("내용"));
    }

    @Test
    @DisplayName("게시글 상세 조회 성공")
    void getBoardDetail_성공() throws Exception {
        Board board = Board.builder()
                .id(1L)
                .title("제목")
                .content("내용")
                .userId("jun")
                .filePath("test.png")
                .created(LocalDateTime.of(2026, 7, 10, 10, 0))
                .build();

        given(boardService.getBoardDetail(1L))
                .willReturn(board);
        mockMvc.perform(get("/api/boards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andExpect(jsonPath("$.userId").value("jun"))
                .andExpect(jsonPath("$.filePath").value("test.png"));
    }

    @Test
    @DisplayName("게시글 작성 성공")
    void createBoard_성공() throws Exception {
        mockMvc.perform(
                        multipart("/api/boards")
                                .param("title", "제목")
                                .param("content", "내용")
                                .param("userId", "jun")
                       )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 수정 성공")
    void updateBoard_성공() throws Exception {
        mockMvc.perform(
                        multipart("/api/boards/1")
                                .with(request -> {
                                    request.setMethod("PUT");
                                    return request;
                                })
                                .param("title", "수정된 제목")
                                .param("content", "수정된 내용")
                       )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 삭제 성공")
    void deleteBoard_성공() throws Exception {
        String requestJson = objectMapper.writeValueAsString(
                Map.of(
                        "password", "1234"
                      )
                                                            );
        mockMvc.perform(
                        delete("/api/boards/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                       )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("파일 다운로드 성공")
    void downloadFile_성공() throws Exception {
        Resource resource = new ByteArrayResource("hello".getBytes()) {
            @Override
            public String getFilename() {
                return "test.txt";
            }
        };

        given(fileService.downloadFile("test.txt"))
                .willReturn(resource);
        mockMvc.perform(
                        get("/api/boards/file/download/test.txt")
                       )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(header().string(
                        "Content-Disposition",
                        "attachment; filename*=UTF-8''test.txt"
                                          ));
    }
}