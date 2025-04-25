package umc.spring.domain.mapping;

import jakarta.persistence.*;
import umc.spring.domain.Users;
import umc.spring.domain.common.BaseEntity;

@Entity
@Table(name = "prefer")
public class Prefer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefer_id")
    private Long preferId;

    private Long userId;
    private Long categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;
}