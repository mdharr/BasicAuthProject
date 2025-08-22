package com.example.authdemo.service;

import com.example.authdemo.dto.AuthorityDto;
import com.example.authdemo.dto.RoleDto;
import com.example.authdemo.dto.UserDto;
import com.example.authdemo.dto.UserResponseDto;
import com.example.authdemo.entity.Authority;
import com.example.authdemo.entity.Role;
import com.example.authdemo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {
    UserResponseDto getUserById(Long id);
    User register(UserDto userDto);
    UserDetails loadUserByUsername(String username);
}
