package com.pp_3_1_2_springboot.service.role;

import com.pp_3_1_2_springboot.model.Role;
import com.pp_3_1_2_springboot.repositoties.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(int id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty())
            throw new NullPointerException("User not found!");
        return role.get();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
