package com.logix.controller;

import com.logix.persistence.dto.UserDto;
import com.logix.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
@RequestMapping("regime-security")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    //--------------------------------------------------------------------------------------------->Registering New User
    @RequestMapping(value="/register", method= RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody @Valid UserDto userDto){
             registrationService.handleNewAccount(userDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
