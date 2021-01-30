package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.user.request.ReturnRequestDTO;
import lk.robot.newgenic.service.ReturnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/return")
public class ReturnController {

    private ReturnService returnService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnController.class);

    @Autowired
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @PostMapping
    public ResponseEntity<?> returnRequest(@RequestBody List<ReturnRequestDTO> returnRequestDTOList,
                                           Principal principal) {
        LOGGER.info("request - registeredUser | returnRequest | returnRequestList: {} | userId: {} |",returnRequestDTOList,principal.getName());
        long userId = Long.parseLong(principal.getName());
        ResponseEntity<?> responseEntity = returnService.returnRequest(returnRequestDTOList, userId);
        LOGGER.info("request - registeredUser | searchProducts | r: {} | userId: {}",responseEntity.getBody().toString());
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<?> getReturn(Principal principal) {
        LOGGER.info("request - registeredUser | getReturnOrders | userID: {}",principal.getName());
        long userId = Long.parseLong(principal.getName());
        ResponseEntity<?> returnOrders = returnService.getReturn(userId);
        LOGGER.info("response - registeredUser | getReturnOrders | returnOrders: {}",returnOrders.getStatusCode());
        return returnOrders;
    }
}
