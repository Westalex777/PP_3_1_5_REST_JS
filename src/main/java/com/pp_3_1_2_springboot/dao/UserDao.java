package com.pp_3_1_2_springboot.dao;

import com.pp_3_1_2_springboot.model.User;

import java.util.List;

public interface UserDao {
    public void saveUser(User user);
    public List<User> getAllUsers();
    public void deleteUser(int id);
    public void updateUser(User user);
    public User getUser(int id);
}
