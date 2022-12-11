package org.niit.UserAuthService.repository;

import org.niit.UserAuthService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,String> {
    User findByUserIdAndPassword(String userId,String password);
}
