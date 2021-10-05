package com.alkemy.java.service.impl;

import com.alkemy.java.dto.RoleDto;
import com.alkemy.java.model.Role;
import com.alkemy.java.repository.RoleRepository;
import com.alkemy.java.service.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public Role registerRole (RoleDto roleRequest){

        Role role = mapToEntity(roleRequest);
        role.setName(roleRequest.getName());
        return roleRepository.save(role);
    }

    private Role mapToEntity(RoleDto roleRequest) {
        return mapper.map(roleRequest, Role.class);
    }
}
