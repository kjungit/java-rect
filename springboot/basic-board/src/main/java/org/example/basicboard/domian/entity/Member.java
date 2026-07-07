package org.example.basicboard.domian.entity;

import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

// 회원 엔티티 - member 테이블과 매핑
@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
// JPA는 기본 생성자가 필요하지만 외부에서 무분별한 생성을 막는다.
@NoArgsConstructor(access = PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String userId;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(name = "user_name", nullable = false, length = 20)
    private String username;

}
