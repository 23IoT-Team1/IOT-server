package iot.wifi_map.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterFingerPrintResponseDto {

    private String message = "FingerPrint가 저장되었습니다";

    public RegisterFingerPrintResponseDto(String message) {
        this.message = message;
    }
}
