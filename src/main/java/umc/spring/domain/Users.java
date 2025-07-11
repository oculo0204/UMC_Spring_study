package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.UserStatus;
import umc.spring.domain.mapping.Prefer;
import umc.spring.domain.mapping.Solve;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "usertype", length = 15)
    private String userType; //GENERAL, SHOPOWNER

    @Column(name = "wholepoint")
    private Long wholePoint;

    @Column(name = "point_status", length = 15)
    private String pointStatus;  //NORMAL, CONSUMED

    @Column(name = "point_date")
    private LocalDateTime pointDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private SocialType socialType;

    // password
    public void encodePassword(String password) {
        this.password = password;
    }

    // 매핑

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default  // @Builder.Default 추가
    private List<Solve> solves = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default  // @Builder.Default 추가
    private List<Prefer> prefers = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default  // @Builder.Default 추가
    private List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default  // @Builder.Default 추가
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default  // @Builder.Default 추가
    private List<Comment> comments = new ArrayList<>();
}
