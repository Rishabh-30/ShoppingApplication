package org.niit.UserAuthService.service;

import org.niit.UserAuthService.domain.User;
import org.niit.UserAuthService.exception.UserAlreadyExistException;
import org.niit.UserAuthService.exception.UserNotFoundException;

public interface UserService {
    User addUser(User user) throws UserAlreadyExistException;
    User findByUserIdAndPassword(String userId,String password) throws UserNotFoundException;
}
