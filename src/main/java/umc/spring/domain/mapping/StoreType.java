package umc.spring.domain.mapping;

import jakarta.persistence.*;

@Entity
@Table(name = "store_type")
public class StoreType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    private Long storeId;
    private Long categoryId;
}
