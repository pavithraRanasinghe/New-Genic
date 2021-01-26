package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.user.request.ReturnRequestDTO;
import org.springframework.http.ResponseEntity;

public interface ReturnService {

    ResponseEntity<?> returnRequest(ReturnRequestDTO returnRequestDTO,long userId);

    ResponseEntity<?> getReturn(long userId);
}
