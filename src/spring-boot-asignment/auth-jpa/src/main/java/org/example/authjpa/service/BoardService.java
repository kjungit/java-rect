package org.example.authjpa.service;

import lombok.RequiredArgsConstructor;
import org.example.authjpa.domain.entity.Board;
import org.example.authjpa.domain.repository.BoardRepository;
import org.example.authjpa.dto.BoardDeleteRequestDto;
import org.example.authjpa.dto.BoardUpdateRequestDto;
import org.example.authjpa.dto.BoardWriteRequestDto;
import org.example.authjpa.exception.BoardNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileService fileService;

    public List<Board> getBoardList( int page, int size ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        return boardRepository.findAll(pageable).getContent();
    }

    public int getTotalBoards() {
        return (int) boardRepository.count();
    }

    @Transactional(readOnly = true)
    public Board getBoardDetail( Long id ) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("[Board] 게시글을 찾을 수 없습니다. id=" + id));
    }

    @Transactional
    public void saveBoard( BoardWriteRequestDto dto ) {
        String filePath = fileService.storeFile(dto.getFile());
        boardRepository.save(
                Board.builder()
                        .userId(dto.getUserId())
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .filePath(filePath)
                        .created(LocalDateTime.now())
                        .build()
        );
    }

    @Transactional
    public void updateBoard(long id, BoardUpdateRequestDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        () -> new BoardNotFoundException("[Board] 수정할 게시글을 찾을 수 없습니다. id: " + id)
                );
        String filePath = board.getFilePath();
        if (dto.isFileFlag()) {
            fileService.deleteFile(filePath);
            filePath = fileService.storeFile(dto.getFile());
        }

        board.update(dto.getTitle(), dto.getContent(), filePath);
    }

    @Transactional
    public void deleteBoard( Long id, BoardDeleteRequestDto dto ) {
        if (!boardRepository.existsById(id)) {
            throw new BoardNotFoundException("[Board] 삭제할 게시글을 찾을 수 없습니다. id=" + id);
        }

    }


}
