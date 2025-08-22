package com.example.authdemo.converter;

import com.example.authdemo.dto.AuthorityDto;
import com.example.authdemo.entity.Authority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorityToAuthorityDtoConverter implements Converter<Authority, AuthorityDto> {

    @Override
    public AuthorityDto convert(Authority source) {
        return new AuthorityDto(source.getName());
    }
}
