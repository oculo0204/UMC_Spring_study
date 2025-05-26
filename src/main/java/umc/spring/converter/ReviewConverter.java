package umc.spring.converter;

import lombok.Getter;
import umc.spring.domain.*;
import umc.spring.web.dto.review.ReviewRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ReviewConverter {

    public static Review toMakeResultDTO(ReviewRequestDto.makeDto request) {

        Review newReview = Review.builder()
                .detail(request.getDetail())
                .starRate(request.getStarRate())
                .reviewImgs(new ArrayList<>())
                .build();
        return newReview;
    }
}

