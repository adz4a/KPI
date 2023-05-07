package com.program.service;

import com.program.exception.RoleException;
import com.program.model.Role;

import java.util.List;

public interface RoleService {

    Role createRole(Role role) throws RoleException;

    List<Role> findAll() throws RoleException;
}
