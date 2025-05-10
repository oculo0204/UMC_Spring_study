package umc.spring.domain;

import jakarta.persistence.*;
import umc.spring.domain.common.BaseEntity;
import lombok.*;

@Entity
@Table(name = "prefer_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 클래스에 @Builder 추가
public class PreferCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prefer_category_id")
    private Long categoryId; //키

    private String name;
}
