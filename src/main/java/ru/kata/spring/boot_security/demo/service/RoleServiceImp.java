package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        saveRole(new Role(1L, "ADMIN"));
        saveRole(new Role(2L, "USER"));
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Set<Role> getRole(List<Long> id) {
        Set<Role> roles = new HashSet<>();
        id.forEach(x -> roles.add(roleRepository.findById(x).get()));
        return roles;
    }

    public List<Role> listAll() {
        return roleRepository.findAll();
    }
}
