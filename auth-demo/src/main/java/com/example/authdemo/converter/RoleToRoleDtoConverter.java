package com.example.authdemo.converter;

import com.example.authdemo.dto.RoleDto;
import com.example.authdemo.entity.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleToRoleDtoConverter implements Converter<Role, RoleDto> {

    private final AuthorityToAuthorityDtoConverter converter;

    public RoleToRoleDtoConverter(AuthorityToAuthorityDtoConverter converter) {
        this.converter = converter;
    }

    @Override
    public RoleDto convert(Role source) {
        return new RoleDto(
                source.getName(),
                source.getAuthorities().stream()
                        .map(converter::convert)
                        .collect(Collectors.toSet()));
    }
}
