package umc.spring.web.dto.mission;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder
public class SolveResponseDto {
    private Long solveId;
    private Long userId;
    private Long missionId;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime solveDate;
}
