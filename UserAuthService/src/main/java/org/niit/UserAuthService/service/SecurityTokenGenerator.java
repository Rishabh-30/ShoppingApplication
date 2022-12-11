package org.niit.UserAuthService.service;

import org.niit.UserAuthService.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
