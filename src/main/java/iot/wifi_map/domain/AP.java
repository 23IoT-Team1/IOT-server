package iot.wifi_map.domain;

import iot.wifi_map.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AP extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String ssid;

    private String bssid;

    @OneToMany(mappedBy = "ap", cascade = CascadeType.ALL)
    private List<FingerPrint> fingerPrints = new ArrayList<>();

    public AP(String ssid, String bssid) {
        this.ssid = ssid;
        this.bssid = bssid;
    }

    public void addFingerPrint(FingerPrint fingerPrint){
        this.fingerPrints.add(fingerPrint);
    }

}
