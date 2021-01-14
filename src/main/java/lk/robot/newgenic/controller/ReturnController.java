package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.Request.ReturnRequestDTO;
import lk.robot.newgenic.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/return")
public class ReturnController {

    private ReturnService returnService;

    @Autowired
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @PostMapping
    public ResponseEntity<?> returnRequest(@RequestBody ReturnRequestDTO returnRequestDTO, Principal principal){
        long userId = Long.parseLong(principal.getName());
        return returnService.returnRequest(returnRequestDTO,userId);
    }
}
