package iot.wifi_map.application;

import iot.wifi_map.application.dto.request.FindPositionRequestDto;
import iot.wifi_map.application.dto.request.RegisterRPRequestDto;
import iot.wifi_map.application.dto.response.FindPositionResponseDto;
import iot.wifi_map.application.dto.response.RegisterRPResponseDto;
import iot.wifi_map.domain.RPRepository;
import iot.wifi_map.domain.RP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RPService {

    private final RPRepository repository;

    @Transactional
    public RegisterRPResponseDto registerRP(RegisterRPRequestDto dto){
        RP newRP = dto.toEntity();
        RP savedRP = repository.save(newRP);

        return new RegisterRPResponseDto(savedRP.getId());
    }

    @Transactional
    public FindPositionResponseDto findPosition(FindPositionRequestDto dto){

        // 받아온 현재 스캔된 ap의 정보(dto)로 위치를 찾는다.

        return new FindPositionResponseDto(); // 현재 위치의 rp와 place 값을 리턴한다
    }

}
