package iot.wifi_map.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRPResponseDto {

    private String message = "해당 RP에 대한 AP 정보들이 저장되었습니다";

    private Long RP_id;

    public RegisterRPResponseDto(Long id){
        this.RP_id = id;
    }
}
