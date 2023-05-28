package iot.wifi_map.presentation;

import iot.wifi_map.application.RPService;
import iot.wifi_map.application.dto.response.FindPositionResponseDto;
import iot.wifi_map.application.dto.response.RegisterRPResponseDto;
import iot.wifi_map.application.dto.response.RemoveAllRpResponseDto;
import iot.wifi_map.application.dto.response.RemoveRpResponseDto;
import iot.wifi_map.presentation.dto.request.FindPositionRequest;
import iot.wifi_map.presentation.dto.request.RegisterRPRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rp")
@RequiredArgsConstructor
public class RPController {

    private final RPService RPService;

    @PostMapping()
    public ResponseEntity registerRP(@RequestBody RegisterRPRequest request){
        RegisterRPResponseDto res = RPService.registerRP(request.toServiceDto());
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @DeleteMapping("/{rpId}")
    public ResponseEntity removeRP(@PathVariable Long rpId){
        RemoveRpResponseDto res = RPService.removeRP(rpId);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity removeAllRP(){
        RPService.removeAllRP();
        return new ResponseEntity(new RemoveAllRpResponseDto(), HttpStatus.OK);
    }

    @PostMapping("/position")
    public ResponseEntity findPosition(@RequestBody FindPositionRequest request){
        FindPositionResponseDto res = RPService.findPosition(request.toServiceDto());
        return new ResponseEntity(res, HttpStatus.OK);
    }

}
