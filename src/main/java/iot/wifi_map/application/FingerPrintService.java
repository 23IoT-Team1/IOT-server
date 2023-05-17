package iot.wifi_map.application;

import iot.wifi_map.application.dto.request.RegisterFingerPrintRequestDto;
import iot.wifi_map.application.dto.response.RegisterFingerPrintResponseDto;
import iot.wifi_map.domain.FingerPrint;
import iot.wifi_map.domain.FingerPrintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FingerPrintService {

    private final FingerPrintRepository fingerPrintRepository;

    @Transactional
    public RegisterFingerPrintResponseDto registerFingerPrint(RegisterFingerPrintRequestDto dto){
        FingerPrint saveFingerPrint = dto.toEntity();
        saveFingerPrint.setFingerPrint();
        fingerPrintRepository.save(saveFingerPrint);
        return new RegisterFingerPrintResponseDto();
    }

}
