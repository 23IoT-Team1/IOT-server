package iot.wifi_map.domain;

import iot.wifi_map.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FingerPrint extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String rss;

    private String rtt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private RP rp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private AP ap;

    public FingerPrint(String rss, String rtt, RP rp, AP ap) {
        this.rss = rss;
        this.rtt = rtt;
        this.rp = rp;
        this.ap = ap;
    }

    public void setFingerPrint(){
        this.rp.addFingerPrint(this);
        this.ap.addFingerPrint(this);
    }

}
