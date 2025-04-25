package umc.spring.domain.alarms;

import jakarta.persistence.*;

@Entity
@Table(name = "notice_alarm")
public class NoticeAlarm extends Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noice_id")
    private Long noiceId;
    private Long alarmId;
    private String title;
    private String body;
    private String image;
}
