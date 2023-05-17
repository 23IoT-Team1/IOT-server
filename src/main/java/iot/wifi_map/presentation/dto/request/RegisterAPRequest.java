package iot.wifi_map.presentation.dto.request;

import iot.wifi_map.application.dto.request.RegisterAPRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterAPRequest {

    private String ssid;

    private String bssid;

    private String rss;

    private String rtt;

    @Builder
    public RegisterAPRequest(String ssid,
                             String bssid,
                             String rss,
                             String rtt) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
        this.rtt = rtt;
    }

    public RegisterAPRequestDto toServiceDto(){
        return new RegisterAPRequestDto(
                ssid,
                bssid,
                rss,
                rtt
        );
    }
}
