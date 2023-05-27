package iot.wifi_map.application.dto.request;

import iot.wifi_map.domain.RP;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterRPRequestDto {

    private Integer floor;

    private String rp;

    private String place;

    private List<RegisterAPRequestDto> registerAPRequestDtos;

    @Builder
    public RegisterRPRequestDto(Integer floor,
                                String rp,
                                String place,
                                List<RegisterAPRequestDto> registerAPRequestDtos) {
        this.floor = floor;
        this.rp = rp;
        this.place = place;
        this.registerAPRequestDtos = registerAPRequestDtos;
    }

    public RP toEntity(){
        return new RP(
                floor,
                rp,
                place,
                registerAPRequestDtos.stream()
                        .map(registerAPRequestDto -> registerAPRequestDto.toEntity())
                        .collect(Collectors.toList())
        );
    }
}
