package com.example.authdemo.service;

import com.example.authdemo.dto.AuthorityDto;
import com.example.authdemo.dto.RoleDto;
import com.example.authdemo.dto.UserDto;
import com.example.authdemo.dto.UserResponseDto;
import com.example.authdemo.entity.Authority;
import com.example.authdemo.entity.Role;
import com.example.authdemo.entity.User;
import com.example.authdemo.model.AuthenticatedUser;
import com.example.authdemo.repository.AuthorityRepository;
import com.example.authdemo.repository.RoleRepository;
import com.example.authdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
    }

    @Cacheable("users")
    @Override
    public UserResponseDto getUserById(Long userId) {
        logger.info("User with ID: {} not found in cache. Fetching from database...", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException("User not found with ID: " + userId));

        Set<RoleDto> roleDtos = user.getRoles().stream()
                .map(r -> new RoleDto(r.getName(), r.getAuthorities().stream()
                        .map(a -> new AuthorityDto(a.getName()))
                        .collect(Collectors.toSet())))
                .collect(Collectors.toSet());

        return new UserResponseDto.Builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roleDtos)
                .build();
    }

    @Override
    public User register(UserDto userDto) {
        logger.info("Starting user registration for username: {}", userDto.username());

        User u = new User();
        u.setUsername(userDto.username());
        u.setPassword(passwordEncoder.encode(userDto.password()));
        u.setEmail(userDto.email());

        Set<Role> roles = userDto.roles().stream().map(roleDto -> {
           Role role = getOrCreateRole(roleDto);

           Set<Authority> authorities = getAuthoritiesFromRequest(roleDto);

           role.setAuthorities(authorities);

           return role;
        }).collect(Collectors.toSet());

        u.setRoles(roles);

        return userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<GrantedAuthority> authorities = u.getRoles().stream()
                .flatMap(role -> role.getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(role.getName()+ "_" + authority.getName())))
                .collect(Collectors.toList());

        authorities.addAll(u.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).toList());

        return new AuthenticatedUser(u, authorities);
    }

    private Role getOrCreateRole(RoleDto roleDto) {
        return roleRepository.findByName(roleDto.name()).orElseGet(() -> {
           Role r = new Role();
           r.setName(roleDto.name());
           return roleRepository.save(r);
        });
    }

    private Authority getOrCreateAuthority(AuthorityDto authorityDto) {
        return authorityRepository.findByName(authorityDto.name()).orElseGet(() -> {
            Authority a = new Authority();
            a.setName(authorityDto.name());
            return authorityRepository.save(a);
        });
    }

    private Set<Authority> getAuthoritiesFromRequest(RoleDto roleDto) {
        return roleDto.authorities().stream().map(this::getOrCreateAuthority).collect(Collectors.toSet());
    }
}
