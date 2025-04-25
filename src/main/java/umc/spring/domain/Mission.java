package umc.spring.domain;

import jakarta.persistence.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.Solve;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
public class Mission extends BaseEntity {
    @Id
    @Column(name = "mission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;
    @Column(name = "store_id")
    private Long storeId;
    private Integer price;
    private Integer point;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Solve> SolveList = new ArrayList<>();
}

