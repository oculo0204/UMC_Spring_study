package umc.spring.domain.alarms;

import jakarta.persistence.*;

@Entity
@Table(name = "marketing_alarm")
public class MarketingAlarm extends Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marketing_id")
    private Long marketingId;
    private Long alarmId;
    private String title;
    private String body;
    private String image;

}
