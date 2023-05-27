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

    @Builder
    public RegisterAPRequestDto(String ssid,
                                String bssid,
                                String rss) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
    }

    public AP toEntity(){
        return new AP(
                ssid,
                bssid,
                rss
        );
    }
}
