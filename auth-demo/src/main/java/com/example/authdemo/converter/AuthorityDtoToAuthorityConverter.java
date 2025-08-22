package com.example.authdemo.converter;

import com.example.authdemo.dto.AuthorityDto;
import com.example.authdemo.entity.Authority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorityDtoToAuthorityConverter implements Converter<AuthorityDto, Authority> {

    @Override
    public Authority convert(AuthorityDto source) {
        Authority authority = new Authority();
        authority.setName(source.name());
        return authority;
    }
}
