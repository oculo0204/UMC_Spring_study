package umc.spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviewimg")
public class ReviewImg {
    @Id
    @Column(name = "image_id")
    private String imageId;

    private Long reviewId;

    @Column(name = "image_url")
    private String imageUrl;

}