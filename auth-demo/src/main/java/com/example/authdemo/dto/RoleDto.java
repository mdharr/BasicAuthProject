package com.example.authdemo.dto;

import java.io.Serializable;
import java.util.Set;

public record RoleDto(String name, Set<AuthorityDto> authorities) implements Serializable {
}
