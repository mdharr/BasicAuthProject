package com.example.authdemo.dto;

import java.io.Serializable;
import java.util.Set;

public record UserDto(String username, String password, String email, Set<RoleDto> roles) implements Serializable {
}

//public class UserDto {
//    private String username;
//    private String password;
//    private String email;
//    private Set<RoleDto> roles;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Set<RoleDto> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<RoleDto> roles) {
//        this.roles = roles;
//    }
//}