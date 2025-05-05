package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.StoreType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 클래스에 @Builder 추가
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId; // 키

    private String name;

    @Column(name = "business_hours")
    private LocalDateTime businessHours;

    @Column(name = "star_sum")
    private BigDecimal starSum;

    private String region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default  // @Builder.Default 추가
    private List<StoreType> storeTypes = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default  // @Builder.Default 추가
    private List<Review> reviews = new ArrayList<>();
    
    //읽기
    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", starSum=" + starSum +
                ", businessHours='" + businessHours + '\'' +
                '}';
    }
}
