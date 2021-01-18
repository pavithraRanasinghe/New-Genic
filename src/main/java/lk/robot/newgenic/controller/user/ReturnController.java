package lk.robot.newgenic.controller.user;

import lk.robot.newgenic.dto.Request.ReturnRequestDTO;
import lk.robot.newgenic.entity.ReturnEntity;
import lk.robot.newgenic.service.user.ReturnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/return")
public class ReturnController {

    private ReturnService returnService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnController.class);

    @Autowired
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @PostMapping
    public ResponseEntity<?> returnRequest(@RequestBody ReturnRequestDTO returnRequestDTO,
                                           Principal principal) {
        LOGGER.info("request - registeredUser | returnRequest | returnRequest: {} | userId: {} |",returnRequestDTO,principal.getName());
        long userId = Long.parseLong(principal.getName());
        ResponseEntity<?> responseEntity = returnService.returnRequest(returnRequestDTO, userId);
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
