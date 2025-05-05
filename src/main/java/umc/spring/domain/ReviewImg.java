package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviewimg")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 클래스에 @Builder 추가
public class ReviewImg {
    @Id
    @Column(name = "image_id")
    private String imageId;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
