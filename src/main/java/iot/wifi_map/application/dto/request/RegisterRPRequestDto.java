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

    private String gridPoint;

    private String node;

    private List<RegisterAPRequestDto> registerAPRequestDtos;

    @Builder
    public RegisterRPRequestDto(Integer floor,
                                String gridPoint,
                                String node,
                                List<RegisterAPRequestDto> registerAPRequestDtos) {
        this.floor = floor;
        this.gridPoint = gridPoint;
        this.node = node;
        this.registerAPRequestDtos = registerAPRequestDtos;
    }

    public RP toEntity(){
        return new RP(
                floor,
                gridPoint,
                node,
                registerAPRequestDtos.stream()
                        .map(registerAPRequestDto -> registerAPRequestDto.toEntity())
                        .collect(Collectors.toList())
        );
    }
}
