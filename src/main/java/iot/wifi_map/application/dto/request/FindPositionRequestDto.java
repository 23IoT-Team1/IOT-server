package iot.wifi_map.application.dto.request;

import iot.wifi_map.presentation.dto.request.FindPositionApInfoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FindPositionRequestDto {

    List<FindPositionApInfoRequestDto> aps;

    public FindPositionRequestDto(List<FindPositionApInfoRequestDto> aps) {
        this.aps = aps;
    }
}
