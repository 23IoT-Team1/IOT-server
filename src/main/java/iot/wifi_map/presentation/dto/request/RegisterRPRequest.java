package iot.wifi_map.presentation.dto.request;

import iot.wifi_map.application.dto.request.RegisterRPRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterRPRequest {

    private Integer floor;

    private String gridPoint;

    private String node;

    private List<RegisterAPRequest> aps;

    @Builder
    public RegisterRPRequest(Integer floor,
                             String gridPoint,
                             String node,
                             List<RegisterAPRequest> aps) {
        this.floor = floor;
        this.gridPoint = gridPoint;
        this.node = node;
        this.aps = aps;
    }

    public RegisterRPRequestDto toServiceDto(){
        return new RegisterRPRequestDto(
                floor,
                gridPoint,
                node,
                aps.stream()
                        .map(RegisterAPRequest::toServiceDto)
                        .collect(Collectors.toList())
        );
    }
}
