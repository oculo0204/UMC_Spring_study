package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users extends BaseEntity {
    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "address_id", nullable = false)
    private Long addressId;
    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    private LocalDateTime birthdate;
    @Column(columnDefinition = "TEXT")
    private String image;
    @Enumerated(EnumType.STRING)
    private UserStatus status; // 일반, 탈퇴대기중
    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;
    private String email;
    @Column(name = "usertype", length = 15)
    private String userType;
    @Column(name = "wholepoint")
    private Long wholePoint;
    @Column(name = "point_status", length = 15)
    private String pointStatus;
    @Column(name = "point_date")
    private LocalDateTime pointDate;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
}