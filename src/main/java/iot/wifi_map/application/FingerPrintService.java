package iot.wifi_map.application;

import iot.wifi_map.application.dto.request.RegisterRPRequestDto;
import iot.wifi_map.application.dto.response.RegisterRPResponseDto;
import iot.wifi_map.domain.RPRepository;
import iot.wifi_map.domain.RP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FingerPrintService {

    private final RPRepository repository;

    @Transactional
    public RegisterRPResponseDto registerRP(RegisterRPRequestDto dto){
        RP newRP = dto.toEntity();
        RP savedRP = repository.save(newRP);

        return new RegisterRPResponseDto(savedRP.getId());
    }

}
