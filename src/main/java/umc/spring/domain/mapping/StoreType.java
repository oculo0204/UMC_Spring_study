package umc.spring.domain.mapping;

import jakarta.persistence.*;
import umc.spring.domain.Store;
import umc.spring.domain.StoreCategory;

@Entity
@Table(name = "store_type")
public class StoreType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_type_id")
    private Long storetypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_category_id")
    private StoreCategory storeCategory;
}
