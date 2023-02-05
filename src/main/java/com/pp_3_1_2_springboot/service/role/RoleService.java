package com.pp_3_1_2_springboot.service.role;

import com.pp_3_1_2_springboot.model.Role;

import java.util.List;

public interface RoleService {

    public Role getRole(int id);

    public List<Role> getAllRoles();

}
