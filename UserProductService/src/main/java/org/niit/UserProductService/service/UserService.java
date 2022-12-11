package org.niit.UserProductService.service;

import org.niit.UserProductService.domain.Product;
import org.niit.UserProductService.domain.User;
import org.niit.UserProductService.exception.ProductNotFoundException;
import org.niit.UserProductService.exception.UserAlreadyExistException;
import org.niit.UserProductService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User addUser(User user) throws UserAlreadyExistException;
    User addProductForUser(String userId, Product product) throws UserNotFoundException;
    User deleteProductForUser(String userId,int productId) throws ProductNotFoundException, UserNotFoundException;
    List<Product> getProductForUser(String userId) throws UserNotFoundException;
}
