package org.niit.UserAuthService.service;

import org.niit.UserAuthService.domain.User;
import org.niit.UserAuthService.exception.UserAlreadyExistException;
import org.niit.UserAuthService.exception.UserNotFoundException;
import org.niit.UserAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) throws UserAlreadyExistException {
        if(userRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistException();
        }
        return userRepository.save(user);
    }

    @Override
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
        User user = userRepository.findByUserIdAndPassword(userId,password);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }
}