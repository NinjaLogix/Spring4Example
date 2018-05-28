package com.logix.service;

import com.logix.persistence.dto.UserDto;
import com.logix.model.User;

public interface RegistrationService {
    User createAccount(UserDto userDto);
}
