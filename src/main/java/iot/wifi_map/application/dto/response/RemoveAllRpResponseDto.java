package iot.wifi_map.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class RemoveAllRpResponseDto {

    private static final String ALL_PR_REMOVED = "모든 RP가 삭제되었습니다.";

    private final String message;

    public RemoveAllRpResponseDto() {
        this.message = ALL_PR_REMOVED;
    }
}
