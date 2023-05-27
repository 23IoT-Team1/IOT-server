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

    private String rp;

    private String place;

    private List<RegisterAPRequest> aps;

    @Builder
    public RegisterRPRequest(Integer floor,
                             String rp,
                             String place,
                             List<RegisterAPRequest> aps) {
        this.floor = floor;
        this.rp = rp;
        this.place = place;
        this.aps = aps;
    }

    public RegisterRPRequestDto toServiceDto(){
        return new RegisterRPRequestDto(
                floor,
                rp,
                place,
                aps.stream()
                        .map(RegisterAPRequest::toServiceDto)
                        .collect(Collectors.toList())
        );
    }
}
