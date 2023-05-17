package iot.wifi_map.domain;

import iot.wifi_map.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RP extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer floor;

    private String gridPoint; // 4-1, 5-1 등등

    private String node; // 경로 or gridPoint 위치 이름

    @OneToMany(mappedBy = "rp", cascade = CascadeType.ALL)
    private List<AP> aps;

    public RP(Integer floor, String gridPoint, String node, List<AP> aps) {
        this.floor = floor;
        this.gridPoint = gridPoint;
        this.node = node;
        this.aps = aps;
        aps.forEach(ap -> ap.addRP(this));
    }
}
