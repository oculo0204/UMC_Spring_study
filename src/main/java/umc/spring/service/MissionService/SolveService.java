package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.Users;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.Solve;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.MissionRepository.SolveRepository;
import umc.spring.repository.UsersRepository.UsersRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.mission.CreateSolveRequestDto;
import umc.spring.web.dto.mission.SolveResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolveService {
    private final SolveRepository solveRepository;
    private final UsersRepository usersRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public SolveResponseDto createSolve(Long missionId, CreateSolveRequestDto request) {
        Users user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found id=" + request.getUserId()));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found id=" + missionId));

        Solve solve = Solve.builder()
                .users(user)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .startDate(LocalDateTime.now())
                .build();

        solve = solveRepository.save(solve);

        return SolveResponseDto.builder()
                .solveId(solve.getSolveId())
                .userId(user.getUserId())
                .missionId(mission.getMissionId())
                .status(solve.getStatus().name())
                .startDate(solve.getStartDate())
                .solveDate(solve.getSolveDate())
                .build();
    }
    public Map<Long, Solve> getSolvesForMissions(List<Long> missionIds) {
        List<Solve> solves = solveRepository.findByMission_MissionIdIn(missionIds);
        return solves.stream()
                .collect(Collectors.toMap(s -> s.getMission().getMissionId(), s -> s));
    }}

