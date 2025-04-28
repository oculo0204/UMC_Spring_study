package umc.spring.domain.mapping;

import jakarta.persistence.*;
import umc.spring.domain.PreferCategory;
import umc.spring.domain.Users;
import umc.spring.domain.common.BaseEntity;

@Entity
@Table(name = "prefer")
public class Prefer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefer_id")
    private Long preferId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prefer_category_id")
    private PreferCategory preferCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;
}