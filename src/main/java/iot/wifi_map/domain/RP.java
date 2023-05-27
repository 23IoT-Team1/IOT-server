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

    private String rp;

    private String place;

    @OneToMany(mappedBy = "rp", cascade = CascadeType.ALL)
    private List<AP> aps;

    public RP(Integer floor, String rp, String place, List<AP> aps) {
        this.floor = floor;
        this.rp = rp;
        this.place = place;
        this.aps = aps;
        aps.forEach(ap -> ap.addRP(this));
    }
}
