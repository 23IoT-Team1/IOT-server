package iot.wifi_map.application.dto.request;

import iot.wifi_map.presentation.dto.request.FindPositionApInfoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FindPositionRequestDto {

    List<FindPositionApInfoRequestDto> aps; // 현재 위치에서 스캔된 ap 정보들

    public FindPositionRequestDto(List<FindPositionApInfoRequestDto> aps) {
        this.aps = aps;
    }
}
