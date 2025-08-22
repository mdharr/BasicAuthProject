package com.example.authdemo.mapper;

import com.example.authdemo.dto.AuthorityDto;
import com.example.authdemo.entity.Authority;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper {

    public AuthorityDto toDto(Authority authority) {
        return new AuthorityDto(authority.getName());
    }
}
