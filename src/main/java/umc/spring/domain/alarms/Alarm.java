package umc.spring.domain.alarms;

import jakarta.persistence.*;
import umc.spring.domain.Users;
import umc.spring.domain.common.BaseEntity;

@Entity
@Table(name = "alarm")
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long alarmId;
    private Long userId;
    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;
}
