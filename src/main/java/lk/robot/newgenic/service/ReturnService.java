package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.user.request.ReturnRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReturnService {

    ResponseEntity<?> returnRequest(List<ReturnRequestDTO> returnRequestDTOList, long userId);

    ResponseEntity<?> getReturn(long userId);
}
