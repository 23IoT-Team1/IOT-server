package iot.wifi_map.presentation.dto.request;

import iot.wifi_map.application.dto.request.RegisterFingerPrintRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterFingerPrintRequest {

    private Integer floor;

    private String gridPoint;

    private String node;

    private String ssid;

    private String bssid;

    private String rss;

    private String rtt;

    public RegisterFingerPrintRequest(Integer floor, String gridPoint, String node, String ssid, String bssid, String rss, String rtt) {
        this.floor = floor;
        this.gridPoint = gridPoint;
        this.node = node;
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
        this.rtt = rtt;
    }

    public RegisterFingerPrintRequestDto toServiceDto(){

        return new RegisterFingerPrintRequestDto(floor,gridPoint,node,ssid,bssid,rss,rtt);
    }

}
