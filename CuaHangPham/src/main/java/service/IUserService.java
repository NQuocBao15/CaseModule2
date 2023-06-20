package service;

import models.ERole;
import models.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User login(String userName, String passWord, ERole role);
    void add(User newUser);
    boolean existById(long id);
    User findById(long id);
    boolean existsByUserName(String nameAccount);
    boolean existsByPhone(String phone);
    void update(User newUser);
    List<User> findByFullName(String value);
    void deleteById(long id);
}
