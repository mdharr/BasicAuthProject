package com.example.authdemo.converter;

import com.example.authdemo.dto.UserDto;
import com.example.authdemo.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    private final RoleToRoleDtoConverter converter;

    public UserToUserDtoConverter(RoleToRoleDtoConverter converter) {
        this.converter = converter;
    }

    @Override
    public UserDto convert(User source) {
        return new UserDto(
                source.getUsername(),
                source.getPassword(),
                source.getEmail(),
                source.getRoles().stream()
                        .map(converter::convert)
                        .collect(Collectors.toSet()));
    }
}
