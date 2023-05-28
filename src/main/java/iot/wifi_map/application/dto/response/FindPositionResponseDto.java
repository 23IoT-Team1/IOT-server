package iot.wifi_map.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindPositionResponseDto {

    private String rp;

    public FindPositionResponseDto(String position) {
        this.rp = position;
    }
}
