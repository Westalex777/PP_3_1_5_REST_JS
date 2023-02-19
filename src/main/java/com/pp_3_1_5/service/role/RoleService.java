package com.pp_3_1_5.service.role;

import com.pp_3_1_5.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRole(int id);

}
