package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.PreferCategory;
import umc.spring.domain.Users;
import umc.spring.domain.common.BaseEntity;

@Entity
@Table(name = "prefer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 클래스에 @Builder 추가
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
