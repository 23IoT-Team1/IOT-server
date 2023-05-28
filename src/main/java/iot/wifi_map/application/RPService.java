package iot.wifi_map.application;

import iot.wifi_map.application.dto.request.FindPositionRequestDto;
import iot.wifi_map.application.dto.request.RegisterRPRequestDto;
import iot.wifi_map.application.dto.response.FindPositionResponseDto;
import iot.wifi_map.application.dto.response.RegisterRPResponseDto;
import iot.wifi_map.application.dto.response.RemoveRpResponseDto;
import iot.wifi_map.domain.AP;
import iot.wifi_map.domain.APRepository;
import iot.wifi_map.domain.RPRepository;
import iot.wifi_map.domain.RP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RPService {

    private final RPRepository rpRepository;

    private final APRepository apRepository;

    private final Similarity similarity;

    @Transactional
    public RegisterRPResponseDto registerRP(RegisterRPRequestDto dto){
        RP newRP = dto.toEntity();
        RP savedRP = rpRepository.save(newRP);
        return new RegisterRPResponseDto(savedRP.getId());
    }

    @Transactional
    public RemoveRpResponseDto removeRP(Long rpId){
        rpRepository.deleteById(rpId);
        return new RemoveRpResponseDto(rpId);
    }

    public FindPositionResponseDto findPosition(FindPositionRequestDto dto){

        Integer numberOfAp = apRepository.getNumberOfAp();
        List<AP> aps = apRepository.findAll(); // 꼭 등록된 순서대로 가져와야하나?
        List<ap> dataSet = aps.stream()
                .map(ap -> new ap(
                        ap.getSsid(),
                        ap.getBssid(),
                        ap.getRss(),
                        ap.getRp().getRp(),
                        ap.getRp().getPlace()
                        )
                )
                .collect(Collectors.toList());

        String position = similarity.findPosition(dataSet, dto, numberOfAp);

        return new FindPositionResponseDto(position); // 현재 위치의 rp와 place 값을 리턴한다
    }
}
