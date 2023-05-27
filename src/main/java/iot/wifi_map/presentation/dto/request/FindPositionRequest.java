package iot.wifi_map.presentation.dto.request;

import iot.wifi_map.application.dto.request.FindPositionRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class FindPositionRequest {

    List<FindPositionApInfoRequest> aps;

    @Builder
    public FindPositionRequest(List<FindPositionApInfoRequest> aps) {
        this.aps = aps;
    }

    public FindPositionRequestDto toServiceDto() {
        return new FindPositionRequestDto(
                aps.stream()
                        .map(ap -> ap.toServiceDto())
                        .collect(Collectors.toList())
        );
    }

}
