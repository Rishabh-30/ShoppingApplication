package org.niit.UserProductService.service;

import org.niit.UserProductService.domain.Product;
import org.niit.UserProductService.domain.User;
import org.niit.UserProductService.exception.ProductNotFoundException;
import org.niit.UserProductService.exception.UserAlreadyExistException;
import org.niit.UserProductService.exception.UserNotFoundException;
import org.niit.UserProductService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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
    public User addProductForUser(String userId, Product product) throws UserNotFoundException {
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findByUserId(userId);
        if(user.getProductList() == null){
            user.setProductList(Arrays.asList(product));
        }else{
            List<Product> products = user.getProductList();
            products.add(product);
            user.setProductList(products);
        }
        return userRepository.save(user);
    }

    @Override
    public User deleteProductForUser(String userId,int productId) throws ProductNotFoundException, UserNotFoundException {
        boolean productIdIsPresent = false;
        if(userRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        List<Product> products = user.getProductList();
        productIdIsPresent = products.removeIf(x->x.getProductId()==productId);
        if(!productIdIsPresent)
        {
            throw new ProductNotFoundException();
        }
        user.setProductList(products);
        return userRepository.save(user);

    }

    @Override
    public List<Product> getProductForUser(String userId) throws UserNotFoundException {
        if(userRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userRepository.findById(userId).get().getProductList();

    }
}