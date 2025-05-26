package umc.spring.web.dto.mission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSolveRequestDto {
    @NotNull
    private Long userId;
}
