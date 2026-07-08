package org.example.basicboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String content;
    private MultipartFile file; // 새로 올린 파일(교체 했을때만 변경할 파일)
    private boolean fileFlag; // 첨부파일 변경이 있었는지 여부
}
