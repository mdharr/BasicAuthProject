package com.example.authdemo.mapper;

import com.example.authdemo.dto.AuthorityDto;
import com.example.authdemo.dto.RoleDto;
import com.example.authdemo.entity.Authority;
import com.example.authdemo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    private final AuthorityMapper authorityMapper;

    public RoleMapper(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    public RoleDto toDto(Role role) {
        Set<AuthorityDto> roleDtos = role.getAuthorities().stream()
                .map(authorityMapper::toDto)
                .collect(Collectors.toSet());
        return new RoleDto(role.getName(), roleDtos);
    }
}
