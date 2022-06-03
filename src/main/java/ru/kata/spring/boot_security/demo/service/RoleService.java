package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void saveRole(Role user);

    Set<Role> getRole(List<Long> id);

    List<Role> listAll();
}
