package lk.robot.newgenic.service.user;

import lk.robot.newgenic.dto.user.Request.ReturnRequestDTO;
import org.springframework.http.ResponseEntity;

public interface ReturnService {

    ResponseEntity<?> returnRequest(ReturnRequestDTO returnRequestDTO,long userId);

    ResponseEntity<?> getReturn(long userId);
}
