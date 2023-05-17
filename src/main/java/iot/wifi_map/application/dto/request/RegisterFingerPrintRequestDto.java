package iot.wifi_map.application.dto.request;

import iot.wifi_map.domain.AP;
import iot.wifi_map.domain.FingerPrint;
import iot.wifi_map.domain.RP;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterFingerPrintRequestDto {

    private Integer floor;

    private String gridPoint;

    private String node;

    private String ssid;

    private String bssid;

    private String rss;

    private String rtt;

    public RegisterFingerPrintRequestDto(Integer floor, String gridPoint, String node, String ssid, String bssid, String rss, String rtt) {
        this.floor = floor;
        this.gridPoint = gridPoint;
        this.node = node;
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
        this.rtt = rtt;
    }

    public FingerPrint toEntity(){
        return new FingerPrint(
                rss, rtt,
                new RP(floor, gridPoint, node),
                new AP(ssid,bssid));
    }
}
