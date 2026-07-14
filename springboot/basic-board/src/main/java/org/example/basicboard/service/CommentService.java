package org.example.basicboard.service;

import lombok.RequiredArgsConstructor;
import org.example.basicboard.domian.entity.Board;
import org.example.basicboard.domian.entity.Comment;
import org.example.basicboard.domian.repository.BoardRepository;
import org.example.basicboard.domian.repository.CommentRepository;
import org.example.basicboard.dto.CommentWriteRequestDto;
import org.example.basicboard.exception.BoardNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void addComment(Long boardId, CommentWriteRequestDto dto) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다. id = " + boardId));

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .userId(dto.getUserId())
                .board(board)
                .created(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
    }
}
