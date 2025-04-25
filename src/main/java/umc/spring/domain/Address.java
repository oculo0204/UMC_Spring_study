package umc.spring.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    private Integer postcode;
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;
}
