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

    @Builder
    public RegisterAPRequest(String ssid,
                             String bssid,
                             String rss) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
    }

    public RegisterAPRequestDto toServiceDto(){
        return new RegisterAPRequestDto(
                ssid,
                bssid,
                rss
        );
    }
}
