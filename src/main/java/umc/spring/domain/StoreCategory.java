package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_category_id")
    private Long storeCategoryId;

    @Builder.Default
    private String name = "Unknown";  // 기본값 설정
}
