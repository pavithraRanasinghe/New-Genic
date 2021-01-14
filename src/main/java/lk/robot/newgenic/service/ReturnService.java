package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.Request.ReturnRequestDTO;
import org.springframework.http.ResponseEntity;

public interface ReturnService {

    ResponseEntity<?> returnRequest(ReturnRequestDTO returnRequestDTO,long userId);

    ResponseEntity<?> getReturn(long userId);
}
