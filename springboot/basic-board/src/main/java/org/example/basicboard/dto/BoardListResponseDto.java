package org.example.basicboard.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.basicboard.domian.entity.Board;

import java.util.List;

@Getter
@Builder
public class BoardListResponseDto {
    private List<Board> boards;
    private boolean last;
    private int totalPages;
}
