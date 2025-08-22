package com.example.authdemo.mapper;

import com.example.authdemo.dto.RoleDto;
import com.example.authdemo.dto.UserResponseDto;
import com.example.authdemo.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserResponseDto toDto(User user) {
        Set<RoleDto> roleDtos = user.getRoles().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toSet());

        return new UserResponseDto.Builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roleDtos)
                .build();
    }
}
