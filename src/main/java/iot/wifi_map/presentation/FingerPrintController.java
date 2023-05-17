package iot.wifi_map.presentation;

import iot.wifi_map.application.FingerPrintService;
import iot.wifi_map.application.dto.response.RegisterFingerPrintResponseDto;
import iot.wifi_map.presentation.dto.request.RegisterFingerPrintRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FingerPrintController {

    private final FingerPrintService fingerPrintService;

    @PostMapping("/save")
    public ResponseEntity registerFingerPrint(@RequestBody RegisterFingerPrintRequest fingerPrintRequest){
        RegisterFingerPrintResponseDto res = fingerPrintService.registerFingerPrint(fingerPrintRequest.toServiceDto());

        return new ResponseEntity(res, HttpStatus.OK);
    }

}
