package iot.wifi_map.presentation.dto.request;

import iot.wifi_map.application.dto.request.FindPositionApInfoRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindPositionApInfoRequest {

    private String ssid;

    private String bssid;

    private String rss;

    @Builder
    public FindPositionApInfoRequest(String ssid,
                                     String bssid,
                                     String rss) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
    }

    public FindPositionApInfoRequestDto toServiceDto(){
        return new FindPositionApInfoRequestDto(ssid, bssid, rss);
    }

}
