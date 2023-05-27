package iot.wifi_map.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindPositionApInfoRequestDto {

    private String ssid;

    private String bssid;

    private String rss;

    public FindPositionApInfoRequestDto(String ssid,
                                        String bssid,
                                        String rss) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
    }
}
