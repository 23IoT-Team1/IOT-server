package iot.wifi_map.presentation;

import iot.wifi_map.application.RPService;
import iot.wifi_map.application.dto.response.FindPositionResponseDto;
import iot.wifi_map.application.dto.response.RegisterRPResponseDto;
import iot.wifi_map.presentation.dto.request.FindPositionRequest;
import iot.wifi_map.presentation.dto.request.RegisterRPRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RPController {

    private final RPService RPService;

    @PostMapping("/rp")
    public ResponseEntity registerRP(@RequestBody RegisterRPRequest request){
        RegisterRPResponseDto res = RPService.registerRP(request.toServiceDto());
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/position")
    public ResponseEntity findPosition(@RequestBody FindPositionRequest request){
        FindPositionResponseDto res = RPService.findPosition(request.toServiceDto());
        return new ResponseEntity(res, HttpStatus.OK);
    }

}
