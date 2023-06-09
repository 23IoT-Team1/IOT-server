package iot.wifi_map.domain;

import iot.wifi_map.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AP extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String ssid;

    private String bssid;

    private String rss;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private RP rp;

    public AP(String ssid, String bssid, String rss) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
    }

    public void addRP(RP rp){
        this.rp = rp;
    }
}
