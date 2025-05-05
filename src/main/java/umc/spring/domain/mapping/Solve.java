package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Mission;
import umc.spring.domain.Users;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "solve")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 클래스에 @Builder 추가
public class Solve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solve_id")
    private Long solveId;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "solve_date")
    private LocalDateTime solveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
}
