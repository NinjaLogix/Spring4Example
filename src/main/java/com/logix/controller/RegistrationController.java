package com.logix.controller;

import com.logix.model.Details;
import com.logix.model.User;
import com.logix.model.UserRole;
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

import java.util.HashSet;
import java.util.Set;

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
     *
     * Setup API to send back http status 418 if something is wrong, basically this server can't brew coffee because it's a teapot
     * https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/418
     * TODO - need to send back any errors in the ResponseBody
     * @param userDto
     * @return
     */
    @RequestMapping(value="/register", method= RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody @Valid UserDto userDto){
        try {
            registrationService.createAccount(userDto); //return UserDto in the ResponseBody for the client
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<String>("Errors with user", HttpStatus.I_AM_A_TEAPOT);
        }
    }

    //--------------------------------------------------------------------------------------------------->Test Endpoints
    @RequestMapping(value="/jsonTest", method=RequestMethod.POST)
    public ResponseEntity testUser(@RequestBody User user){
        log.info(user.toString());
        userDao.create(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value="/entityTest", method=RequestMethod.GET)
    public ResponseEntity entityTest(){
        User user = new User();
        UserRole role = new UserRole();
        Details details = new Details();
        Set<UserRole> roles = new HashSet<>();

        user.setLname("last");
        user.setFname("first");
        user.setUname("Anderson123");
        user.setEmail("something.something23@gmail.com");
        user.setPass("password");
        user.setCpass("password");

        role.setRole("ADMIN");
        roles.add(role);
        user.setRoles(roles);

        details.setAcctNotExpired(true);
        details.setAcctNotLocked(true);
        details.setCredsNotExpired(true);
        details.setEnabled(true);
        user.setDetails(details);

        userDao.create(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}