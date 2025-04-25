package umc.spring.domain.alarms;

import jakarta.persistence.*;
import umc.spring.domain.Mission;
import umc.spring.domain.Users;

@Entity
@Table(name = "mission_alarm")
public class MissionAlarm extends Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_alarm_id")
    private Long missionAlarmId;
    private Long alarmId;
    private Long missionId;
    private String title;
    private String body;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
}