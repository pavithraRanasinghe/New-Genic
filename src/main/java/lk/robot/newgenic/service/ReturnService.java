package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.request.ReturnRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReturnService {

    ResponseEntity<?> returnRequest(List<ReturnRequestDTO> returnRequestDTOList, String userId);

    ResponseEntity<?> getReturn(String userId);
}
