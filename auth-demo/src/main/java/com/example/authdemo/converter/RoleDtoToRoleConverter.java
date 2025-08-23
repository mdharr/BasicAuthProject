package com.example.authdemo.converter;

import com.example.authdemo.dto.RoleDto;
import com.example.authdemo.entity.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleDtoToRoleConverter implements Converter<RoleDto, Role> {

    private AuthorityDtoToAuthorityConverter converter;

    public RoleDtoToRoleConverter(AuthorityDtoToAuthorityConverter converter) {
        this.converter = converter;
    }

    @Override
    public Role convert(RoleDto source) {
        Role role = new Role();
        role.setName(source.name());
        role.setAuthorities(source.authorities().stream().map(converter::convert).collect(Collectors.toSet()));
        return role;
    }
}
