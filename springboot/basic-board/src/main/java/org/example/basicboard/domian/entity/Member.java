package org.example.basicboard.domian.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.basicboard.config.oauth2.AuthProvider;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String userId;

    @Column()
    private String password;

    @Column(name = "user_name", nullable = false, length = 20)
    private String username;

    @Column(length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.ROLE_USER;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(length = 20)
    private AuthProvider provider = AuthProvider.LOCAL;

    @Column(length = 100)
    private String providerId;

    public void updateSocialInfo(AuthProvider provider, String providerId) {
        this.provider = provider;
        this.providerId = providerId;
    }
}
