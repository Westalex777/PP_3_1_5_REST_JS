package com.pp_3_1_2_springboot.service.user;

import com.pp_3_1_2_springboot.dao.UserDao;
import com.pp_3_1_2_springboot.model.User;
import com.pp_3_1_2_springboot.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.getUser(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");
        return new UserDetailsImpl(user.get());
    }
}
