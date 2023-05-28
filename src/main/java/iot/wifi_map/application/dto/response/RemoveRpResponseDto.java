package iot.wifi_map.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RemoveRpResponseDto {

    private Long removedRp;

    public RemoveRpResponseDto(Long removedRp) {
        this.removedRp = removedRp;
    }
}
