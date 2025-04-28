package umc.spring.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "store_categories")
public class StoreCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_category_id")
    private Long storeCategoryId;

    private String name;
}
