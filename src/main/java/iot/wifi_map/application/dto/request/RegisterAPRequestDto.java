package iot.wifi_map.application.dto.request;

import iot.wifi_map.domain.AP;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterAPRequestDto {

    private String ssid;

    private String bssid;

    private String rss;

    private String rtt;

    @Builder
    public RegisterAPRequestDto(String ssid,
                                String bssid,
                                String rss,
                                String rtt) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
        this.rtt = rtt;
    }

    public AP toEntity(){
        return new AP(
                ssid,
                bssid,
                rss,
                rtt
        );
    }
}
