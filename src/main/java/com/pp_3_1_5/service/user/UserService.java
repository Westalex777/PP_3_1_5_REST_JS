package com.pp_3_1_5.service.user;

import com.pp_3_1_5.model.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);

    public List<User> getAllUsers();

    public void deleteUser(int id);

    public void updateUser(User user);

    public User getUser(int id);
}
