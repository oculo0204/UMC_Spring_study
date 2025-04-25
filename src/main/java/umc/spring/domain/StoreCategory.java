package umc.spring.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "store_categories")
public class StoreCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    private String name;
}
