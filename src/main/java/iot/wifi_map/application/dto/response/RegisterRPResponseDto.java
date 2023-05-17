package iot.wifi_map.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRPResponseDto {

    private String message = "RP의 AP들이 저장되었습니다";

    private Long id;

    public RegisterRPResponseDto(Long id){
        this.id = id;
    }
}
