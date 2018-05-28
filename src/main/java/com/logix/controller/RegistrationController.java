package com.logix.controller;

import com.logix.model.User;
import com.logix.persistence.dao.UserDao;
import com.logix.persistence.dto.UserDto;
import com.logix.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
@RequestMapping("security")
public class RegistrationController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RegistrationService registrationService;

    @Autowired
    private UserDao userDao;

    //--------------------------------------------------------------------------------------------->Registering New User

    /**
     * Test this endpoint with a JSON request formatted for the UserDTO object. Example is as follows:
     * {
     * 	"firstName":"Test",
     * 	"lastName":"User",
     * 	"email":"some.thing@user.mail.com",
     * 	"pass":"pass",
     * 	"cpass":"pass"
     * }
     *
     * Email and password will be validated before any data actions.
     * TODO - need to send back any errors in the ResponseBody
     * @param userDto
     * @return
     */
    @RequestMapping(value="/register", method= RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody @Valid UserDto userDto){
        registrationService.createAccount(userDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //--------------------------------------------------------------------------------------------------->Test Endpoints
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ResponseEntity testUser(@RequestBody User user){
        log.info(user.toString());
        userDao.create(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}